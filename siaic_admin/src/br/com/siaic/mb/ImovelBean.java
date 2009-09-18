package br.com.siaic.mb;

import br.com.siaic.businesslogic.Imovel;

public class ImovelBean {
    
	private Imovel imovel;
	private int codigoImovel;
    
    public ImovelBean() {
    	this.imovel = new Imovel();
    }
    
    public Imovel getImovel() {
    	return this.imovel;
    }
    
    public int getCodigoImovel() {
    	return this.codigoImovel;
    }
    
    public void setCodigoImovel(int codigoImovel) {
    	this.codigoImovel = codigoImovel;
    }
    
    public void consultaImovel() {
    	this.imovel = Imovel.getImovel(this.codigoImovel);
    }
}
