package br.siaic.mb.cond;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.cond.Regras;
import br.com.siaic.dao.cond.RegrasDAO;

public class ConsultaRegraBean {
	
	private Regras regra;
	
	
	public ConsultaRegraBean(){
		regra = new Regras();
	}
	
	
	
	
	public Regras getRegra() {
		return regra;
	}




	public void setRegra(Regras regra) {
		this.regra = regra;
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
		Integer regraCodigo = new Integer(req.getParameter("codigoRegra")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		RegrasDAO dao = new RegrasDAO();
		setRegra(dao.getRegraId(regraCodigo));
	
		
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
		Integer regraCodigo = new Integer(req.getParameter("codigoPessoa")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		RegrasDAO dao = new RegrasDAO();
		setRegra(dao.getRegraId(regraCodigo));	
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
