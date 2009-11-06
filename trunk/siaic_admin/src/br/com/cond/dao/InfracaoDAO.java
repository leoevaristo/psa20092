package br.com.cond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.Infracao;
import br.com.siaic.dao.FabricaConexao;

/**
 * 
 * @author George Fernandes Maia
 * @author Fellipe Weldson
 *
 */


public class InfracaoDAO {
	
	private Connection conexao = null;

	public InfracaoDAO() {}
	
	
	
	public boolean adicionarInfracao(Infracao infracao) throws SQLException{
		boolean retorno = false;
		PreparedStatement ps = null;
		String sql = "INSERT INTO admcon_infracao(INF_DESCRICAO) VALUES (?)";
		
		try {
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			ps = conexao.prepareStatement(sql);
			ps.setString(1, infracao.getDescricaoInfracao());
			ps.executeUpdate();
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

	

	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Infracao> getTodasAsInfracoes() throws SQLException {
		// TODO
		String sql = 
			
			"SELECT * " + 
			"FROM " + 
				"admcon_infracao " ;
		
		conexao = FabricaConexao.getInstancia().conectar();
			
		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Infracao> listaTodasAsInfracoes = new ArrayList<Infracao>();

		while (rs.next()) {

		Infracao infracao = new Infracao();	
			

		//	regra.setCodigoRegra(rs.getString());
		//	regra.setRegra(rs.getString());
			
		infracao.setCodigoInfracao(rs.getInt(1));
		infracao.setDescricaoInfracao(rs.getString(2));
		
		
			listaTodasAsInfracoes.add(infracao);

		}
		conexao.close();

		return listaTodasAsInfracoes;

	}
	
	
	
	public Infracao getInfracaoId(int infracaoCodigo) throws SQLException
	{
		
		String sql = "SELECT * " + 
		"FROM " + 
		"admcon_infracao " +
	"WHERE " +
		"INF_CODIGO = ?";

		conexao = FabricaConexao.getInstancia().conectar();	
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, infracaoCodigo);
		
		ResultSet rs = ps.executeQuery();
		
		Infracao infracao = new Infracao();	
		
		rs.first();
		
		
		infracao.setCodigoInfracao(rs.getInt(1));
		infracao.setDescricaoInfracao(rs.getString(2));
		
		ps.close();
		rs.close();
		
		
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
		String sql =   "UPDATE admcon_infracao SET INF_DESCRICAO = ?," +  "WHERE INF_CODIGO = ?;";
		
		
		try {
			
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, infracao.getCodigoInfracao());
			ps.setString(2, infracao.getDescricaoInfracao());
			
			ps.executeUpdate();
			ps.close();
			conexao.close();
			retorno = true;
			
		}

		catch (Exception e) {

			throw new SQLException("Nï¿½o foi possï¿½vel realizar a atualizaï¿½ï¿½o" + e);
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

			throw new SQLException("Nï¿½o foi possï¿½vel realizar a remoção" + e);
		}
		return retorno;  
	}
	
}//class
