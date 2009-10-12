package br.com.cond.businesslogic;
/**
 * 
 * @author Alain Rosemberg
 * @author Robson (12/10/2009 - 13h)
 *
 */

public class Condomino {

	private int codigoCondomino;
	private String Nome;
	private String DataNasc;
	private Apartamento apartamento;
	
	public int getCodigoCondomino() {
		return codigoCondomino;
	}
	public void setCodigoCondomino(int codigoCondomino) {
		this.codigoCondomino = codigoCondomino;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getDataNasc() {
		return DataNasc;
	}
	public void setDataNasc(String dataNasc) {
		DataNasc = dataNasc;
	}
	public Apartamento getApartamento() {
		return apartamento;
	}
	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}


}
