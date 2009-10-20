package br.com.cond.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.TaxasCondominio;
import br.com.cond.dao.TaxasCondominioDAO;

public class ConsultaTaxasCondominioBean {
	
	private Apartamento apartamento;
	private TaxasCondominio taxasCondominio;
	
	
	public ConsultaTaxasCondominioBean(){
		
		
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
	
	public void destroiSessao(){		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove("consultaTaxasCondominioBean");		
	}

	public List<TaxasCondominio> getAll() throws SQLException{
		TaxasCondominioDAO daoTaxas = new TaxasCondominioDAO();
		return daoTaxas.getAll();
	}
	
	public List<TaxasCondominio> getTaxasTodosApartamentos() throws SQLException{
		TaxasCondominioDAO daoTaxas = new TaxasCondominioDAO();
		return daoTaxas.getTaxasTodosApartamentos();
	}
	
	public void update(){
		TaxasCondominioDAO daoTaxas = new TaxasCondominioDAO();
		daoTaxas.update(taxasCondominio);
	}
	
	public void pagar(){
		if(getTaxasCondominio().getDataPagamento() != null){
			getTaxasCondominio().setEstaPago('S');
			TaxasCondominioDAO daoTaxas = new TaxasCondominioDAO();
			daoTaxas.setarPagamentoTaxa(taxasCondominio);
		}
		
		destroiSessao();
	}
	
	public List<TaxasCondominio> getInadimplentes(){
		TaxasCondominioDAO daoTaxas = new TaxasCondominioDAO();
		return daoTaxas.getInadimplentes();
	}

}
