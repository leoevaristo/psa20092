package br.com.cond.mb;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import br.com.cond.businesslogic.AgendaDependencia;
import br.com.cond.businesslogic.Reuniao;
import br.com.cond.dao.ReuniaoDAO;
import br.com.siaic.dao.DB;

/**
 * @author Tamie
 */

public class ReuniaoBean {
	private Reuniao reuniao;
	private Reuniao atual;
	private String acao;
	private String msg;
	
	public ReuniaoBean(){
		reuniao = new Reuniao();
		acao = "Cadastrar";
	}
	
	public String addReuniao() throws SQLException{
		String s = "";
		String nav = "";
		if (acao.equals("Editar")) {
			s = new ReuniaoDAO().altReuniao(atual, reuniao) ? "Sucesso" : "Falha";
			acao = "Cadastrar";
			nav = "Editar";
		} else {
			s = new ReuniaoDAO().addReuniao(reuniao) ? "Sucesso" : "Falha";
		}
		if (s.equals("Sucesso")) {
			msg = "Operação realizada com sucesso";
		} else {
			msg = "Operação não realizada";
			nav = "";
		}
		return nav;
	}
	
	public void Limpar() {
		msg = new String("");
		reuniao = new Reuniao();
	}
	public String getMsg(){
		return msg;
	}
	public String getAcao(){
		return acao;
	}
	
	public String Cancelar(){
		Limpar();
		String s = "";
		String nav = "";
		if (acao.equals("Editar")) {
			acao = "Cadastrar";
			nav = "Editar";
		} else {
			nav = "Cadastrar";
		}
		return nav;
	}
	
	public List<SelectItem> getReservaList() {
		List<AgendaDependencia> l = null;
		try {
			l = new ReuniaoDAO().getDependencia();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<SelectItem> reservaList = new ArrayList<SelectItem>();
		
		for (AgendaDependencia d : l){
			reservaList.add(new SelectItem(d.getCodigo(), d.toString()));
		}
		return reservaList;
	}
	
	public Reuniao getReuniao() {
		return reuniao;
	}

	public void setReuniao(Reuniao reuniao) {
		this.reuniao = reuniao;
	}
	
	public void excluiEntrada() throws SQLException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer cod = new Integer(req.getParameter("codigoEntrada"))
				.intValue();

		new ReuniaoDAO().delReuniao(new ReuniaoDAO().getReuniao(cod));
		
	}
	
	public List<Reuniao> getListaReuniao() {
		List<Reuniao> l = null;
		try {
			l = new ReuniaoDAO().getReuniaoListByDate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	public String editarEntrada(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer cod = new Integer(req.getParameter("codigoEntrada"))
				.intValue();
		try {
			reuniao = new ReuniaoDAO().getReuniao(cod);
			atual = new ReuniaoDAO().getReuniao(reuniao.getCodigo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		msg = "";
		acao = "Editar";
		return acao;
	}
	
	private String getDiretorioReal(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}

	private String getContextPath() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getServletContext().getContextPath();
	}

	private void preenchePdf(JasperPrint print) throws JRException {
		// Pego o caminho completo do PDF desde a raiz
		String saida;
		saida = getDiretorioReal("/pdf/RelRegras.pdf");
		// Exporto para PDF
		JasperExportManager.exportReportToPdfFile(print, saida);
		/*
		 * Jogo na variável saída o nome da aplicação mais o caminho para o PDF.
		 * Essa variável será utilizada pela view
		 */
		saida = getContextPath() + "/pdf/RelRegras.pdf";
	}
	
	public void impAviso() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer cod = new Integer(req.getParameter("codigoEntrada"))
				.intValue();

		Map parameters = new HashMap(); 
		parameters.put("cod", new Integer(cod));

		
		String rel = getDiretorioReal("rel/AvisoReuniao.jasper");
		
		
		try {
			JasperPrint print = JasperFillManager.fillReport(rel, parameters, DB.getConn());
			preenchePdf(print);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		 
		
	}
	
}
