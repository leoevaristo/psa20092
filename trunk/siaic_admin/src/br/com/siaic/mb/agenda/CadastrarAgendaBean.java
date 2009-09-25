package br.com.siaic.mb.agenda;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Agenda;
import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.UsuarioDAO;

public class CadastrarAgendaBean {

	private Agenda agenda;
	private Cliente cliente;
	private Usuario corretor;
	private Imovel imovel;
	
	public CadastrarAgendaBean(){
		this.agenda = new Agenda();
		this.cliente = new Cliente();
		this.corretor = new Usuario();
		this.imovel = new Imovel();
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getCorretor() {
		return corretor;
	}

	public void setCorretor(Usuario corretor) {
		this.corretor = corretor;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public List<Cliente> getTodosClientes() throws SQLException {
		ClienteDAO dao = new ClienteDAO();
		return dao.getTodosClientes();
	}
	
	public String ConsultarCliente(){
		return "ConsultarCliente";
	}
	
	public String SelecionarCliente() throws SQLException{
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idPessoa = new Integer(req.getParameter("codigoPessoa")).intValue();
		
		ClienteDAO dao = new ClienteDAO();
		setCliente(dao.getClientePorId(idPessoa));
		return "SelecionarCliente";
	}
	
	public List<Usuario> getTodosCorretores() throws SQLException {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.getTodosUsuarios();
	}
	
	public String ConsultarCorretor(){
		return "ConsultarCorretor";
	}
	
	public String SelecionarCorretor() throws SQLException{
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idPessoa = new Integer(req.getParameter("codigoPessoa")).intValue();
		
		UsuarioDAO dao = new UsuarioDAO();
		setCorretor((dao.getUsuarioId(idPessoa)));
		return "SelecionarCorretor";
	}

}