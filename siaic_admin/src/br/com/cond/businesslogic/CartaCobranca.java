package br.com.cond.businesslogic;

public class CartaCobranca {
	private Apartamento apt;
	private TaxasCondominio txCond;
	private int dias;
	
	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public CartaCobranca(){
		apt = new Apartamento();
		txCond = new TaxasCondominio();
	}
	
	public Apartamento getApt() {
		return apt;
	}
	public void setApt(Apartamento apt) {
		this.apt = apt;
	}
	public TaxasCondominio getTxCond() {
		return txCond;
	}
	public void setTxCond(TaxasCondominio txCond) {
		this.txCond = txCond;
	}
	
	

}
