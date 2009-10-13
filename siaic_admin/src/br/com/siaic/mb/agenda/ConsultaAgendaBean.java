package br.com.siaic.mb.agenda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Agenda;
import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.dao.AgendaDAO;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.ImovelDAO;
import br.com.siaic.dao.UsuarioDAO;

public class ConsultaAgendaBean {

	private Agenda agenda;

	private Cliente cliente;

	private Imovel imovel;

	private Usuario usuario;
	
	List<Imovel> imoveisAgenda = new ArrayList<Imovel>();
	

	public List<Imovel> getImoveisAgenda() {
		return imoveisAgenda;
	}

	public void setImoveisAgenda(List<Imovel> imoveisAgenda) {
		this.imoveisAgenda = imoveisAgenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ConsultaAgendaBean() {

		agenda = new Agenda();

	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public List<Agenda> getTodasEntradas() throws SQLException {

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

	public String localizarAgendaPorId() throws SQLException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();
		Integer idEntrada = new Integer(req.getParameter("codigoEntrada"))
				.intValue();

		AgendaDAO daoAgenda = new AgendaDAO();

		setAgenda(daoAgenda.getAgenda(idEntrada));
		buscaClienteEntrada();
		buscaCorretorEntrada();
		buscaImovelEntrada();

		return "sucesso";
	}

	public String updateAgenda() throws SQLException {

		AgendaDAO daoAgenda = new AgendaDAO();
		daoAgenda.AtualizarAgenda(agenda);

		destroiSessao();

		return "sucesso";

	}

	public String destroiSessao() {

		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove(
				"consultaAgendaBean");

		return "destruido";
	}

	public void buscaImovelEntrada() {
		ImovelDAO daoImovel = new ImovelDAO();		
		setImoveisAgenda(daoImovel.getImoveisAgenda(getAgenda().getCodigo()));				
	}

	public void buscaCorretorEntrada() throws SQLException {
		UsuarioDAO daoUsuairo = new UsuarioDAO();
		setUsuario(daoUsuairo.getUsuarioId(getAgenda().getCodCorretor()));
		
	}

	public void buscaClienteEntrada() throws SQLException {
		ClienteDAO daoCliente = new ClienteDAO();

		setCliente(daoCliente.getClientePorId(getAgenda().getCodCliente()));
		
	}
	
	public String exibirDetalhesAgenda() throws SQLException{
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idEntrada = new Integer(req.getParameter("codigoEntrada")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		AgendaDAO daoAgenda = new AgendaDAO();

		setAgenda(daoAgenda.getAgenda(idEntrada));
		buscaClienteEntrada();
		buscaCorretorEntrada();
		buscaImovelEntrada();		
		
		
		
		return "detalhes";
	}

}
