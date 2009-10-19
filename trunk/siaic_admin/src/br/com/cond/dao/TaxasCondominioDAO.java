package br.com.cond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.cond.businesslogic.TaxasCondominio;
import br.com.siaic.dao.FabricaConexao;

public class TaxasCondominioDAO {

	private Connection conexao = null;

	public TaxasCondominioDAO() {
		FabricaConexao.getInstancia();
	}

	public boolean addTaxaCondominio(TaxasCondominio taxas) {
		try {
			this.conexao = FabricaConexao.conectar();
		} catch (SQLException e1) {
			e1.getLocalizedMessage();
		}

		if (!checarExistenciaTaxa(taxas.getMes(), taxas.getAno(), taxas
				.getApartamento().getCodigoApartamento())) {
			return false;
		} else {

			String sql = "INSERT INTO ADMCON_TAXA_CONDOMINIO_FATOS "
					+ "(COF_APA_CODIGO, COF_MES, COF_ANO, COF_VALOR,"
					+ " COF_DATA_VENCIMENTO) VALUES(?, ?, ?, ?, ?);";

			try {
				PreparedStatement ps = this.conexao.prepareStatement(sql);
				ps.setInt(1, taxas.getApartamento().getCodigoApartamento());
				ps.setString(2, taxas.getMes());
				ps.setString(3, taxas.getAno());
				ps.setDouble(4, taxas.getValor());
				ps.setDate(5, java.sql.Date.valueOf(taxas.getDataVencimento().toString()));
				ps.execute();
				ps.close();

			} catch (SQLException e) {
				e.getLocalizedMessage();
			} finally {
				try {
					this.conexao.close();
				} catch (SQLException e) {
					e.getLocalizedMessage();
				}
			}
		}
		return true;
	}

	public boolean setarPagamentoTaxa(TaxasCondominio taxas) {
		try {
			this.conexao = FabricaConexao.conectar();
		} catch (SQLException e1) {
			e1.getLocalizedMessage();
		}

		String sql = "UPDATE ADMCON_TAXA_CONDOMINIO_FATOS SET"
				+ " COF_PAGO = ?, COF_DATA_PAGAMENTO = ?"
				+ " WHERE COF_MES = ? AND COF_ANO= ? AND COF_APA_CODIGO = ?";

		try {
			PreparedStatement ps = this.conexao.prepareStatement(sql);
			ps.setString(1, taxas.getEstaPago().toString());
			ps.setDate(2, java.sql.Date.valueOf(taxas.getDataPagamento().toString()));
			ps.setString(3, taxas.getMes());
			ps.setString(4, taxas.getAno());
			ps.setInt(5, taxas.getApartamento().getCodigoApartamento());

			if (ps.execute()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				this.conexao.close();
			} catch (SQLException e) {
				e.getLocalizedMessage();
			}
		}
		return false;

	}

	public boolean checarExistenciaTaxa(String mes, String ano,
			int codigoApartamento) {
		String sql = "SELECT DISTINCT COF_MES, COF_ANO, COF_APA_CODIGO FROM ADMCON_TAXA_CONDOMINIO_FATOS"
				+ " WHERE COF_MES = ? AND COF_ANO = ? AND COF_APA_CODIGO = ?  ;";

		try {
			PreparedStatement ps = this.conexao.prepareStatement(sql);
			ps.setString(1, mes);
			ps.setString(2, ano);
			ps.setInt(3, codigoApartamento);

			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				if (rs.getString("COF_MES").equals(mes) && rs.getString("COF_ANO").equals(ano)
						&& rs.getInt("COF_APA_CODIGO") == codigoApartamento) {
					return false;
				}
			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<TaxasCondominio> getAll() throws SQLException {
		try {
			this.conexao = FabricaConexao.conectar();
		} catch (SQLException e1) {
			e1.getLocalizedMessage();
		}

		String sql = "SELECT COF_MES, COF_ANO, COF_VALOR, COF_DATA_VENCIMENTO "
				+ "FROM ADMCON_TAXA_CONDOMINIO_FATOS "
				+ "GROUP BY COF_ANO, COF_MES";

		try {
			PreparedStatement ps = this.conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<TaxasCondominio> todasTaxas = new ArrayList<TaxasCondominio>();
			while (rs.next()) {
				TaxasCondominio tx = new TaxasCondominio();
				tx.setAno(new SimpleDateFormat("yyyy").format(rs.getDate("COF_ANO")));
				tx.setMes(rs.getString("COF_MES"));
				tx.setValor(rs.getDouble("COF_VALOR"));
				tx.setDataVencimento(rs.getDate("COF_DATA_VENCIMENTO"));
				todasTaxas.add(tx);
			}

			ps.close();
			rs.close();
			
			return todasTaxas;

		}finally{
			this.conexao.close();
		}
		
	}

	public List<TaxasCondominio> getTaxasTodosApartamentos() throws SQLException {
		try {
			this.conexao = FabricaConexao.conectar();
		} catch (SQLException e1) {
			e1.getLocalizedMessage();
		}

		String sql = "SELECT COF_MES, COF_ANO, COF_APA_CODIGO, COF_VALOR, COF_PAGO,"
				+ " COF_DATA_VENCIMENTO, COF_DATA_PAGAMENTO FROM ADMCON_TAXA_CONDOMINIO_FATOS "
				+ "ORDER BY COF_ANO";

		try {
			PreparedStatement ps = this.conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<TaxasCondominio> todasTaxas = new ArrayList<TaxasCondominio>();
			while (rs.next()) {
				TaxasCondominio tx = new TaxasCondominio();
				tx.setAno(new SimpleDateFormat("yyyy").format(rs.getDate("COF_ANO")));
				tx.setMes(rs.getString("COF_MES"));
				tx.setValor(rs.getDouble("COF_VALOR"));
				tx.setEstaPago(rs.getString("COF_PAGO").charAt(0));
				tx.setDataVencimento(rs.getDate("COF_DATA_VENCIMENTO"));
				tx.setDataPagamento(rs.getDate("COF_DATA_PAGAMENTO"));
				tx.setApartamento(new ApartamentoDAO().getApartamentoId(rs.getInt("COF_APA_CODIGO")));
				todasTaxas.add(tx);
			}

			ps.close();
			rs.close();
			
			return todasTaxas;

		}finally{
			this.conexao.close();
		}
		
	}
	
	public boolean update(TaxasCondominio taxas){
		try {
			this.conexao = FabricaConexao.conectar();
		} catch (SQLException e1) {
			e1.getLocalizedMessage();
		}
		
		String sql = "UPDATE ADMCON_TAXA_CONDOMINIO_FATOS SET COF_DATA_VENCIMENTO = ?, COF_VALOR = ? "
					+"WHERE COF_MES = ? AND COF_ANO = ? ";
		
		try{
			PreparedStatement ps = this.conexao.prepareStatement(sql);
			ps.setDate(1, java.sql.Date.valueOf(taxas.getDataVencimento().toString()));
			ps.setDouble(2, taxas.getValor());
			ps.setString(3, taxas.getMes());
			ps.setString(4, taxas.getAno());
			
			return  ps.executeUpdate() > 0 ? true : false;
			
		} catch (SQLException e) {		
			e.getLocalizedMessage();
		}finally{
			
		}
		return false;
		
	}
}
