package br.com.cond.dao;

/**
 * @author José Carlos
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.cond.businesslogic.AgendaDependencia;
import br.com.cond.businesslogic.AgendaFinalidade;
import br.com.cond.businesslogic.Condomino;
import br.com.cond.businesslogic.Dependencia;
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
	
	public AgendaDependencia getAgendaDependencia(int cod) throws SQLException {
		String sql = "select AGD_CODIGO, AGD_DATA, AGD_HORA_INICIO, AGD_HORA_FINAL, AGD_CON_CODIGO, "
				+ "AGD_DEP_CODIGO, AGD_AGF_CODIGO, AGD_COMPARECIMENTO from admcon_agenda_dependencia "
				+ "where AGD_CODIGO = ?";

		PreparedStatement ps = FabricaConexao.getInstancia().conectar().prepareStatement(sql);
		ps.setInt(1, cod);

		ResultSet rs = ps.executeQuery();

		AgendaDependencia d = null;

		if (rs.first()) {
			d = new AgendaDependencia();
			d.setCodigo(rs.getInt("AGD_CODIGO"));
			d.setData(rs.getDate("AGD_DATA"));
			d.setHoraInicio(rs.getTime("AGD_HORA_INICIO"));
			d.setHoraFinal(rs.getTime("AGD_HORA_FINAL"));
			d.setCondomino(new CondominoDAO().getCondominio(rs
					.getInt("AGD_CON_CODIGO")));
			d.setDependencia(new DependenciaDAO().buscaDependencia(rs
					.getInt("AGD_DEP_CODIGO")));
			d.setFinalidade(new AgendaFinalidadeDAO().buscarAgendaFinalidade(rs
					.getInt("AGD_AGF_CODIGO")));
			char c = (rs.getString("AGD_COMPARECIMENTO") == null || rs
					.getString("AGD_COMPARECIMENTO").isEmpty()) ? '\0' : rs
					.getString("AGD_COMPARECIMENTO").charAt(0);
		}
		
		rs.close();
		ps.close();
		
		return d;

	}

	public List<AgendaDependencia> buscarAgendaDependencia(AgendaDependencia agendaDependencia) throws SQLException{
		
		//TODO buscar através dos filtros fornecidos pelo objeto AgendaDependencia todos os registros relacionados  
		
		String sql;
		StringBuilder where = new StringBuilder();
		PreparedStatement ps;
		ResultSet rs;
		List<AgendaDependencia> listaAgendaDependencia = new ArrayList<AgendaDependencia>();
		
		if(agendaDependencia.getCodigo() != 0){
			if(where.length() == 0){
				where.append("AGD_CODIGO = " + agendaDependencia.getCodigo());
			}else{
				where.append(" and AGD_CODIGO = " + agendaDependencia.getCodigo());
			}
		}
		
		if(agendaDependencia.getData() != null){
			if(where.length() == 0){
				where.append("AGD_DATA = '" + new Date(agendaDependencia.getData().getTime()) + "'");
			}else{
				where.append(" and AGD_DATA = '" + new Date(agendaDependencia.getData().getTime()) + "'");
			}
		}
		
		if(agendaDependencia.getHoraInicio() != null){
			if(where.length() == 0){
				where.append("AGD_HORA_INICIO = '" + agendaDependencia.getHoraInicio() + "'");
			}else{
				where.append(" and AGD_HORA_INICIO = '" + agendaDependencia.getHoraInicio() + "'");
			}
		}
		
		if(agendaDependencia.getHoraFinal() != null){
			if(where.length() == 0){
				where.append("AGD_HORA_FINAL = '" + agendaDependencia.getHoraFinal() + "'");
			}else{
				where.append(" and AGD_HORA_FINAL = '" + agendaDependencia.getHoraFinal() + "'");
			}
		}

		if(agendaDependencia.getCondomino().getCodigo() != null){
			if(where.length() == 0){
				where.append("AGD_CON_CODIGO = " + agendaDependencia.getCondomino().getCodigo()) ;
			}else{
				where.append(" and AGD_CON_CODIGO = " + agendaDependencia.getCondomino().getCodigo());
			}
		}
		
		if(agendaDependencia.getDependencia().getCodigo() != 0){
			if(where.length() == 0){
				where.append("AGD_DEP_CODIGO = " + agendaDependencia.getDependencia().getCodigo()) ;
			}else{
				where.append(" and AGD_DEP_CODIGO = " + agendaDependencia.getDependencia().getCodigo());
			}
		}
		
		if(agendaDependencia.getFinalidade().getCodigo() != 0){
			if(where.length() == 0){
				where.append("AGD_AGF_CODIGO = " + agendaDependencia.getFinalidade().getCodigo()) ;
			}else{
				where.append(" and AGD_AGF_CODIGO = " + agendaDependencia.getFinalidade().getCodigo());
			}
		}
		
		if(agendaDependencia.getComparecimento() != '\u0000'){
			if(where.length() == 0){
				where.append("AGD_COMPARECIMENTO = '" + agendaDependencia.getComparecimento() + "'") ;
			}else{
				where.append(" and AGD_COMPARECIMENTO = '" + agendaDependencia.getComparecimento() + "'");
			}
		}
		
		if(where.length() == 0){
			sql = "select AGD_CODIGO, AGD_DATA, AGD_HORA_INICIO, AGD_HORA_FINAL, AGD_CON_CODIGO, " +
				  "AGD_DEP_CODIGO, AGD_AGF_CODIGO, AGD_COMPARECIMENTO " +
				  "from admcon_agenda_dependencia";
		}else{
			sql = "select AGD_CODIGO, AGD_DATA, AGD_HORA_INICIO, AGD_HORA_FINAL, AGD_CON_CODIGO, " +
			  "AGD_DEP_CODIGO, AGD_AGF_CODIGO, AGD_COMPARECIMENTO " +
			  "from admcon_agenda_dependencia where " + where;
		}
		
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				AgendaDependencia agendaDepen = new AgendaDependencia();
				agendaDepen.setCodigo(rs.getInt("AGD_CODIGO"));
				agendaDepen.setData(rs.getDate("AGD_DATA"));
				agendaDepen.setHoraInicio(rs.getTime("AGD_HORA_INICIO"));
				agendaDepen.setHoraFinal(rs.getTime("AGD_HORA_FINAL"));
				
				//Pegando um Condomino
				CondominoDAO condominoDAO = new CondominoDAO();
				agendaDepen.setCondomino(condominoDAO.getCondominio(rs.getInt("AGD_CON_CODIGO")));
				
				//Pegando uma Dependencia
				DependenciaDAO dependenciaDao = new DependenciaDAO();
				agendaDepen.setDependencia(dependenciaDao.buscaDependencia(rs.getInt("AGD_DEP_CODIGO")));
				
				//Pegando uma AgendaFinalidade
				AgendaFinalidadeDAO agendaFDao = new AgendaFinalidadeDAO();
 		   	    agendaDepen.setFinalidade(agendaFDao.buscarAgendaFinalidade(rs.getInt("AGD_AGF_CODIGO")));
				
				
			}
		}finally{
			conn.close();
		}
		
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
