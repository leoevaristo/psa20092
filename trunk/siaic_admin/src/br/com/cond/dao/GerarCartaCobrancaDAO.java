package br.com.cond.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.CartaCobranca;
import br.com.cond.businesslogic.Condomino;
import br.com.cond.businesslogic.ReceitaDespesa;
import br.com.cond.businesslogic.ReceitaDespesaTipos;
import br.com.cond.businesslogic.TaxasCondominio;
import br.com.siaic.dao.FabricaConexao;

public class GerarCartaCobrancaDAO {
private Connection conexao = null;
	
	public GerarCartaCobrancaDAO() {

		try {

			FabricaConexao.getInstancia();
			this.conexao = FabricaConexao.conectar();

		}

		catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	public List<CartaCobranca> getDevedores () throws SQLException {
		String sql = "SELECT APA_CODIGO, APA_ANDAR, APA_BLOCO, APA_NUMERO "
			+ "COF_MES, COF_ANO, COF_VALOR, COF_DATA_VENCIMENTO, to_days(NOW()- COF_DATA_VENCIMENTO)[DIAS EM ATRASO]"
			+ "FROM ADMCON_APARTAMENTO JOIN ADMCON_TAXA_CONDOMINIO_FATOS "
			+ "WHERE APA_CODIGO = COF_APA_CODIGO AND COF_PAGO = \"N\" "
			+ "HAVING toDays((NOW() - DATA_VENCIMENTO)) > 15"
			+ "ORDER BY APA_ANDAR, APA_BLOCO, APA_NUMERO";
		
		try{			
			PreparedStatement ps = conexao.prepareStatement(sql);
						
			ResultSet rs = ps.executeQuery();

			List<CartaCobranca> mostraAptoDevedores = new ArrayList<CartaCobranca>();

			while (rs.next()) {
			
				CartaCobranca ctCobranca = new CartaCobranca();
				Apartamento aptm = new Apartamento();
				TaxasCondominio txCond = new TaxasCondominio();
				
												
				aptm.setCodigoApartamento(rs.getInt(1));
				aptm.setAndar(rs.getInt(2));
				aptm.setBloco(rs.getString(3));
				aptm.setNumero(rs.getInt(4));
				
				txCond.setApartamento(aptm);
				txCond.setMes(rs.getString(5));
				txCond.setAno(rs.getString(6));
				txCond.setValor(rs.getDouble(7));
				txCond.setDataVencimento(rs.getDate(8));
				txCond.setDataPagamento(null);
				txCond.setEstaPago('N');
				
				ctCobranca.setApt(aptm);
				ctCobranca.setTxCond(txCond);
				ctCobranca.setDias(rs.getInt(9));				
				
				mostraAptoDevedores.add(ctCobranca);
			}

			ps.close();
			rs.close();
		
			return mostraAptoDevedores;
		
		}finally{
			conexao.close();
		}
		
	}

}
