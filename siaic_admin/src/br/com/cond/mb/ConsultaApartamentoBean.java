package br.com.cond.mb;


import java.sql.SQLException;
import java.util.List;


import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;


import br.com.cond.businesslogic.Apartamento;
import br.com.cond.dao.ApartamentoDAO;
import br.com.siaic.dao.FabricaConexao;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;




public class ConsultaApartamentoBean {
	
	private Connection conexao = null;
	
	private Apartamento apartamento;
		
	private String tipoPesquisa;
	
	private String campoPesquisa;
	

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


	
	public ConsultaApartamentoBean(){
		apartamento = new Apartamento();
	}
	
		
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Apartamento> getTodosOsApartamentos() throws SQLException {

		ApartamentoDAO dao = new ApartamentoDAO();
		
		return dao.getTodosOsApartamentos();

	}
	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String ApartamentoId() throws SQLException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idApartamento = new Integer(req.getParameter("codigoApartamento")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		ApartamentoDAO dao = new ApartamentoDAO();
		setApartamento(dao.getApartamentoId(idApartamento));
		
		return "modifica";

	}
	

	

	/**
	 * 
	 * @throws SQLException
	 */
	public void excluiApartamento() throws SQLException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer idApartamento = new Integer(req.getParameter("codigoApartamento"))
				.intValue();

		ApartamentoDAO dao = new ApartamentoDAO();
		dao.removerApartamento(idApartamento);

	}
	
	
	public String exibeDetalhesApartamento() throws SQLException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idApartamento = new Integer(req.getParameter("codigoApartamento")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		ApartamentoDAO dao = new ApartamentoDAO();
		setApartamento(dao.getApartamentoId(idApartamento));	
		//getEnderecoUsuario();
		
		
		return "detalhes";
	}
	
	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String updateApartamento() throws SQLException {
		// TODO
		
		String r = "sucesso";
		
		ApartamentoDAO daoApartamento = new ApartamentoDAO();
		
		daoApartamento.alterarApartamento(apartamento);
		
		destroiSessao();

		return r;

	}

	
	public String escolheTipoPesquisa() throws SQLException{
		if(tipoPesquisa.equals("bloco")){
			
			getApartamentoPorAndar();
		} 
		return campoPesquisa;
}
	
	public List<Apartamento> getApartamentoPorAndar() throws SQLException {
		
		ApartamentoDAO daoApartamento = new ApartamentoDAO();
		
		return daoApartamento.getApartamentoPorBloco(campoPesquisa);
		
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

