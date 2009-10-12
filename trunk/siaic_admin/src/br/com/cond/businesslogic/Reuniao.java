package br.com.cond.businesslogic;

/**
 * 
 * @author Yasmim
 *
 */

public class Reuniao {
	private int codigo;
	private String descricao;
	private AgendaDependencia dependencia;
	
	public Reuniao() {
		dependencia = new AgendaDependencia();
		descricao = new String("");
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public AgendaDependencia getDependencia() {
		return dependencia;
	}
	public void setDependencia(AgendaDependencia dependencia) {
		this.dependencia = dependencia;
	}
	
}
