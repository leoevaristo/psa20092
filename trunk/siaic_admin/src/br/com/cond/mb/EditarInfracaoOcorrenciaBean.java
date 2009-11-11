package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.Infracao;
import br.com.cond.businesslogic.Veiculo;
import br.com.cond.dao.ApartamentoDAO;
import br.com.cond.dao.InfracaoDAO;
import br.com.cond.dao.InfracaoOcorrenciaDAO;
import br.com.cond.dao.VeiculoDAO;

public class EditarInfracaoOcorrenciaBean {
	
	
	private Infracao infracao;
	private Apartamento apartamento;
	
	private static List<SelectItem> listaApartamentos = new ArrayList<SelectItem>();
	private static  List<SelectItem> listaTodasAsInfracoes = new ArrayList<SelectItem>();
	

	 private static int codigoGambiMasterFii;
	 
	 public EditarInfracaoOcorrenciaBean(){
		 
		 			
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

	public Infracao getInfracao() {
		return infracao;
	}

	public void setInfracao(Infracao infracao) {
		this.infracao = infracao;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}
	 

	public List<SelectItem> getListaTodasAsInfracoes(){
		return listaTodasAsInfracoes;
	
	}
		
		public void setInfracao(List<SelectItem> infracao) {
			EditarInfracaoOcorrenciaBean.listaTodasAsInfracoes = infracao;
		
	}
	
	public List<SelectItem> getApartamento1() {
		return listaApartamentos;
	}



	public static void setApartamentos(List<SelectItem> apartamentos) {
		EditarInfracaoOcorrenciaBean.listaApartamentos = apartamentos;
	}
    public String voltar() {
    	return "sucesso";
    }

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String alteraInfracaoOcorrencia() throws SQLException {
		InfracaoOcorrenciaDAO infOcorrenciadao = new InfracaoOcorrenciaDAO();
		infOcorrenciadao.alterarInfracaoOcorrencia(infracaoOcorrencia);
		
		return "sucesso";
	

}

}
