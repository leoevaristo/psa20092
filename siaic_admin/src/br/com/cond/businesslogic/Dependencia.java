package br.com.cond.businesslogic;
/**
 * @author José Carlos
 * @author Yasmim
 * 
 */
public class Dependencia {
	private int codigo;
	private String descricao;
	private char reservavel;

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

	public char getReservavel() {
		return reservavel;
	}

	public void setReservavel(char reservavel) {
		this.reservavel = reservavel;
	}

	public String toString() {
		return descricao;
	}
}
