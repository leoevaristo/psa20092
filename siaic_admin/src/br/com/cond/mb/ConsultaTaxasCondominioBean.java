package br.com.cond.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.TaxasCondominio;
import br.com.cond.dao.TaxasCondominioDAO;

/**
 * Managed Bean que prover opções de manipulção aos dados
 * das taxas de condomínio já existentes.
 * @author carlos
 *
 */
public class ConsultaTaxasCondominioBean {
	
	/**
	 * Propriedade que referencia a classe Apartamento
	 */
	private Apartamento apartamento;
	
	/**
	 * Propriedade que referencia a classe TaxasCondominio
	 */
	private TaxasCondominio taxasCondominio;
	
	/**
	 * Construtor da classe ConsultaCondominioBean
	 */
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
	
	/**
	 * Método que retorna uma lista de objetos do tipo TaxaCondominios  existentes no banco.
	 * @return List<TaxasCondominio>
	 * @throws SQLException
	 */
	public List<TaxasCondominio> getAll() throws SQLException{
		TaxasCondominioDAO daoTaxas = new TaxasCondominioDAO();
		return daoTaxas.getAll();
	}
	
	/**
	 * Método que retorna uma lista de objetos do tipo TaxaCondominios
	 * filtrada por apartamentos.
	 * @return List<TaxasCondominio>
	 * @throws SQLException
	 */
	public List<TaxasCondominio> getTaxasTodosApartamentos() throws SQLException{
		TaxasCondominioDAO daoTaxas = new TaxasCondominioDAO();
		return daoTaxas.getTaxasTodosApartamentos();
	}
	
	/**
	 * Método que atualiza os registros de uma taxa de condomínio.
	 */
	public void update(){
		TaxasCondominioDAO daoTaxas = new TaxasCondominioDAO();
		daoTaxas.update(taxasCondominio);
	}
	
	/**
	 * Método que simula a realização de pagamento de taxa de condomínio
	 */
	public void pagar(){
		if(getTaxasCondominio().getDataPagamento() != null){
			getTaxasCondominio().setEstaPago('S');
			TaxasCondominioDAO daoTaxas = new TaxasCondominioDAO();
			daoTaxas.setarPagamentoTaxa(taxasCondominio);
		}
		
		destroiSessao();
	}
	
	/**
	 * Método que retorna uma lista de apartamentos que estão inadimplentes
	 * @return List<TaxaCondominio>
	 */
	public List<TaxasCondominio> getInadimplentes(){
		TaxasCondominioDAO daoTaxas = new TaxasCondominioDAO();
		return daoTaxas.getInadimplentes();
	}

}
