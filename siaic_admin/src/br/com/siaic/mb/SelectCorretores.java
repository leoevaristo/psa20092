package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.dao.UsuarioDAO;

/**
 * Classe para manipular os dados de corretores na view
 * @author carlos
 *
 */
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
	
	/**
	 * Retorna uma lista de SelectItem contendo dados de corretores
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getCorretores() {
		return corretores;
	}

	/**
	 * Busca os dados de corretores no banco de dados
	 * e seta-os numa lista de SelectItem
	 * @throws SQLException
	 */
	public void setCorretores() throws SQLException {
		
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usu = dao.getTodosCorretores();
		
		for(Usuario usuario : usu)			
			corretores.add(new SelectItem(usuario.getCodigoPessoa(),usuario.getNome()));
					
	}

}
