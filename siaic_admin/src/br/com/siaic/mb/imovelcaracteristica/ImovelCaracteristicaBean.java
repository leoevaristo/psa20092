package br.com.siaic.mb.imovelcaracteristica;

import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.tools.JavaCompiler;

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
	
	private int codigo;
	
	public void setCodigo(int valor) {
		this.codigo = valor;
	}
	public int getCodigo() {
		return codigo;
	}
	
	public ImovelCaracteristicaBean(){
		imovelCaracteristica = new ImovelCaracteristica();
		this.consultaCaracteristica();
	}

	public ImovelCaracteristica getImovelCaracteristica() {
		return imovelCaracteristica;
	}

	public void setImovelCaracteristica(ImovelCaracteristica imovelCaracteristica) {
		this.imovelCaracteristica = imovelCaracteristica;
	}
	
	public String consultaCaracteristica() {
        Integer codCarac = this.getCodigoParam();
		try {
			if (codCarac != null) {
			    this.imovelCaracteristica = ImovelCaracteristicaDAO.getInstance().getImovelCaracteristica(codCarac);
			    if (this.imovelCaracteristica == null)
			    {
			    	this.imovelCaracteristica = new ImovelCaracteristica();
			    	return "";
			    }
			} else 
				return "";
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return "";
	}
	
	public String addImovelCaracteristica() throws SQLException{
		if (ImovelCaracteristicaDAO.getInstance().addCaracteristica(this.getImovelCaracteristica())) {
			return "sucesso";
		} else {
			return "falha";
		}
	}
	
	public String atualizaCarac() {
	    try {
			Integer codCarac = this.getCodigoParam();
	    	ImovelCaracteristicaDAO.getInstance().altImovelCaracteristica(this.imovelCaracteristica, this.imovelCaracteristica);
			this.imovelCaracteristica = ImovelCaracteristicaDAO.getInstance().getImovelCaracteristica(codCarac);
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return "";
	}
	
	public Integer getCodigoParam() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer codCarac = null;
		if (req.getParameter("codigoCarac") != null)
		    codCarac = new Integer(req.getParameter("codigoCarac")).intValue();
		return codCarac;
	}
	
	public String voltarParaImovel() {
		return "imovel";
	}
	
	public static void main(String[] args) {
		ImovelCaracteristicaBean be = new ImovelCaracteristicaBean();
		System.out.println(be.getImovelCaracteristica().getPiscina());
	}
}
