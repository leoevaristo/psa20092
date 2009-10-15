package br.com.cond.dao;

/**
 * @author José Carlos
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
		
		boolean flag;
		String sql = "insert into admcon_agenda_dependencia(AGD_DATA, AGD_HORA_INICIO, AGD_HORA_FINAL, AGD_CON_CODIGO, " +
					 "AGD_DEP_CODIGO, AGD_AGF_CODIGO, AGD_COMPARECIMENTO) values(?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps;
		try{
			ps = conn.prepareStatement(sql);
			//ps.setTimestamp(1, new Timestamp(agendaDependencia.getData().getTime()));
			ps.setDate(1, new Date(agendaDependencia.getData().getTime()));
			ps.setTime(2, agendaDependencia.getHoraInicio());
			ps.setTime(3, agendaDependencia.getHoraFinal());
			ps.setInt(4, agendaDependencia.getCondomino().getCodigo());
			ps.setInt(5, agendaDependencia.getDependencia().getCodigo());
			ps.setInt(6, agendaDependencia.getFinalidade().getCodigo());
			ps.setString(7, "" + agendaDependencia.getComparecimento() );
			
			flag = ps.execute();
			
			ps.close();
			
			return flag;
		}finally{
			conn.close();
		}
	}
	
	public boolean alterarAgendaDependencia(AgendaDependencia agendaDependencia) throws SQLException{
		
		boolean flag;
		String sql = "update admcon_agenda_dependencia set AGD_DATA = ?, AGD_HORA_INICIO = ?, " +
				"AGD_HORA_FINAL = ?, AGD_CON_CODIGO = ?, AGD_DEP_CODIGO = ?, AGD_AGF_CODIGO = ?, AGD_COMPARECIMENTO = ? " +
				"where AGD_CODIGO = ?";
		
		PreparedStatement ps;
		try{
			ps = conn.prepareStatement(sql);
			//ps.setTimestamp(1, new Timestamp(agendaDependencia.getData().getTime()));
			ps.setDate(1, new Date(agendaDependencia.getData().getTime()));
			ps.setTime(2, agendaDependencia.getHoraInicio());
			ps.setTime(3, agendaDependencia.getHoraFinal());
			ps.setInt(4, agendaDependencia.getCondomino().getCodigo());
			ps.setInt(5, agendaDependencia.getDependencia().getCodigo());
			ps.setInt(6, agendaDependencia.getFinalidade().getCodigo());
			ps.setString(7, "" + agendaDependencia.getComparecimento() );
			ps.setInt(8, agendaDependencia.getCodigo());
			
			flag = ps.execute();
			
			ps.close();
			
			return flag;
		}finally{
			conn.close();
		}
	}
	
	public boolean removerAgendaDependencia(AgendaDependencia agendaDependencia) throws SQLException{
		
		boolean flag;
		String sql = "delete from admcon_agenda_dependencia where AGD_CODIGO = ?";
		
		PreparedStatement ps;
		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(7, agendaDependencia.getCodigo());
			
			flag = ps.execute();
			
			ps.close();
			
			return flag;
		}finally{
			conn.close();
		}
	}

	public List<AgendaDependencia> buscarAgendaDependencia(AgendaDependencia agendaDependencia) throws SQLException{
		
		//TODO buscar através dos filtros fornecidos pelo objeto AgendaDependencia todos os registros relacionados  
		
//		String sql = "delete from admcon_agenda_dependencia where AGD_CODIGO = ?";
//		
//		PreparedStatement ps;
//		try{
//			ps = conn.prepareStatement(sql);
//			ps.setInt(7, agendaDependencia.getCodigo());
//			
//			ps.close();
//			
//			return null;
//		}finally{
//			conn.close();
//		}
		
		return null;
	}
	
	public static void main(String[] args){
		
//		AgendaDependenciaDAO agendaDao = new AgendaDependenciaDAO();
//		AgendaDependencia agenda = new AgendaDependencia();
//		
//		
//		Date dat = new Date(Calendar.getInstance().getTimeInMillis());
//		
//		agenda.setData(dat);
//		
//		agenda.setHoraInicio(new Time(dat.getTime()));
//
//		try {
//			agendaDao.adicionarAgendaDependencia(agenda);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		AgendaDependencia agenda = new AgendaDependencia();
		
	}
}
