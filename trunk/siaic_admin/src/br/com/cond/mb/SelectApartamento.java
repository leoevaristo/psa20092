package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.dao.ApartamentoDAO;


/**
 * @author Alain Rosemberg
 *
 */
public class SelectApartamento {
	
	
	
	
	private static List<SelectItem> apartamentos = new ArrayList<SelectItem>();
	
	
	
	
	public SelectApartamento() throws SQLException{		
		
		if(apartamentos.isEmpty())			
			setApartamentos();
		
	}
	
	
	
	
	public List<SelectItem> getApartamentos()  {		
		
			return apartamentos;
	
	}
	
	
	

	public void setApartamentos() throws SQLException {
		
		ApartamentoDAO dao = new ApartamentoDAO();
		List<Apartamento> ap = dao.getTodosOsApartamentos();
				
		for(Apartamento apartamento : ap){
			apartamentos.add( new SelectItem(apartamento.getCodigoApartamento(),apartamento.getBloco()));			
		}	
	}
	
	
	
	

}
