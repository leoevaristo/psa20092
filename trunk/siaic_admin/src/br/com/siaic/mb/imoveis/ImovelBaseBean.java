package br.com.siaic.mb.imoveis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.richfaces.demo.fileupload.FileUploadBean;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Foto;
import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.businesslogic.ImovelCaracteristica;
import br.com.siaic.businesslogic.ImovelFinalidade;
import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.businesslogic.endereco.Estado;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.FotoDAO;
import br.com.siaic.dao.ImovelCaracteristicaDAO;
import br.com.siaic.dao.ImovelFinalidadeDAO;

public class ImovelBaseBean {

	public ImovelBaseBean() {

		this.imovel = new Imovel();
		this.imovelCaracteristica = new ImovelCaracteristica();
		this.imovelFinalidade = new ImovelFinalidade();
		this.imovelProprietario = new Cliente();
		this.imovelEndereco = new Endereco();
		this.bairro = new Bairro();
		this.cidade = new Cidade();
		this.estado = new Estado();
		
		if (logradouros.isEmpty())
			setLogradouro();
		
		if (listaClientes.isEmpty()) {
			try {
				setClientes();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (listaCaracteristica.isEmpty()) {
			try {
				setCaracteristicas();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private Imovel imovel;
	private ImovelCaracteristica imovelCaracteristica;
	private ImovelFinalidade imovelFinalidade;
	private Cliente imovelProprietario;

	private Endereco imovelEndereco;
	private Bairro bairro;
	private Cidade cidade;
	private Estado estado;

	private static List<SelectItem> listaClientes = new ArrayList<SelectItem>();
	private static List<SelectItem> listaCaracteristica = new ArrayList<SelectItem>();

	private static List<SelectItem> logradouros = new ArrayList<SelectItem>();
	private static List<SelectItem> cidades = new ArrayList<SelectItem>();
	private static List<SelectItem> bairros = new ArrayList<SelectItem>();

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public ImovelCaracteristica getImovelCaracteristica() {
		return imovelCaracteristica;
	}

	public void setImovelCaracteristica(ImovelCaracteristica imovelCaracteristica) {
		this.imovelCaracteristica = imovelCaracteristica;
	}

	public ImovelFinalidade getImovelFinalidade() {
		return imovelFinalidade;
	}

	public void setImovelFinalidade(ImovelFinalidade imovelFinalidade) {
		this.imovelFinalidade = imovelFinalidade;
	}

	public Cliente getImovelProprietario() {
		return imovelProprietario;
	}

	public void setImovelProprietario(Cliente imovelProprietario) {
		this.imovelProprietario = imovelProprietario;
	}

	public Endereco getImovelEndereco() {
		return imovelEndereco;
	}

	public void setImovelEndereco(Endereco imovelEndereco) {
		this.imovelEndereco = imovelEndereco;
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

	public static List<SelectItem> getLogradouros() {
		return logradouros;
	}

	public static void setLogradouros(List<SelectItem> logradouros) {
		ImovelBaseBean.logradouros = logradouros;
	}

	public List<SelectItem> getListaLogradouro() {
		return logradouros;
	}

	public void setListaLogradouro(List<SelectItem> listaLogradouro) {
		ImovelBaseBean.logradouros = listaLogradouro;
	}

	public List<SelectItem> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<SelectItem> listaClientes) {
		ImovelBaseBean.listaClientes = listaClientes;
	}

	public List<SelectItem> getListaCaracteristica() {
		return listaCaracteristica;
	}

	public void setListaCaracteristica(List<SelectItem> listaCaracteristica) {
		ImovelBaseBean.listaCaracteristica = listaCaracteristica;
	}

	public void filtraCidadePorEstado(ValueChangeEvent event)
			throws SQLException {

		if (event.getNewValue() != event.getOldValue()) {
			this.listaCidadePorEstado(event.getNewValue().toString());
		}
	}
	
	private void listaCidadePorEstado(String estado) throws SQLException {
		List<Cidade> cid = new ArrayList<Cidade>();
		EnderecoDAO dao = new EnderecoDAO();
		cid = dao.getCidadePorEstado(estado);
		setCidades(cid);
	}

	public void filtraBairroPorCidade(ValueChangeEvent event)
			throws SQLException {
		if (event.getNewValue() != event.getOldValue()) {
			this.listaBairroPorCidade(event.getNewValue().toString());
		}
	}
	
	private void listaBairroPorCidade(String cidade) throws SQLException {
		List<Bairro> listaBairros = new ArrayList<Bairro>();
		EnderecoDAO daoEndereco = new EnderecoDAO();
		listaBairros = daoEndereco.getBairroPorCidade(cidade);
		setBairros(listaBairros);
	}

	public List<SelectItem> getCidades() {
		return cidades;
	}

	public static void setCidades(List<Cidade> listCidades) {

		if (!cidades.isEmpty()) {
			cidades.clear();
		}

		for (Cidade cid : listCidades) {
			cidades.add(new SelectItem(cid.getCidadeCodigo(), cid
					.getCidadeNome()));
		}
	}

	public List<SelectItem> getBairros() {
		return bairros;
	}

	public static void setBairros(List<Bairro> listBairros) {
		if (!bairros.isEmpty()) {
			bairros.clear();
		}
		for (Bairro bai : listBairros) {
			bairros.add(new SelectItem(bai.getBairroCodigo(), bai
					.getBairroNome()));
		}
	}

	public void setLogradouro() {

		logradouros.add(new SelectItem("Rua", "Rua"));
		logradouros.add(new SelectItem("Avenida", "Avenida"));
		logradouros.add(new SelectItem("Estrada", "Estrada"));
		logradouros.add(new SelectItem("Praça", "Praça"));
		logradouros.add(new SelectItem("Travessa", "Travessa"));
		logradouros.add(new SelectItem("Alameda", "Alameda"));
		logradouros.add(new SelectItem("Parque", "Parque"));

	}
	
	public void setClientes() throws SQLException {
		ClienteDAO dc = new ClienteDAO();
		List<Cliente> lc = dc.getTodosClientes();
		if (lc != null) {
			for (Cliente cli : lc) {
				listaClientes.add(new SelectItem(cli.getCodigoPessoa(), cli
						.getNome()));
			}
		}
	}
	
	public void setCaracteristicas() throws SQLException {

		List<ImovelCaracteristica> lc = ImovelCaracteristicaDAO.getInstance()
				.getImovelCaracteristicaList();

		for (ImovelCaracteristica car : lc) {
			listaCaracteristica.add(new SelectItem(car.getCodigo(), car.toString()));
		}

	}
	
	public void setImovelRequest(Imovel imovel) throws SQLException {
		this.setImovel(imovel);
		this.setImovelCaracteristica(ImovelCaracteristicaDAO.getInstance().getImovelCaracteristica(this.getImovel().getCaracteristica()));
		this.setImovelProprietario(new ClienteDAO().getClientePorId(this.getImovel().getProprietario()));
		this.setImovelFinalidade(new ImovelFinalidadeDAO().getImovelFinalidade(this.getImovel().getFinalidade()));
		EnderecoDAO edao = new EnderecoDAO();
		this.setImovelEndereco(edao.getEnderecoPorCodigo(this.getImovel().getEndereco()));
		this.setBairro(edao.getBairroPorCodigo(this.getImovelEndereco().getEnderecoBairro().getBairroCodigo()));
		this.setCidade(edao.getCidadePorCodigo(this.getBairro().getBairroCidade()));
		this.setEstado(edao.getEstadoPorSigla(this.getCidade().getCidadeEstado()));
		
	    this.listaCidadePorEstado(this.estado.getEstadoSigla());
	    this.listaBairroPorCidade(String.valueOf(this.cidade.getCidadeCodigo()));
	    
	    FileUploadBean fileUploadBean = FileUploadBean.getCurrentSession();
	    fileUploadBean.clearUploadData();
	    ArrayList<Foto> fotos = (ArrayList<Foto>)new FotoDAO().getFotos(this.imovel.getCodigo());
	    fileUploadBean.setFiles(fotos);
	}
	
	public static String getRealPath(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}
	
	protected void salvarFotos() throws SQLException {
		FileUploadBean fileUploadBean = FileUploadBean.getCurrentSession();
		for (Foto fh : fileUploadBean.getFiles()) {
			fh.setImovel(this.getImovel().getCodigo());
			new FotoDAO().salvaFoto(fh);
		}
		fileUploadBean.clearUploadData();
	}

}
