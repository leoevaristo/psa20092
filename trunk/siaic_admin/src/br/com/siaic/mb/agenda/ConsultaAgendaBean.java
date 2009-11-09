package br.com.siaic.mb.agenda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Classe para manipular todos dados referentes
 * a exibição, alteração e exclusão dos dados
 * de uma Agenda
 * @author carlos
 *
 */
public class ConsultaAgendaBean {

	/**
	 * Representa um objeto do tipo Agenda
	 */
	private Agenda agenda;

	/**
	 * Representa um objeto do tipo Cliente
	 */
	private Cliente cliente;

	/**
	 * Representa um objeto do tipo Imovel
	 */
	private Imovel imovel;
	
	/**
	 * Representa um objeto do tipo Usuario
	 */
	private Usuario usuario;

	/**
	 * Representa um objeto do tipo Endereco
	 */
	private Endereco endereco;

	/**
	 * Representa um objeto do tipo ImovelCaracteristica
	 */
	private ImovelCaracteristica imovelCaracteristica;

	/**
	 * Representa uma lista de objetos do tipo Imovel
	 */
	private List<Imovel> imoveisAgenda = new ArrayList<Imovel>();

	/**
	 * Representa uma lista de objetos do tipo Endereco
	 */
	private List<Endereco> imoveisEnderecoAgenda = new ArrayList<Endereco>();

	/**
	 * Representa uma lista de objetos do tipo Map
	 */
	private List<Map> infoAgenda = new ArrayList<Map>();

	/**
	 * Representa uma lista de objetos do tipo String
	 */
	private List<String> imoveisSelecionados;

	/**
	 * Representa uma lista de objetos do tipo SelectItem
	 */
	private static List<SelectItem> listaImoveis = new ArrayList<SelectItem>();

	
	/**
	 * Construtor da classe ConsultaAgendaBean
	 */
	public ConsultaAgendaBean() {
		agenda = new Agenda();
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
	 * Método getter da propriedade imovelCaracteristica
	 * @return ImovelCaracteristica
	 */
	public ImovelCaracteristica getImovelCaracteristica() {
		return imovelCaracteristica;
	}

	/**
	 * Método setter da propriedade imovelCaracteristica
	 * @param imovelCaracteristica
	 */
	public void setImovelCaracteristica(
			ImovelCaracteristica imovelCaracteristica) {
		this.imovelCaracteristica = imovelCaracteristica;
	}
	
	/**
	 * Método getter da propriedade infoAgenda
	 * @return List<Map>
	 */
	public List<Map> getInfoAgenda() {
		return infoAgenda;
	}
	
	/**
	 * Método setter da propriedade infoAgenda
	 * @param infoAgenda
	 */
	public void setInfoAgenda(List<Map> infoAgenda) {
		this.infoAgenda = infoAgenda;
	}
	
	/**
	 * Método getter da propriedade imoveisEnderecoAgenda
	 * @return List<Endereco>
	 */
	public List<Endereco> getImoveisEnderecoAgenda() {
		return imoveisEnderecoAgenda;
	}

	/**
	 * Método setter da propriedade imovelCaracteristica
	 * @param imoveisEnderecoAgenda
	 */
	public void setImoveisEnderecoAgenda(List<Endereco> imoveisEnderecoAgenda) {
		this.imoveisEnderecoAgenda = imoveisEnderecoAgenda;
	}
	
	/**
	 * Método getter da propriedade endereco
	 * @return Endereco
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * Método setter da propriedade endereco
	 * @param endereco
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	/**
	 * Método getter da propriedade imoveisAgenda
	 * @return List<Imovel>
	 */
	public List<Imovel> getImoveisAgenda() {
		return imoveisAgenda;
	}

	/**
	 * Método setter da propriedade imoveisAgenda
	 * @param imoveisAgenda
	 */
	public void setImoveisAgenda(List<Imovel> imoveisAgenda) {
		this.imoveisAgenda = imoveisAgenda;
	}

	/**
	 * Método getter da propriedade cliente
	 * @return Cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}
	
	/**
	 * Método setter da propriedade cliente
	 * @param cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Método getter da propriedade imovel
	 * @return Imovel
	 */
	public Imovel getImovel() {
		return imovel;
	}

	/**
	 * Método setter da propriedade imovel
	 * @param imovel
	 */
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	
	/**
	 * Método getter da propriedade usuario
	 * @return Usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Método setter da propriedade usuario
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Método getter da propriedade agenda
	 * @return Agenda
	 */
	public Agenda getAgenda() {
		return agenda;
	}

	/**
	 * Método setter da propriedade agendas
	 * @param agenda
	 */
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
	/**
	 * Retorna uma Lista de todas as entradas de Agenda
	 * existentes
	 * @return List<Agenda>
	 * @throws SQLException
	 */
	public List<Agenda> getTodasEntradas() throws SQLException {

		AgendaDAO daoAgenda = new AgendaDAO();
		return daoAgenda.getAgendaList();
	}
	
	/**
	 * Remove uma entrada da Agenda
	 * @throws SQLException
	 */
	public void excluiEntrada() throws SQLException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer idEntradaAgenda = new Integer(req.getParameter("codigoEntrada"))
				.intValue();

		AgendaDAO daoAgenda = new AgendaDAO();
		daoAgenda.ApagarAgenda(idEntradaAgenda);

	}
	
