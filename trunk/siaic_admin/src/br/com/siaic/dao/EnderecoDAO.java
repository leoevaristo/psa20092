package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Pessoa;
import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.businesslogic.endereco.Estado;

/**
 * O desenvolvedor poder� utilizar essa classe para poder manipular todas as entidades relacionadas a Endere�o:
 * Estado, Cidade, Bairro, Endereco.
 * 
 * @author george
 *
 */

public class EnderecoDAO {
	
	private Connection conexao = null;
	/*private static EnderecoDAO instance = null;
	
	public static EnderecoDAO getInstancia() {
		if (instance == null) {
			instance = new EnderecoDAO();
		}

		return instance;
	}

	*/
	public EnderecoDAO(){}
	
	
	
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
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
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
	 * Adiciona uma Cidade a Base de Dados. Aten��o, o c�digo da cidade ser� ignorado nesta implementa��o. Este c�digo ser�
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
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			ps = conexao.prepareStatement(sql);
			ps.setString(1, cidade.getCidadeNome());
			ps.setString(2, cidade.getCidadeEstado());
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
	 * Adiciona um bairro na Base de Dados. Aten��o, o c�digo do bairro ser� ignorado nesta implementa��o. Este c�digo ser�
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
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			ps = conexao.prepareStatement(sql);
			ps.setString(1, bairro.getBairroNome() );
			ps.setInt(2, bairro.getBairroCidade());
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
	 * Adiciona um endere�o na Base de Dados. Aten��o: o c�digo do endere�o ser� ignorado nesta implementa��o. Este c�digo ser�
	 * corrigido mais tarde. 
	 * @param endereco
	 * @return
	 * @throws SQLException
	 */
	public boolean adicionarEndereco(Endereco endereco) throws SQLException{
		boolean retorno = false;
		PreparedStatement ps = null;
		String sql = "INSERT INTO ENDERECO( END_LOGRADOURO, END_NOME, END_CEP, END_BAIRRO) VALUES ( ?, ?,?,?)";
		
		try {
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			ps = conexao.prepareStatement(sql);
			ps.setString(1, endereco.getEnderecoLogradouro() );
			ps.setString(2, endereco.getEnderecoNome() );
			ps.setString(3, endereco.getEnderecoCep() );
			ps.setInt(4, endereco.getEnderecoBairro().getBairroCodigo() );
			ps.execute();
			setIdEndereco(endereco);
			retorno = true;
		}//try
		catch (SQLException e) {
			e.printStackTrace();
		}//catch()
		finally{
			ps.close();
			conexao.close();
		}//finally
		
		return retorno;
	}//public boolean adicionarBairro(Estado estado)
	

