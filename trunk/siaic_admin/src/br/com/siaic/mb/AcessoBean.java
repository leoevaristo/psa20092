package br.com.siaic.mb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.dao.FabricaConexao;
import br.com.siaic.dao.UsuarioDAO;

public class AcessoBean {

	private Usuario usuario;

	private Connection conexao = null;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public AcessoBean() {

		usuario = new Usuario();
		try {
			FabricaConexao.getInstancia();
			this.conexao = FabricaConexao.conectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Usuario getAcesso(String login, String senha) throws SQLException {

		conexao = FabricaConexao.conectar();
		
		String sql = "SELECT PEU_CODIGO " + "FROM PESSOA_USUARIOS "
				+ "WHERE PEU_LOGIN LIKE ? AND PEU_SENHA LIKE ? ";

		try {

			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, login);
			ps.setString(2, senha);

			ResultSet rs = ps.executeQuery();

			Usuario u = null;
			
			if (rs.first()) {
				u = new UsuarioDAO().getUsuarioId(rs.getInt(1));
			}

			rs.close();
			ps.close();

			return u;

		} finally {
			conexao.close();
		}

	}

	public String ValidaLogin() {

		String s;

		try {
			if (usuario == null)
			{
				System.out.println("mizera");
			}else
			{	
			usuario = getAcesso(usuario.getLogin(), usuario.getSenha());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		s = (usuario != null) ? "sucesso" : "falha";
		
		if (usuario == null) {
			usuario = new Usuario();
		}

		return s;
	}

}