	/**
	 * Busca uma entrada da agenda filtrada por sua ID
	 * @return String
	 * @throws SQLException
	 */
	public String localizarAgendaPorId() throws SQLException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();
		Integer idEntrada = new Integer(req.getParameter("codigoEntrada"))
				.intValue();

		AgendaDAO daoAgenda = new AgendaDAO();
		ImovelDAO daoImovel = new ImovelDAO();

		setAgenda(daoAgenda.getAgenda(idEntrada));
		buscaClienteEntrada();
		buscaCorretorEntrada();
		buscaImovelEntrada();
		setImoveisSelecionados(daoImovel.getIndiceImoveisAgenda(idEntrada));

		return "sucesso";
	}
	
	/**
	 * Realiza atualização dos dados de uma entrada da Agenda
	 * @return String
	 * @throws SQLException
	 */
	public String updateAgenda() throws SQLException {

		ImovelDAO daoImovel = new ImovelDAO();
		AgendaDAO daoAgenda = new AgendaDAO();

		int qtdImoveisSelecionados = getImoveisSelecionados().size();
		int qtdImoveisAgenda = daoImovel.getNumeroImoveisAgenda(getAgenda().getCodigo());
		List<String> indicesImoveisAgenda = daoImovel.getIndiceImoveisAgenda(getAgenda().getCodigo());
		System.out.println(qtdImoveisAgenda);
		System.out.println(qtdImoveisSelecionados);

		if (qtdImoveisSelecionados > qtdImoveisAgenda) {
			for (int i = qtdImoveisAgenda; i <= qtdImoveisSelecionados - 1; i++) {
				daoAgenda.associarImovelAgenda(Integer.parseInt(getImoveisSelecionados().get(i)), getAgenda().getCodigo());
			}
		} else if (qtdImoveisAgenda > qtdImoveisSelecionados) {
			for (int i = qtdImoveisSelecionados - 1; i <= qtdImoveisAgenda; i++) {
				if(qtdImoveisSelecionados == 0){					
					daoAgenda.apagarTodosImoveisAgenda(getAgenda().getCodigo());
					break;					
				}else if (getImoveisSelecionados().get(i) != indicesImoveisAgenda.get(i)) {					
					daoAgenda.apagarImovelAgenda(Integer.parseInt(getImoveisSelecionados().get(i)),	getAgenda().getCodigo());
					qtdImoveisAgenda--;					
					if(qtdImoveisAgenda == qtdImoveisSelecionados){
						break;
					}									
				}
			}
		}

		daoAgenda.AtualizarAgenda(agenda);
		destroiSessao();

		return "sucesso";

	}
	
	/**
	 * Retira o Managed Bean do mapa de sessão
	 * @return String
	 */
	public String destroiSessao() {

		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove("consultaAgendaBean");
		if (!listaImoveis.isEmpty()) {
			listaImoveis.clear();
		}

		return "destruido";
	}
	
	/**
	 * Busca os dados dos imóveis que pertencem
	 * a uma entrada da Agenda
	 * @throws SQLException
	 */
	public void buscaImovelEntrada() throws SQLException {
		ImovelDAO daoImovel = new ImovelDAO();
		EnderecoDAO daoEndereco = new EnderecoDAO();
		ImovelCaracteristicaDAO daoCaracteristica = ImovelCaracteristicaDAO.getInstance();

		List<Map> dados = new ArrayList<Map>();		

		setImoveisAgenda(daoImovel.getImoveisAgenda(getAgenda().getCodigo()));
		
		for (Imovel imo : imoveisAgenda) {			
			setImovel(imo);
			setImovelCaracteristica(daoCaracteristica.getImovelCaracteristica(imo.getCaracteristica()));
			setEndereco(daoEndereco.getEnderecoPorCodigo(imo.getEndereco()));	
			dados.add(addDadosImoveis());
			setInfoAgenda(dados);
		}
	}
	
	/**
	 * Adiciona dados dos imóveis pertecentes
	 * à uma entrada da Agenda a um objeto do tipo Map
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	public Map addDadosImoveis(){
		
		Map dadosImoveis = new HashMap();
		dadosImoveis.put("nomeEnd", getEndereco().getEnderecoNome());
		dadosImoveis.put("logEnd", getEndereco().getEnderecoLogradouro());
		dadosImoveis.put("cepEnd", getEndereco().getEnderecoCep());
		dadosImoveis.put("bairroEnd",getEndereco().getEnderecoBairro().getBairroNome());
		dadosImoveis.put("temPiscina", getImovelCaracteristica().getPiscina());
		dadosImoveis.put("numQuartos", getImovelCaracteristica().getQtdeDormitorio());
		dadosImoveis.put("numSuite", getImovelCaracteristica().getQtdeSuite());
		dadosImoveis.put("numVagaGaragem", getImovelCaracteristica().getQtdeGaragem());
		dadosImoveis.put("valorImovel", getImovel().getValor());
		dadosImoveis.put("detalhesImovel",getImovel().getDetalhe());
		dadosImoveis.put("formaPagamento", getImovel().getFormaPagamento());
		
		return dadosImoveis;
	}
	
	/**
	 * Seta o corretor responsável por aquela entrada na Agenda
	 * @throws SQLException
	 */
	public void buscaCorretorEntrada() throws SQLException {
		UsuarioDAO daoUsuairo = new UsuarioDAO();
		setUsuario(daoUsuairo.getUsuarioId(getAgenda().getCodCorretor()));

	}
	
	/**
	 * Seta cliente de uma entrada na Agenda
	 * @throws SQLException
	 */
	public void buscaClienteEntrada() throws SQLException {
		ClienteDAO daoCliente = new ClienteDAO();
		setCliente(daoCliente.getClientePorId(getAgenda().getCodCliente()));
	}
	
	/**
	 * Exibe todos os dados pertecentes a uma entrada na Agenda
	 * @return
	 * @throws SQLException
	 */
	public String exibirDetalhesAgenda() throws SQLException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();
		Integer idEntrada = new Integer(req.getParameter("codigoEntrada"))
				.intValue();
		// String tipoExibicao = new
		// String(req.getParameter("tipoExibicao").toString());

		AgendaDAO daoAgenda = new AgendaDAO();

		setAgenda(daoAgenda.getAgenda(idEntrada));
		buscaClienteEntrada();
		buscaCorretorEntrada();
		buscaImovelEntrada();

		return "detalhes";
	}
	
	
	public List<Agenda> getTodasAtividades() throws SQLException {

		AgendaDAO dao = new AgendaDAO();
		
		return dao.getAgendaList();

	}

	/**
	 * Retorna uma lista de SelectItem contendo dados de imóveis 
	 * @return
	 */
	public List<SelectItem> getListaImoveis() {
		return listaImoveis;
	}
	
	/**
	 * Lista todos imóveis que se encaixam no Perfil de um determinado Cliente
	 * e os adiciona numa lista de Select Item
	 * @throws SQLException
	 */
	public void listarImoveisPerfil() throws SQLException {

		if (listaImoveis.isEmpty()) {
			EnderecoDAO daoEndereco = new EnderecoDAO();
			ImovelCaracteristicaDAO daoCaracteristica = ImovelCaracteristicaDAO.getInstance();
			ImovelDAO daoImovel = new ImovelDAO();

			List<Imovel> imoveis = daoImovel.getImoveisPorPerfilCliente(getCliente().getCodigoPessoa());

			for (Imovel imo : imoveis) {

				setEndereco(daoEndereco.getEnderecoPorCodigo(imo.getEndereco()));
				String nomeEnd = getEndereco().getEnderecoNome();
				String logEnd = getEndereco().getEnderecoLogradouro();
				String cepEnd = getEndereco().getEnderecoCep();
				String bairroEnd = getEndereco().getEnderecoBairro().getBairroNome();

				setImovelCaracteristica(daoCaracteristica
						.getImovelCaracteristica(imo.getCaracteristica()));
				String caracteristica = getImovelCaracteristica().toString();

				listaImoveis.add((new SelectItem(imo.getCodigo(),
						caracteristica + " - " + logEnd + " " + nomeEnd + " - "
								+ bairroEnd + " - " + cepEnd)));
			}
		}
	}

}
