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
	
	private Usuario login;
	
	private Usuario Senha;
	
	private Connection conexao = null;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getLogin() {
		return login;
	}

	public void setLogin(Usuario login) {
		this.login = login;
	}

	public Usuario getSenha() {
		return Senha;
	}

	public void setSenha(Usuario senha) {
		Senha = senha;
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

	
	public List<Usuario> getAcesso(String login) throws SQLException {

		String sql = "SELECT PEU_LOGIN "
			+ "FROM PESSOA_USUARIOS "
			+ "WHERE PEU_LOGIN LIKE ? ";


		try{
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, "%" + login + "%");

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
