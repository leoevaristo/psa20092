package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cond.businesslogic.BalanceteMensal;
import br.com.cond.businesslogic.ReceitaDespesa;
import br.com.cond.dao.GerarBalanceteMensalDAO;


public class BalanceteMensalBean {
	private BalanceteMensal bal = new BalanceteMensal();
	private GerarBalanceteMensalDAO dao = new GerarBalanceteMensalDAO();
	private List<ReceitaDespesa> listaReceitas = new ArrayList<ReceitaDespesa>();
	private List<ReceitaDespesa> listaDespesas = new ArrayList<ReceitaDespesa>();
	private double receitas;
	private double despesas;
	
	
	public BalanceteMensalBean(){		
		
	}
	
	public double getReceitas() {
		return receitas;
	}

	public void setReceitas() throws SQLException {		
		this.receitas = dao.getTotalReceitas(bal);
	}

	public double getDespesas() {
		return despesas;
	}

	public void setDespesas() throws SQLException {
		this.despesas = dao.getTotalDespesas(bal);
	}

	public BalanceteMensal getBal(){
		return bal;
	}
	
	public void setBal(String mes, String ano){
		BalanceteMensal balancete = new BalanceteMensal();
		balancete.setMes(mes);
		balancete.setAno(ano);
		this.bal = balancete;		
	}	

	public List<ReceitaDespesa> getListaReceitas() {
		return listaReceitas;
	}
	
	public void setListaReceitas() throws SQLException {
		this.listaReceitas = dao.getReceitas(bal);	
	}
	
	public List<ReceitaDespesa> getListaDespesas() {
		return listaDespesas;
	}

	public void setListaDespesas() throws SQLException {
		this.listaDespesas = dao.getDespesas(bal);
	}
	
	
	//Ação...
	
	public void buscar(String mm, String aaaa) throws SQLException{
		setBal(mm, aaaa);
		
		setListaReceitas();
		setListaDespesas();		
		setReceitas();
		setDespesas();
		
		getListaReceitas();
		getListaDespesas();
		getReceitas();
		getDespesas();
		
		
		
		
		
	}
		
	
	
	

}
