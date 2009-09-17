package br.com.siaic.businesslogic.endereco;

/**
 * 
 * @author george
 *
 */


public class Estado {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Estado [estadoNome=" + estadoNome + ", estadoSigla="
				+ estadoSigla + "]";
	}
	
	
	/**
	 * @return the estadoSigla
	 */
	public String getEstadoSigla() {
		return estadoSigla;
	}
	/**
	 * @param estadoSigla the estadoSigla to set
	 */
	public void setEstadoSigla(String estadoSigla) {
		this.estadoSigla = estadoSigla;
	}
	/**
	 * @return the estadoNome
	 */
	public String getEstadoNome() {
		return estadoNome;
	}
	/**
	 * @param estadoNome the estadoNome to set
	 */
	public void setEstadoNome(String estadoNome) {
		this.estadoNome = estadoNome;
	}
	
	
	private String estadoSigla;
	private String estadoNome;

	public Estado(String estadoSigla, String estadoNome) {
		super();
		this.estadoSigla = estadoSigla;
		this.estadoNome = estadoNome;
	}
	
}
