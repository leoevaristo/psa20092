package br.com.cond.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.cond.businesslogic.ReceitaDespesa;
import br.com.cond.dao.ReceitasDespesasDAO;

public class ListaReceitasDespesasBean {
	private static List<SelectItem> listaReceitasDespesas = new ArrayList<SelectItem>();
	
	public ListaReceitasDespesasBean() throws Exception {
		if(listaReceitasDespesas.isEmpty())
			setReceitasDespesas();
	}
	
	public void setReceitasDespesas() throws Exception {
		
		ReceitasDespesasDAO rdd = new ReceitasDespesasDAO();
		List<ReceitaDespesa> rd = rdd.listaTudo();
			
		for(ReceitaDespesa recdep : rd){
			listaReceitasDespesas.add(new SelectItem(recdep.getCodigo(),
					recdep.getTipoRD().getDescricao(),
					recdep.getTipo()));
		}
	}
	
	public List<SelectItem> getReceitasDespesas()  {		
		
		return listaReceitasDespesas;

	}
}
