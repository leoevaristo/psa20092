package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.dao.UsuarioDAO;

public class SelectCorretores {
	
	private static List<SelectItem> corretores = new ArrayList<SelectItem>();
	
	public SelectCorretores(){
		if(corretores.isEmpty())
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

}
