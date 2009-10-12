package br.com.cond.businesslogic;

import java.sql.Date;

/**
 * 
 * @author Alain Rosemberg
 * @author Robson (12/10/2009 - 13h)
 *
 */

public class Condomino {

	private int codigo;
	private String nome;
	private char sexo;
	private Date dataNasc;
	private Condomino condomino;
	private Apartamento apartamento;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public Condomino getCondomino() {
		return condomino;
	}
	public void setCondomino(Condomino condomino) {
		this.condomino = condomino;
	}
	public Apartamento getApartamento() {
		return apartamento;
	}
	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}
	
	public String toString() {
		String s = nome+", "+apartamento.toString();
		return s;
	}


}
