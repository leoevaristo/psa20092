package br.siaic.mb.cond;

import java.sql.SQLException;
import br.com.siaic.businesslogic.cond.Regras;
import br.com.siaic.dao.cond.RegrasDAO;

public class CadastraCondominoBean {
	
	
   private Regras regra;
	

	
	public Regras getRegra() {
	return regra;
}


public void setRegra(Regras regra) {
	this.regra = regra;
}


	public CadastraCondominoBean(){
		
		regra = new Regras();
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
	

	

}
