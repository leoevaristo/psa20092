package br.com.cond.mb;

import java.sql.SQLException;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.dao.ApartamentoDAO;


public class CadastraApartamentoBean {
	

	private Apartamento apartamento;
	
	
	
	
	public CadastraApartamentoBean(){
		
		apartamento = new Apartamento();

	}
	
	
	
	
	public Apartamento getApartamento() {
		return apartamento;
	}




	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}




	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String addApartamento() throws SQLException {
		// TODO
		
		String r = "sucesso";

		ApartamentoDAO daoApartamento = new ApartamentoDAO();
    	daoApartamento.adicionarApartamento(apartamento);	

    	return r;

	}
	
	

	
	public void limpaCampos(){
		
		//TODO
	}
	

}
