package br.com.siaic.mb.imoveis;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.dao.ImovelDAO;

public class ConsultaImovelBean {

	public List<Imovel> getTodosImoveis() {
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
	
}
