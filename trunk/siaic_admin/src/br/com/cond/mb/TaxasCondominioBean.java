	package br.com.cond.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.TaxasCondominio;
import br.com.cond.dao.ApartamentoDAO;
import br.com.cond.dao.TaxasCondominioDAO;

/**
 * Managed Bean responsável por registrar as taxas de condomínio.
 * @author carlos
 *
 */
public class TaxasCondominioBean {
	
	/**
	 * Propriedade que referencia a classe Apartamento
	 */
	private Apartamento apartamento;
	
	/**
	 * Propriedade que referencia a classe TaxasCondominio
	 */
	private TaxasCondominio taxasCondominio;
	
	/**
	 * Construtor da classe TaxasCondominioBean
	 */
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

	/**
	 * Método que grava os dados das taxas de condomínio no banco de dados
	 * @return String
	 * @throws SQLException
	 */
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
		destroiSessao();
		limparForm();
		return "sucesso";
		
	}
	
	/**
	 * Método que destrói os objetos dessa classe no SessionMap
	 */
	public void destroiSessao(){		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove("taxasCondominioBean");		
	}
	
	public void limparForm(){
		apartamento = new Apartamento();
		taxasCondominio = new TaxasCondominio();
	}
	
	


	

}
