package br.com.cond.businesslogic;

public class ReceitaDespesa {
	private int codigo = 0;
	private ReceitaDespesaTipos tipoRD = null;
	private double valor = 0;
	private String tipo = null;
	private String data = null;
	private Condomino condomino = null;
	
	public ReceitaDespesa() {
		this.codigo = 0;
		this.tipoRD = null;
		this.valor = 0;
		this.tipo = null;
		this.data = null;
		this.condomino = null;
	}
	
	public ReceitaDespesa(int codigo, ReceitaDespesaTipos tipoRD, double valor,
			String tipo, String data, Condomino condomino) {
		this.codigo = codigo;
		this.tipoRD = tipoRD;
		this.valor = valor;
		this.tipo = tipo;
		this.data = data;
		this.condomino = condomino;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public ReceitaDespesaTipos getTipoRD() {
		return tipoRD;
	}

	public void setTipoRD(ReceitaDespesaTipos tipoRD) {
		this.tipoRD = tipoRD;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Condomino getCondominio() {
		return condomino;
	}

	public void setCondominio(Condomino condomino) {
		this.condomino = condomino;
	}
}
