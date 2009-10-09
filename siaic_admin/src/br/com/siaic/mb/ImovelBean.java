package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.businesslogic.ImovelCaracteristica;
import br.com.siaic.businesslogic.ImovelFinalidade;
import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.businesslogic.endereco.Estado;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.ImovelCaracteristicaDAO;
import br.com.siaic.dao.ImovelDAO;
import br.com.siaic.dao.ImovelFinalidadeDAO;
import br.com.siaic.mb.endereco.SelectBairros;

public class ImovelBean {

	private Imovel imovel;
	private ImovelCaracteristica imoCar;
	private ImovelFinalidade imoFin;
	private Cliente prop;

	private Endereco endereco;
	private Bairro bairro;
	private Cidade cidade;
	private Estado estado;

	private static List<SelectItem> logradouro = new ArrayList<SelectItem>();
	private static List<SelectItem> listaClientes = new ArrayList<SelectItem>();	
	private static List<SelectItem> listaCaracteristica = new ArrayList<SelectItem>();

	public List<SelectItem> getLogradouroo() {
		return logradouro;
	}

	private int codigoImovel;

	public ImovelBean() {
		this.imovel = new Imovel();
		this.endereco = new Endereco();
		this.imoCar = new ImovelCaracteristica();
		this.imoFin = new ImovelFinalidade();
		this.prop = new Cliente();

		endereco = new Endereco();
		cidade = new Cidade();
		estado = new Estado();
		bairro = new Bairro();

		if (logradouro.isEmpty())
			setLogradouro();
		
		if(listaClientes.isEmpty()){
			try {
				setListaClientes();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		if(listaCaracteristica.isEmpty()){
			try {
				setListaCaracteristica();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}

	public List<SelectItem> getListaClientes() {
		return listaClientes;
	}

	public static void setListaClientes(List<SelectItem> listaClientes) {
		ImovelBean.listaClientes = listaClientes;
	}

	public void setLogradouro() {

		logradouro.add(new SelectItem("Rua", "Rua"));
		logradouro.add(new SelectItem("Avenida", "Avenida"));
		logradouro.add(new SelectItem("Estrada", "Estrada"));
		logradouro.add(new SelectItem("Praça", "Praça"));
		logradouro.add(new SelectItem("Travessa", "Travessa"));
		logradouro.add(new SelectItem("Alameda", "Alameda"));
		logradouro.add(new SelectItem("Parque", "Parque"));

	}

	public ImovelFinalidade getImoFin() {
		return imoFin;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public int getCodigoImovel() {
		return this.codigoImovel;
	}

	public void setCodigoImovel(int codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String consultaImovel() {
		this.imovel = Imovel.getImovel(this.getParamCodigoImovel());
		try {
			this.imoCar = ImovelCaracteristicaDAO.getInstance()
					.getImovelCaracteristica(this.imovel.getCaracteristica());
			this.prop = new ClienteDAO().getClientePorId(this.imovel
					.getProprietario());
			this.imoFin = new ImovelFinalidadeDAO()
					.getImovelFinalidade(this.imovel.getFinalidade());
			EnderecoDAO edao = new EnderecoDAO();
			this.endereco = edao
					.getEnderecoPorCodigo(this.imovel.getEndereco());

			this.bairro = edao.getBairroPorCodigo(this.endereco
					.getEnderecoBairro().getBairroCodigo());
			this.cidade = edao
					.getCidadePorCodigo(this.bairro.getBairroCidade());
			this.estado = edao.getEstadoPorSigla(this.cidade.getCidadeEstado());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "altera";
	}

	private int getParamCodigoImovel() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();
		Integer codImovel = new Integer(req.getParameter("codigoImovel"))
				.intValue();
		return codImovel;
	}

	public String consultaCaracteristica() {
		return "consultacaracteristica";
	}

	public String novoImovel() {
		return "novo";
	}

	public String excluiImovel() {
		new ImovelDAO().getImovel(this.getParamCodigoImovel()).excluir();
		return "";
	}

	public List<Imovel> getTodosImoveis() {
		return new ImovelDAO().getImoveis();
	}

	public String atualizaImovel() {
		try {
			EnderecoDAO daoEndereco = new EnderecoDAO();
			daoEndereco.adicionarEndereco(endereco);
			this.imovel.setCaracteristica(endereco.getEnderecoCodigo());
			this.imovel.atualizar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String salvaImovel() {
		ImovelFinalidadeDAO finDao = new ImovelFinalidadeDAO();
		finDao.salvar(this.imoFin);
		this.imovel.setFinalidade(this.imoFin.getCodigo());
		
		this.imovel.setProprietario(this.prop.getCodigoPessoa());
		this.imovel.setCaracteristica(this.imoCar.getCodigo());
		
		EnderecoDAO end = new EnderecoDAO();
		try {
			end.adicionarEndereco(this.endereco);
			this.imovel.setEndereco(this.endereco.getEnderecoCodigo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.imovel.salvar();
		return "novo";
	}

	public ImovelCaracteristica getImoCar() {
		return imoCar;
	}

	public Cliente getProp() {
		return prop;
	}
	
	public void setListaClientes() throws SQLException {

		List<Cliente> lc = new ClienteDAO().getTodosClientes();

		for (Cliente cli : lc) {
			listaClientes.add(new SelectItem(cli.getCodigoPessoa(), cli.getNome()));
		}

	}
	
	public void setListaCaracteristica() throws SQLException {

		List<ImovelCaracteristica> lc = ImovelCaracteristicaDAO.getInstance().getImovelCaracteristicaList();

		for (ImovelCaracteristica car : lc) {
			StringBuilder sb = new StringBuilder();
			sb.append("Piscina: ");
			sb.append(car.getPiscina() == 'S' ? "SIM" : "N�O");
			sb.append(" Dormitorios: ");
			sb.append(car.getQtdeDormitorio());
			sb.append(" V. Garagem: ");
			sb.append(car.getQtdeGaragem());
			sb.append(" Suites: ");
			sb.append(car.getQtdeSuite());
			
			listaCaracteristica.add(new SelectItem(car.getCodigo(), sb.toString()));
		}

	}
	
	public List<SelectItem> getListaCaracteristica() {
		return ImovelBean.listaCaracteristica;
	}
	
}












