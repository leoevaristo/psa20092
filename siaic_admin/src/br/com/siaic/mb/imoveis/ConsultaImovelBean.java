package br.com.siaic.mb.imoveis;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.dao.ImovelDAO;

public class ConsultaImovelBean {

	public ConsultaImovelBean() {
		this.tipoPesquisa = BUSCA_EMPTY;
		this.palavraChave = "";
		this.listaImoveis = new ImovelDAO().getImoveis();
	}
	
	private String tipoPesquisa;
	private String palavraChave;
	private List<Imovel> listaImoveis;
	
	public final static String BUSCA_CIDADE = "Cidade";
	public final static String BUSCA_BAIRRO = "Bairro";
	public final static String BUSCA_ENDERECO = "Endereço";
	public final static String BUSCA_VALOR = "Valor";
	public final static String BUSCA_CLIENTE = "Cliente";
	public final static String BUSCA_EMPTY = "";
	
	public List<SelectItem> getBuscas() {
		List<SelectItem> buscas = new ArrayList<SelectItem>();
		
		buscas.add(new SelectItem(BUSCA_CIDADE,BUSCA_CIDADE));
		buscas.add(new SelectItem(BUSCA_BAIRRO,BUSCA_BAIRRO));
		buscas.add(new SelectItem(BUSCA_ENDERECO,BUSCA_ENDERECO));
		buscas.add(new SelectItem(BUSCA_VALOR,BUSCA_VALOR));
		buscas.add(new SelectItem(BUSCA_CLIENTE,BUSCA_CLIENTE));
		
		return buscas;
	}

	public List<Imovel> getTodosImoveis() {
		return this.listaImoveis;
	}

	public String excluiImovel() {
		new ImovelDAO().getImovel(this.getCodigoImovelParametroGET()).excluir();
		return "";
	}

	private int getCodigoImovelParametroGET() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		String aff = req.getParameter("codigoImovel");
		if (aff != null)
			return new Integer(aff).intValue();
		else
			return -1;
	}

	public String escolheTipoPesquisa() {
        if (this.tipoPesquisa.equals(BUSCA_EMPTY)) {
        	this.listaImoveis = new ImovelDAO().getImoveis();
        } else if (this.tipoPesquisa.equals(BUSCA_BAIRRO)) {
            this.listaImoveis = new ImovelDAO().getImovesPorBairro(this.palavraChave);
        } else if (this.tipoPesquisa.equals(BUSCA_CLIENTE)) {
        	this.listaImoveis = new ImovelDAO().getImovesPorCliente(this.palavraChave);
        } else if (this.tipoPesquisa.equals(BUSCA_ENDERECO)) {
        	this.listaImoveis = new ImovelDAO().getImovesPorEndereco(this.palavraChave);
        } else if (this.tipoPesquisa.equals(BUSCA_VALOR)) {
        	this.listaImoveis = new ImovelDAO().getImovesPorValor(this.palavraChave);
        } else if (this.tipoPesquisa.equals(BUSCA_CIDADE)) {
            this.listaImoveis = new ImovelDAO().getImovesPorCidade(this.palavraChave);
        }
        return "";
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public void setPalavraChave(String campoPesquisa) {
		this.palavraChave = campoPesquisa;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

}
