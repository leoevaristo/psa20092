package br.com.cond.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.TaxasCondominio;
import br.com.cond.dao.ApartamentoDAO;
import br.com.cond.dao.TaxasCondominioDAO;

public class TaxasCondominioBean {
	
	private Apartamento apartamento;
	
	private TaxasCondominio taxasCondominio;
	
	public TaxasCondominioBean(){
		apartamento = new Apartamento();
		taxasCondominio = new TaxasCondominio();
	}
	
	
	public Apartamento getApartamento() {
		return apartamento;
	}


	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}


	public TaxasCondominio getTaxasCondominio() {
		return taxasCondominio;
	}


	public void setTaxasCondominio(TaxasCondominio taxasCondominio) {
		this.taxasCondominio = taxasCondominio;
	}


	public String cadastrar() throws SQLException{
		ApartamentoDAO daoApartamento = new ApartamentoDAO();
		TaxasCondominioDAO daoTaxas = new TaxasCondominioDAO();
		List<Apartamento> todosApartamentos = daoApartamento.getTodosOsApartamentos();
		
		for(Apartamento ap : todosApartamentos){
			getTaxasCondominio().setApartamento(ap);
			if(daoTaxas.addTaxaCondominio(this.taxasCondominio) == false){
				FacesContext ctx = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage("As taxas para esse mês já foram geradas.");
				ctx.addMessage("formTaxa", msg);
			}else{
				FacesContext ctx = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage("Taxas cadastradas com sucesso.");
				ctx.addMessage("formTaxa", msg);				
			}
		}
		
		return "sucesso";
		
	}
	
	public String alterar(){
		return null;
		
	}
	
	public String setarPagamento(){
		TaxasCondominioDAO daoTaxas = new TaxasCondominioDAO();
		if(daoTaxas.setarPagamentoTaxa(taxasCondominio)){
			return "sucesso";
		}		
		
		return "falha";		
	}
	
	


	

}
