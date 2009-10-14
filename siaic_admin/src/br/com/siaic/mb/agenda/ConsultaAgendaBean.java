package br.com.siaic.mb.agenda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

public class ConsultaAgendaBean {

	private Agenda agenda;

	private Cliente cliente;

	private Imovel imovel;

	private Usuario usuario;

	private Endereco endereco;

	private ImovelCaracteristica imovelCaracteristica;

	private List<Imovel> imoveisAgenda = new ArrayList<Imovel>();

	private List<Endereco> imoveisEnderecoAgenda = new ArrayList<Endereco>();

	private List<HashMap<String, String>> infoAgenda = new ArrayList<HashMap<String, String>>();

	private List<String> imoveisSelecionados;

	private static List<SelectItem> listaImoveis = new ArrayList<SelectItem>();

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

	public ImovelCaracteristica getImovelCaracteristica() {
		return imovelCaracteristica;
	}

	public void setImovelCaracteristica(
			ImovelCaracteristica imovelCaracteristica) {
		this.imovelCaracteristica = imovelCaracteristica;
	}

	public List<HashMap<String, String>> getInfoAgenda() {
		return infoAgenda;
	}

	public void setInfoAgenda(List<HashMap<String, String>> infoAgenda) {
		this.infoAgenda = infoAgenda;
	}

	public List<Endereco> getImoveisEnderecoAgenda() {
		return imoveisEnderecoAgenda;
	}

	public void setImoveisEnderecoAgenda(List<Endereco> imoveisEnderecoAgenda) {
		this.imoveisEnderecoAgenda = imoveisEnderecoAgenda;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

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
		ImovelDAO daoImovel = new ImovelDAO();

		setAgenda(daoAgenda.getAgenda(idEntrada));
		buscaClienteEntrada();
		buscaCorretorEntrada();
		buscaImovelEntrada();
		setImoveisSelecionados(daoImovel.getIndiceImoveisAgenda(idEntrada));

		return "sucesso";
	}

	public String updateAgenda() throws SQLException {

		ImovelDAO daoImovel = new ImovelDAO();
		AgendaDAO daoAgenda = new AgendaDAO();

		int qtdImoveisSelecionados = getImoveisSelecionados().size();
		int qtdImoveisAgenda = daoImovel.getNumeroImoveisAgenda(getAgenda()
				.getCodigo());
		List<String> indicesImoveisAgenda = daoImovel
				.getIndiceImoveisAgenda(getAgenda().getCodigo());
		System.out.println(qtdImoveisAgenda);
		System.out.println(qtdImoveisSelecionados);

		if (qtdImoveisSelecionados > qtdImoveisAgenda) {
			for (int i = qtdImoveisAgenda; i <= qtdImoveisSelecionados - 1; i++) {
				daoAgenda.associarImovelAgenda(Integer
						.parseInt(getImoveisSelecionados().get(i)), getAgenda()
						.getCodigo());
			}
		} else if (qtdImoveisAgenda > qtdImoveisSelecionados) {
			for (int i = qtdImoveisSelecionados - 1; i <= qtdImoveisAgenda; i++) {
				if(qtdImoveisSelecionados == 0){
					
					daoAgenda.apagarTodosImoveisAgenda(getAgenda().getCodigo());
					break;
					
				}else if (getImoveisSelecionados().get(i) != indicesImoveisAgenda
						.get(i)) {
					
					daoAgenda.apagarImovelAgenda(Integer
							.parseInt(getImoveisSelecionados().get(i)),
							getAgenda().getCodigo());
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

	public String destroiSessao() {

		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove(
				"consultaAgendaBean");
		if (!listaImoveis.isEmpty()) {
			listaImoveis.clear();
		}

		return "destruido";
	}

	public void buscaImovelEntrada() throws SQLException {
		ImovelDAO daoImovel = new ImovelDAO();
		EnderecoDAO daoEndereco = new EnderecoDAO();
		ImovelCaracteristicaDAO daoCaracteristica = ImovelCaracteristicaDAO
				.getInstance();

		List<HashMap<String, String>> dados = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> info = new HashMap<String, String>();

		setImoveisAgenda(daoImovel.getImoveisAgenda(getAgenda().getCodigo()));

		for (Imovel imo : imoveisAgenda) {

			setEndereco(daoEndereco.getEnderecoPorCodigo(imo.getEndereco()));
			String nomeEnd = getEndereco().getEnderecoNome();
			String logEnd = getEndereco().getEnderecoLogradouro();
			String bairroEnd = getEndereco().getEnderecoBairro()
					.getBairroNome();

			setImovelCaracteristica(daoCaracteristica
					.getImovelCaracteristica(imo.getCaracteristica()));

			String numQuartos = String.valueOf(getImovelCaracteristica()
					.getQtdeDormitorio());

			String numSuite = String.valueOf(getImovelCaracteristica()
					.getQtdeSuite());

			String numVagaGaragem = String.valueOf(getImovelCaracteristica()
					.getQtdeGaragem());

			String temPiscina = String.valueOf(getImovelCaracteristica()
					.getPiscina());

			info.put("nomeEnd", nomeEnd);
			info.put("logEnd", logEnd);
			info.put("bairroEnd", bairroEnd);
			info.put("numQuartos", numQuartos);
			info.put("numSuite", numSuite);
			info.put("numVagaGaragem", numVagaGaragem);
			info.put("temPiscina", temPiscina);

			dados.add(info);
			setInfoAgenda(dados);
		}
	}

	public void buscaCorretorEntrada() throws SQLException {
		UsuarioDAO daoUsuairo = new UsuarioDAO();
		setUsuario(daoUsuairo.getUsuarioId(getAgenda().getCodCorretor()));

	}

	public void buscaClienteEntrada() throws SQLException {
		ClienteDAO daoCliente = new ClienteDAO();
		setCliente(daoCliente.getClientePorId(getAgenda().getCodCliente()));
	}

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

	public List<SelectItem> getListaImoveis() {
		return listaImoveis;
	}

	public void listarImoveisPerfil() throws SQLException {

		if (listaImoveis.isEmpty()) {
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

				listaImoveis.add((new SelectItem(imo.getCodigo(),
						caracteristica + " - " + logEnd + " " + nomeEnd + " - "
								+ bairroEnd + " - " + cepEnd)));
			}
		}
	}

}
