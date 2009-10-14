package br.com.cond.mb;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Statement;
import br.com.cond.businesslogic.Regras;
import br.com.cond.dao.RegrasDAO;
import br.com.siaic.dao.FabricaConexao;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;



public class ConsultaRegraBean {
	
	private java.sql.Connection conexao = null;
	
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
		try
			{
				FabricaConexao.getInstancia();
				this.conexao = FabricaConexao.conectar();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
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
	
	
	
//    private String getContextPath() { 
//		  HttpSession session = (HttpSession) 
//		FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
//		  return session.getServletContext().getContextPath(); 
//		}	
//	
//	
	private String getDiretorioReal(String diretorio) { 
		  HttpSession session = (HttpSession) 
		FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
		  return session.getServletContext().getRealPath(diretorio); 
		} 
////	
////	private void preenchePdf(JasperPrint print) throws JRException { 
////	//	   Pego o caminho completo do PDF desde a raiz 
////		  String saida;
////		  saida = getDiretorioReal("/pdf/RelRegras.pdf"); 
////		//   Exporto para PDF 
////		  JasperExportManager.exportReportToPdfFile(print, saida); 
////		  /* 
////		  * Jogo na vari�vel sa�da o nome da aplica��o mais o  
////		  * caminho para o PDF. Essa vari�vel ser� utilizada pela view  
////		  */ 
////		  saida = getContextPath() + "/pdf/RelRegras.pdf"; 
////		}
////	
////
////	
////	public String gerar() { 
////		  String jasper = getDiretorioReal("/rel/RelRegras.jasper"); 
////		 
////		  try { 
////		    // Abro a conex�o com o banco que ser� passada para o JasperReports 
////			   FabricaConexao.getInstancia();
////				 this.conexao = (Connection) FabricaConexao.conectar();
////
////		    // Mando o jasper gerar o relat�rio 
////		    JasperPrint print = JasperFillManager.fillReport(jasper, null, 
////		conexao); 
////		    // Gero o PDF 
////		    preenchePdf(print);  // VEJA O M�TODO NO CAP�TULO 3 DO TUTORIAL 
////		  } catch (Exception e) { 
////		    e.printStackTrace(); 
////		  } finally { 
////		    try { 
////		      // Sempre mando fechar a conex�o, mesmo que tenha dado erro 
////		      if (conexao != null) 
////		        conexao.close(); 
////		    } catch (SQLException e) { 
////		       
////		    } 
////		  }
////		     
////		  return "exibeRelatorio"; 
////		}
////
////
//	
//	public void geraRelatorio() throws JRException, Exception 
//	 { 
//	   Statement stm = (Statement) conexao.createStatement(); 
//	   String query = "select * from admcon_regras"; 
//	   ResultSet rs =  stm.executeQuery( query ); 
//	 
//	/* implementação da interface JRDataSource para DataSource ResultSet */ 
//	   JRResultSetDataSource jrRS = new JRResultSetDataSource( rs ); 
//	    
//	   /* HashMap de parametros utilizados no relatório. Sempre instanciados */ 
//	   Map parameters = new HashMap(); 
//	   // parameters.put("COLUNA", valor); 
//	   
//	   String s = getDiretorioReal("rel/RelRegras.jasper");
//	    
//	   /* Preenche o relatório com os dados. Gera o arquivo BibliotecaPessoal.jrprint */ 
//	   JasperFillManager.fillReportToFile(s, parameters, jrRS ); 
//	 
//	 
//	    /* Exporta para o formato PDF */ 
//	    JasperExportManager.exportReportToPdfFile("/pdf/RelRegras.jrprint" ); 
//	 
//	   /* Preenche o relatorio e o salva diretamente em arquivo PDF. Sem 
//	       a necessidade do .jrprint */ 
//	   // JasperRunManager.runReportToPdfFile("BibliotecaPessoal.jasper", parameters, jrRS); 
//	    
//	   /* Visualiza o relatório em formato PDF */ 
//	   JasperViewer.viewReport("/pdf/RelRegras.pdf", false );    
//	 } 
//	  
////	   public static void main(String[] args) throws JRException, Exception 
////	   { 
////	     new RelatorioBibliotecaPessoal().geraRelatorio(); 
////	   } 
//	 
	
	public void impAviso() throws JRException, SQLException, IOException {
		/*FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer cod = new Integer(req.getParameter("codigoEntrada")).intValue();

		Map parameters = new HashMap();
		parameters.put("cod", new Integer(cod));*/

		String rel = getDiretorioReal("rel/RelRegras.jasper");
		JasperPrint print = JasperFillManager.fillReport(rel, parameters, FabricaConexao.getInstancia().conectar());
		byte[] bytes = JasperExportManager.exportReportToPdf(print);

		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		/***********************************************************************
		 * Pour afficher une bo�te de dialogue pour enregistrer le fichier sous
		 * le nom rapport.pdf
		 **********************************************************************/
		response.addHeader("Content-disposition",
				"attachment;filename=reporte.pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();

	}

	
	} 
	


