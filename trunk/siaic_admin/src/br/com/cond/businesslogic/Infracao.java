package br.com.cond.businesslogic;

/**
 * 
 * @author George Fernandes Maia
 *
 */

public class Infracao {
	
	@Override
	public String toString() {
		return "Infracao [codigoInfracao=" + codigoInfracao
				+ ", descricaoInfracao=" + descricaoInfracao + "]";
	}
	public int getCodigoInfracao() {
		return codigoInfracao;
	}
	public void setCodigoInfracao(int codigoInfracao) {
		this.codigoInfracao = codigoInfracao;
	}
	public String getDescricaoInfracao() {
		return descricaoInfracao;
	}
	public void setDescricaoInfracao(String descricaoInfracao) {
		this.descricaoInfracao = descricaoInfracao;
	}
	
	
	private int codigoInfracao;
	private String descricaoInfracao;

	public Infracao(int codigoInfracao, String descricaoInfracao) {
		super();
		this.codigoInfracao = codigoInfracao;
		this.descricaoInfracao = descricaoInfracao;
	}

	
	public Infracao(){}
	

}
