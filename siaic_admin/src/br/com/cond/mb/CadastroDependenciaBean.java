package br.com.cond.mb;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import br.com.cond.businesslogic.Dependencia;
import br.com.cond.dao.DependenciaDAO;
import br.com.cond.msg.MensagensUtil;
import br.com.siaic.dao.DB;
import br.com.siaic.dao.FabricaConexao;

public class CadastroDependenciaBean {

	private Dependencia dependencia;
	private boolean reservado;

	public boolean isReservado() {

		if (dependencia.getReservavel() == 'S') {
			return true;
		} else if (dependencia.getReservavel() == 'N') {
			return false;
		}

		return false;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}

	public CadastroDependenciaBean() {

		dependencia = new Dependencia();
		// System.out.println("construtor");
	}

	public Dependencia getDependencia() {
		return dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public void cadastroDependencia() {

		DependenciaDAO depDAO = new DependenciaDAO();

		if (this.reservado) {
			getDependencia().setReservavel('S');
		} else {
			getDependencia().setReservavel('N');
		}

		try {
			
			depDAO.adicionarDependencia(dependencia);
			throw new SQLException("Porque eu quero");
		} catch (SQLException e) {
			MensagensUtil.empiplharMensagem(e);
			e.printStackTrace();
		}
		dependencia = new Dependencia();
	}

	public void alterarDependencia() {

		DependenciaDAO depDAO = new DependenciaDAO();

		if (this.reservado) {
			getDependencia().setReservavel('S');
		} else {
			getDependencia().setReservavel('N');
		}

		try {
			depDAO.alterarDependencia(dependencia);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		dependencia = new Dependencia();
	}

	public void removerDependencia() {

		DependenciaDAO depDAO = new DependenciaDAO();

		try {
			depDAO.removerDependencia(dependencia);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// retorna a navega��o
		// return "removerDependencia";
		dependencia = new Dependencia();
	}

	public List<Dependencia> getBuscaDependencia() {

		DependenciaDAO depDAO = new DependenciaDAO();

		try {
			return depDAO.buscaDependencia(new Dependencia());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	
	private String getRealPath(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}


	public void impDependencia() throws JRException, SQLException, IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();

		Integer cod = new Integer(req.getParameter("codigoDependencia"));

		Map<String, Integer> parameters = new HashMap<String, Integer>();
		parameters.put("cod", cod);
				
		String jasperFile = getRealPath("rel/RelReservaDependencia.jasper");
		
		JasperPrint print = JasperFillManager.fillReport(jasperFile, parameters,FabricaConexao.getInstancia().conectar());
		byte[] bytes = JasperExportManager.exportReportToPdf(print);

		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=RelReservaDependencia.pdf");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.setContentType("application/pdf");
		context.responseComplete();

	}

}
