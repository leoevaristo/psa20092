package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.businesslogic.endereco.Endereco;
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
			conexao.close();
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
			conexao.close();
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
			conexao.close();
		}//finally

		return retorno;
	}//public boolean adicionarBairro(Estado estado)
		
	/**
	 * Adiciona um endereço na Base de Dados. Atenção: o código do endereço será ignorado nesta implementação. Este código será
	 * corrigido mais tarde. 
	 * @param endereco
	 * @return
	 * @throws SQLException
	 */
	public boolean adicionarEndereco(Endereco endereco) throws SQLException{
		boolean retorno = false;
		PreparedStatement ps = null;
		String sql = "INSERT INTO ENDERECO(END_CODIGO, END_LOGRADOURO, END_NOME, END_CEP, END_BAIRRO) VALUES (null, ?, ?,?,?)";
		
		try {
			conexao = FabricaConexao.getInstancia().conectar();
			ps = conexao.prepareStatement(sql);
			ps.setString(1, endereco.getEnderecoLogradouro() );
			ps.setString(2, endereco.getEnderecoNome() );
			ps.setString(3, endereco.getEnderecoCep() );
			ps.setInt(4, endereco.getEnderecoBairro().getBairroCodigo() );
			ps.execute();
			retorno = true;
		}//try
		catch (Exception e) {
			throw new SQLException("Erro ao inserir dados no banco." + e);
		}//catch()
		finally{
			ps.close();
			conexao.close();
		}//finally

		return retorno;
	}//public boolean adicionarBairro(Estado estado)
	
	/**
	 * Recuperará um Estado enviando uma Sigla como argumento.
	 * @param estadoSigla
	 * @return
	 * @throws SQLException
	 */
	public Estado getEstadoPorSigla(String estadoSigla) throws SQLException
	{
		
		String sql = 		"SELECT * " + 
							"FROM " + 
								"ESTADO " +
							"WHERE " +
								"EST_SIGLA = ?";
		
		conexao = FabricaConexao.getInstancia().conectar();
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, estadoSigla);
		
		ResultSet rs = ps.executeQuery();
		
		Estado estado = new Estado();
		
		rs.next();
		estado.setEstadoSigla(rs.getString(1));
		estado.setEstadoNome(rs.getString(2));

		ps.close();
		rs.close();
		conexao.close();
		
		return estado;
	}
	
	/**
	 * Retorna um objeto Cidade enviando o código definido na base de dados
	 * 
	 * @param cidadeCodigo
	 * @return
	 * @throws SQLException
	 */
	public Cidade getCidadePorCodigo(int cidadeCodigo) throws SQLException
	{
		
		String sql = 		"SELECT * " + 
							"FROM " + 
								"CIDADE " +
							"WHERE " +
								"CID_CODIGO = ?";

		conexao = FabricaConexao.getInstancia().conectar();
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, cidadeCodigo);
		ResultSet rs = ps.executeQuery();
		Cidade cidade = new Cidade();
		rs.next();
		cidade.setCidadeCodigo(rs.getInt(1));
		cidade.setCidadeNome(rs.getString(2));
		cidade.setCidadeEstado(getEstadoPorSigla(rs.getString(3)));

		ps.close();
		rs.close();
		conexao.close();
		return cidade;
	}

	
	/**
	 * Recuperará um registro da Tabela Bairro enviando um código como argumento.
	 * @param bairroCodigo
	 * @return
	 * @throws SQLException
	 */
	public Bairro getBairroPorCodigo(int bairroCodigo) throws SQLException
	{
		
		String sql = 		"SELECT * " + 
							"FROM " + 
								"BAIRRO " +
							"WHERE " +
								"BAR_CODIGO = ?";

		conexao = FabricaConexao.getInstancia().conectar();
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, bairroCodigo);
		ResultSet rs = ps.executeQuery();
		Bairro bairro = new Bairro();
		rs.next();
		
		bairro.setBairroCodigo(rs.getInt(1));
		bairro.setBairroNome(rs.getString(2));
		bairro.setBairroCidade( getCidadePorCodigo( rs.getInt(3)) );
		
		ps.close();
		rs.close();
		conexao.close();
		
		return bairro;
	}
	
	/**
	 * Recuperará um endereço pelo Código enviado como argumento.
	 * @param enderecoCodigo
	 * @return
	 * @throws SQLException
	 */
	public Endereco getEnderecoPorCodigo(int enderecoCodigo) throws SQLException
	{
		
		String sql = 		"SELECT * " + 
							"FROM " + 
								"ENDERECO " +
							"WHERE " +
								"END_CODIGO = ?";

		conexao = FabricaConexao.getInstancia().conectar();
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, enderecoCodigo);
		ResultSet rs = ps.executeQuery();
		Endereco endereco = new Endereco();
		rs.next();
		
		endereco.setEnderecoCodigo( rs.getInt(1));
		endereco.setEnderecoLogradouro( rs.getString(2));
		endereco.setEnderecoNome(rs.getString(3));
		endereco.setEnderecoCep( rs.getString(4));
		endereco.setEnderecoBairro( getBairroPorCodigo(rs.getInt(5)) );
		
		ps.close();
		rs.close();
		conexao.close();
		
		return endereco;
	}
	
	/**
	 * Recuperará um endereço pelo Cep enviado como argumento.
	 * @param enderecoCep
	 * @return
	 * @throws SQLException
	 */
	public Endereco getEnderecoPorCep(String enderecoCep) throws SQLException
	{
		
		String sql = 		"SELECT * " + 
							"FROM " + 
								"ENDERECO " +
							"WHERE " +
								"END_CEP = ?";

		conexao = FabricaConexao.getInstancia().conectar();
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, enderecoCep);
		ResultSet rs = ps.executeQuery();
		Endereco endereco = new Endereco();
		rs.next();
		
		endereco.setEnderecoCodigo( rs.getInt(1));
		endereco.setEnderecoLogradouro( rs.getString(2));
		endereco.setEnderecoNome(rs.getString(3));
		endereco.setEnderecoCep( rs.getString(4));
		endereco.setEnderecoBairro( getBairroPorCodigo(rs.getInt(5)) );
		
		ps.close();
		rs.close();
		conexao.close();
		
		return endereco;
	}
	
	
	/**
	 * Este método irá atualizar um registro na tabela Estado enviando um objeto do tipo estado como argumento.
	 * Atenção: será considerado o Estado a ser atualizado pelo código contido dentro do objeto enviado como argumento.
	 * @param estado
	 * @return
	 * @throws SQLException
	 */
	public boolean alterarEstado(Estado estado) throws SQLException {

		boolean retorno = false; 
		String sql =    "UPDATE " + 
							"ESTADO " + 
						"SET " +
							"EST_SIGLA = ?, " + 
							  "EST_NOME = ? " +
						"WHERE " +
							"EST_SIGLA = ? ";
		try {
			
			conexao = FabricaConexao.getInstancia().conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, estado.getEstadoSigla());
			ps.setString(2, estado.getEstadoNome());
			ps.setString(3, estado.getEstadoSigla());
			ps.executeUpdate();
			ps.close();
			conexao.close();
			retorno = true;
			
		}

		catch (Exception e) {

			throw new SQLException("Não foi possível realizar a atualização" + e);
		}
		return retorno;  
	}
	
	/**
 	 * Este método irá atualizar um registro na tabela Cidade enviando um objeto do tipo Cidade como argumento.
	 * Atenção: será considerada a Cidade a ser atualizada pelo código contido dentro do objeto enviado como argumento.

	 * @param cidade
	 * @return
	 * @throws SQLException
	 */
	public boolean alterarCidade(Cidade cidade) throws SQLException {

		boolean retorno = false; 
		String sql =    "UPDATE " + 
							"CIDADE " + 
						"SET " +
							"CID_CODIGO = ?, " + 
							"CID_NOME = ?, " +
							"CID_ESTADO = ? " +
						"WHERE " +
							"CID_CODIGO = ? ";
		try {
			
			conexao = FabricaConexao.getInstancia().conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, cidade.getCidadeCodigo());
			ps.setString(2, cidade.getCidadeNome());
			ps.setString(3, cidade.getCidadeEstado().getEstadoSigla());
			ps.setInt(4, cidade.getCidadeCodigo());
			ps.executeUpdate();

			ps.close();
			conexao.close();
			retorno = true;
			
		}

		catch (Exception e) {

			throw new SQLException("Não foi possível realizar a atualização" + e);
		}
		return retorno;  
	}

	/**
 	 * Este método irá atualizar um registro na tabela Bairro enviando um objeto do tipo Bairro como argumento.
	 * Atenção: será considerado o Bairro a ser atualizado pelo código contido dentro do objeto enviado como argumento.
	 * 
	 * @param bairro
	 * @return
	 * @throws SQLException
	 */
	public boolean alterarBairro(Bairro bairro) throws SQLException {

		boolean retorno = false; 
		String sql =    "UPDATE " + 
							"BAIRRO " + 
						"SET " +
							"BAR_CODIGO = ?, " + 
							"BAR_NOME = ?, " +
							"BAR_CIDADE = ? " +
						"WHERE " +
							"BAR_CODIGO = ? ";
		try {
			
			conexao = FabricaConexao.getInstancia().conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, bairro.getBairroCodigo());
			ps.setString(2, bairro.getBairroNome());
			ps.setInt(3, bairro.getBairroCidade().getCidadeCodigo());
			ps.setInt(4, bairro.getBairroCodigo());
			ps.executeUpdate();

			ps.close();
			conexao.close();
			retorno = true;
			
		}

		catch (Exception e) {

			throw new SQLException("Não foi possível realizar a atualização" + e);
		}
		return retorno;  
	}
	
	/**
 	 * Este método irá atualizar um registro na tabela Endereco enviando um objeto do tipo Endereco como argumento.
	 * Atenção: será considerado o Endereco a ser atualizado pelo código contido dentro do objeto enviado como argumento.
	 * @param endereco
	 * @return
	 * @throws SQLException
	 */
	public boolean alterarEndereco(Endereco endereco) throws SQLException {

		boolean retorno = false; 
		String sql =    "UPDATE " + 
							"ENDERECO " + 
						"SET " +
							"END_CODIGO = ?, " + 
							"END_LOGRADOURO = ?, " +
							"END_NOME = ?, " +
							"END_CEP = ?, " +
							"END_BAIRRO = ? " +
						"WHERE " +
							"END_CODIGO = ? ";
		try {
			
			conexao = FabricaConexao.getInstancia().conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, endereco.getEnderecoCodigo());
			ps.setString(2, endereco.getEnderecoLogradouro());
			ps.setString(3, endereco.getEnderecoNome());
			ps.setString(4, endereco.getEnderecoCep());
			ps.setInt(5, endereco.getEnderecoBairro().getBairroCodigo());
			ps.setInt(6, endereco.getEnderecoCodigo());
			
			ps.executeUpdate();

			ps.close();
			conexao.close();
			retorno = true;
			
		}

		catch (Exception e) {

			throw new SQLException("Não foi possível realizar a atualização" + e);
		}
		return retorno;  
	}
		
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		try {
			
			Endereco c = EnderecoDAO.getInstancia().getEnderecoPorCodigo(3);
			
			c.setEnderecoLogradouro("Rua");
			EnderecoDAO.getInstancia().alterarEndereco(c);
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}//class
