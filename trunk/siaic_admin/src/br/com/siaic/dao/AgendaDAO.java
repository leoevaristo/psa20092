package br.com.siaic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Agenda;
import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Usuario;

/**
 * Singleton - Responsavel por todas as consultas, inse��es, exclus�es, edi��es
 * e trasa��es com a base de dados na tabela "dbo.siaic.agenda".
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
	 *            - c�digo da agenda.
	 * @return {@code Agenda} caso exista uma com o codigo passa por par�metro
	 *         ou {@code null} caso contr�rio.
	 * @throws SQLException
	 */
	public Agenda getAgenda(int codigo) throws SQLException {
		String query = new String("select * from agenda where AGE_CODIGO = ?");
		PreparedStatement ps = DB.getConn().prepareStatement(query);
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
			// a.setImovel(ImovelDAO.getInstance().getImovel(
			// rs.getInt("AGE_PESSOA_USUARIO")));
			a.setData(rs.getDate("AGE_DATA"));
			a.setHoraInicio(rs.getTime("AGE_HORA_INICIO"));
			a.setHoraFim(rs.getTime("AGE_HORA_INICIO"));
			// a.setDescricao(rs.getString("AGE_DESCRICAO"));
		}
		rs.close();
		ps.close();
		return a;
	}

	/**
	 * Lista todas as ocorrencias da tabela Agenda
	 * 
	 * @return Lista de {@link Agenda}
	 * @throws SQLException
	 */
	public List<Agenda> getAgendaList() throws SQLException {
		String query = new String("select * from agenda");
		PreparedStatement ps = DB.getConn().prepareStatement(query);
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
			// a.setImovel(ImovelDAO.getInstance().getImovel(
			// rs.getInt("AGE_PESSOA_USUARIO")));
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

	/**
	 * Lista todas as ocorrencias da tabela Agenda de um determinado Usu�rio
	 * (Corretor)
	 * 
	 * @param u
	 *            {@link Usuario} (Corretor)
	 * @return Lista de {@link Agenda}
	 * @throws SQLException
	 */
	public List<Agenda> getAgendaList(Usuario u) throws SQLException {
		String query = new String(
				"select * from agenda where AGE_PESSOA_USUARIO = ?");
		PreparedStatement ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, u.getCodigoPessoa());
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
			// a.setImovel(ImovelDAO.getInstance().getImovel(
			// rs.getInt("AGE_PESSOA_USUARIO")));
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

	/**
	 * Lista todas as ocorrencias da tabela Agenda de um determinado Cliente
	 * 
	 * @param c
	 *            {@link Cliente}
	 * @return Lista de {@link Agenda}
	 * @throws SQLException
	 */
	public List<Agenda> getAgendaList(Cliente c) throws SQLException {
		String query = new String(
				"select * from agenda where AGE_PESSOA_CLIENTE = ?");
		PreparedStatement ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, c.getCodigoPessoa());
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
			// a.setImovel(ImovelDAO.getInstance().getImovel(
			// rs.getInt("AGE_PESSOA_USUARIO")));
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

	/**
	 * Lista todas as ocorrencias da tabela Agenda de um determinado Im�vel
	 * 
	 * @param i
	 *            {@link Imovel}
	 * @return Lista de {@link Agenda}
	 * @throws SQLException
	 */
	/*public List<Agenda> getAgendaList(Imovel i) throws SQLException {
		String query = new String(
				"select * from agenda where AGE_IMOVEL = ?");
		PreparedStatement ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, i.getCodigo());
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
			// a.setImovel(ImovelDAO.getInstance().getImovel(
			// rs.getInt("AGE_PESSOA_USUARIO")));
			a.setData(rs.getDate("AGE_DATA"));
			a.setHoraInicio(rs.getTime("AGE_HORA_INICIO"));
			a.setHoraFim(rs.getTime("AGE_HORA_INICIO"));
			// a.setDescricao(rs.getString("AGE_DESCRICAO"));
			l.add(a);
		}
		rs.close();
		ps.close();
		return l;
	}*/
	
	public boolean AtualizarAgenda(Agenda atual, Agenda nova){
		
		return false;
		
	}
}