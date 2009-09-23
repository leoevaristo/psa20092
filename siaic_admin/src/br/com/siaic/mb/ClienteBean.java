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
	public String addCliente() throws SQLException {
		// TODO
		
		String r = "sucesso";

		ClienteDAO daoCliente = new ClienteDAO();

		PessoaDAO daoPessoa = new PessoaDAO();

		daoPessoa.adicionarPessoa(cliente);
		daoCliente.adicionarCliente(cliente);
		
		destroiSessao();

		return r;

	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String updateCliente() throws SQLException {
		// TODO
		
		String r = "";

		ClienteDAO dao = new ClienteDAO();

		dao.alterarCliente(cliente);

		return r;

	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Cliente> getTodosClientes() throws SQLException {

		ClienteDAO dao = new ClienteDAO();
		
		return dao.getTodosClientes();

	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String localizarClientePorId() throws SQLException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idPessoa = new Integer(req.getParameter("codigoPessoa")).intValue();
		
		ClienteDAO dao = new ClienteDAO();
		setCliente(dao.getClientePorId(idPessoa));		
		
				
		return "altera";

	}

	
	
	
	/**
	 * 
	 * @throws SQLException
	 */
	public void excluiCliente() throws SQLException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer idPessoa = new Integer(req.getParameter("codigoPessoa"))
				.intValue();

		ClienteDAO dao = new ClienteDAO();

		dao.removerCliente(idPessoa);

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
