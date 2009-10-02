package br.com.siaic.mb.agenda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Agenda;
import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.dao.AgendaDAO;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.ImovelDAO;
import br.com.siaic.dao.UsuarioDAO;

public class CadastrarAgendaBean {
	

	private Agenda agenda;
	
	private Cliente cliente;
	
	private static List<SelectItem> corretores = new ArrayList<SelectItem>();
	
	private static List<SelectItem> listaClientes = new ArrayList<SelectItem>();
	
	private static List<SelectItem> listaImoveis = new ArrayList<SelectItem>();
	
	
	



	public CadastrarAgendaBean(){
		
		agenda = new Agenda();
		setCliente(new Cliente());

		
		if(corretores.isEmpty()){
			try {
				setCorretores();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(listaImoveis.isEmpty()){
			try {
				setListaImoveis();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	
		
	
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
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
		agenda.setCodCliente(dao.getClientePorId(idPessoa).getCodigoPessoa());
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
		agenda.setCodCorretor(dao.getUsuarioId(idPessoa).getCodigoPessoa());
		return "SelecionarCorretor";
	}
	
	public List<Imovel> getTodosImoveis() throws SQLException {
		ImovelDAO dao = new ImovelDAO();
		return dao.getImoveis();
	}
	
	public String ConsultarImovel(){
		return "ConsultarImovel";
	}
	
	public String SelecionarImovel() throws SQLException{
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idImovel = new Integer(req.getParameter("codigoImovel")).intValue();
		
		ImovelDAO dao = new ImovelDAO();
		agenda.setCodImovel(dao.getImovel(idImovel).getCodigo());
		return "SelecionarImovel";
	}
	
	public List<SelectItem> getCorretores() {
		return corretores;
	}

	public void setCorretores() throws SQLException {
		
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usu = dao.getTodosCorretores();
		
		for(Usuario usuario : usu)			
			corretores.add(new SelectItem(usuario.getCodigoPessoa(),usuario.getNome()));
					
	}

	public void setListaClientes(List<Cliente> listagemClientes) {
		if(!listaClientes.isEmpty()){			
			listaClientes.clear();
		}
				
		for(Cliente cliente : listagemClientes){
			
			listaClientes.add(new SelectItem(cliente.getCodigoPessoa(), cliente.getNome() + " , " + cliente.getTelefone() +" , " + cliente.getEmail()));
			
		}
	}

	public List<SelectItem> getListaClientesSelect() {
		return listaClientes;
	}
	
	public void listarClientes(ValueChangeEvent evento) throws SQLException{
		if(evento.getNewValue() != evento.getOldValue()){
			ClienteDAO dao = new ClienteDAO();
			setListaClientes(dao.getClientesPeloNome(evento.getNewValue().toString()));
		}
	}


	public List<SelectItem> getListaImoveis() {
		return listaImoveis;
	}

	public void setListaImoveis() throws SQLException {
		
		ImovelDAO daoImovel = new ImovelDAO();
		EnderecoDAO daoEndereco = new EnderecoDAO();		
		
		
		List<Imovel> imoveis = daoImovel.getImoveis();
		
		
		for(Imovel imo : imoveis){
			
			Endereco end = daoEndereco.getEnderecoPorCodigo(imo.getEndereco());
			String nomeEnd = end.getEnderecoNome();
			String logEnd = end.getEnderecoLogradouro();
			String cepEnd = end.getEnderecoCep();
			String bairroEnd = end.getEnderecoBairro().getBairroNome();
			
			listaImoveis.add((new SelectItem(imo.getCodigo(),logEnd + " " + nomeEnd + " " + bairroEnd + " " + cepEnd)));
		}
		
	}
	
	public String criarRegistro() throws SQLException{
		AgendaDAO daoAgenda = new AgendaDAO();
		if(daoAgenda.InserirAgenda(agenda))
			return "sucesso";
		
		return "falha";
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}



	
	

}