package br.com.siaic.mb;

/**
 * @author george
 */

import java.util.List;

import br.com.siaic.businesslogic.ImovelFinalidade;
import br.com.siaic.dao.ImovelFinalidadeDAO;

public class ImovelFinalidadeBean {
	

	private ImovelFinalidade imovelFinalidade;
	private int codigoImovelFinalidade; 
	private List<ImovelFinalidade> imovelFinalidadeLista;

	public ImovelFinalidadeBean() {
		imovelFinalidade = new ImovelFinalidade();
	}
	
	public ImovelFinalidade getImovelFinalidade() {
		return imovelFinalidade;
	}

	
	public int getCodigoImovelFinalidade() {
		return this.codigoImovelFinalidade;
	}
	    
	    
	public void setCodigoImovelFinalidade(int codigoImovelFinalidade) {
		this.codigoImovelFinalidade = codigoImovelFinalidade;
	}
	   
    public void consultaImovelFinalidade() {
    	this.imovelFinalidade = new ImovelFinalidadeDAO().getImovelFinalidade(this.codigoImovelFinalidade); 
    }
	
    public void consultaImovelFinalidadeLista() {
    	this.imovelFinalidadeLista = new ImovelFinalidadeDAO().getImovelFinalidade(); 
    }

}
