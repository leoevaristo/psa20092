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
	public String getCidadeEstado() {
		return cidadeEstado;
	}
	/**
	 * @param cidadeEstado the cidadeEstado to set
	 */
	public void setCidadeEstado(String cidadeEstado) {
		this.cidadeEstado = cidadeEstado;
	}
	
	
	
	
	private int cidadeCodigo;
	private String cidadeNome;
	private String cidadeEstado;

	
	public Cidade(int cidadeCodigo, String cidadeNome, String cidadeEstado) {
		
		this.cidadeCodigo = cidadeCodigo;
		this.cidadeNome = cidadeNome;
		this.cidadeEstado = cidadeEstado;
	}

	public Cidade() {
	
	}
	
	
	


}
