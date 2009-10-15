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
	
	
	private static List<SelectItem> listaApartamentos = new ArrayList<SelectItem>();
	

	
	public CadastraVeiculoBean(){
		
		veiculo = new Veiculo();
		apartamento = new Apartamento();
	    
		if (listaApartamentos.isEmpty()) {
			ApartamentoDAO apartDao = new ApartamentoDAO();
			try {
				for (Apartamento ap : apartDao.getTodosOsApartamentos()) {
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
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



	public List<SelectItem> getApartamentos() {
		return listaApartamentos;
	}



	public static void setApartamentos(List<SelectItem> apartamentos) {
		CadastraVeiculoBean.listaApartamentos = apartamentos;
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
