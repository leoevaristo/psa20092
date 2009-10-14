package br.com.cond.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.cond.businesslogic.ReceitaDespesa;
import br.com.siaic.dao.FabricaConexao;

public class ReceitasDespesasDAO {
	private Connection con = null;
	private PreparedStatement pstm = null;
	private ResultSet rst = null;
	
	private final String INSERIR_DESPESA_RECEITA = 
		"INSERT INTO ADMCON_DESPESA_RECEITA(DER_DRD_CODIGO, " +
		"                                   DER_VALOR, " +
		"                                   DER_TIPO, " +
		"                                   DER_DATA, " +
		"                                   DER_CON_CODIGO) " +
		"VALUES(?,?,?,?,?) ";
		
	public ReceitasDespesasDAO() {
		
	}
	
	public boolean inserirReceitaDespesa(ReceitaDespesa rd) throws Exception {
		boolean inseriu = false;
		int resultado = 0;
		
		try {
			
			// Abre uma conexão com o banco de dados
			FabricaConexao.getInstancia();
			this.con = FabricaConexao.conectar();
			
			pstm = this.con.prepareStatement(this.INSERIR_DESPESA_RECEITA);
			pstm.setInt(1, rd.getTipoRD().getCodigo());
			pstm.setDouble(2, rd.getValor());
			pstm.setString(3, rd.getTipo());
			pstm.setString(4, rd.getData());
			pstm.setInt(5, rd.getCondominio().getCodigo());
			
			resultado = pstm.executeUpdate();
			if(resultado == 0) {
				System.out.println("Registro não inserido. ");
				inseriu = false;
			} else {
				System.out.println("Registro inserido com sucesso. ");
				inseriu = true;
			}
			
		} catch ( SQLException sql ) {
			sql.printStackTrace();
			System.out.println("Erro ao tentar inserir receitas e despesas. " +
					sql.getMessage());
		}
		
		finally {
			
			try {
				
				// Fecha a instrução e a conexão com o banco de dados
				this.pstm.close();
				this.con.close();
				
			} catch ( Exception e ) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		return inseriu;
	}
	
	public static void main(String[] args) throws Exception {
		ReceitasDespesasDAO rdd = new ReceitasDespesasDAO();
		ReceitaDespesa rd = new ReceitaDespesa();
		
		rd.setTipoRD(new ReceitasDespesasTiposDAO().getReceitasDespesasTiposPorCodigo(1));
		rd.setCondominio(new CondominoDAO().getCondominio(1));
		rd.setValor(100);
		rd.setTipo("R");
		rd.setData("2009-10-14");
		
		rdd.inserirReceitaDespesa(rd);
	}
}
