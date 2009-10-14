package br.com.siaic.mb;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.endereco.Endereco;


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
	
	public Endereco getEnderecoCliente(){
		//TODO
		
		return new Endereco();
	}

}
