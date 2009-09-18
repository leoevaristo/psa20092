package br.com.siaic.mb;

import java.sql.SQLException;

import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.dao.UsuarioDAO;
import br.com.siaic.dao.PessoaDAO;

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
	 * @param cliente
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
		String r = "";

		PessoaDAO.getInstancia().adicionarPessoa(usuario);

		UsuarioDAO.getInstancia().adicionarUsuario(usuario);

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

		UsuarioDAO.getInstancia().adicionarUsuario(usuario);

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

		return UsuarioDAO.getInstancia().getUsuarioId(
				getUsuario().getCodigoPessoa());

	}

}
