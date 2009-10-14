package br.com.siaic.mb.endereco;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.dao.EnderecoDAO;

/**
 * Classe que retorna os dados dos bairros cadastradas no banco.
 * Para serem utilizados em um componente <h:selectOneMenu>.
 * @author carlos
 *
 */
public class SelectBairros {
	
	private static List<SelectItem> bairros = new ArrayList<SelectItem>();
	
	
	public SelectBairros() throws SQLException{		
		
		if(bairros.isEmpty())
			setBairros();
	}

	
	public  List<SelectItem> getBairros()  {
		return bairros;
	}


	public void setBairros() throws SQLException {
		
			EnderecoDAO daoBairros = new EnderecoDAO();
				
			List<Bairro> bar = 	daoBairros.getTodosBairros();
			
			
			for(Bairro bairro : bar){
				bairros.add(new SelectItem(bairro.getBairroCodigo(),bairro.getBairroNome()));				
				
			}
	
	}

}
