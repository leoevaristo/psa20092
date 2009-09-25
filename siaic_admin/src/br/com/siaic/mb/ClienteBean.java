package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.PessoaDAO;


/**
 * Classe que controla as informações relacionadas a Cliente.
 * 
 * 
 * @author carlos
 *
 */
public class ClienteBean {

	/**
	 * @author Carlos Junior
	 */
	private Cliente cliente;

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
	public ClienteBean() {
		
		cliente = new Cliente();

	}

	/**
	 * 
	 * @return
	 */
	public Cliente getCliente() {

		return cliente;

	}
	
	
	

	/**
	 * 
	 * @param cliente
	 */
	public void setCliente(Cliente cliente) {
		
		this.cliente = cliente;

	}
	
	
	

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String updateCliente() throws SQLException {
		// TODO
		
		String r = "";
		
		PessoaDAO daoPessoa = new PessoaDAO();
		ClienteDAO daoCliente = new ClienteDAO();
		
		daoPessoa.alterarPessoa(cliente);
		daoCliente.alterarCliente(cliente);

		return r;

	}





	
	
	

	
	
	
	
	/**
	 * 
	 * @return
	 */
	public String destroiSessao(){
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove("clienteBean");
		
		return "destruido";
	}
	
	
	

	
	
	
	
	public Endereco getEnderecoCliente(){
		//TODO
		
		return new Endereco();
	}

}
