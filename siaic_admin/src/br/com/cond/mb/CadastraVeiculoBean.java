package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.faces.model.SelectItem;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.Veiculo;
import br.com.cond.dao.ApartamentoDAO;
import br.com.cond.dao.VeiculoDAO;


public class CadastraVeiculoBean {
	
	
	private Apartamento apartamento;
	
	private Veiculo veiculo;
	
	
	private static List<SelectItem> apartamentos = new ArrayList<SelectItem>();
	

	
	public CadastraVeiculoBean(){
		
		veiculo = new Veiculo();
		apartamento = new Apartamento();
	
	
	}
	
	
	
	public Apartamento getApartamento() {
		return apartamento;
	}



	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}



	public Veiculo getVeiculo() {
		return veiculo;
	}



	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}



	public static List<SelectItem> getApartamentos() {
		return apartamentos;
	}



	public static void setApartamentos(List<SelectItem> apartamentos) {
		CadastraVeiculoBean.apartamentos = apartamentos;
	}



	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String addVeiculo() throws SQLException {
		// TODO
		
		String r = "sucesso";

		VeiculoDAO daoVeiculo = new VeiculoDAO();

		ApartamentoDAO daoApartamento = new ApartamentoDAO();
		
		daoApartamento.adicionarApartamento(apartamento);
		
		veiculo.setCodigoApartamento(apartamento.getCodigoApartamento());
	
     	daoVeiculo.adicionarVeiculo(veiculo);	

     	return r;

	}
	
	

	
	public void limpaCampos(){
		
		//TODO
	}
	


}
