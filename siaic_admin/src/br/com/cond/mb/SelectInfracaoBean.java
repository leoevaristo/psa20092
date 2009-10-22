package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.Infracao;
import br.com.cond.dao.ApartamentoDAO;
import br.com.cond.dao.InfracaoDAO;

public class SelectInfracaoBean {
	
private static List<SelectItem> infracoes = new ArrayList<SelectItem>();
	
	
		
	public SelectInfracaoBean() throws SQLException{		
		
		if(infracoes.isEmpty())			
			setInfracoes();
		
	}
	
	
	

	public List<SelectItem> getInfracoes()  {		
		
			return infracoes;
	
	}
	
	
	

	public void setInfracoes() throws SQLException {
		
		InfracaoDAO dao = new InfracaoDAO();
		List<Infracao> inf = dao.getTodasAsInfracoes();
				
		for(Infracao infracao : inf){
						
			infracoes.add(new SelectItem(infracao.getCodigoInfracao(),infracao.getDescricaoInfracao()));
		}	
	}
	
	
	
	

}



