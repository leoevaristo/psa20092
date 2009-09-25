package br.com.siaic.mb.imovelcaracteristica;

import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.ImovelCaracteristica;
import br.com.siaic.dao.ImovelCaracteristicaDAO;

/**
 * 
 * @author Yasmim Tamie Hiramoto Pereira
 * @version 1.0
 *  
 */

public class ImovelCaracteristicaBean {
	private ImovelCaracteristica imovelCaracteristica;
	
	public void imovelCaracteristicaBean(){
		imovelCaracteristica = new ImovelCaracteristica();
	}

	public ImovelCaracteristica getImovelCaracteristica() {
		return imovelCaracteristica;
	}

	public void setImovelCaracteristica(ImovelCaracteristica imovelCaracteristica) {
		this.imovelCaracteristica = imovelCaracteristica;
	}
	
	public String consultaCaracteristica() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer codCarac = new Integer(req.getParameter("codigoCarac")).intValue();
		try {
			this.imovelCaracteristica = new ImovelCaracteristicaDAO().getImovelCaracteristica(codCarac);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "consultacaracteristica";
	}
	
	public String addImovelCaracteristica() throws SQLException{
		String s = "";
		ImovelCaracteristicaDAO daoIC = new ImovelCaracteristicaDAO();
		daoIC.addCaracteristica(imovelCaracteristica);
		
		return s;
	}
}
