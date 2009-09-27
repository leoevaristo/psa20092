package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.PessoaDAO;
import br.com.siaic.dao.UsuarioDAO;


/**
 *
 * 
 * 
 * @author Alain Rosemberg
 *
 */
public class UsuarioBean {

	private Usuario usuario;

	/**
	 * 
	 */
	private Endereco endereco;

	/**
	 * 
	 * @return
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * 
	 * @param endereco
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

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
	public String updateUsuario() throws SQLException {
		// TODO
		
		String r = "";
		
		PessoaDAO daoPessoa = new PessoaDAO();
		UsuarioDAO daoUsuario = new UsuarioDAO();
		
		daoPessoa.alterarPessoa(usuario);
		daoUsuario.alterarUsuario(usuario);

		return r;

	}





	
	
	

	
	
	
	
	/**
	 * 
	 * @return
	 */
	public String destroiSessao(){
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove("usuarioBean");
		
		return "destruido";
	}
	
	
	

	
	
	
	
	public Endereco getEnderecoCliente(){
		//TODO
		
		return new Endereco();
	}

}
