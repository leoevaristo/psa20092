package br.com.cond.businesslogic;

/**
 * 
 * @author Yasmim
 *
 */

public class Reuniao {
	private int codigo;
	private String descrição;
	private AgendaDependencia dependencia;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescrição() {
		return descrição;
	}
	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}
	public AgendaDependencia getDependencia() {
		return dependencia;
	}
	public void setDependencia(AgendaDependencia dependencia) {
		this.dependencia = dependencia;
	}
	
}
