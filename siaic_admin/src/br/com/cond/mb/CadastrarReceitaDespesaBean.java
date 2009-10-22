package br.com.cond.mb;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

import br.com.cond.businesslogic.ReceitaDespesa;
import br.com.cond.businesslogic.ReceitaDespesaTipos;
import br.com.cond.dao.ReceitasDespesasDAO;
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
public class CadastrarReceitaDespesaBean {
	
	private ReceitaDespesa rd = null;
	private ReceitaDespesaTipos rdt = null;
	
	public ReceitaDespesaTipos getRdt() {
		return rdt;
	}

	public void setRdt(ReceitaDespesaTipos rdt) {
		this.rdt = rdt;
	}

	public CadastrarReceitaDespesaBean() {
		this.rd = new ReceitaDespesa();
	}

	public ReceitaDespesa getRd() {
		return rd;
	}

	public void setRd(ReceitaDespesa rd) {
		this.rd = rd;
	}
	
	
	/**
	 * Adiciona uma Receita / Despesa
	 */
	
	public String adicionarReceitaDespesa() throws Exception {
		String resposta = "sucesso";
		
		ReceitasDespesasDAO rdc = new ReceitasDespesasDAO();
		rdc.inserirReceitaDespesa(this.rd);
		
		return resposta;
	}
	
	/**
	 * Exibe em outra tela a receita/despesa
	 * cadastrada
	 * @throws Exception
	 */
	
	public void exibeReceitaDespesaCadastrada() throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer idReceitaDespesa = new Integer(req.getParameter("codigoReceitaDespesa"))
				.intValue();

		ReceitasDespesasDAO rdd = new ReceitasDespesasDAO();
		this.rd = rdd.consultaReceitaDespesaPorCodigo(idReceitaDespesa);
	}
	
	public void limpar() throws Exception {
		this.rd = new ReceitaDespesa();
		new ListaTiposReceitasDespesasBean().getReceitasDespesas().clear();
	}
}
