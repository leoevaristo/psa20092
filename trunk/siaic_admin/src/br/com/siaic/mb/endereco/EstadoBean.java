package br.com.siaic.mb.endereco;

/**
 * @author george
 */

import br.com.siaic.businesslogic.endereco.Estado;

public class EstadoBean {
	
	
	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	private Estado estado;

	public EstadoBean(Estado estado) {
		this.estado = estado;
	}
	
	
	
	

}
