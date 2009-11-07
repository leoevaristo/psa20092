package br.com.siaic.mb.imoveis;

import java.sql.SQLException;
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
	}
	
	private String tipoPesquisa;
	private String palavraChave;
	
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
		// TODO: Trazer os imoveis de acordo com o tipo de pesquisa escolhido, jutamente com sua palavra-chave.
		return new ImovelDAO().getImoveis();
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

	public void escolheTipoPesquisa() throws SQLException {
        // TODO: Mudar a palavra-chave
//		if (tipoPesquisa.equals("email")) {
//
//			pesquisaClientePorEmail(campoPesquisa);
//
//		} else if (tipoPesquisa.equals("nome")) {
//
//			getClientePorNome();
//
//		} else if (tipoPesquisa.equals("telefone")) {
//
//			pesquisaClientePorTelefone(campoPesquisa);
//		}

	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void getTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public void getPalavraChave(String campoPesquisa) {
		this.palavraChave = campoPesquisa;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

}
