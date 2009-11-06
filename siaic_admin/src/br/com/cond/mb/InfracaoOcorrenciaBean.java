package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.Infracao;
import br.com.cond.dao.InfracaoDAO;
import br.com.cond.dao.ApartamentoDAO;

public class InfracaoOcorrenciaBean {
	
	private Apartamento apartamento;
	private Infracao infracao;
	private static List<SelectItem> listaApartamentos = new ArrayList<SelectItem>();
	private List<SelectItem> listaTodasAsInfracoes = new ArrayList<SelectItem>();
	
	
	
	public InfracaoOcorrenciaBean() {
	
		infracao = new Infracao();
		apartamento = new Apartamento();
		InfracaoDAO infDao = null;
		
	
		if (listaTodasAsInfracoes.isEmpty())
			infDao = new InfracaoDAO();
		
		try {
			for (Infracao infracao: infDao.getTodasAsInfracoes());
			StringBuilder label = new StringBuilder();
			label.append("Tipo: ");
			label.append(infracao.getDescricaoInfracao());
			listaTodasAsInfracoes.add( new SelectItem(infracao.getCodigoInfracao(),label.toString() ));
	}catch(Exception e)
	{} 
	
	
	
	if (listaApartamentos.isEmpty()) {
		ApartamentoDAO apartDao = new ApartamentoDAO();
		try {
			for (Apartamento ap : apartDao.getTodosOsApartamentos()) {
				StringBuilder label = new StringBuilder();
				label.append("Nº: ");
				label.append(ap.getNumero());
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



	public Infracao getInfracao() {
		return infracao;
	}



	public void setInfracao(Infracao infracao) {
		this.infracao = infracao;
	}



	public static List<SelectItem> getListaApartamentos() {
		return listaApartamentos;
	}



	public static void setListaApartamentos(List<SelectItem> listaApartamentos) {
		InfracaoOcorrenciaBean.listaApartamentos = listaApartamentos;
	}



	public List<SelectItem> getListaTodasAsInfracoes() {
		return listaTodasAsInfracoes;
	}



	public void setListaTodasAsInfracoes(List<SelectItem> listaTodasAsInfracoes) {
		this.listaTodasAsInfracoes = listaTodasAsInfracoes;
	}

	
	
	
	
	

}//class
	
	
		 
		
		
		
		
	


