package br.com.siaic.mb;

import java.sql.SQLException;

import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.dao.PessoaDAO;
import br.com.siaic.dao.UsuarioDAO;

public class UsuarioBean {

	/**
	 * @author Alain Rosemberg
	 */
	private Usuario usuario;

	/**
	 * 
	 */
	public UsuarioBean() {

		usuario = new Usuario();

	}

	/**
	 * 
	 * @return
	 */
	public Usuario getUsuario() {

		return usuario;

	}

	/**
	 * 
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;

	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String addUsuario() throws SQLException {
		// TODO
		String r = "sucesso";

		PessoaDAO daoPessoa = new PessoaDAO();
		UsuarioDAO daoUsuario = new UsuarioDAO();
		
		daoPessoa.adicionarPessoa(usuario);
		daoUsuario.adicionarUsuario(usuario);

		

		return r;

	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String updateUsuario() throws SQLException {
		// TODO
		String r = "";
		
		UsuarioDAO daoUsuario = new UsuarioDAO();

		daoUsuario.adicionarUsuario(usuario);

		return r;

	}

	/*
	 * /
	 * 
	 * @return
	 * 
	 * @throws SQLException
	 * 
	 * public List<Usuario> ExibirTodosUsuarios() throws SQLException {
	 * 
	 * return UsuarioDAO.getInstancia().getTodosUsuarios();
	 * 
	 * }
	 */

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Usuario localizarUsuarioPorId() throws SQLException {
			UsuarioDAO daoUsuario = new UsuarioDAO();
			
		return daoUsuario.getUsuarioId(
				getUsuario().getCodigoPessoa());

	}

}
