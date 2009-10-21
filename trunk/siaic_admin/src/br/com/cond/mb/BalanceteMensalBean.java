package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cond.businesslogic.BalanceteMensal;
import br.com.cond.businesslogic.ReceitaDespesa;
import br.com.cond.dao.GerarBalanceteMensalDAO;


public class BalanceteMensalBean {
	private String paramMes;
	private String paramAno;
	private ReceitaDespesa rctdp = new ReceitaDespesa();
	private BalanceteMensal bal = new BalanceteMensal();
	private GerarBalanceteMensalDAO dao = new GerarBalanceteMensalDAO();
	private List<ReceitaDespesa> listaReceitas = new ArrayList<ReceitaDespesa>();
	private List<ReceitaDespesa> listaDespesas = new ArrayList<ReceitaDespesa>();
	private double receitas;
	private double despesas;
	
	
	public BalanceteMensalBean(){		
		
	}
	
	public String getParamMes() {
		return paramMes;
	}

	public void setParamMes(String paramMes) {
		this.paramMes = paramMes;
	}
	
	public String getParamAno() {
		return paramAno;
	}

	public void setParamAno(String paramAno) {
		this.paramAno = paramAno;
	}

	public double getReceitas() {
		return receitas;
	}

	public void setReceitas(BalanceteMensal balancete) throws SQLException {		
		this.receitas = dao.getTotalReceitas(balancete);
	}

	public double getDespesas() {
		return despesas;
	}

	public void setDespesas(BalanceteMensal balancete) throws SQLException {
		this.despesas = dao.getTotalDespesas(balancete);
	}

	public BalanceteMensal getBal(){
		return bal;
	}
	
	public void setBal(BalanceteMensal bal) {
		this.bal = bal;
	}
	
	public List<ReceitaDespesa> getListaReceitas() {
		return listaReceitas;
	}
	
	public void setListaReceitas(BalanceteMensal balancete) throws SQLException {
		this.listaReceitas = dao.getReceitas(balancete);	
	}
	
	public List<ReceitaDespesa> getListaDespesas() {
		return listaDespesas;
	}

	public void setListaDespesas(BalanceteMensal balancete) throws SQLException {
		this.listaDespesas = dao.getDespesas(balancete);
	}	
		
	//Ação...
	
	public ReceitaDespesa getRctdp() {
		return rctdp;
	}

	public String buscar() throws SQLException{
		String s ="sucesso";
		bal.setMes(paramMes);
		bal.setAno(paramAno);
		
		setListaReceitas(bal);
		setListaDespesas(bal);	
		
		setReceitas(bal);
		setDespesas(bal);
		
		return s;
				
	}
	
	/*public void exibeDespesas(){
		
		for(ReceitaDespesa rd:listaDespesas){
			this.rctdp = rd;
			rctdp.getCodigo();
			rctdp.getTipoRD().getDescricao();
			rctdp.getTipo();
			rctdp.getData();
			rctdp.getValor();
		}
				
	}*/
	
	
}
