package br.com.cond.mb;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.sun.el.parser.ParseException;

import br.com.cond.businesslogic.Regras;
import br.com.cond.dao.RegrasDAO;
import br.com.siaic.dao.FabricaConexao;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;



public class ConsultaRegraBean {
	
	private Connection conexao = null;
	
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
	
	
	
	private String getContextPath() { 
		  HttpSession session = (HttpSession) 
		FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
		  return session.getServletContext().getContextPath(); 
		}	
	
	
	private String getDiretorioReal(String diretorio) { 
		  HttpSession session = (HttpSession) 
		FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
		  return session.getServletContext().getRealPath(diretorio); 
		} 
	
	private void preenchePdf(JasperPrint print) throws JRException { 
	//	   Pego o caminho completo do PDF desde a raiz 
		  String saida;
		  saida = getDiretorioReal("/pdf/RelRegras.pdf"); 
		//   Exporto para PDF 
		  JasperExportManager.exportReportToPdfFile(print, saida); 
		  /* 
		  * Jogo na variável saída o nome da aplicação mais o  
		  * caminho para o PDF. Essa variável será utilizada pela view  
		  */ 
		  saida = getContextPath() + "/pdf/RelRegras.pdf"; 
		}
	

	
	public String gerar() { 
		  String jasper = getDiretorioReal("/rel/RelRegras.jasper"); 
		 
		  try { 
		    // Abro a conexão com o banco que será passada para o JasperReports 
			   FabricaConexao.getInstancia();
				 this.conexao = (Connection) FabricaConexao.conectar();

		    // Mando o jasper gerar o relatório 
		    JasperPrint print = JasperFillManager.fillReport(jasper, null, 
		conexao); 
		    // Gero o PDF 
		    preenchePdf(print);  // VEJA O MÉTODO NO CAPÍTULO 3 DO TUTORIAL 
		  } catch (Exception e) { 
		    e.printStackTrace(); 
		  } finally { 
		    try { 
		      // Sempre mando fechar a conexão, mesmo que tenha dado erro 
		      if (conexao != null) 
		        conexao.close(); 
		    } catch (SQLException e) { 
		       
		    } 
		  }
		     
		  return "exibeRelatorio"; 
		}


	
}

