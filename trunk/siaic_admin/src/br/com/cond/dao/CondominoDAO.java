package br.com.cond.dao;

import java.sql.SQLException;

import br.com.cond.businesslogic.Condomino;
import br.com.siaic.dao.DB;

import java.sql.PreparedStatement;

public class CondominoDAO {
	
	public Condomino getCondominio(int cod) throws SQLException {
		Condomino c = new Condomino();
		String sql = "select * from admcon_condomino where con_codigo = ?";
		PreparedStatement ps = DB.getConn().prepareStatement(sql);
		
		ps.setInt(1, cod);
		
		
		
		return c;
	}
}
