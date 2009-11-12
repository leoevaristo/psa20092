package br.com.cor.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cond.businesslogic.Reuniao;
import br.com.cond.dao.AgendaDependenciaDAO;
import br.com.cor.businesslogic.ReservarImovel;
import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.DB;
import br.com.siaic.dao.ImovelDAO;

public class ReservarImovelDAO {
	
	// get da tabela reserva imovel
	public ReservarImovel getReserva(int codigo)
			throws SQLException {
		String query = new String(
				"SELECT * FROM ADMCOR_RESERVA_IMOVEL WHERE PEC_CODIGO = ?");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, codigo);
		ResultSet rs = ps.executeQuery();

		ReservarImovel r = null;

		if (rs.first()) {
			r = new ReservarImovel();
			r.setCodigo(rs.getInt("REI_CODIGO"));
			r.setCliente(new ClienteDAO().getClientePorId(rs.getInt("REI_CLIENTE")));
			r.setImovel(new ImovelDAO().getImovel(rs.getInt("REI_IMOVEL")));
			r.setTempoReserva(rs.getInt("REI_TEMPO_RESERVA"));
			r.setData(rs.getDate("REI_DATA"));
		}
		ps.close();
		rs.close();

		return r;
	}
	// Insert da tabela reserva imovel
	public boolean addReuniao(Reuniao r)
			throws SQLException {
		String query = new String(
				"INSERT INTO ADMCON_REUNIAO (REU_DESCRICAO, REU_AGD_CODIGO) "
				+ "VALUES (?, ?)");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);

		ps.setString(1, r.getDescricao());
		ps.setInt(2, r.getDependencia().getCodigo());

		boolean result = ps.executeUpdate() > 0;
		ps.close();

		return result;
	}
	// Delete da tabela reservar imovel
	public boolean delReserva(ReservarImovel r)
			throws SQLException {
		String query = new String(
				"DELETE FROM ADMCOR_RESERVA_IMOVEL WHERE REI_CODIGO = ? ");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, r.getCodigo());

		boolean result = ps.executeUpdate() > 0;
		ps.close();

		return result;
	}
	// Listagem da tabela RESERVAR IMOVEL
	public List<ReservarImovel> getReservaList()
			throws SQLException {
		String query = new String("SELECT * FROM ADMCOR_RESERVA_IMOVEL");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		List<ReservarImovel> l = new ArrayList<ReservarImovel>();
		ReservarImovel r = null;

		while (rs.next()) {
			r = new ReservarImovel();
			r.setCodigo(rs.getInt("REI_CODIGO"));
			r.setCliente(new ClienteDAO().getClientePorId(rs.getInt("REI_CLIENTE")));
			r.setImovel(new ImovelDAO().getImovel(rs.getInt("REU_AGD_CODIGO")));
			r.setData(rs.getDate("REI_DATA"));
			r.setTempoReserva(rs.getInt("REI_TEMPO_RESERVA"));
			l.add(r);
		}
		rs.close();
		ps.close();
		return l;
	}
}
