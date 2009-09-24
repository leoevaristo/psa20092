package br.com.siaic.mb.endereco;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.dao.EnderecoDAO;

/**
 * 
 * Classe que retorna os dados dascidades cadastradas no banco.
 * Para serem utilizados em um componente <h:selectOneMenu>.
 * @author Carlos Junior
 *
 */
public class SelectCidades {
	
	private static List<SelectItem> cidades = new ArrayList<SelectItem>();
	
	
	public SelectCidades() throws SQLException{		
		
		if(cidades.isEmpty())
			setCidades();
	}

	
	public  List<SelectItem> getCidades()  {
		return cidades;
	}


	public void setCidades() throws SQLException {
		
			EnderecoDAO daoCidades = new EnderecoDAO();
				
			List<Cidade> cid = 	daoCidades.getTodasCidades();
			
			
			for(Cidade cidade : cid){
				cidades.add( new SelectItem(cidade.getCidadeCodigo(),cidade.getCidadeNome()));				
				
			}
	
	}

}
