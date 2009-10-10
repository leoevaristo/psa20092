package br.com.cond.mb;

import java.sql.SQLException;

import br.com.cond.businesslogic.Regras;
import br.com.cond.dao.RegrasDAO;


public class CadastraRegrasBean {
	
	
	private Regras regra;
	
	
	public CadastraRegrasBean(){
		
		regra = new Regras();
	}
	
	
	public Regras getRegra() {
		return regra;
	}


	public void setRegra(Regras regra) {
		this.regra = regra;
	}


	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String addRegras() throws SQLException {
		// TODO
		
		String r = "sucesso";

		RegrasDAO daoRegra = new RegrasDAO();

		daoRegra.adicionarRegras(regra);	

		return r;

	}
	
	
	
	
	public void limpaCampos(){
		
		//TODO
	}
	

}
