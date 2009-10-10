package br.com.cond.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.cond.businesslogic.Regras;
import br.com.cond.dao.RegrasDAO;

public class ConsultaRegraBean {
	
	private Regras regra;
	
	private String tipoPesquisa;
	
	private String campoPesquisa;
	


	public Regras getRegra() {
		return regra;
	}


	public void setRegra(Regras regra) {
		this.regra = regra;
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


	
	public ConsultaRegraBean(){
		regra = new Regras();
	}
	
		
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Regras> getTodasAsRegras() throws SQLException {

		RegrasDAO dao = new RegrasDAO();
		
		return dao.getTodasAsRegras();

	}
	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String RegraId() throws SQLException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idRegra = new Integer(req.getParameter("codigoRegra")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		RegrasDAO dao = new RegrasDAO();
		setRegra(dao.getRegraId(idRegra));
		
		return "modifica";

	}
	

	

	/**
	 * 
	 * @throws SQLException
	 */
	public void excluiRegra() throws SQLException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer idRegra = new Integer(req.getParameter("codigoRegra"))
				.intValue();

		RegrasDAO dao = new RegrasDAO();
		dao.removerRegra(idRegra);

	}
	
	
	public String exibeDetalhesRegras() throws SQLException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idRegra = new Integer(req.getParameter("codigoRegra")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		RegrasDAO dao = new RegrasDAO();
		setRegra(dao.getRegraId(idRegra));	
		//getEnderecoUsuario();
		
		
		return "detalhes";
	}
	
	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String updateRegra() throws SQLException {
		// TODO
		
		String r = "sucesso";
		
		RegrasDAO daoRegra = new RegrasDAO();
		
		daoRegra.alterarRegras(regra);
		
		destroiSessao();

		return r;

	}

	
	public String escolheTipoPesquisa() throws SQLException{
		if(tipoPesquisa.equals("regra")){
			
			getRegraPorRegra();
		} 
		return campoPesquisa;
}
	
	public List<Regras> getRegraPorRegra() throws SQLException {
		
		RegrasDAO daoRegra = new RegrasDAO();
		
		return daoRegra.getRegraPelaRegra(campoPesquisa);
		
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