package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

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
	private static UsuarioDAO instance = null;

	/**
	 * 
	 * @return
	 */
	public static UsuarioDAO getInstancia() {
		if (instance == null) {
			instance = new UsuarioDAO();
		}

		return instance;
	}

	/**
	 * 
	 */
	private UsuarioDAO() {

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

	public void removerUsuario(Usuario usuario) {

	}

	public void alterarUsuario(Usuario usuario) throws SQLException {

		String sql = "UPDATE PESSOA_USUARIO SET PEU_CRECI = ?, PEU_LOGIN = ?, PEU_SENHA = ?"
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
	 * @param usuarioodigo
	 * @return
	 * @throws SQLException
	 */
	public Usuario getUsuarioId(int usuarioCodido) throws SQLException
	{
		
		String sql = "SELECT u.PEU_CODIGO, u.PEU_CRECI, u.PEU_LOGIN, u.PEU_SENHA " 
					+"FROM PESSOA_USUARIO u, PESSOA p" 
					+"WHERE u.PEU_CODIGO = ?  AND u.PEU_CODIGO = p.PES_CODIGO ";
		
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, usuarioCodido);
		
		ResultSet rs = ps.executeQuery();
		
		Usuario usuario = new Usuario();
		
		usuario.setCodigoPessoa(rs.getInt("PEU_CODIGO"));
		usuario.setCRECI(rs.getString("PEU_CPF"));
		usuario.setLogin(rs.getString("PEU_RG"));
		usuario.setSenha(rs.getString("PEU_CNPJ"));
			
		ps.close();
		rs.close();
		
		
		return usuario;
		
	}
	



}
