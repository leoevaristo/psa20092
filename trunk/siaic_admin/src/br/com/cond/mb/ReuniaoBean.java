package br.com.cond.mb;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
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
