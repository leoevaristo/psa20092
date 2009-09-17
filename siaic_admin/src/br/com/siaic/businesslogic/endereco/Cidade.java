package br.com.siaic.businesslogic.endereco;

/**
 * 
 * @author george
 *
 */


public class Cidade {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cidade [cidadeCodigo=" + cidadeCodigo + ", cidadeEstado="
				+ cidadeEstado + ", cidadeNome=" + cidadeNome + "]";
	}
	
	/**
	 * @return the cidadeCodigo
	 */
	public int getCidadeCodigo() {
		return cidadeCodigo;
	}
	/**
	 * @param cidadeCodigo the cidadeCodigo to set
	 */
	public void setCidadeCodigo(int cidadeCodigo) {
		this.cidadeCodigo = cidadeCodigo;
	}
	/**
	 * @return the cidadeNome
	 */
	public String getCidadeNome() {
		return cidadeNome;
	}
	/**
	 * @param cidadeNome the cidadeNome to set
	 */
	public void setCidadeNome(String cidadeNome) {
		this.cidadeNome = cidadeNome;
	}
	/**
	 * @return the cidadeEstado
	 */
	public Estado getCidadeEstado() {
		return cidadeEstado;
	}
	/**
	 * @param cidadeEstado the cidadeEstado to set
	 */
	public void setCidadeEstado(Estado cidadeEstado) {
		this.cidadeEstado = cidadeEstado;
	}
	
	
	
	
	private int cidadeCodigo;
	private String cidadeNome;
	private Estado cidadeEstado;

	
	public Cidade(int cidadeCodigo, String cidadeNome, Estado cidadeEstado) {
		super();
		this.cidadeCodigo = cidadeCodigo;
		this.cidadeNome = cidadeNome;
		this.cidadeEstado = cidadeEstado;
	}
	
	
	


}
