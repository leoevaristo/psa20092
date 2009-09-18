package br.com.siaic.mb;

import br.com.siaic.businesslogic.ImovelCaracteristica;

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
}
