package br.com.cond.mb;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.Infracao;
import br.com.cond.dao.ApartamentoDAO;
import br.com.cond.dao.InfracaoDAO;

public class ConsultaInfracaoBean {
	
	private Connection conexao = null;
	
	private Infracao infracao;
	
	private String tipopesquisa;
	
	private String campopesquisa;

	public Infracao getInfracao() {
		return infracao;
	}

	public void setInfracao(Infracao infracao) {
		this.infracao = infracao;
	}

	public String getTipopesquisa() {
		return tipopesquisa;
	}

	public void setTipopesquisa(String tipopesquisa) {
		this.tipopesquisa = tipopesquisa;
	}

	public String getCampopesquisa() {
		return campopesquisa;
	}

	public void setCampopesquisa(String campopesquisa) {
		this.campopesquisa = campopesquisa;
	}

	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Infracao> getTodasAsInfracoes() throws SQLException {

		InfracaoDAO dao = new InfracaoDAO();
		return dao.getTodasAsInfracoes();
		
		
	}

	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String InfracaoId() throws SQLException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idInfracao = new Integer(req.getParameter("codigoInfracao")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		InfracaoDAO dao = new InfracaoDAO();
		setInfracao(dao.getInfracaoId(idInfracao));
		
		return "modifica";
		
		
	}
	
	
	/**
	 * 
	 * @throws SQLException
	 */
	public void excluiInfracao() throws SQLException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer idInfracao = new Integer(req.getParameter("codigoInfracao"))
				.intValue();

		InfracaoDAO dao =  new InfracaoDAO();
		dao.removerInfracao(idInfracao);
		
		
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String updateInfracao() throws SQLException {
		// TODO
		
		String r = "sucesso";
		
		InfracaoDAO daoInfracao = new InfracaoDAO();
		
		daoInfracao.alterarInfracao(infracao);
		
		
		destroiSessao();

		return r;

	}

	public String escolheTipoPesquisa() throws SQLException{
		if(tipopesquisa.equals("Codigo")){
			
			getInfracaoPorCodigo();
		} 
		return campopesquisa;
}
	
	public List<Infracao> getInfracaoPorCodigo() throws SQLException {

		InfracaoDAO daoInfracao = new InfracaoDAO();
		return null;
		//return daoInfracao.getInfracaoId(campopesquisa);
		
		
	}

	
	
	/**
	 * 
	 * @return
	 */
	public String destroiSessao(){
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove("regraBean");
		
		return "destruido";
	}
	
	



	
}

