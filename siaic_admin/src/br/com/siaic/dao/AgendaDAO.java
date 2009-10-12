package br.com.siaic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Agenda;
import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Imovel;
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
		String query = new String("SELECT * FROM AGENDA WHERE AGE_CODIGO = ?");
		PreparedStatement ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, codigo);
		ResultSet rs = ps.executeQuery();

		Agenda a = null;
		if (rs.first()) {
			a = new Agenda();
			a.setCodigo(rs.getInt("AGE_CODIGO"));
			a.setCodCliente(rs.getInt("AGE_PESSOA_CLIENTE"));
			a.setCodCorretor(rs.getInt("AGE_PESSOA_USUARIO"));
			a.setCodImovel(rs.getInt("AGE_IMOVEL"));
			a.setData(rs.getString("AGE_DATA"));
			a.setHoraInicio(rs.getString("AGE_HORA_INICIO"));
			a.setHoraFim(rs.getString("AGE_HORA_FIM"));
			a.setDescricao(rs.getString("AGE_DESCRICAO"));
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

		String query = new String(
				"SELECT * FROM AGENDA ORDER BY AGE_CODIGO DESC;");
		PreparedStatement ps = DB.getConn().prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		List<Agenda> l = new ArrayList<Agenda>();

		while (rs.next()) {
			Agenda a = new Agenda();
			a.setCodigo(rs.getInt("AGE_CODIGO"));
			a.setCodCliente(rs.getInt("AGE_PESSOA_CLIENTE"));
			a.setCodCorretor(rs.getInt("AGE_PESSOA_USUARIO"));
			a.setCodImovel(rs.getInt("AGE_PESSOA_USUARIO"));
			a.setData(rs.getString("AGE_DATA"));
			a.setHoraInicio(rs.getString("AGE_HORA_INICIO"));
			a.setHoraFim(rs.getString("AGE_HORA_FIM"));
			a.setDescricao(rs.getString("AGE_DESCRICAO"));

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
			a.setCodCliente(rs.getInt("AGE_PESSOA_CLIENTE"));
			a.setCodCorretor(rs.getInt("AGE_PESSOA_USUARIO"));
			a.setCodImovel(rs.getInt("AGE_PESSOA_USUARIO"));
			a.setData(rs.getString("AGE_DATA"));
			a.setHoraInicio(rs.getString("AGE_HORA_INICIO"));
			a.setHoraFim(rs.getString("AGE_HORA_FIM"));
			a.setDescricao(rs.getString("AGE_DESCRICAO"));
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
			a.setCodCliente(rs.getInt("AGE_PESSOA_CLIENTE"));
			a.setCodCorretor(rs.getInt("AGE_PESSOA_USUARIO"));
			a.setCodImovel(rs.getInt("AGE_PESSOA_USUARIO"));
			a.setData(rs.getString("AGE_DATA"));
			a.setHoraInicio(rs.getString("AGE_HORA_INICIO"));
			a.setHoraFim(rs.getString("AGE_HORA_FIM"));
			a.setDescricao(rs.getString("AGE_DESCRICAO"));
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
	 * @throws SQLException
	 */
	public List<Agenda> getAgendaList(Imovel i) throws SQLException {
		String query = new String("select * from agenda where AGE_IMOVEL = ?");
		PreparedStatement ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, i.getCodigo());
		ResultSet rs = ps.executeQuery();

		List<Agenda> l = new ArrayList<Agenda>();
		Agenda a = null;

		while (rs.next()) {
			a = new Agenda();
			a.setCodigo(rs.getInt("AGE_CODIGO"));
			a.setCodCliente(rs.getInt("AGE_PESSOA_CLIENTE"));
			a.setCodCorretor(rs.getInt("AGE_PESSOA_USUARIO"));
			a.setCodImovel(rs.getInt("AGE_PESSOA_USUARIO"));
			a.setData(rs.getString("AGE_DATA"));
			a.setHoraInicio(rs.getString("AGE_HORA_INICIO"));
			a.setHoraFim(rs.getString("AGE_HORA_FIM"));
			a.setDescricao(rs.getString("AGE_DESCRICAO"));
			l.add(a);
		}
		rs.close();
		ps.close();
		return l;
	}

	/**
	 * Atualiza um registro na tabela Agenda.</br>Substitui o registro "atual"
	 * representado pelo objeto passado por par�metro pelo objeto "novo" tamb�m
	 * passado por par�metro.
	 * 
	 * @param atual
	 *            - {@link Agenda} representa o registro atual na tabela
	 *            "Agenda"
	 * @param nova
	 *            - {@link Agenda} representa o registro novo que substituir� o
	 *            atual.
	 * @return <code>true</code> caso a atualiza��o seja realizada com sucesso
	 *         ou <code>false</code> caso contr�rio
	 * @throws SQLException
	 */
	public boolean AtualizarAgenda(Agenda atual, Agenda nova)
			throws SQLException {
		String query = new String("update agenda set "
				+ "AGE_CODIGO = ?, AGE_PESSOA_CLIENTE = ?, "
				+ "AGE_PESSOA_USUARIO = ?, AGE_IMOVEL = ?, "
				+ "AGE_DATA = ?, AGE_HORA_INICIO = ?, "
				+ "AGE_HORA_FIM = ?, AGE_DESCRICAO = ? "
				+ "where AGE_CODIGO = ?");
		PreparedStatement ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, nova.getCodigo());
		ps.setInt(2, nova.getCodCliente());
		ps.setInt(3, nova.getCodCorretor());
		ps.setInt(4, nova.getCodImovel());
		ps.setString(5, nova.getData());
		ps.setString(6, nova.getHoraInicio());
		ps.setString(7, nova.getHoraFim());
		ps.setString(8, nova.getDescricao());
		ps.setInt(9, atual.getCodigo());

		boolean result = ps.executeUpdate() > 0;
		ps.close();
		return result;
	}

	/**
	 * Deleta o registro na tabela Agenda representado pelo objeto
	 * {@link Agenda} passado por par�metro.
	 * 
	 * @param a
	 *            - {@link Agenda} registro que ser� deletado.
	 * @return <code>true</code> caso a exclus�o seja realizada com sucesso ou
	 *         <code>false</code> caso contr�rio.
	 * @throws SQLException
	 */
	public boolean ApagarAgenda(int codigoEntrada) throws SQLException {
		String query = new String("DELETE FROM AGENDA WHERE AGE_CODIGO = ?");
		PreparedStatement ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, codigoEntrada);

		boolean result = ps.executeUpdate() > 0;
		ps.close();

		return result;
	}

	/**
	 * Insere o registro na tabela Agenda representado pelo objeto
	 * {@link Agenda} passado por par�metro.
	 * 
	 * @param a
	 *            - {@link Agenda} que ser� inserida no banco de dados.
	 * @return <code>true</code> caso a insers�o seja realizada com sucesso ou
	 *         <code>false</code> caso contr�rio.
	 * @throws SQLException
	 */
	public boolean InserirAgenda(Agenda a) throws SQLException {
		String query = new String("INSERT INTO AGENDA "
				+ "(AGE_PESSOA_CLIENTE, AGE_PESSOA_USUARIO, "
				+ "AGE_IMOVEL, AGE_DATA, AGE_HORA_INICIO, "
				+ "AGE_HORA_FIM, AGE_DESCRICAO) "
				+ "values (?, ?, ?, ?, ?, ?, ?)");

		try {

			PreparedStatement ps = DB.getConn().prepareStatement(query);

			ps.setInt(1, a.getCodCliente());
			ps.setInt(2, a.getCodCorretor());
			ps.setInt(3, a.getCodImovel());
			ps.setString(4, a.getData());
			ps.setString(5, a.getHoraInicio());
			ps.setString(6, a.getHoraFim());
			ps.setString(7, a.getDescricao());

			boolean result = ps.executeUpdate() > 0;
			ps.close();

			return result;

		} finally {

			DB.getConn().close();

		}
	}

	public boolean AtualizarAgenda(Agenda agenda) throws SQLException {

		String query = new String("UPDATE AGENDA SET "
				+ "AGE_PESSOA_CLIENTE = ?, "
				+ "AGE_PESSOA_USUARIO = ?, AGE_IMOVEL = ?, "
				+ "AGE_DATA = ?, AGE_HORA_INICIO = ?, "
				+ "AGE_HORA_FIM = ?, AGE_DESCRICAO = ? "
				+ "WHERE AGE_CODIGO = ?");

		try {
			PreparedStatement ps = DB.getConn().prepareStatement(query);
			ps.setInt(1, agenda.getCodCliente());
			ps.setInt(2, agenda.getCodCorretor());
			ps.setInt(3, agenda.getCodImovel());
			ps.setString(4, agenda.getData());
			ps.setString(5, agenda.getHoraInicio());
			ps.setString(6, agenda.getHoraFim());
			ps.setString(7, agenda.getDescricao());
			ps.setInt(8, agenda.getCodigo());

			boolean result = ps.executeUpdate() > 0;
			ps.close();

			return result;

		} finally {
			DB.getConn().close();
		}
	}

	public void associarImovelAgenda(int imovelCodigo, int agendaCodigo) {

		String sql = "INSERT INTO IMOVEL_AGENDA (IMA_IMOVEL_CODIGO,IMA_AGENDA_CODIGO)"
				+ " VALUES(?,?);";

		try {

			PreparedStatement ps = DB.getConn().prepareStatement(sql);
			ps.setInt(1, imovelCodigo);
			ps.setInt(2, agendaCodigo);
			ps.execute();

			ps.close();

		} catch (SQLException e) {

			e.getLocalizedMessage();
		} finally {

			try {
				DB.getConn().close();
			} catch (SQLException e) {

				e.getLocalizedMessage();
			}

		}

	}

	public int getUltimoIndiceAgenda() {

		String sql = "SELECT max(AGE_CODIGO) AS CODIGO  FROM AGENDA";

		int codigoAgenda = 0;

		try {

			PreparedStatement ps = DB.getConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				codigoAgenda = rs.getInt("CODIGO");
			}

		} catch (SQLException e) {
			e.getLocalizedMessage();
		} finally {
			try {
				DB.getConn().close();
			} catch (SQLException e) {
				e.getLocalizedMessage();
			}
		}
		return codigoAgenda;
	}
}