	/**
	 * Recuperar� um Estado enviando uma Sigla como argumento.
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
		
		FabricaConexao.getInstancia();
		conexao = FabricaConexao.conectar();
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
	 * Retorna um objeto Cidade enviando o c�digo definido na base de dados
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

		FabricaConexao.getInstancia();
		conexao = FabricaConexao.conectar();
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, cidadeCodigo);
		ResultSet rs = ps.executeQuery();
		Cidade cidade = new Cidade();
		rs.next();
		cidade.setCidadeCodigo(rs.getInt(1));
		cidade.setCidadeNome(rs.getString(2));
		cidade.setCidadeEstado(rs.getString(3));

		ps.close();
		rs.close();
		conexao.close();
		return cidade;
	}

	
	/**
	 * Recuperar� um registro da Tabela Bairro enviando um c�digo como argumento.
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

		FabricaConexao.getInstancia();
		conexao = FabricaConexao.conectar();
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, bairroCodigo);
		ResultSet rs = ps.executeQuery();
		Bairro bairro = new Bairro();
		rs.next();
		
		bairro.setBairroCodigo(rs.getInt(1));
		bairro.setBairroNome(rs.getString(2));
		bairro.setBairroCidade(  rs.getInt(3) );
		
		ps.close();
		rs.close();
		conexao.close();
		
		return bairro;
	}
	
	/**
	 * Recuperar� um endere�o pelo C�digo enviado como argumento.
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

		FabricaConexao.getInstancia();
		conexao = FabricaConexao.conectar();
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, enderecoCodigo);
		ResultSet rs = ps.executeQuery();
		Endereco endereco = new Endereco();
		
		while(rs.next()){
		
			
			endereco.setEnderecoCodigo( rs.getInt(1));
			endereco.setEnderecoLogradouro( rs.getString(2));
			endereco.setEnderecoNome(rs.getString(3));
			endereco.setEnderecoCep( rs.getString(4));
			endereco.setEnderecoBairro( getBairroPorCodigo(rs.getInt(5)) );
		}
		ps.close();
		rs.close();
		conexao.close();
		
		return endereco;
	}
	
	/**
	 * Recuperar� um endere�o pelo Cep enviado como argumento.
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

		FabricaConexao.getInstancia();
		conexao = FabricaConexao.conectar();
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
	 * Este m�todo ir� atualizar um registro na tabela Estado enviando um objeto do tipo estado como argumento.
	 * Aten��o: ser� considerado o Estado a ser atualizado pelo c�digo contido dentro do objeto enviado como argumento.
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
			
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
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

			throw new SQLException("N�o foi poss�vel realizar a atualiza��o" + e);
		}
		return retorno;  
	}
	
	
	
	
	
	public boolean removerEstado(Estado estado) throws SQLException {

		boolean retorno = false; 
		String sql =  "DELETE FROM ESTADO WHERE EST_SIGLA = ? ";
		try {
			
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, estado.getEstadoSigla());
			ps.execute();
			ps.close();
			conexao.close();
			retorno = true;
			
		}

		catch (Exception e) {

			throw new SQLException("N�o foi poss�vel realizar a atualiza��o" + e);
		}
		return retorno;  
	}

	
	
	
	/**
 	 * Este m�todo ir� atualizar um registro na tabela Cidade enviando um objeto do tipo Cidade como argumento.
	 * Aten��o: ser� considerada a Cidade a ser atualizada pelo c�digo contido dentro do objeto enviado como argumento.

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
			
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, cidade.getCidadeCodigo());
			ps.setString(2, cidade.getCidadeNome());
			ps.setString(3, cidade.getCidadeEstado());
			ps.setInt(4, cidade.getCidadeCodigo());
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

	/**
 	 * Este m�todo ir� atualizar um registro na tabela Bairro enviando um objeto do tipo Bairro como argumento.
	 * Aten��o: ser� considerado o Bairro a ser atualizado pelo c�digo contido dentro do objeto enviado como argumento.
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
			
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, bairro.getBairroCodigo());
			ps.setString(2, bairro.getBairroNome());
			ps.setInt(3, bairro.getBairroCidade());
			ps.setInt(4, bairro.getBairroCodigo());
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
	
	/**
 	 * Este m�todo ir� atualizar um registro na tabela Endereco enviando um objeto do tipo Endereco como argumento.
	 * Aten��o: ser� considerado o Endereco a ser atualizado pelo c�digo contido dentro do objeto enviado como argumento.
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
			
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, endereco.getEnderecoCodigo());
			ps.setString(2, endereco.getEnderecoLogradouro());
			ps.setString(3, endereco.getEnderecoNome());
			ps.setString(4, endereco.getEnderecoCep());
			ps.setInt(5, endereco.getEnderecoBairro().getBairroCodigo());
			ps.setInt(6, endereco.getEnderecoCodigo());
			//ps.setInt(6, endereco.getEnderecoCodigo());
			
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
		
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		try {
			EnderecoDAO dao = new EnderecoDAO();
			Endereco endereco = dao.getEnderecoPorCep("58033904");
			System.out.println(endereco.getEnderecoLogradouro());
			System.out.println(endereco.getEnderecoNome());
			System.out.println(endereco.getEnderecoCep());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * Retorna o último código de endereço cadastrado.
	 * @param endereco
	 * @throws SQLException
	 */
	public void setIdEndereco(Endereco endereco) throws SQLException
	{
		
		String sql = "SELECT MAX(END_CODIGO) AS CODIGO FROM ENDERECO;";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			
			endereco.setEnderecoCodigo(rs.getInt("CODIGO"));
		
		}				
		
