package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.dao.ApartamentoDAO;


public class SelectApartamento {
	
	private static List<SelectItem> apartamentoLista = new ArrayList<SelectItem>();
	
	public SelectApartamento(){
		if(apartamentoLista.isEmpty())
			try {
				setApartamentoLista();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public List<SelectItem> getApartamentoV() {
		return apartamentoLista;
	}

	public void setApartamentoLista() throws SQLException {
		
		ApartamentoDAO dao = new ApartamentoDAO();
		List<Apartamento> ap = dao.getTodosOsApartamentos();
		
		for(Apartamento apartamento :ap)			
			apartamentoLista.add(new SelectItem(apartamento.getCodigoApartamento(),apartamento.getBloco()));
					
	}

}
