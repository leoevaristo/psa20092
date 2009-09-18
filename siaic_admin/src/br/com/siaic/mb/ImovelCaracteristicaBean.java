package br.com.siaic.mb;

import java.sql.SQLException;

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
	public String addImovelCaracteristica() throws SQLException{
		String s = "";
		ImovelCaracteristicaDAO daoIC = new ImovelCaracteristicaDAO();
		daoIC.addCaracteristica(imovelCaracteristica);
		
		return s;
	}
}
