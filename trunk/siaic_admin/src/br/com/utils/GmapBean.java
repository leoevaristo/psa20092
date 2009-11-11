package br.com.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.cor.businesslogic.Gmap;
import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.businesslogic.ImovelCaracteristica;
import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.ImovelCaracteristicaDAO;
import br.com.siaic.dao.ImovelDAO;

public class GmapBean {

	private Gmap googlemap;
	
	private Imovel imovel;
	
	private Endereco endereco;
	
	private Bairro bairro;
	
	private ImovelCaracteristica imovelCaracteristica;
	
	private List<Imovel> imoveis = new ArrayList<Imovel>();
	
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	private List<Map> infoMapa = new ArrayList<Map>();
	
	private static List<SelectItem> bairros = new ArrayList<SelectItem>();
	
	
	

	public GmapBean() throws SQLException {
		googlemap = new Gmap();
		imovel = new Imovel();
		endereco = new Endereco();
		imovelCaracteristica = new ImovelCaracteristica();
		bairro = new Bairro();
		if(bairros.isEmpty()){
			setBairros();
		}
		buscaImovelEntrada();

	}
	
	

	public Bairro getBairro() {
		return bairro;
	}



	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}



	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Gmap getGooglemap() {
		return googlemap;
	}

	public void setGooglemap(Gmap googlemap) {
		this.googlemap = googlemap;
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public ImovelCaracteristica getImovelCaracteristica() {
		return imovelCaracteristica;
	}

	public void setImovelCaracteristica(ImovelCaracteristica imovelCaracteristica) {
		this.imovelCaracteristica = imovelCaracteristica;
	}

	public List<Map> getInfoMapa() {
		return infoMapa;
	}

	public void setInfoMapa(List<Map> infoMapa) {
		this.infoMapa = infoMapa;
	}

	public void transformaEndereco() {

		String endereco = googlemap.getUrl() + "&q="
						+ googlemap.getLugar().replace(" ", "+")
						+ "&output=csv&oe=utf8&sensor=false" + googlemap.getKey();
		String resultado = "";
		String dados[];
		try {
			URL url = new URL(endereco);
			InputStreamReader isr;
			isr = new InputStreamReader(url.openStream());
			BufferedReader bf = new BufferedReader(isr);			
			while (isr.ready()) {
				resultado = bf.readLine();
			}
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		googlemap.setDadosEndereco(resultado);
		dados = resultado.split(",");
		if (dados[2].equals("0") && dados[3].equals("0")) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Nenhum endereço encontrado.");
			ctx.addMessage("msgMapa", msg);
		} else {
			googlemap.setLatitude(dados[2]);
			googlemap.setLongitude(dados[3]);
		}

	}
	
	
	/**
	 * Busca todos imóveis cadastrados e exibe-os no mapa.
	 * @throws SQLException
	 */
	public void buscaImovelEntrada() throws SQLException {
		ImovelDAO daoImovel = new ImovelDAO();
		EnderecoDAO daoEndereco = new EnderecoDAO();
		ImovelCaracteristicaDAO daoCaracteristica = ImovelCaracteristicaDAO.getInstance();

		List<Map> dados = new ArrayList<Map>();		

		setImoveis(daoImovel.getImoveis());
		
		for (Imovel imo : imoveis) {			
			setImovel(imo);
			setImovelCaracteristica(daoCaracteristica.getImovelCaracteristica(imo.getCaracteristica()));
			setEndereco(daoEndereco.getEnderecoPorCodigo(imo.getEndereco()));	
			dados.add(addDadosImoveis());
			setInfoMapa(dados);
		}
	}
	
	/**
	 * Adiciona dados dos imóveis cadastrados a um objeto do tipo Map
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
	
	public  List<SelectItem> getBairros() {
		return bairros;
	}

	public static void setBairros() throws SQLException {
		if(!bairros.isEmpty()){
			bairros.clear();
		}
		EnderecoDAO dao = new EnderecoDAO();
		List<Bairro> b = dao.getTodosBairros();
		
		for(Bairro bai : b){
			bairros.add(new SelectItem(bai.getBairroNome(), bai.getBairroNome()));
		}
	}

	public void buscaImovelPorBairro() throws SQLException{
		ImovelDAO daoImovel = new ImovelDAO();
		EnderecoDAO daoEndereco = new EnderecoDAO();
		ImovelCaracteristicaDAO daoCaracteristica = ImovelCaracteristicaDAO.getInstance();

		List<Map> dados = new ArrayList<Map>();		

		setImoveis(daoImovel.getImovesPorBairro(bairro.getBairroNome()));
		
		for (Imovel imo : imoveis) {			
			setImovel(imo);
			setImovelCaracteristica(daoCaracteristica.getImovelCaracteristica(imo.getCaracteristica()));
			setEndereco(daoEndereco.getEnderecoPorCodigo(imo.getEndereco()));	
			dados.add(addDadosImoveis());
			setInfoMapa(dados);
		}
	}
	
	

}
