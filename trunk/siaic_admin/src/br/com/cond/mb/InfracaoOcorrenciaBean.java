package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.Infracao;
import br.com.cond.dao.InfracaoDAO;
import br.com.cond.dao.ApartamentoDAO;
import br.com.cond.dao.InfracaoOcorrenciaDAO;
import br.com.cond.dao.VeiculoDAO;

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

	
	
	
	
	 public String voltar() {
	    	return "sucesso";
	    }

		/**
		 * 
		 * @return
		 * @throws SQLException
		 */
		public String addInfracaoOcorrencia() throws SQLException {
			// TODO
			
			String r = "sucesso";

			
			InfracaoOcorrenciaDAO daoInfracaoOcorrencia = new InfracaoOcorrenciaDAO();
			
			//ApartamentoDAO daoApartamento = new ApartamentoDAO();
			
			//daoApartamento.adicionarApartamento(apartamento);
			
			//veiculo.setCodigoApartamento();

			daoInfracaoOcorrencia.adicionarInfracaoOcorrencia(null);
			
				     	
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
			
			
		}
		


	}
	

//class
	
	
		 
		
		
		
		
	


