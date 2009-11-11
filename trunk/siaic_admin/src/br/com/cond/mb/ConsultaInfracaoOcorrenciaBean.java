package br.com.cond.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.Infracao;
import br.com.cond.businesslogic.InfracaoOcorrencia;
import br.com.cond.businesslogic.Veiculo;
import br.com.cond.dao.InfracaoOcorrenciaDAO;
import br.com.cond.dao.VeiculoDAO;


import com.mysql.jdbc.Connection;

public class ConsultaInfracaoOcorrenciaBean {

private Connection conexao = null;
	
	private InfracaoOcorrencia infracaoOcorrencia;
	
	private Infracao infracao;
	
	private Apartamento apartamento;
		
    private String tipoPesquisa;
	
	private String campoPesquisa;

	public InfracaoOcorrencia getInfracaoOcorrencia() {
		return infracaoOcorrencia;
	}

	public void setInfracaoOcorrencia(InfracaoOcorrencia infracaoOcorrencia) {
		this.infracaoOcorrencia = infracaoOcorrencia;
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

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getCampoPesquisa() {
		return campoPesquisa;
	}

	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}
	
	public ConsultaInfracaoOcorrenciaBean(){
		
		infracaoOcorrencia = new InfracaoOcorrencia();
		infracao = new Infracao();
		apartamento = new Apartamento();
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<InfracaoOcorrencia> getTodasAsInfracoesOcorrencia() throws SQLException {

		InfracaoOcorrenciaDAO dao = new InfracaoOcorrenciaDAO();
		return dao.getInfracaoOcorrenciaPorApartamento(0);
		
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String InfracaoOcorrenciaId() throws SQLException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idInfracaoOcorrencia = new Integer(req.getParameter("codigoInfracaoOcorrencia")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		InfracaoOcorrenciaDAO dao = new InfracaoOcorrenciaDAO();
		setInfracaoOcorrencia(dao.getInfracaoOcorrenciaPorCodigo(idInfracaoOcorrencia));
		getApartamento();
		
		
		return "modifica";

	}
}


