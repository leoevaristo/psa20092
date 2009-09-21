package br.com.siaic.mb.endereco;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.businesslogic.endereco.Estado;


/**
 * Classe que retorna os dados dos estados cadastrados no banco.
 * Para serem utilizados em um componente <h:selectOneMenu>.
 * @author Carlos Junior
 *
 */
public class SelectEstados {
	
	
	
	
	private static List<SelectItem> estados = new ArrayList<SelectItem>();
	
	
	
	
	public SelectEstados() throws SQLException{		
		
		if(estados.isEmpty())			
			setEstados();
		
	}
	
	
	
	
	public List<SelectItem> getEstados() throws SQLException {		
		
			return estados;
	
	}
	
	
	

	public void setEstados() throws SQLException {
		
		EnderecoDAO dao = new EnderecoDAO();
		List<Estado> est = dao.getTodosEstados();
				
		for(Estado estado : est){
			estados.add( new SelectItem(estado.getEstadoSigla(),estado.getEstadoNome()));			
		}	
	}
	
	
	
	

}
