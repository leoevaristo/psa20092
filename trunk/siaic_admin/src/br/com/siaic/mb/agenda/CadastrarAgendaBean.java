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
import br.com.siaic.businesslogic.ImovelCaracteristica;
import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.dao.AgendaDAO;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.ImovelCaracteristicaDAO;
import br.com.siaic.dao.ImovelDAO;
import br.com.siaic.dao.UsuarioDAO;

/**
 * Classe CadastrarAgendaBean
 * 
 * @author carlos
 * 
 */
public class CadastrarAgendaBean {

	private Agenda agenda;

	private Cliente cliente;

	private Imovel imovel;

	private Endereco endereco;

	private String campoPesquisa;

	private String tipoPesquisa;

	private ImovelCaracteristica imovelCaracteristica;

	private List<String> imoveisSelecionados;

	private static List<SelectItem> corretores = new ArrayList<SelectItem>();

	private static List<SelectItem> listaClientes = new ArrayList<SelectItem>();

	private static List<SelectItem> listaImoveis = new ArrayList<SelectItem>();

	/**
	 * Construtor da classe CadastrarAgendaBean
	 */
	public CadastrarAgendaBean() {

		agenda = new Agenda();
		cliente = new Cliente();

		if (corretores.isEmpty()) {
			try {
				setCorretores();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Método Getter da propriedade endereco
	 * 
	 * @return Endereco
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * Método setter da propriedade endereco
	 * 
	 * @param endereco
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	/**
	 * Método getter da propriedade imovelCaracteristica
	 * 
	 * @return ImovelCaracteristica
	 */
	public ImovelCaracteristica getImovelCaracteristica() {
		return imovelCaracteristica;
	}

	/**
	 * Método setter da propriedade imovelCaracteristica
	 * 
	 * @param imovelCaracteristica
	 */
	public void setImovelCaracteristica(
			ImovelCaracteristica imovelCaracteristica) {
		this.imovelCaracteristica = imovelCaracteristica;
	}

	/**
	 * Método getter da propriedade agenda
	 * 
	 * @return Agenda
	 */
	public Agenda getAgenda() {
		return agenda;
	}

	/**
	 * Método setter da propriedade agenda
	 * 
	 * @param agenda
	 */
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	/**
	 * Método setter da propriedade tipoPesquisa
	 * 
	 * @param tipoPesquisa
	 */
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	/**
	 * Método getter da propriedade tipoPesquisa
	 * 
	 * @return String
	 */
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	/**
	 * Método setter da propriedade cliente
	 * 
	 * @param cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Método getter da propriedade cliente
	 * 
	 * @return Cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Método getter da propriedade corretores
	 * 
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getCorretores() {
		return corretores;
	}

	/**
	 * Método setter da propriedade campoPesquisa
	 * 
	 * @param campoPesquisa
	 */
	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}

	/**
	 * Método getter da propriedade campoPesquisa
	 * 
	 * @return String
	 */
	public String getCampoPesquisa() {
		return campoPesquisa;
	}

	/**
	 * Método getter da propriedade imovel
	 * 
	 * @return Imovel
	 */
	public Imovel getImovel() {
		return imovel;
	}

	/**
	 * Método setter da propriedade imovel
	 * 
	 * @param imovel
	 */
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	/**
	 * Método setter da propriedade imoveisSelecionados
	 * 
	 * @param imoveisSelecionados
	 */
	public void setImoveisSelecionados(List<String> imoveisSelecionados) {
		this.imoveisSelecionados = imoveisSelecionados;
	}

	/**
	 * Método getter da propriedade imoveisSelecionados
	 * 
	 * @return List<Integer>
	 */
	public List<String> getImoveisSelecionados() {

		return imoveisSelecionados;
	}

	/**
	 * Método que recupera do banco, uma lista de usuários que possuem CRECI, ou
	 * seja, os corretores
	 * 
	 * @throws SQLException
	 */
	public void setCorretores() throws SQLException {

		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usu = dao.getTodosCorretores();

		for (Usuario usuario : usu)
			corretores.add(new SelectItem(usuario.getCodigoPessoa(), usuario
					.getNome()));

	}

	/**
	 * Método que recupera do banco uma lista de objetos do tipo Cliente
	 * 
	 * @return List<Cliente>
	 * @throws SQLException
	 */
	public List<Cliente> getTodosClientes() throws SQLException {
		ClienteDAO dao = new ClienteDAO();
		return dao.getTodosClientes();
	}

	public String ConsultarCliente() {
		return "ConsultarCliente";
	}

	/*
	 * public String SelecionarCliente() throws SQLException { FacesContext
	 * context = FacesContext.getCurrentInstance(); HttpServletRequest req =
	 * (HttpServletRequest) context .getExternalContext().getRequest(); Integer
	 * idPessoa = new Integer(req.getParameter("codigoPessoa")) .intValue();
	 * 
	 * ClienteDAO dao = new ClienteDAO();
	 * agenda.setCodCliente(dao.getClientePorId(idPessoa).getCodigoPessoa());
	 * return "SelecionarCliente"; }
	 */

	/*
	 * public List<Usuario> getTodosCorretores() throws SQLException {
	 * UsuarioDAO dao = new UsuarioDAO(); return dao.getTodosUsuarios(); }
	 */

	public String ConsultarCorretor() {
		return "ConsultarCorretor";
	}

	public String SelecionarCorretor() throws SQLException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();
		Integer idPessoa = new Integer(req.getParameter("codigoPessoa"))
				.intValue();

		UsuarioDAO dao = new UsuarioDAO();
		agenda.setCodCorretor(dao.getUsuarioId(idPessoa).getCodigoPessoa());
		return "SelecionarCorretor";
	}

	/**
	 * Recupera do banco uma lista de objetos do tipo Imovel
	 * 
	 * @return List<Imovel>
	 * @throws SQLException
	 */
	public List<Imovel> getTodosImoveis() throws SQLException {
		ImovelDAO dao = new ImovelDAO();
		return dao.getImoveis();
	}

	public String ConsultarImovel() {
		return "ConsultarImovel";
	}

	/*
	 * public String SelecionarImovel() throws SQLException { FacesContext
	 * context = FacesContext.getCurrentInstance(); HttpServletRequest req =
	 * (HttpServletRequest) context .getExternalContext().getRequest(); Integer
	 * idImovel = new Integer(req.getParameter("codigoImovel")) .intValue();
	 * 
	 * ImovelDAO dao = new ImovelDAO();
	 * agenda.setCodImovel(dao.getImovel(idImovel).getCodigo()); return
	 * "SelecionarImovel"; }
	 */

	/*
	 * public void setListaClientes(List<Cliente> listagemClientes) { if
	 * (!listaClientes.isEmpty()) { listaClientes.clear(); }
	 * 
	 * for (Cliente cliente : listagemClientes) {
	 * 
	 * listaClientes.add(new SelectItem(cliente.getCodigoPessoa(), cliente
	 * .getNome() + " , " + cliente.getEmail()));
	 * 
	 * } }
	 * 
	 * public List<SelectItem> getListaClientesSelect() { return listaClientes;
	 * }
	 * 
	 * public void listarClientes(ValueChangeEvent evento) throws SQLException {
	 * if (evento.getNewValue() != evento.getOldValue()) { ClienteDAO dao = new
	 * ClienteDAO();
	 * setListaClientes(dao.getClientesPeloNome(evento.getNewValue()
	 * .toString())); } }
	 */

	/**
	 * 
	 */
	public List<SelectItem> getListaImoveis() {

		return listaImoveis;
	}

	/**
	 * Adiciona um registro do tipo Agenda no banco de dados
	 * 
	 * @return String
	 * @throws SQLException
	 */
	public String criarRegistro() throws SQLException {
		
		AgendaDAO daoAgenda = new AgendaDAO();	
		List<String> imo = getImoveisSelecionados();
		agenda.setCodCliente(getCliente().getCodigoPessoa());
		
		if (daoAgenda.InserirAgenda(agenda)) {
			agenda.setCodigo(daoAgenda.getUltimoIndiceAgenda());
			
			for(String codigo : imo){
				
				daoAgenda.associarImovelAgenda( Integer.parseInt(codigo), getAgenda().getCodigo());		
			}

			destroiSessao();
			return "sucesso";
		}

		return "falha";
	}

	/**
	 * Retira esse Managed Bean do Session Map
	 * 
	 * @return String
	 */
	public String destroiSessao() {

		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove(
				"cadastrarAgendaBean");

		if (!listaImoveis.isEmpty()) {
			listaImoveis.clear();
		}

		return "destruido";
	}

	/**
	 * Filtra os imóveis que se adequam ao Perfil de um cliente específico
	 * 
	 * @throws SQLException
	 */
	public void listarImoveisPerfil() throws SQLException {

		EnderecoDAO daoEndereco = new EnderecoDAO();
		ImovelCaracteristicaDAO daoCaracteristica = ImovelCaracteristicaDAO
				.getInstance();
		ImovelDAO daoImovel = new ImovelDAO();

		List<Imovel> imoveis = daoImovel
				.getImoveisPorPerfilCliente(getCliente().getCodigoPessoa());

		for (Imovel imo : imoveis) {

			setEndereco(daoEndereco.getEnderecoPorCodigo(imo.getEndereco()));
			String nomeEnd = getEndereco().getEnderecoNome();
			String logEnd = getEndereco().getEnderecoLogradouro();
			String cepEnd = getEndereco().getEnderecoCep();
			String bairroEnd = getEndereco().getEnderecoBairro()
					.getBairroNome();

			setImovelCaracteristica(daoCaracteristica
					.getImovelCaracteristica(imo.getCaracteristica()));
			String caracteristica = getImovelCaracteristica().toString();

			listaImoveis.add((new SelectItem(imo.getCodigo(), caracteristica
					+ " - " + logEnd + " " + nomeEnd + " - " + bairroEnd
					+ " - " + cepEnd)));
		}
	}
	/**
	 * Retorna uma lista do tipo Cliente
	 * filtrada pela string contida na propriedade
	 * campoPesquisa
	 * @return List<Cliente>
	 * @throws SQLException
	 */
	public List<Cliente> getClientePorNome() throws SQLException {

		ClienteDAO daoCliente = new ClienteDAO();

		return daoCliente.getClientesPeloNome(campoPesquisa);
	}
	/**
	 * Retorna uma lista do tipo Cliente
	 * filtrada pela string contida na propriedade
	 * e que contenham perfil de cliente
	 * @return
	 * @throws SQLException
	 */
	public List<Cliente> getClientePerfilPorNome() throws SQLException {

		ClienteDAO daoCliente = new ClienteDAO();

		return daoCliente.getClientesPerfilPeloNome(campoPesquisa);
	}
	/**
	 * Escolhe o método de pesquisa
	 * @throws SQLException
	 */
	public void escolheTipoPesquisa() throws SQLException {

		if (tipoPesquisa.equals("nome")) {
			getClientePorNome();
		}
	}
	/**
	 * Limpa os campos do formulário de cadastro
	 */
	public void limparCampos() {

		this.agenda = new Agenda();
		this.cliente = new Cliente();
		this.endereco = new Endereco();
		this.imovelCaracteristica = new ImovelCaracteristica();
		this.imovel = new Imovel();

		if (!listaImoveis.isEmpty()) {
			listaImoveis.clear();
		}
	}

}