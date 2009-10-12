package br.com.cond.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.cond.businesslogic.Condomino;
import br.com.siaic.dao.DB;

import java.sql.PreparedStatement;

/**
 * 
 * @author Robson
 *
 */
public class CondominoDAO {
	
	public Condomino getCondominio(int cod) throws SQLException {
		
		String sql = "select * from admcon_condomino where con_codigo = ?";
		PreparedStatement ps = DB.getConn().prepareStatement(sql);
		
		ps.setInt(1, cod);
		
		ResultSet rs = ps.executeQuery();
		Condomino c = null;
		
		if (rs.first()) {
			c = new Condomino();
			c.setCodigo(rs.getInt(1));
			c.setNome(rs.getString(2));
			c.setSexo(rs.getString(3).charAt(0));
			c.setDataNasc(rs.getDate(4));
			c.setCondomino(new CondominoDAO().getCondominio(rs.getInt(5)));
			c.setApartamento(new ApartamentoDAO().getApartamentoId(rs.getInt(6)));
		}
		
		rs.close();
		ps.close();
		
		return c;
	}
	
	public boolean inserir(Condomino c) throws SQLException {
		String sql = "insert into admcon_condomino "+
		"(CON_NOME, CON_SEXO, CON_DATA_NASCIMENTO, CON_CON_CODIGO, CON_APA_CODIGO) "+
		"values (?, ?, ?, ?, ?)";
		
		PreparedStatement ps = DB.getConn().prepareStatement(sql);
		ps.setString(1, c.getNome());
		ps.setString(2, Character.toString(c.getSexo()));
		ps.setDate(3, c.getDataNasc());
		ps.setInt(4, c.getCondomino().getCodigo());
		ps.setInt(5, c.getApartamento().getCodigoApartamento());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		
		return result;
	}
	
	public boolean deletar(Condomino c) throws SQLException {
		String sql = "delete from admcon_condomino where CON_CODIGO = ?";
		
		PreparedStatement ps = DB.getConn().prepareStatement(sql);
		ps.setInt(1, c.getCodigo());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();

		return result;
	}
	
	public boolean update(Condomino cAtual, Condomino cNovo) throws SQLException {
		String sql = "update admcon_condomino set "+
		"CON_NOME = ?, CON_SEXO = ?, CON_DATA_NASCIMENTO = ?, CON_CON_CODIGO = ?, CON_APA_CODIGO = ?) "+
		"where CON_CODIGO = ?";
		
		PreparedStatement ps = DB.getConn().prepareStatement(sql);
		ps.setString(1, cNovo.getNome());
		ps.setString(2, Character.toString(cNovo.getSexo()));
		ps.setDate(3, cNovo.getDataNasc());
		ps.setInt(4, cNovo.getCondomino().getCodigo());
		ps.setInt(5, cNovo.getApartamento().getCodigoApartamento());
		ps.setInt(6, cAtual.getCodigo());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();

		return result;
	}
}
