package br.com.cond.businesslogic;

/**
 * 
 * @author Yasmim
 *
 */

public class Reuniao {
	private int codigo;
	private String descri��o;
	private AgendaDependencia dependencia;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescri��o() {
		return descri��o;
	}
	public void setDescri��o(String descri��o) {
		this.descri��o = descri��o;
	}
	public AgendaDependencia getDependencia() {
		return dependencia;
	}
	public void setDependencia(AgendaDependencia dependencia) {
		this.dependencia = dependencia;
	}
	
}
