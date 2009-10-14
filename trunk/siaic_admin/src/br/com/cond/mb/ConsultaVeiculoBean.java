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

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.Regras;
import br.com.cond.businesslogic.Veiculo;
import br.com.cond.dao.RegrasDAO;
import br.com.cond.dao.VeiculoDAO;
import br.com.siaic.dao.FabricaConexao;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;



public class ConsultaVeiculoBean {
	
	private Connection conexao = null;
	
	private Veiculo veiculo;
	
	private Apartamento apartamento;
		
   private String tipoPesquisa;
	
	private String campoPesquisa;
	

	
	public Veiculo getVeiculo() {
		return veiculo;
	}


	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
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


	
	public ConsultaVeiculoBean(){
		veiculo = new Veiculo();
		apartamento = new Apartamento();
	}
	
		
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Veiculo> getTodosOsVeiculos() throws SQLException {

		VeiculoDAO dao = new VeiculoDAO();
		
		return dao.getTodosOsVeiculos();

	}
	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String VeiculoId() throws SQLException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idVeiculo = new Integer(req.getParameter("codigoVeiculo")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		VeiculoDAO dao = new VeiculoDAO();
		setVeiculo(dao.getVeiculoId(idVeiculo));
		getApartamento();
		
		return "modifica";

	}
	

	

	/**
	 * 
	 * @throws SQLException
	 */
	public void excluiveiculo() throws SQLException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer idveiculo = new Integer(req.getParameter("codigoveiculo"))
				.intValue();

		VeiculoDAO dao = new VeiculoDAO();
		dao.removerveiculo(idveiculo);

	}
	
	
	public String exibeDetalhesveiculos() throws SQLException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idveiculo = new Integer(req.getParameter("codigoVeiculo")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		VeiculoDAO dao = new VeiculoDAO();
		setVeiculo(dao.getVeiculoId(idveiculo));	
		//getEnderecoUsuario();
		
		
		return "detalhes";
	}
	
	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String updateveiculo() throws SQLException {
		// TODO
		
		String r = "sucesso";
		
		VeiculoDAO daoveiculo = new VeiculoDAO();
		
		daoveiculo.alterarVeiculos(veiculo);
		
		destroiSessao();

		return r;

	}

	
	public String escolheTipoPesquisa() throws SQLException{
		if(tipoPesquisa.equals("veiculo")){
			
			getveiculoPorveiculo();
		} 
		return campoPesquisa;
}
	
	public List<Veiculo> getveiculoPorveiculo() throws SQLException {
		
		VeiculoDAO daoveiculo = new VeiculoDAO();
		
		return daoveiculo.getVeiculoPelaveiculo(campoPesquisa);
		
	}

	
	
	/**
	 * 
	 * @return
	 */
	public String destroiSessao(){
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove("veiculoBean");
		
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
		  saida = getDiretorioReal("/pdf/Relveiculos.pdf"); 
		//   Exporto para PDF 
		  JasperExportManager.exportReportToPdfFile(print, saida); 
		  /* 
		  * Jogo na variável saída o nome da aplicação mais o  
		  * caminho para o PDF. Essa variável será utilizada pela view  
		  */ 
		  saida = getContextPath() + "/pdf/Relveiculos.pdf"; 
		}
	

	
	public String gerar() { 
		  String jasper = getDiretorioReal("/rel/Relveiculos.jasper"); 
		 
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

