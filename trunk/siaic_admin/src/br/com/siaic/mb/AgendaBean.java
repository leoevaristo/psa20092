package br.com.siaic.mb;

import java.sql.SQLException;

import br.com.siaic.businesslogic.Agenda;
import br.com.siaic.dao.AgendaDAO;

public class AgendaBean {

	private Agenda agenda;

	public AgendaBean() {
		this.agenda = new Agenda();
	}

	public Agenda getAgenda() {
		return this.agenda;
	}



	public void consultaAgenda() throws SQLException {
		this.agenda = AgendaDAO.getInstance().getAgenda((this.getAgenda().getCodigo()));
	}
}