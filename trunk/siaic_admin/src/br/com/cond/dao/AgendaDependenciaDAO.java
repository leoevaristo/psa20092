package br.com.cond.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.com.cond.businesslogic.AgendaDependencia;
import br.com.siaic.dao.FabricaConexao;

public class AgendaDependenciaDAO {
	
	private Connection conn;
	
	public AgendaDependenciaDAO() {
		
		try {
			conn = FabricaConexao.getInstancia().conectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean adicionarAgendaDependencia(AgendaDependencia agendaDependencia) throws SQLException{
		
		String sql = "insert into tbteste(hora, data) values(?, ?)";
		
		PreparedStatement ps;
		try{
			ps = conn.prepareStatement(sql);
			ps.setTime(1, agendaDependencia.getHoraInicio());
			//ps.setTimestamp(2, new Timestamp(agendaDependencia.getData().getTime()));
			
			ps.execute();
		}finally{
			conn.close();
		}
		
		
		return true;
	}
	
	public static void main(String[] args){
		AgendaDependenciaDAO agendaDao = new AgendaDependenciaDAO();
		AgendaDependencia agenda = new AgendaDependencia();
		
		agenda.setData(new Date(2009, 10, 14));
		agenda.setHoraInicio(new Time(13, 9, 45));

		try {
			agendaDao.adicionarAgendaDependencia(agenda);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(agenda.getData().getYear());
	}
}
