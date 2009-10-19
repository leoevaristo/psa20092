package br.com.siaic.mb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.dao.FabricaConexao;

public class AcessoBean {
	
	private Usuario usuario;
	
	private Usuario login_acesso;
	
	private Usuario senha_acesso;
	
	private Connection conexao = null;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	

	public Usuario getLogin_acesso() {
		return login_acesso;
	}

	public void setLogin_acesso(Usuario login_acesso) {
		this.login_acesso = login_acesso;
	}

	public Usuario getSenha_acesso() {
		return senha_acesso;
	}

	public void setSenha_acesso(Usuario senha_acesso) {
		this.senha_acesso = senha_acesso;
	}

	public AcessoBean() {

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

	
	public List<Usuario> getAcesso(String login, String senha) throws SQLException {

		String sql = "SELECT PEU_LOGIN, PEU_SENHA "
			+ "FROM PESSOA_USUARIOS "
			+ "WHERE PEU_LOGIN LIKE ? and PEU_SENHA LIKE ? ";


		try{
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, "%" + login + "%");
			ps.setString(2, "%" + senha + "%");

			ResultSet rs = ps.executeQuery();
			
			List<Usuario> listaUsuarios = new ArrayList<Usuario>();

			
			while (rs.next()) {
			
				Usuario usuario = new Usuario();
				usuario.setLogin(rs.getString("PEU_LOGIN"));
				listaUsuarios.add(usuario);
			}

			ps.close();
			rs.close();

			return listaUsuarios;

			}finally{
				conexao.close();
		}
		
	}
	
  public String ValidaLogin()
  {
     return null;
	  
	  
  }

	
	
	
}