		ps.close();
		
		rs.close();
		
		
	}
	
	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Bairro> getTodosBairros() throws SQLException{
		//TODO
		String sql = "SELECT b.BAR_CODIGO, b.BAR_NOME, b.BAR_CIDADE, ci.CID_CODIGO" +
				"  FROM BAIRRO b, CIDADE ci WHERE b.BAR_CIDADE = ci.CID_CODIGO;";
		
		try{
		
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			List<Bairro> todosBairros = new ArrayList<Bairro>();
			
			while(rs.next()){
				
				Bairro bar = new Bairro();
				bar.setBairroCodigo(rs.getInt("BAR_CODIGO"));
				bar.setBairroNome(rs.getString("BAR_NOME"));
				todosBairros.add(bar);
				
			}
			ps.close();
			rs.close();
			
			return todosBairros;
			
		}finally{
			conexao.close();
		}
		
		
	}
	
	/**
	 * Retorna uma lista com todos os estados cadastrados no banco.
	 * @return 
	 * @throws SQLException
	 */
	public List<Estado> getTodosEstados() throws SQLException{
		
		String sql = "SELECT EST_SIGLA, EST_NOME FROM ESTADO;";
		
		try{
		
		FabricaConexao.getInstancia();
		conexao = FabricaConexao.conectar();
		PreparedStatement ps = conexao.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			List<Estado> todosEstados = new ArrayList<Estado>();
			while(rs.next()){
				
				Estado est = new Estado();
				est.setEstadoSigla(rs.getString("EST_SIGLA"));
				est.setEstadoNome(rs.getString("EST_NOME"));
				todosEstados.add(est);
				
			}
			
			ps.close();
			rs.close();
			
			return todosEstados;
			
		}finally{
			conexao.close();
		}
			
		
	}
	
	
	
	
	public List<Cidade> getTodasCidades() throws SQLException{
		
		String sql = "SELECT CID_CODIGO, CID_NOME, CID_ESTADO FROM CIDADE;";
		
		try{
			
		
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);	
		
			ResultSet rs = ps.executeQuery();
			List<Cidade> todasCidades = new ArrayList<Cidade>();
		
			while(rs.next()){
			
				Cidade cid = new Cidade();
				cid.setCidadeCodigo(rs.getInt("CID_CODIGO"));
				cid.setCidadeNome(rs.getString("CID_NOME"));
				cid.setCidadeEstado(rs.getString("CID_ESTADO"));	
				todasCidades.add(cid);
				
			}
			
			System.out.println(todasCidades.size());
			ps.close();
			rs.close();
		
			return todasCidades;
	
		}finally{
			conexao.close();
		
		}
	}
	
	public List<Cidade> getCidadePorEstado(String estadoSigla) throws SQLException
	{
		
		String sql = 		"SELECT * " + 
							"FROM " + 
								"CIDADE " +
							"WHERE " +
								"CID_ESTADO = ?";

		
		FabricaConexao.getInstancia();
		conexao = FabricaConexao.conectar();
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, estadoSigla);
		ResultSet rs = ps.executeQuery();
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		while(rs.next()){
		
			Cidade cid = new Cidade();
			cid.setCidadeCodigo(rs.getInt("CID_CODIGO"));
			cid.setCidadeNome(rs.getString("CID_NOME"));
			cid.setCidadeEstado(rs.getString("CID_ESTADO"));	
			cidades.add(cid);
			
		}
		ps.close();
		rs.close();
		conexao.close();
		return cidades;
	}

}//class
