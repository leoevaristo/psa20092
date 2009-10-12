package br.com.cond.mb;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

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
	}
	
	public void setReservaList(){
		List<br.com.cond.dao.Dependencia> l = null;
		try {
			l = new ReuniaoDAO().getDependencia();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (br.com.cond.dao.Dependencia d : l){
			reservaList.add
		}
		
		reservaList.add(arg0)
	}
	
	public Reuniao getReuniao() {
		return reuniao;
	}

	public void setReuniao(Reuniao reuniao) {
		this.reuniao = reuniao;
	}
	
}
