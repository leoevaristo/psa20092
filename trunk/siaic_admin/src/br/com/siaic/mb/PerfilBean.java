package br.com.siaic.mb;

import java.sql.SQLException;

import br.com.siaic.businesslogic.Perfil;
import br.com.siaic.dao.ImovelCaracteristicaDAO;
import br.com.siaic.dao.PerfilDAO;

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
	public String addPerfil() throws SQLException{
		String s = "";
		PerfilDAO daoP = new PerfilDAO();
		daoP.addPerfil(perfil);
		
		return s;
	}
}
