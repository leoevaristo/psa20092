package br.com.siaic.mb.agenda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
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

public class ConsultaAgendaBean {

	private Agenda agenda;
	
	private Cliente cliente;
	
	
	
	
	private static List<SelectItem> corretores = new ArrayList<SelectItem>();
	private static List<SelectItem> listaImoveis = new ArrayList<SelectItem>();
	
	
	public ConsultaAgendaBean(){
		
		agenda = new Agenda();
		cliente = new Cliente();
		
		
	}


	public Agenda getAgenda() {
		return agenda;
	}


	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
	
	public List<Agenda> getTodasEntradas() throws SQLException{
		System.out.println("getTodasEntradas");
		AgendaDAO daoAgenda = new AgendaDAO();
		return daoAgenda.getAgendaList();
	}
	
	public void excluiEntrada() throws SQLException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer idEntradaAgenda = new Integer(req.getParameter("codigoEntrada"))
				.intValue();

		AgendaDAO daoAgenda = new AgendaDAO();
		daoAgenda.ApagarAgenda(idEntradaAgenda);

	}
	
	public String localizarAgendaPorId() throws SQLException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idEntrada = new Integer(req.getParameter("codigoEntrada")).intValue();
		
		AgendaDAO daoAgenda = new AgendaDAO();
		ClienteDAO daoCliente = new ClienteDAO();		
				
		setAgenda(daoAgenda.getAgenda(idEntrada));
		setCliente(daoCliente.getClientePorId(this.agenda.getCodCliente()));
		
		
		
		
		return "sucesso";
	}
	
	public String updateAgenda() throws SQLException{
		
		AgendaDAO daoAgenda = new AgendaDAO();
		daoAgenda.AtualizarAgenda(agenda);	
				
		destroiSessao();
		
		return "sucesso";
	
	}
	
	public String destroiSessao(){
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove("consultaAgendaBean");
		
		return "destruido";
	}
	
	
	public List<SelectItem> getCorretores() {
		return corretores;
	}

	public void setCorretores() throws SQLException {
		System.out.println("setCorretores()");
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usu = dao.getTodosCorretores();
		
		for(Usuario usuario : usu)			
			corretores.add(new SelectItem(usuario.getCodigoPessoa(),usuario.getNome()));
			
					
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Cliente getCliente() {
		return cliente;
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




}
