package br.com.cond.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.cond.businesslogic.Dependencia;
import br.com.cond.dao.DependenciaDAO;
import br.com.cond.msg.MensagensUtil;

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

		// retorna a navegação
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
}
