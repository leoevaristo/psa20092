package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.PessoaDAO;

public class ConsultaClienteBean {
	
	private Cliente cliente;
	
	
	private Endereco endereco;
	
	
	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public ConsultaClienteBean(){
		cliente = new Cliente();
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		ClienteDAO dao = new ClienteDAO();
		setCliente(dao.getClientePorId(idPessoa));
		
		
		
		return "modifica";

	}
	
	

	
	
	public String getEnderecoCliente() throws SQLException{
		
		EnderecoDAO daoEndereco = new EnderecoDAO();
		setEndereco(daoEndereco.getEnderecoPorCodigo(getCliente().getEnderecoCodigo()));
		
		return   getEndereco().getEnderecoLogradouro() +" " + getEndereco().getEnderecoNome() + " CEP: " 
				+ getEndereco().getEnderecoCep();
		
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
	
	
	public String exibeDetalhesCliente() throws SQLException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idPessoa = new Integer(req.getParameter("codigoPessoa")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		ClienteDAO dao = new ClienteDAO();
		setCliente(dao.getClientePorId(idPessoa));	
		getEnderecoCliente();
		
		
		return "detalhes";
	}
	
	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String updateCliente() throws SQLException {
		// TODO
		
		String r = "sucesso";
		
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
	

}
