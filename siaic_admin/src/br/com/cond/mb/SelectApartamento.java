package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.dao.ApartamentoDAO;


public class SelectApartamento {
	
	private static List<SelectItem> apartamentoV = new ArrayList<SelectItem>();
	
	public SelectApartamento(){
		if(apartamentoV.isEmpty())
			try {
				setApartamentoV();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public List<SelectItem> getApartamentoV() {
		return apartamentoV;
	}

	public void setApartamentoV() throws SQLException {
		
		ApartamentoDAO dao = new ApartamentoDAO();
		List<Apartamento> ap = dao.getTodosOsApartamentos();
		
		for(Apartamento apartamento :ap)			
			apartamentoV.add(new SelectItem(apartamento.getCodigoApartamento(),apartamento.getBloco()));
					
	}

}
