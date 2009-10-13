package br.com.cond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
