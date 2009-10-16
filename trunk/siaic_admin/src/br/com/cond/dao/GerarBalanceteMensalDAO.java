package br.com.cond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cond.businesslogic.BalanceteMensal;
import br.com.cond.businesslogic.ReceitaDespesa;
import br.com.cond.businesslogic.ReceitaDespesaTipos;
import br.com.siaic.dao.FabricaConexao;


public class GerarBalanceteMensalDAO {
	
private Connection conexao = null;
	
	public GerarBalanceteMensalDAO() {

		try {

			FabricaConexao.getInstancia();
			this.conexao = FabricaConexao.conectar();

		}

		catch (Exception e) {

			e.printStackTrace();

		}
	}

	//metodo que trará como retorno a descrição detalhada de todas as receitas e despesas do mes desejado
	public List<ReceitaDespesa> getReceitasDespesas(BalanceteMensal bal) throws SQLException {
			
		String sql = "SELECT DRC_CODIGO, DRC_DESCRICAO, DER_CODIGO,   "
			+ "DER_VALOR, DER_TIPO, DER_DATA "
			+ "FROM ADMCON_DESPESA_RECEITA JOIN ADMCON_DESPESA_RECEITA_DOMINIO"
			+ "WHERE ADMCON_DESPESA_RECEITA.DRC_CODIGO = ADMCON_DESPESA_RECEITA_DOMINIO.DER_DRD_CODIGO"
			+ " AND MONTH(DER_DATA) = ? AND YEAR(DER_DATA)= ? "
			+ "ORDER BY DER_TIPO, DER_DATA";

		try{
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, bal.getMes());
			ps.setString(2, bal.getAno());
			
			ResultSet rs = ps.executeQuery();

			List<ReceitaDespesa> listaReceitas = new ArrayList<ReceitaDespesa>();

			while (rs.next()) {
			
				ReceitaDespesaTipos rcdt = new ReceitaDespesaTipos();
				ReceitaDespesa recDesp = new ReceitaDespesa();
								
				rcdt.setCodigo(rs.getInt(1));
				rcdt.setDescricao(rs.getString(2));
				
				recDesp.setCodigo(rs.getInt(3));
				recDesp.setValor(rs.getDouble(4));
				recDesp.setTipoRD(rcdt);
				recDesp.setData(rs.getString(5));
				recDesp.setCondominio(null);	//nao é relevante saber o responsavel no balancete			
				
				listaReceitas.add(recDesp);
			}

			ps.close();
			rs.close();
		
			return listaReceitas;
		
		}finally{
			conexao.close();
		}
	}
	
	//metodo que retornara o total mensal em valores, das despesas e receitas do condominio
	public List<ReceitaDespesa> getTotalReceitasDespesas(BalanceteMensal bal) throws SQLException {
				
		String sql = "SELECT DER_TIPO, SUM(DER_VALOR) "
				+ "FROM ADMCON_DESPESA_RECEITA "
				+ "GROUP BY DER_TIPO";

		try{
					
			PreparedStatement ps = conexao.prepareStatement(sql);
								
			ResultSet rs = ps.executeQuery();

			List<ReceitaDespesa> listaTotal = new ArrayList<ReceitaDespesa>();

			while (rs.next()) {
				
				ReceitaDespesaTipos rcd_tp = new ReceitaDespesaTipos();
				ReceitaDespesa total = new ReceitaDespesa();				
				
				//setCodigo de total, recebe zero pois essa ocorrencia nao existe no banco
				total.setCodigo(0);
				total.setTipo(rs.getString(1));
				//setCodigo de rcd_tp tambem recebe zero pois a descrição abaixo nao existe no banco
				rcd_tp.setCodigo(0);
				// aqui de acordo com seu tipo(natureza) o objeto rcd_tp 
				// do tipo receitaDespesaTipos, recebe uma descrição "fantasia"
				//apenas como demonstrativo no relatorio
				
				if ((rs.getString(1)== "R") || (rs.getString(1)== "r")){
					rcd_tp.setDescricao("Receita Total");
				}else{
					rcd_tp.setDescricao("Despesa Total");
				}
				
				total.setTipoRD(rcd_tp);
				total.setValor(rs.getDouble(2));
				//nao é relevante saber a qual morador a despesa ou receita total esta atrelada
				total.setCondominio(null);
				//so haverao dois resultados nesta query, que retornara numa lista de obj receitaDespesa
				listaTotal.add(total);
						
			}

			ps.close();
			rs.close();
				
			return listaTotal;
				
		}finally{
				conexao.close();
		}

	}

}
