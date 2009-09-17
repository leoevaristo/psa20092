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
 * Singleton - Responsavel por todas as consultas, inseções, exclusões, edições
 * e trasações com a base de dados na tabela "dbo.siaic.agenda".
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
	 * Lista todas as ocorrencias da tabela Agenda de um determinado Usuário
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
	 * Lista todas as ocorrencias da tabela Agenda de um determinado Imóvel
	 * 
	 * @param i
	 *            {@link Imovel}
	 * @return Lista de {@link Agenda}
	 * @throws SQLException 
	 * @throws SQLException
	 */
//	public List<Agenda> getAgendaList(Imovel i) throws SQLException {
//		String query = new String(
//				"select * from agenda where AGE_IMOVEL = ?");
//		PreparedStatement ps = DB.getConn().prepareStatement(query);
//		ps.setInt(1, i.getCodigo());
//		ResultSet rs = ps.executeQuery();
//
//		List<Agenda> l = new ArrayList<Agenda>();
//		Agenda a = null;
//
//		while (rs.next()) {
//			a = new Agenda();
//			a.setCodigo(rs.getInt("AGE_CODIGO"));
//			a.setCliente(ClienteDAO.getInstancia().getClientePorId(
//					rs.getInt("AGE_PESSOA_CLIENTE")));
//			a.setCorretor(UsuarioDAO.getInstancia().getUsuarioId(
//					rs.getInt("AGE_PESSOA_USUARIO")));
//			// a.setImovel(ImovelDAO.getInstance().getImovel(
//			// rs.getInt("AGE_PESSOA_USUARIO")));
//			a.setData(rs.getDate("AGE_DATA"));
//			a.setHoraInicio(rs.getTime("AGE_HORA_INICIO"));
//			a.setHoraFim(rs.getTime("AGE_HORA_INICIO"));
//			// a.setDescricao(rs.getString("AGE_DESCRICAO"));
//			l.add(a);
//		}
//		rs.close();
//		ps.close();
//		return l;
//	}

	/**
	 * Atualiza um registro na tabela Agenda.</br>Substitui o registro "atual"
	 * representado pelo objeto passado por parâmetro pelo objeto "novo" também
	 * passado por parâmetro.
	 * 
	 * @param atual
	 *            - {@link Agenda} representa o registro atual na tabela
	 *            "Agenda"
	 * @param nova
	 *            - {@link Agenda} representa o registro novo que substituirá o
	 *            atual.
	 * @return <code>true</code> caso a atualização seja realizada com sucesso
	 *         ou <code>false</code> caso contrário
	 * @throws SQLException
	 */
	public boolean AtualizarAgenda(Agenda atual, Agenda nova) throws SQLException {
		String query = new String("update agenda set "
				+ "AGE_CODIGO = ?, AGE_PESSOA_CLIENTE = ?, "
				+ "AGE_PESSOA_USUARIO = ?, AGE_IMOVEL = ?, "
				+ "AGE_DATA = ?, AGE_HORA_INICIO = ?, "
				+ "AGE_HORA_FIM = ?, AGE_DESCRICAO = ? "
				+ "where AGE_CODIGO = ?");
		PreparedStatement ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, nova.getCodigo());
		ps.setInt(2, nova.getCliente().getCodigoPessoa());
		ps.setInt(3, nova.getCorretor().getCodigoPessoa());
		//ps.setInt(4, nova.getImovel().getCodigoImovel());
		ps.setDate(5, nova.getData());
		ps.setTime(6, nova.getHoraInicio());
		ps.setTime(7, nova.getHoraFim());
		ps.setString(8, nova.getDescricao());
		ps.setInt(9, atual.getCodigo());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		return result;
	}
	
	/**
	 * Deleta o registro na tabela Agenda representado pelo objeto {@link Agenda} passado por parâmetro.
	 * 
	 * @param a - {@link Agenda} registro que será deletado.
	 * @return <code>true</code> caso a exclusão seja realizada com sucesso
	 *         ou <code>false</code> caso contrário.
	 * @throws SQLException
	 */
	public boolean ApagarAgenda(Agenda a) throws SQLException{
		String query = new String("delete from agenda where AGE_CODIGO = ?");
		PreparedStatement ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, a.getCodigo());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		return result;
	}
	
	/**
	 * Insere o registro na tabela Agenda representado pelo objeto {@link Agenda} passado por parâmetro.
	 * 
	 * @param a - {@link Agenda} que será inserida no banco de dados.
	 * @return <code>true</code> caso a insersão seja realizada com sucesso
	 *         ou <code>false</code> caso contrário.
	 * @throws SQLException
	 */
	public boolean InserirAgenda(Agenda a) throws SQLException{
		String query = new String("insert into agenda "
				+ "(AGE_PESSOA_CLIENTE, AGE_PESSOA_USUARIO, "
				+ "AGE_IMOVEL, AGE_DATA, AGE_HORA_INICIO, "
				+ "AGE_HORA_FIM, AGE_DESCRICAO) "
				+ "values (?, ?, ?, ?, ?, ?, ?)");
		PreparedStatement ps = DB.getConn().prepareStatement(query);
		
		ps.setInt(1, a.getCliente().getCodigoPessoa());
		ps.setInt(2, a.getCorretor().getCodigoPessoa());
		//ps.setInt(3, nova.getImovel().getCodigoImovel());
		ps.setDate(4, a.getData());
		ps.setTime(5, a.getHoraInicio());
		ps.setTime(6, a.getHoraFim());
		ps.setString(7, a.getDescricao());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		return result;
	}
}