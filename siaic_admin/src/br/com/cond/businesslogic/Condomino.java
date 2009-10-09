package br.com.cond.businesslogic;
/**
 * 
 * @author Alain Rosemberg
 *
 */

public class Condomino {

	private int codigoCondomino;
	private String Nome;
	private String DataNasc;
	private int codApartamento;
	
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
	public int getCodApartamento() {
		return codApartamento;
	}
	public void setCodApartamento(int codApartamento) {
		this.codApartamento = codApartamento;
	}



}
