package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

import br.com.cond.businesslogic.Condomino;
import br.com.cond.dao.CondominoDAO;

public class SelectCondomino {
	
	
	private static List<SelectItem> condominoLista = new ArrayList<SelectItem>();
	
	public SelectCondomino(){
		if(condominoLista.isEmpty())
			try {
				setApartamentoLista();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public List<SelectItem> getApartamentoV() {
		return condominoLista;
	}

	public void setApartamentoLista() throws SQLException {
		
		CondominoDAO dao = new CondominoDAO();
		List<Condomino> cd = dao.getTodasOsCondominos();
		
		for(Condomino condomino :cd)			
			condominoLista.add(new SelectItem(condomino.getCodigo(),condomino.getNome()));
					
	}

}
