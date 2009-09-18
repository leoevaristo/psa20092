package br.com.siaic.mb;

import br.com.siaic.businesslogic.Perfil;

/**
 * 
 * @author Yasmim Tamie Hiramoto Pereira
 * @version 1.0
 *  
 */

public class PerfilBean {
	private Perfil perfil;
	
	public void perfilBean(){
		perfil = new Perfil(); 
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}
