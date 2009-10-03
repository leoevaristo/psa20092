package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Usuario;

/**
 * 
 * @author Alain Rosemberg 
 */
public class UsuarioDAO {

	/**
	 * 
	 */
	private Connection conexao = null;

	



	/**
	 * 
	 */
	public UsuarioDAO() {

		try
		{
			FabricaConexao.getInstancia();
			this.conexao = FabricaConexao.conectar();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public void adicionarUsuario(Usuario usuario) throws SQLException {

		String sql = "INSERT INTO PESSOA_USUARIOS( PEU_CODIGO,PEU_CRECI,PEU_LOGIN,PEU_SENHA)"
				+ "VALUES (?, ?, ?,?)";

		try {

			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setInt(1, usuario.getCodigoPessoa());
			ps.setString(2, usuario.getCRECI());
			ps.setString(3, usuario.getLogin());
			ps.setString(4, usuario.getSenha());

			ps.execute();

			ps.close();

		}

		catch (Exception e) {

			throw new SQLException("Erro ao inserir dados no banco.");

		}

	}

	public void removerUsuario(int idPessoa) throws SQLException {
	
		String sql = " DELETE PESSOA_USUARIOS, PESSOA FROM PESSOA_USUARIOS INNER JOIN PESSOA INNER JOIN ENDERECO "
			+ " WHERE PESSOA_USUARIOS.PEU_CODIGO = ? AND PESSOA_USUARIOS.PEU_CODIGO =PESSOA.PES_CODIGO "
			+ " AND PESSOA.PES_ENDERECO = ENDERECO.END_CODIGO; ";

	

	try {
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, idPessoa);
		if (ps.execute())
			System.out.println("Dados apagados!");
		ps.close();
		
	} finally {
		
		conexao.close();
	}

	}

	public void alterarUsuario(Usuario usuario) throws SQLException {

		String sql = "UPDATE PESSOA_USUARIOS SET PEU_CRECI = ?, PEU_LOGIN = ?, PEU_SENHA = ?"
				+ " WHERE PEU_CODIGO = ?";

		try {

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, usuario.getCRECI());
			ps.setString(2, usuario.getLogin());
			ps.setString(3, usuario.getSenha());
			ps.setInt(4, usuario.getCodigoPessoa());

			ps.executeUpdate();
			ps.close();

		}

		catch (Exception e) {

			throw new SQLException("N�o foi poss�vel alterar o banco de dados.");

		}
	}

	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Usuario> getTodosUsuarios() throws SQLException {
		// TODO
		String sql = "SELECT PEU_CODIGO, PEU_LOGIN, PEU_SENHA, PEU_CRECI, "
				+ " PES_CODIGO, PES_NOME, PES_EMAIL, PES_TELEFONE, PES_CELULAR"
				+ " FROM PESSOA_USUARIOS, PESSOA "
				+ "	WHERE PEU_CODIGO = PES_CODIGO ORDER BY PES_CODIGO ASC;";

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Usuario> listaTodosUsuarios = new ArrayList<Usuario>();

		while (rs.next()) {

			Usuario usuario = new Usuario();

			usuario.setCodigoPessoa(rs.getInt("PES_CODIGO"));
			usuario.setNome(rs.getString("PES_NOME"));
			usuario.setEmail(rs.getString("PES_EMAIL"));
			usuario.setTelefone(rs.getString("PES_TELEFONE"));
			usuario.setCelular(rs.getString("PES_CELULAR"));
			usuario.setLogin(rs.getString("PEU_LOGIN"));
			usuario.setSenha(rs.getString("PEU_SENHA"));
			usuario.setCRECI(rs.getString("PEU_CRECI"));
			

			listaTodosUsuarios.add(usuario);

		}
		conexao.close();

		return listaTodosUsuarios;

	}
	
	
	public List<Usuario> getUsuariosLogin(Usuario login) throws SQLException {
		// TODO
		String sql = "SELECT PEU_LOGIN "
				+ " FROM PESSOA_USUARIOS "
				+ "WHERE u.PEU_LOGIN LIKE ? ";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "%" + login + "%");
		
		ResultSet rs = ps.executeQuery();

		List<Usuario> listaUsuariosLogin = new ArrayList<Usuario>();

