package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.cond.businesslogic.AgendaDependencia;
import br.com.cond.businesslogic.Reuniao;
import br.com.cond.dao.ReuniaoDAO;

/**
 * @author Tamie
 */

public class ReuniaoBean {
	private Reuniao reuniao;
	private List<SelectItem> reservaList = new ArrayList<SelectItem>();
	private String msg;
	
	public ReuniaoBean(){
		reuniao = new Reuniao();
		setReservaList();
	}
	
	public void setReservaList(){
		List<AgendaDependencia> l = null;
		try {
			l = new ReuniaoDAO().getDependencia();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (AgendaDependencia d : l){
			reservaList.add(new SelectItem(d.getCodigo(), d.toString()));
		}
	}

	public String addReuniao() throws SQLException{
		String s = "";
		
		s = new ReuniaoDAO().addReuniao(reuniao) ? "Sucesso" : "Falha";
		
		if (s.equals("Sucesso")) {
			msg = "Cadastro realizado com sucesso";
		} else {
			msg = "Cadastro não realizado";
		}
			
		return s;
	}
	
	public void Limpar() {
		msg = new String("");
		reuniao = new Reuniao();
	}
	public String getMsg(){
		return msg;
	}
	
	public String Cancelar(){
		Limpar();
		return "Cancelar";
	}
	
	public List<SelectItem> getReservaList() {
		return reservaList;
	}
	
	public Reuniao getReuniao() {
		return reuniao;
	}

	public void setReuniao(Reuniao reuniao) {
		this.reuniao = reuniao;
	}
	
}
