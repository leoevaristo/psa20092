package br.com.siaic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Imovel;

/**
 * 
 * @author Filipe
 * 
 */
public class ImovelDAO {
	private Query conn;

	public ImovelDAO() {
		this.conn = new Query();

	}

	/**
	 * Retorna o im�vel do c�digo passado, do contr�rio retorna null.
	 * 
	 * @param codigo
	 * @return Imovel ou null.
	 */
	public Imovel getImovel(int codigo) {
		PreparedStatement ps = this.conn
				.getPreparedStatement("SELECT * FROM IMOVEL WHERE IMO_CODIGO = ?;");
		try {
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Imovel imo = new Imovel(rs.getInt("IMO_CODIGO"));
				imo.setCaracteristica(rs.getInt("IMO_CARACTERISTICA"));
				imo.setFinalidade(rs.getInt("IMO_FINALIDADE"));
				imo.setDetalhe(rs.getString("IMO_DETALHE"));
				imo.setFormaPagamento(rs.getString("IMO_FORMA_PAGAMENTO"));
				imo.setTipo(rs.getInt("IMO_TIPO"));
				imo.setValor(rs.getInt("IMO_VALOR"));
				imo.setValorCondominio(rs.getInt("IMO_VALOR_CONDOMINIO"));
				imo.setProprietario(rs.getInt("IMO_CLIENTE"));
				imo.setEndereco(rs.getInt("IMO_ENDERECO"));
				return imo;
			} else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Imovel> getImoveis() {

		List<Imovel> imos = new ArrayList<Imovel>();
		PreparedStatement ps = this.conn
				.getPreparedStatement("SELECT * FROM IMOVEL, ENDERECO WHERE IMOVEL.IMO_ENDERECO = ENDERECO.END_CODIGO;");
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Imovel imo = new Imovel(rs.getInt("IMO_CODIGO"));
				imo.setCaracteristica(rs.getInt("IMO_CARACTERISTICA"));
				imo.setFinalidade(rs.getInt("IMO_FINALIDADE"));
				imo.setDetalhe(rs.getString("IMO_DETALHE"));
				imo.setFormaPagamento(rs.getString("IMO_FORMA_PAGAMENTO"));
				imo.setTipo(rs.getInt("IMO_TIPO"));
				imo.setValor(rs.getInt("IMO_VALOR"));
				imo.setValorCondominio(rs.getInt("IMO_VALOR_CONDOMINIO"));
				imo.setProprietario(rs.getInt("IMO_CLIENTE"));
				imo.setEndereco(rs.getInt("IMO_ENDERECO"));
				imos.add(imo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return imos;
	}

	public List<Imovel> getImovesPorCidade(String cidade) {
		return this.getImoveisPorFiltro(" AND CID_NOME LIKE ? ", "%" + cidade
				+ "%");
	}

	public List<Imovel> getImovesPorBairro(String bairro) {
		return this.getImoveisPorFiltro(" AND BAR_NOME LIKE ? ", "%" + bairro
				+ "%");
	}

	public List<Imovel> getImovesPorEndereco(String endereco) {
		return this.getImoveisPorFiltro(" AND END_NOME LIKE ? ", "%" + endereco
				+ "%");
	}

	public List<Imovel> getImovesPorValor(String valor) {
		return this.getImoveisPorFiltro(" AND IMO_VALOR = ? ", valor);
	}

	public List<Imovel> getImovesPorCliente(String cliente) {
		return this.getImoveisPorFiltro(" AND PES_NOME LIKE ? ", "%" + cliente
				+ "%");
	}

	protected List<Imovel> getImoveisPorFiltro(String like, Object value) {

		List<Imovel> imos = new ArrayList<Imovel>();

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * ");
		sb
				.append(" FROM IMOVEL, ENDERECO, BAIRRO, CIDADE, PESSOA, PESSOA_CLIENTE ");
		sb.append(" WHERE IMO_ENDERECO = END_CODIGO ");
		sb.append(" AND END_BAIRRO = BAR_CODIGO ");
		sb.append(" AND BAR_CIDADE = CID_CODIGO ");
		sb.append(" AND IMO_CLIENTE = PEC_CODIGO ");
		sb.append(" AND PEC_CODIGO = PES_CODIGO ");
		sb.append(like);

		PreparedStatement ps = this.conn.getPreparedStatement(sb.toString());

		try {
			ps.setObject(1, value);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Imovel imo = new Imovel(rs.getInt("IMO_CODIGO"));
				imo.setCaracteristica(rs.getInt("IMO_CARACTERISTICA"));
				imo.setFinalidade(rs.getInt("IMO_FINALIDADE"));
				imo.setDetalhe(rs.getString("IMO_DETALHE"));
				imo.setFormaPagamento(rs.getString("IMO_FORMA_PAGAMENTO"));
				imo.setTipo(rs.getInt("IMO_TIPO"));
				imo.setValor(rs.getInt("IMO_VALOR"));
				imo.setValorCondominio(rs.getInt("IMO_VALOR_CONDOMINIO"));
				imo.setProprietario(rs.getInt("IMO_CLIENTE"));
				imo.setEndereco(rs.getInt("IMO_ENDERECO"));
				imos.add(imo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return imos;
	}

	public void atualiza(Imovel imo) {
		PreparedStatement ps = this.conn
				.getPreparedStatement("update IMOVEL set "
						+ " IMO_CARACTERISTICA = ?," + " IMO_FINALIDADE = ?,"
						+ " IMO_DETALHE = ?," + " IMO_FORMA_PAGAMENTO = ?,"
						+ " IMO_TIPO = ?," + " IMO_VALOR = ?,"
						+ " IMO_VALOR_CONDOMINIO = ?," + " IMO_CLIENTE = ?,"
						+ " IMO_ENDERECO = ?" + " where imo_codigo = ?;");
		try {
			ps.setInt(1, imo.getCaracteristica());
			ps.setInt(2, imo.getFinalidade());
			ps.setString(3, imo.getDetalhe());
			ps.setString(4, imo.getFormaPagamento());
			ps.setInt(5, imo.getTipo());
			ps.setDouble(6, imo.getValor());
			ps.setDouble(7, imo.getValorCondominio());
			ps.setInt(8, imo.getProprietario());
			ps.setInt(9, imo.getEndereco());
			ps.setInt(10, imo.getCodigo());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void salva(Imovel imo) throws SQLException {
		PreparedStatement ps = this.conn
				.getPreparedStatement("insert into IMOVEL ("
						+ " IMO_CARACTERISTICA," + " IMO_FINALIDADE,"
						+ " IMO_DETALHE," + " IMO_FORMA_PAGAMENTO,"
						+ " IMO_TIPO," + " IMO_VALOR,"
						+ " IMO_VALOR_CONDOMINIO,"
						+ " IMO_CLIENTE,IMO_ENDERECO)"
						+ " values (?,?,?,?,?,?,?,?,?);");
		try {
			ps.setInt(1, imo.getCaracteristica());
			ps.setInt(2, imo.getFinalidade());
			ps.setString(3, imo.getDetalhe());
			ps.setString(4, imo.getFormaPagamento());
			ps.setInt(5, imo.getTipo());
			ps.setDouble(6, imo.getValor());
			ps.setDouble(7, imo.getValorCondominio());
			ps.setInt(8, imo.getProprietario());
			ps.setInt(9, imo.getEndereco());
			ps.execute();
			this.setCodigoImovel(imo);
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new SQLException(e.getMessage());
			}
		}
	}

	private void setCodigoImovel(Imovel imo) {
		PreparedStatement ps = this.conn
				.getPreparedStatement("SELECT MAX(IMO_CODIGO) CODIGO_IMOVEL FROM IMOVEL;");
		try {
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				imo.setCodigo(rs.getInt("CODIGO_IMOVEL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void exclui(Imovel imo) {
		PreparedStatement ps = this.conn
				.getPreparedStatement("delete from IMOVEL where imo_codigo = ?;");
		try {
			ps.setInt(1, imo.getCodigo());
			ps.execute();
			new FotoDAO().apagaFotos(imo.getCodigo());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Retorna uma lista do tipo Imovel filtrada pelo Perfil do Cliente
	 * 
	 * @param codigoCliente
	 * @return List<Imovel>
	 * @author carlos
	 */
	public List<Imovel> getImoveisPorPerfilCliente(int codigoCliente) {

		String sql = "SELECT p.PRF_PESSOA_CLIENTE, p.PRF_IMOVEL_CARACTERISTICA, i.IMO_CARACTERISTICA,"
				+ " i.IMO_VALOR, i.IMO_ENDERECO, i.IMO_DETALHE, i.IMO_TIPO, i.IMO_VALOR_CONDOMINIO, i.IMO_FINALIDADE,"
				+ " i.IMO_FORMA_PAGAMENTO, i.IMO_CODIGO FROM PERFIL p, IMOVEL i WHERE p.PRF_PESSOA_CLIENTE = ?"
				+ " AND p.PRF_IMOVEL_CARACTERISTICA = i.IMO_CARACTERISTICA;";

		try {

			PreparedStatement ps = this.conn.getPreparedStatement(sql);
			ps.setInt(1, codigoCliente);

			ResultSet rs = ps.executeQuery();

			List<Imovel> listaImoveisPerfil = new ArrayList<Imovel>();

			while (rs.next()) {

				Imovel imovel = new Imovel();
				imovel.setCodigo(rs.getInt("IMO_CODIGO"));
				imovel.setCaracteristica(rs.getInt("IMO_CARACTERISTICA"));
				imovel.setTipo(rs.getInt("IMO_TIPO"));
				imovel.setValor(rs.getDouble("IMO_VALOR"));
				imovel.setFinalidade(rs.getInt("IMO_FINALIDADE"));
				imovel.setDetalhe(rs.getString("IMO_DETALHE"));
				imovel.setEndereco(rs.getInt("IMO_ENDERECO"));
				imovel.setFormaPagamento(rs.getString("IMO_FORMA_PAGAMENTO"));
				imovel.setValorCondominio(rs.getDouble("IMO_VALOR_CONDOMINIO"));

				listaImoveisPerfil.add(imovel);
			}

			ps.close();
			rs.close();

			return listaImoveisPerfil;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Método que retorna uma Lista de objetos do tipo Imovel que pertencem a
	 * uma entrada específica de Agenda
	 * 
	 * @param agendaCodigo
	 * @return List<Imovel>
	 * @author carlos
	 */
	public List<Imovel> getImoveisAgenda(int agendaCodigo) {

		String sql = "SELECT ima.IMA_IMOVEL_CODIGO, imo.IMO_CODIGO, imo.IMO_DETALHE,"
				+ " imo.IMO_ENDERECO, imo.IMO_CARACTERISTICA,imo.IMO_FORMA_PAGAMENTO, imo.IMO_VALOR FROM IMOVEL_AGENDA ima, IMOVEL imo "
				+ " WHERE ima.IMA_AGENDA_CODIGO = ? AND ima.IMA_IMOVEL_CODIGO = imo.IMO_CODIGO";

		try {
			PreparedStatement ps = this.conn.getPreparedStatement(sql);
			ps.setInt(1, agendaCodigo);
			ResultSet rs = ps.executeQuery();

			List<Imovel> imoveisAgenda = new ArrayList<Imovel>();

			while (rs.next()) {
				Imovel imo = new Imovel();
				imo.setCodigo(rs.getInt("IMO_CODIGO"));
				imo.setEndereco(rs.getInt("IMO_ENDERECO"));
				imo.setCaracteristica(rs.getInt("IMO_CARACTERISTICA"));
				imo.setDetalhe(rs.getString("IMO_DETALHE"));
				imo.setFormaPagamento(rs.getString("IMO_FORMA_PAGAMENTO"));
				imo.setValor(rs.getDouble("IMO_VALOR"));
				imoveisAgenda.add(imo);
			}

			ps.close();
			rs.close();

			return imoveisAgenda;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Retorna uma lista com os identificadores únicos de cada imóvel
	 * pertecente a uma entrada da Agenda
	 * 
	 * @param agendaCodigo
	 * @return List<String>
	 * @author carlos
	 */
	public List<String> getIndiceImoveisAgenda(int agendaCodigo) {

		String sql = "SELECT ima.IMA_IMOVEL_CODIGO, imo.IMO_CODIGO "
				+ " FROM IMOVEL_AGENDA ima, IMOVEL imo"
				+ " WHERE ima.IMA_AGENDA_CODIGO = ? AND ima.IMA_IMOVEL_CODIGO = imo.IMO_CODIGO";

		try {
			PreparedStatement ps = this.conn.getPreparedStatement(sql);
			ps.setInt(1, agendaCodigo);
			ResultSet rs = ps.executeQuery();

			List<String> imoveisAgenda = new ArrayList<String>();

			while (rs.next()) {
				String codigo = "";
				codigo = String.valueOf(rs.getInt("IMO_CODIGO"));

				imoveisAgenda.add(codigo);
			}

			ps.close();
			rs.close();

			return imoveisAgenda;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Retorna o número de imóveis registrados em uma entrada da Agenda
	 * 
	 * @param codigoAgenda
	 * @return int
	 * @author carlos
	 */
	public int getNumeroImoveisAgenda(int codigoAgenda) {

		String sql = "SELECT COUNT(IMA_IMOVEL_CODIGO) AS QTD FROM IMOVEL_AGENDA WHERE "
				+ "IMA_AGENDA_CODIGO = ?";

		try {
			PreparedStatement ps = this.conn.getPreparedStatement(sql);
			ps.setInt(1, codigoAgenda);
			ResultSet rs = ps.executeQuery();
			int qtdImoveis = 0;
			while (rs.next()) {
				qtdImoveis = rs.getInt("QTD");
			}

			ps.close();
			rs.close();

			return qtdImoveis;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
