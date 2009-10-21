package br.com.cond.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.event.ActionEvent;

import br.com.cond.businesslogic.Dependencia;
import br.com.cond.dao.DependenciaDAO;

public class CadastroDependenciaBean {
	
	private Dependencia dependencia = new Dependencia();
	private boolean reservado;
	
	public boolean isReservado() {
		
		if(dependencia.getReservavel() == 'S'){
			return true;
		}else if(dependencia.getReservavel() == 'N'){
			return false;
		}
		
		return false;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}

	public CadastroDependenciaBean() {
		//dependencia = new Dependencia();
		//System.out.println("construtor");
	}
	
	public Dependencia getDependencia() {
		return dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public void cadastroDependencia(){
		
		DependenciaDAO depDAO = new DependenciaDAO();
		
		if(this.reservado){
			getDependencia().setReservavel('S');
		}else{
			getDependencia().setReservavel('N');
		}
		
		try {
			depDAO.adicionarDependencia(dependencia);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void alterarDependencia(){
		
		DependenciaDAO depDAO = new DependenciaDAO();
		
		if(this.reservado){
			getDependencia().setReservavel('S');
		}else{
			getDependencia().setReservavel('N');
		}
		
		try {
			depDAO.alterarDependencia(dependencia);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public void removerDependencia(){
		
		DependenciaDAO depDAO = new DependenciaDAO();
		
		try {
			depDAO.removerDependencia(dependencia);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//retorna a navegação
		//return "removerDependencia";

	}
	
	public List<Dependencia> getBuscaDependencia(){
		
		DependenciaDAO depDAO = new DependenciaDAO();
		
		try {
			return depDAO.buscaDependencia(new Dependencia());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
