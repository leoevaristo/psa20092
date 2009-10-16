package br.com.cond.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import br.com.cond.businesslogic.ReceitaDespesaTipos;
import br.com.cond.dao.ReceitasDespesasTiposDAO;


/********************************************************************************
 * Title:       SIAIC - Sistema de Administração de Imobiliárias e Condomínion
 * Description: Módulo Condomínios
 * Copyright:   Copyright (c) 2009                                            
 * Company:     Faculdade IDEZ
 * Data:        14/10/2009
 * @author:     Kleiton Vasconcelos Costa
 * @version:    1.0
 ********************************************************************************
 */
public class ListaTiposReceitasDespesasBean {
	
private static List<SelectItem> receitasdespesas = new ArrayList<SelectItem>();
	
	public ListaTiposReceitasDespesasBean() throws Exception {
		
		if(receitasdespesas.isEmpty())			
			setReceitasDespesas();
		
	}
	
	
	public List<SelectItem> getReceitasDespesas()  {		
		
			return receitasdespesas;
	
	}

	public void setReceitasDespesas() throws Exception {
		
		ReceitasDespesasTiposDAO rdtd = new ReceitasDespesasTiposDAO();
		List<ReceitaDespesaTipos> rdt = rdtd.listaTipos();
				
		for(ReceitaDespesaTipos receitasdespesa : rdt){
			receitasdespesas.add( new SelectItem(receitasdespesa.getCodigo(),receitasdespesa.getDescricao()));			
		}	
	}
}
