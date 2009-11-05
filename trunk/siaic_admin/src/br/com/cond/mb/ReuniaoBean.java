package br.com.cond.mb;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
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
			msg = "Opera��o realizada com sucesso";
		} else {
			msg = "Opera��o n�o realizada";
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
	
	private String getRealPath(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}

	public void impAviso() throws JRException, SQLException, IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();

		Integer cod = new Integer(req.getParameter("codigoEntrada"));

		Map<String, Integer> parameters = new HashMap<String, Integer>();
		parameters.put("cod", cod);
				
		String jasperFile = getRealPath("rel/AvisoReuniao.jasper");
		
		JasperPrint print = JasperFillManager.fillReport(jasperFile, parameters, DB.getConn());
		byte[] bytes = JasperExportManager.exportReportToPdf(print);

		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=report.pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();

	}
	public void impAta() throws JRException, SQLException, IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();

		Integer cod = new Integer(req.getParameter("codigoEntrada"));

		Map<String, Integer> parameters = new HashMap<String, Integer>();
		parameters.put("cod", cod);
				
		String jasperFile = getRealPath("rel/AtaReuniao.jasper");
		
		JasperPrint print = JasperFillManager.fillReport(jasperFile, parameters, DB.getConn());
		byte[] bytes = JasperExportManager.exportReportToPdf(print);

		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=report.pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();

	}
}
