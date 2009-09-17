package br.com.siaic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Agenda;

/**
 * Singleton - Responsavel por todas as consultas, inseções, exclusões, edições e trasações
 * com a base de dados na tabela "dbo.siaic.agenda".
 * 
 * @author Robson R. Vieira da Cunha
 * @version 1.0
 * @see ClienteDAO
 * @see UsuarioDAO
 * @see ImovelDAO
 * 
 */
public class AgendaDAO {
	private static AgendaDAO instance;

	public static AgendaDAO getInstance() {
		if (AgendaDAO.instance == null) {
			AgendaDAO.instance = new AgendaDAO();
		}
		return AgendaDAO.instance;
	}

	/**
	 * Realiza uma consulta na tabela "agenda" retornando uma ocorrencia caso a
	 * mesma exista.
	 * 
	 * @param codigo
	 *            - código da agenda.
	 * @return {@code Agenda} caso exista uma com o codigo passa por parâmetro
	 *         ou {@code null} caso contrário.
	 * @throws SQLException
	 */
	public Agenda getAgenda(int codigo) throws SQLException {
		String query = new String("select * from agenda where AGE_CODIGO = ?");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, codigo);
		ResultSet rs = ps.executeQuery();

		Agenda a = null;
		if (rs.first()) {
			a = new Agenda();
			a.setCodigo(rs.getInt("AGE_CODIGO"));
			a.setCliente(ClienteDAO.getInstancia().getClientePorId(
					rs.getInt("AGE_PESSOA_CLIENTE")));
			a.setCorretor(UsuarioDAO.getInstancia().getUsuarioId(
					rs.getInt("AGE_PESSOA_USUARIO")));
			//a.setImovel(ImovelDAO.getInstance().getImovel(
			//		rs.getInt("AGE_PESSOA_USUARIO")));
			a.setData(rs.getDate("AGE_DATA"));
			a.setHoraInicio(rs.getTime("AGE_HORA_INICIO"));
			a.setHoraFim(rs.getTime("AGE_HORA_INICIO"));
			// a.setDescricao(rs.getString("AGE_DESCRICAO"));
		}
		rs.close();
		ps.close();
		return a;
	}
	
	public List<Agenda> getAgendaList() throws SQLException{
		String query = new String("select * from agenda");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		List<Agenda> l = new ArrayList<Agenda>();
		Agenda a = null;
		
		while (rs.next()) {
			a = new Agenda();
			a.setCodigo(rs.getInt("AGE_CODIGO"));
			a.setCliente(ClienteDAO.getInstancia().getClientePorId(
					rs.getInt("AGE_PESSOA_CLIENTE")));
			a.setCorretor(UsuarioDAO.getInstancia().getUsuarioId(
					rs.getInt("AGE_PESSOA_USUARIO")));
			//a.setImovel(ImovelDAO.getInstance().getImovel(
			//		rs.getInt("AGE_PESSOA_USUARIO")));
			a.setData(rs.getDate("AGE_DATA"));
			a.setHoraInicio(rs.getTime("AGE_HORA_INICIO"));
			a.setHoraFim(rs.getTime("AGE_HORA_INICIO"));
			// a.setDescricao(rs.getString("AGE_DESCRICAO"));
			l.add(a);
		}
		rs.close();
		ps.close();
		return l;
	}
}