		while (rs.next()) {

			Usuario usuario = new Usuario();

			usuario.setLogin(rs.getString("PEU_LOGIN"));

			listaUsuariosLogin.add(login);

		}
		conexao.close();
		return listaUsuariosLogin;

       
//		return listaUsuariosLogin;
	}
	
	
	
	
	public List<Usuario> getUsuarioPeloNome(String nome) throws SQLException {

		String sql = "SELECT p.PES_ENDERECO, p.PES_NOME, p.PES_TELEFONE, p.PES_CELULAR, p.PES_EMAIL, "
			+ "c.PEU_CODIGO,c.PEU_LOGIN,c.PEU_SENHA,c.PEU_CRECI, "
			+ "e.END_LOGRADOURO,e.END_NOME,e.END_CEP,e.END_BAIRRO,e.END_CODIGO "
			+ "FROM PESSOA p, PESSOA_USUARIOS c, ENDERECO e "
			+ "WHERE p.PES_NOME LIKE ? AND p.PES_ENDERECO = e.END_CODIGO AND p.PES_CODIGO = c.PEU_CODIGO;";

		try{
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, "%" + nome + "%");

			ResultSet rs = ps.executeQuery();

			List<Usuario> listaUsuarios = new ArrayList<Usuario>();

			while (rs.next()) {
			
				Usuario usuario = new Usuario();
				usuario.setCodigoPessoa(rs.getInt("PEU_CODIGO"));
				usuario.setLogin(rs.getString("PEU_LOGIN"));
				usuario.setSenha(rs.getString("PEU_SENHA"));
				usuario.setCRECI(rs.getString("PEU_CRECI"));
				usuario.setNome(rs.getString("PES_NOME"));
				usuario.setEmail(rs.getString("PES_EMAIL"));
				usuario.setTelefone(rs.getString("PES_TELEFONE"));

				listaUsuarios.add(usuario);

			}

			ps.close();
			rs.close();
		
			return listaUsuarios;
		
			}finally{
				conexao.close();
		}
		
	}



	
	
	public List<Usuario> getAcessoUsuario(Usuario login) throws SQLException {

		String sql = "SELECT u.PEU_LOGIN, u.PEU_SENHA "
			+ "FROM PESSOA_USUARIOS u "
			+ "WHERE u.PEU_LOGIN LIKE ? ";

		try{
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1,"%"+login+"%");

			ResultSet rs = ps.executeQuery();

			List<Usuario> listaAcesso = new ArrayList<Usuario>();

			while (rs.next()) {
			
				Usuario usuario = new Usuario();
				usuario.setLogin(rs.getString("PEU_LOGIN"));
				usuario.setSenha(rs.getString("PEU_SENHA"));

				listaAcesso.add(login);

			}

			ps.close();
			rs.close();
	    
		
			return listaAcesso;
		
			}finally{
				conexao.close();
		}
		
	}
	

	
	
	
	/**
	 * 
	 * @param usuarioCodigo
	 * @return
	 * @throws SQLException
	 */
	public Usuario getUsuarioId(int usuarioCodigo) throws SQLException
	{
		
		String sql = "SELECT pu.PEU_CODIGO, pu.PEU_CRECI, pu.PEU_LOGIN, pu.PEU_SENHA , pe.PES_CODIGO, " 
			+"pe.PES_NOME, pe.PES_EMAIL, pe.PES_TELEFONE, pe.PES_CELULAR, pe.PES_ENDERECO, pe.PES_TIPO, pe.PES_SEXO "
			+"FROM PESSOA_USUARIOS pu , PESSOA pe " 
			+"WHERE pu.PEU_CODIGO = ?  AND pu.PEU_CODIGO = pe.PES_CODIGO ";
		
			
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, usuarioCodigo);
		
		ResultSet rs = ps.executeQuery();
		
		Usuario usuario = new Usuario();
		
		rs.first();
		usuario.setCodigoPessoa(rs.getInt("PEU_CODIGO"));
		usuario.setLogin(rs.getString("PEU_LOGIN"));
		usuario.setSenha(rs.getString("PEU_SENHA"));
		usuario.setCRECI(rs.getString("PEU_CRECI"));
		usuario.setNome(rs.getString("PES_NOME"));
		usuario.setEmail(rs.getString("PES_EMAIL"));
		usuario.setTelefone(rs.getString("PES_TELEFONE"));
		usuario.setCelular(rs.getString("PES_CELULAR"));
		usuario.setEnderecoCodigo(rs.getInt("PES_ENDERECO"));
		usuario.setTipoPessoa(rs.getString("PES_TIPO"));
		usuario.setSexo(rs.getString("PES_SEXO"));
			
		ps.close();
		rs.close();
		
		
		return usuario;
		
	}
	
	public List<Usuario> getTodosCorretores() throws SQLException {
		// TODO
		String sql = "SELECT PEU_CODIGO, PEU_LOGIN, PEU_SENHA, PEU_CRECI, "
				+ " PES_CODIGO, PES_NOME, PES_EMAIL, PES_TELEFONE, PES_CELULAR"
				+ " FROM PESSOA_USUARIOS, PESSOA "
				+ "	WHERE PEU_CODIGO = PES_CODIGO ORDER BY PES_CODIGO ASC;";

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Usuario> listaTodosUsuarios = new ArrayList<Usuario>();

		while (rs.next()) {
			
			if(!rs.getString("PEU_CRECI").isEmpty()){
				
				Usuario usuario = new Usuario();
				usuario.setCodigoPessoa(rs.getInt("PES_CODIGO"));
				usuario.setNome(rs.getString("PES_NOME"));
				usuario.setEmail(rs.getString("PES_EMAIL"));
				usuario.setTelefone(rs.getString("PES_TELEFONE"));
				usuario.setCelular(rs.getString("PES_CELULAR"));
				usuario.setLogin(rs.getString("PEU_LOGIN"));
				usuario.setSenha(rs.getString("PEU_SENHA"));
				usuario.setCRECI(rs.getString("PEU_CRECI"));			

				listaTodosUsuarios.add(usuario);
			}
		}
		conexao.close();

		return listaTodosUsuarios;

	}
	



}

