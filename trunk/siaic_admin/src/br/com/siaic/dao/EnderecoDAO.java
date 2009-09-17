package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.businesslogic.endereco.Estado;

/**
 * O desenvolvedor poderá utilizar essa classe para poder manipular todas as entidades relacionadas a Endereço:
 * Estado, Cidade, Bairro, Endereco.
 * 
 * @author george
 *
 */

public class EnderecoDAO {
	
	private Connection conexao = null;
	private static EnderecoDAO instance = null;
	
	public static EnderecoDAO getInstancia() {
		if (instance == null) {
			instance = new EnderecoDAO();
		}

		return instance;
	}

	
	private EnderecoDAO(){}
	
	
	
	/**
	 * Adiciona um Estado
	 * 
	 * @param estadoSigla
	 * @param estadoNome
	 * @return
	 * @throws SQLException 
	 */
	public boolean adicionarEstado(Estado estado) throws SQLException{
		boolean retorno = false;
		PreparedStatement ps = null;
		String sql = "INSERT INTO ESTADO( EST_SIGLA, EST_NOME) VALUES (?, ?)";
		
		try {
			conexao = FabricaConexao.getInstancia().conectar();
			ps = conexao.prepareStatement(sql);
			ps.setString(1, estado.getEstadoSigla());
			ps.setString(2, estado.getEstadoNome());
			ps.execute();
			retorno = true;
		}//try
		catch (Exception e) {
			throw new SQLException("Erro ao inserir dados no banco. " + e);
		}//catch()
		finally{
			ps.close();
		}//finally

		return retorno;
	}//public boolean adicionarEstado(Estado estado)
	
	
	/**
	 * Adiciona uma Cidade a Base de Dados. Atenção, o código da cidade será ignorado nesta implementação. Este código será
	 * corrigido mais tarde. 
	 * @param cidade
	 * @return
	 * @throws SQLException
	 */
	public boolean adicionarCidade(Cidade cidade) throws SQLException{
		boolean retorno = false;
		PreparedStatement ps = null;
		String sql = "INSERT INTO CIDADE(CID_CODIGO, CID_NOME, CID_ESTADO) VALUES (null, ?, ?)";
		
		try {
			conexao = FabricaConexao.getInstancia().conectar();
			ps = conexao.prepareStatement(sql);
			ps.setString(1, cidade.getCidadeNome());
			ps.setString(2, cidade.getCidadeEstado().getEstadoSigla());
			ps.execute();
			retorno = true;
		}//try
		catch (Exception e) {
			throw new SQLException("Erro ao inserir dados no banco." + e);
		}//catch()
		finally{
			ps.close();
		}//finally

		return retorno;
	}//public boolean adicionarCidade(Estado estado)
		
	/**
	 * Adiciona um bairro na Base de Dados. Atenção, o código do bairro será ignorado nesta implementação. Este código será
	 * corrigido mais tarde. 
	 * @param bairro
	 * @return true se o cadastro for efetuado com sucesso.
	 * @throws SQLException
	 */
	public boolean adicionarBairro(Bairro bairro) throws SQLException{
		boolean retorno = false;
		PreparedStatement ps = null;
		String sql = "INSERT INTO BAIRRO(BAR_CODIGO, BAR_NOME, BAR_CIDADE) VALUES (null, ?, ?)";
		
		try {
			conexao = FabricaConexao.getInstancia().conectar();
			ps = conexao.prepareStatement(sql);
			ps.setString(1, bairro.getBairroNome() );
			ps.setInt(2, bairro.getBairroCidade().getCidadeCodigo());
			ps.execute();
			retorno = true;
		}//try
		catch (Exception e) {
			throw new SQLException("Erro ao inserir dados no banco." + e);
		}//catch()
		finally{
			ps.close();
		}//finally

		return retorno;
	}//public boolean adicionarBairro(Estado estado)
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		try {
			
			boolean r = EnderecoDAO.getInstancia().adicionarBairro(new Bairro(-1, "Batalhão", new Cidade( 1 , "Catolé do Rocha", new Estado("PB", "Paraíba"))));
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}//class
