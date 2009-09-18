package br.com.siaic.mb;

import java.sql.SQLException;

import br.com.siaic.businesslogic.Agenda;
import br.com.siaic.dao.AgendaDAO;

public class AgendaBean {

	private Agenda agenda;
	private int codigoAgenda;

	public AgendaBean() {
		this.agenda = new Agenda();
	}

	public Agenda getAgenda() {
		return this.agenda;
	}

	public int getCodigoAgenda() {
		return this.codigoAgenda;
	}

	public void setCodigoAgenda(int codigoAgenda) {
		this.codigoAgenda = codigoAgenda;
	}

	public void consultaAgenda() throws SQLException {
		this.agenda = AgendaDAO.getInstance().getAgenda((this.codigoAgenda));
	}
}