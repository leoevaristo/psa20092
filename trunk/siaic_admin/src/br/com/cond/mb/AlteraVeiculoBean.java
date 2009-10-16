package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.faces.model.SelectItem;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.Veiculo;
import br.com.cond.dao.ApartamentoDAO;
import br.com.cond.dao.VeiculoDAO;


public class AlteraVeiculoBean {
	
	
	private Apartamento apartamento;
	
	private Veiculo veiculo;
	
	
	private static List<SelectItem> listaApartamentos = new ArrayList<SelectItem>();
	

	
	public AlteraVeiculoBean(){
		
		veiculo = new Veiculo();
		apartamento = new Apartamento();
	    
		if (listaApartamentos.isEmpty()) {
			ApartamentoDAO apartDao = new ApartamentoDAO();
			try {
				for (Apartamento ap : apartDao.getTodosOsApartamentos()) {
					StringBuilder label = new StringBuilder();
					label.append("Nº: ");
					label.append(ap.getCodigoApartamento());
					label.append(" - Andar ");
					label.append(ap.getAndar());
					label.append(" - Bloco ");
					label.append(ap.getBloco());
					listaApartamentos.add(new SelectItem(ap.getCodigoApartamento(), label.toString()));
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
		AlteraVeiculoBean.listaApartamentos = apartamentos;
	}

    public String voltar() {
    	return "voltar";
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

		//ApartamentoDAO daoApartamento = new ApartamentoDAO();
		
		//daoApartamento.adicionarApartamento(apartamento);
		
		//veiculo.setCodigoApartamento();
	
     	daoVeiculo.adicionarVeiculo(veiculo);	

     	return r;

	}
	
	public static void main(String[] args) {
		ApartamentoDAO ap = new ApartamentoDAO();
		
		Apartamento[] apartamento = new Apartamento[4]; 
		
		apartamento[0] = new Apartamento();
		apartamento[0].setCodigoApartamento(101);
		apartamento[0].setAndar(1);
		apartamento[0].setBloco("1");
		
		apartamento[1] = new Apartamento();
		apartamento[1].setCodigoApartamento(102);
		apartamento[1].setAndar(1);
		apartamento[1].setBloco("1");
		
		apartamento[2] = new Apartamento();
		apartamento[2].setCodigoApartamento(201);
		apartamento[2].setAndar(2);
		apartamento[2].setBloco("1");
		
		apartamento[3] = new Apartamento();
		apartamento[3].setCodigoApartamento(202);
		apartamento[3].setAndar(2);
		apartamento[3].setBloco("1");
		
		
		try {
			ap.adicionarApartamento(apartamento[0]);
			ap.adicionarApartamento(apartamento[1]);
			ap.adicionarApartamento(apartamento[2]);
			ap.adicionarApartamento(apartamento[3]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void limpaCampos(){
		
		//TODO
	}
	


}
