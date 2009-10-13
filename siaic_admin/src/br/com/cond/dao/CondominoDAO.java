package br.com.cond.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.cond.businesslogic.Condomino;
import br.com.siaic.dao.DB;

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
			c.setDataNasc(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(4)));
			c.setResponsavel(new CondominoDAO().getCondominio(rs.getInt(5)));
			c.setApartamento(new ApartamentoDAO().getApartamentoId(rs.getInt(6)));
		}
		
		rs.close();
		ps.close();
		
		return c;
	}
	
	public boolean inserir(Condomino c) throws SQLException, ParseException {
		String sql = "insert into admcon_condomino "+
		"(CON_NOME, CON_SEXO, CON_DATA_NASCIMENTO, CON_CON_CODIGO, CON_APA_CODIGO) "+
		"values (?, ?, ?, ?, ?)";
		
		PreparedStatement ps = DB.getConn().prepareStatement(sql);
		ps.setString(1, c.getNome());
		ps.setString(2, c.getSexo().toString());
		ps.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(c.getDataNasc())));
		if (!(c.getResponsavel().getCodigo() == null)) {
			if (!c.getResponsavel().getCodigo().equals(new Integer(0))) {
				ps.setInt(4, c.getResponsavel().getCodigo());
			}
		} else {
			ps.setNull(4, c.getResponsavel().getCodigo());
		}
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
	
	public boolean update(Condomino cAtual, Condomino cNovo) throws SQLException, ParseException {
		String sql = "update admcon_condomino set "+
		"CON_NOME = ?, CON_SEXO = ?, CON_DATA_NASCIMENTO = ?, CON_CON_CODIGO = ?, CON_APA_CODIGO = ?) "+
		"where CON_CODIGO = ?";
		
		PreparedStatement ps = DB.getConn().prepareStatement(sql);
		ps.setString(1, cNovo.getNome());
		ps.setString(2, Character.toString(cNovo.getSexo()));
		ps.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(cNovo.getDataNasc())));
		if (!(cNovo.getResponsavel().getCodigo() == null)) {
			if (!cNovo.getResponsavel().getCodigo().equals(new Integer(0))) {
				ps.setInt(4, cNovo.getResponsavel().getCodigo());
			}
		} else {
			ps.setNull(4, cNovo.getResponsavel().getCodigo());
		}
		ps.setInt(5, cNovo.getApartamento().getCodigoApartamento());
		ps.setInt(5, cNovo.getApartamento().getCodigoApartamento());
		ps.setInt(6, cAtual.getCodigo());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();

		return result;
	}
	
	public List<Condomino> getTodasOsCondominos() throws SQLException {
		String sql = "select * from admcon_condomino";
		
		PreparedStatement ps = DB.getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<Condomino> l = new ArrayList<Condomino>();
		Condomino c = null;
		while (rs.next()) {
			c = new Condomino();
			c.setCodigo(rs.getInt(1));
			c.setNome(rs.getString(2));
			c.setSexo(rs.getString(3).charAt(0));
			c.setDataNasc(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(4)));
			c.setResponsavel(new CondominoDAO().getCondominio(rs.getInt(5)));
			c.setApartamento(new ApartamentoDAO().getApartamentoId(rs.getInt(6)));
			l.add(c);
		}
		rs.close();
		ps.close();

		return l;
	}

	
	
	
	
}
