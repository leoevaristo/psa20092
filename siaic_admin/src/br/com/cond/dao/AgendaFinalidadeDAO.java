package br.com.cond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cond.businesslogic.AgendaFinalidade;
import br.com.siaic.dao.FabricaConexao;

public class AgendaFinalidadeDAO {
	
	private Connection conn;
	
	public AgendaFinalidadeDAO() {
		
		try {
			conn = FabricaConexao.getInstancia().conectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean adicionarAgendaFinalidade(AgendaFinalidade agendaFinalidade) throws SQLException{
		
		boolean flag;
		PreparedStatement ps;

		String sql = "insert into admcon_agenda_finalidade(agf_descricao) values(?)";

		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1, agendaFinalidade.getDescricao());
			flag = ps.execute();
			ps.close();
			
			return flag;

		}finally{
			conn.close();
		}
	}

	public boolean alterarAgendaFinalidade(AgendaFinalidade agendaFinalidade) throws SQLException {
		boolean flag;
		PreparedStatement ps;
		
		String sql = "update admcon_agenda_finalidade set " +
					 "agf_descricao = ? " +
					 " where agf_codigo = ?";
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1, agendaFinalidade.getDescricao());
			ps.setInt(2, agendaFinalidade.getCodigo());
			
			flag = ps.execute();
			
			ps.close();
			return flag;
		}finally{
			conn.close();
		}
	}

	public boolean removerAgendaFinalidade(AgendaFinalidade agendaFinalidade) throws SQLException {
		
		boolean flag;
		PreparedStatement ps;
		
		String sql = "delete from admcon_agenda_finalidade where agf_codigo = ?";
		
		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, agendaFinalidade.getCodigo());
			
			flag = ps.execute();
			
			ps.close();
			
			return flag;
		}finally{
			conn.close();
		}
	}

	public List<AgendaFinalidade> buscarAgendaFinalidade(AgendaFinalidade agendaFinalidade) throws SQLException {
		
		String sql;
		StringBuilder where = new StringBuilder();
		PreparedStatement ps;
		ResultSet rs;
		List<AgendaFinalidade> listaAgendaFinalidade = new ArrayList<AgendaFinalidade>();
		
		if(agendaFinalidade.getCodigo() != 0){
			if(where.length() == 0){
				where.append("agf_codigo = " + agendaFinalidade.getCodigo());
			}else{
				where.append(" and agf_codigo = " + agendaFinalidade.getCodigo());
			}
		}
		
		if( agendaFinalidade.getDescricao() != null){
			if(where.length() == 0){
				where.append("agf_descricao like '" + agendaFinalidade.getDescricao() + "'");
			}else{
				where.append(" and agf_descrica like '" + agendaFinalidade.getDescricao() + "'");
			}
		}
		
		if(where.length() == 0){
			sql = "select agf_codigo, agf_descricao from admcon_agenda_finalidade";
		}else{
			sql = "select agf_codigo, agf_descricao from admcon_agenda_finalidade where " + where;
		}
		
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				AgendaFinalidade agenfin = new AgendaFinalidade();
				agenfin.setCodigo(rs.getInt("agf_codigo"));
				agenfin.setDescricao(rs.getString("agf_descricao"));
				
				listaAgendaFinalidade.add(agenfin);
			}
			
			ps.close();
			rs.close();
			
			return listaAgendaFinalidade;
		}finally{
			conn.close();
			
		}
	}
}
