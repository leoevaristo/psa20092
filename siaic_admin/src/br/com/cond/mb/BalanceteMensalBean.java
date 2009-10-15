package br.com.cond.mb;

import java.sql.SQLException;
import java.util.List;

import br.com.cond.businesslogic.BalanceteMensal;
import br.com.cond.businesslogic.ReceitaDespesa;
import br.com.cond.dao.GerarBalanceteMensalDAO;


public class BalanceteMensalBean {
	private ReceitaDespesa recDesp;
	private BalanceteMensal bal;
	
	

	public BalanceteMensal getBal() {
		return bal;
	}

	public void setBal(BalanceteMensal bal) {
		this.bal = bal;
	}

	public ReceitaDespesa getRecDesp() {
		return recDesp;
	}

	public void setRec_desp(ReceitaDespesa recDesp) {
		this.recDesp = recDesp;
	}
	
	
	public List<ReceitaDespesa> getReceitasDespesas() throws SQLException {

		GerarBalanceteMensalDAO dao = new GerarBalanceteMensalDAO();
		
		return dao.getReceitasDespesas(bal);

	}
	
	public List<ReceitaDespesa> getTotalReceitaDespesa() throws SQLException {

		GerarBalanceteMensalDAO dao = new GerarBalanceteMensalDAO();
		
		return dao.getTotalReceitasDespesas(bal);

	}

}
