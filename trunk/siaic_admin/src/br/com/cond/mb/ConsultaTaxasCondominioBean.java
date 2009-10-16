package br.com.cond.mb;

import java.sql.SQLException;
import java.util.List;

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

	public List<TaxasCondominio> getAll() throws SQLException{
		TaxasCondominioDAO daoTaxas = new TaxasCondominioDAO();
		return daoTaxas.getAll();
	}

}
