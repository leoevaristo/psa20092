package br.com.cond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.cond.businesslogic.Infracao;
import br.com.siaic.dao.FabricaConexao;

/**
 * 
 * @author George Fernandes Maia
 *
 */


public class InfracaoDAO {
	
	private Connection conexao = null;

	public InfracaoDAO() {}
	
	
	
	public boolean adicionarInfracao(Infracao infracao) throws SQLException{
		boolean retorno = false;
		PreparedStatement ps = null;
		String sql = "INSERT INTO admcon_infracao( INF_CODIGO, INF_DESCRICAO) VALUES (null, ?)";
		
		try {
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			ps = conexao.prepareStatement(sql);
			ps.setString(2, infracao.getDescricaoInfracao());
			ps.execute();
			retorno = true;
		}//try
		catch (Exception e) {
			throw new SQLException("Erro ao inserir dados no banco. " + e);
		}//catch()
		finally{
			ps.close();
			conexao.close();
		}//finally

		return retorno;
	}//public boolean adicionarInfracao(Infracao infracao)

	

	
	
	public Infracao getInfracaoPorCodigo(int codigoInfracaoParametro) throws SQLException
	{
		String sql = 		"SELECT * " + 
							"FROM " + 
								"admcon_infracao " +
							"WHERE " +
								"INF_CODIGO = ?";
		
		FabricaConexao.getInstancia();
		conexao = FabricaConexao.conectar();
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, codigoInfracaoParametro);
		
		ResultSet rs = ps.executeQuery();
		
		Infracao infracao = new Infracao();
		
		rs.next();
		infracao.setCodigoInfracao(rs.getInt(1));
		infracao.setDescricaoInfracao(rs.getString(2));

		ps.close();
		rs.close();
		conexao.close();
		
		return infracao;
	}
	
	public ArrayList<Infracao> pesquisaInfracao(String infracaoPesquisaParametro) throws SQLException
	{
		String sql = "SELECT * FROM admcon_infracao WHERE inf_descricao LIKE '%"+ infracaoPesquisaParametro +"%'";
		
		FabricaConexao.getInstancia();
		conexao = FabricaConexao.conectar();
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Infracao> infracaoLista = new ArrayList<Infracao>();
		
		rs.next();
		
		do
		{
			infracaoLista.add(new Infracao(rs.getInt(1),rs.getString(2) ));
			
		}while(rs.next());
		
		ps.close();
		rs.close();
		conexao.close();
		
		return infracaoLista;
	}

	
	public boolean alterarInfracao(Infracao infracao) throws SQLException {

		boolean retorno = false; 
		String sql =    "UPDATE " + 
							"ADMCON_INFRACAO " + 
						"SET " +
							"INF_CODIGO = ?, " + 
							  "INF_DESCRICAO = ? " +
						"WHERE " +
							"INF_CODIGO = ? ";
		try {
			
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, infracao.getCodigoInfracao());
			ps.setString(2, infracao.getDescricaoInfracao());
			ps.setInt(3, infracao.getCodigoInfracao());
			ps.executeUpdate();
			ps.close();
			conexao.close();
			retorno = true;
			
		}

		catch (Exception e) {

			throw new SQLException("N�o foi poss�vel realizar a atualiza��o" + e);
		}
		return retorno;  
	}
	
	
	public boolean removerInfracao(int infracaoCodigoParametro) throws SQLException {

		boolean retorno = false; 
		String sql =  "DELETE FROM admcon_infracao WHERE INF_CODIGO = ? ";
		try {
			
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, infracaoCodigoParametro );
			ps.execute();
			ps.close();
			conexao.close();
			retorno = true;
			
		}

		catch (Exception e) {

			throw new SQLException("N�o foi poss�vel realizar a remo��o" + e);
		}
		return retorno;  
	}
	
}//class