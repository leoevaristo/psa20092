package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.siaic.businesslogic.Perfil;
import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.dao.PerfilDAO;
import br.com.siaic.dao.UsuarioDAO;

/**
 * 
 * @author Yasmim Tamie Hiramoto Pereira
 * @version 1.0
 *  
 */

public class PerfilBean {
	private Perfil perfil;
	
	private static List<SelectItem> corretores = new ArrayList<SelectItem>();
	
	public void perfilBean() {
		perfil = new Perfil();

		try {
			setCorretores();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<SelectItem> getCorretores() {
		return corretores;
	}
	
	public void setCorretores() throws SQLException {
		
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usu = dao.getTodosCorretores();
		
		for(Usuario usuario : usu)			
			corretores.add(new SelectItem(usuario.getCodigoPessoa(),usuario.getNome()));
					
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
