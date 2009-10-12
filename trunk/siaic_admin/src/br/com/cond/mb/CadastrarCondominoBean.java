package br.com.cond.mb;

import br.com.cond.businesslogic.Condomino;

/**
 * 
 * @author Robson
 *
 */

public class CadastrarCondominoBean {
	private Condomino condomino;
	
	public CadastrarCondominoBean() {
		condomino = new Condomino();
	}

	public Condomino getCondomino() {
		return condomino;
	}

	public void setCondomino(Condomino condomino) {
		this.condomino = condomino;
	}
}
