package br.com.cond.businesslogic;

public class ReceitaDespesaTipos {
	private int codigo = 0;
	private String descricao = null;
	
	public ReceitaDespesaTipos() {
		this.codigo = 0;
		this.descricao = null;
	}
	
	public ReceitaDespesaTipos(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
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
	
}
