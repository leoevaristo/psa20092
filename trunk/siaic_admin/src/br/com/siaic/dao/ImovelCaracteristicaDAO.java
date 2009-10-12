package br.com.siaic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.ImovelCaracteristica;

/**
 * 
 * @author Yasmim Tamie Hiramoto Pereira
 * @version 1.0
 * 
 */

public class ImovelCaracteristicaDAO {
	private static ImovelCaracteristicaDAO instance;

	public static ImovelCaracteristicaDAO getInstance() {
		if (ImovelCaracteristicaDAO.instance == null) {
			ImovelCaracteristicaDAO.instance = new ImovelCaracteristicaDAO();
		}
		return ImovelCaracteristicaDAO.instance;
	}

	private ImovelCaracteristicaDAO(){
		
	}
	
	// get da tabela Imovel_Caracteristicas
	public ImovelCaracteristica getImovelCaracteristica(int codigo)
			throws SQLException {
		String query = new String(
				"select * from IMOVEL_CARACTERISTICAS where IMC_CODIGO = ?");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, codigo);
		ResultSet rs = ps.executeQuery();

		ImovelCaracteristica ic = null;

		if (rs.first()) {
			ic = new ImovelCaracteristica();
			ic.setCodigo(rs.getInt("IMC_CODIGO"));
			ic.setPiscina(rs.getString("IMC_PISCINA").charAt(0));
			ic.setQtdeDormitorio(rs.getInt("IMC_DORMITORIOS_QTDE"));
			ic.setQtdeGaragem(rs.getInt("IMC_VAGAS_GARAGEM_QTDE"));
			ic.setQtdeSuite(rs.getInt("IMC_SUITES_QTDE"));
			ic.setPiscina(rs.getString("IMC_PISCINA").charAt(0));
		}
		ps.close();
		rs.close();

		return ic;
	}

	// Insert da tabela Imovel_Caracteristicas
	public boolean addCaracteristica(ImovelCaracteristica ic)
			throws SQLException {
		String query = new String(
				"insert into IMOVEL_CARACTERISTICAS (IMC_DORMITORIOS_QTDE, IMC_SUITES_QTDE, IMC_VAGAS_GARAGEM_QTDE, IMC_PISCINA) ")
				+ "values (?, ?, ?, ?)";
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);

		ps.setInt(1, ic.getQtdeDormitorio());
		ps.setInt(2, ic.getQtdeSuite());
		ps.setInt(3, ic.getQtdeGaragem());
		ps.setString(4, Character.toString(ic.getPiscina()));

		boolean result = ps.executeUpdate() > 0;
		ps.close();

		return result;
	}

	// Listagem da tabela Imovel_Caracteristicas
	public List<ImovelCaracteristica> getImovelCaracteristicaList()
			throws SQLException {
		String query = new String("select * from IMOVEL_CARACTERISTICAS");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		List<ImovelCaracteristica> l = new ArrayList<ImovelCaracteristica>();
		ImovelCaracteristica ic = null;

		while (rs.next()) {
			ic = new ImovelCaracteristica();
			ic.setCodigo(rs.getInt("IMC_CODIGO"));
			ic.setPiscina(rs.getString("IMC_PISCINA").charAt(0));
			ic.setQtdeDormitorio(rs.getInt("IMC_DORMITORIOS_QTDE"));
			ic.setQtdeGaragem(rs.getInt("IMC_VAGAS_GARAGEM_QTDE"));
			ic.setQtdeSuite(rs.getInt("IMC_SUITES_QTDE"));
			ic.setPiscina(rs.getString("IMC_PISCINA").charAt(0));
			l.add(ic);
		}
		rs.close();
		ps.close();
		return l;
	}

	// Delete da tabela Imovel_Caracteristicas
	public boolean delImovelCaracteristica(ImovelCaracteristica ic)
			throws SQLException {
		String query = new String(
				"delete from IMOVEL_CARACTERISTICAS where IMC_CODIGO = ? ");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, ic.getCodigo());

		boolean result = ps.executeUpdate() > 0;
		ps.close();

		return result;
	}

	// Update da tabela Imovel_Caracteristicas
	public boolean altImovelCaracteristica(ImovelCaracteristica icAtual,
			ImovelCaracteristica icNovo) throws SQLException {
		String query = new String(
				"update IMOVEL_CARACTERISTICAS "
						+ "set IMC_CODIGO = ?, IMC_DORMITORIOS_QTDE = ?, IMC_SUITES_QTDE = ?, "
						+ "IMC_VAGAS_GARAGEM_QTDE = ?, IMC_PISCINA = ? "
						+ "where IMC_CODIGO = ?");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);

		ps.setInt(1, icNovo.getCodigo());
		ps.setInt(2, icNovo.getQtdeDormitorio());
		ps.setInt(3, icNovo.getQtdeSuite());
		ps.setInt(4, icNovo.getQtdeGaragem());
		ps.setString(5, Character.toString(icNovo.getPiscina()));
		ps.setInt(6, icAtual.getCodigo());

		boolean result = ps.executeUpdate() > 0;
		ps.close();
		return result;
	}

	public List<ImovelCaracteristica> getAllCaracteristica(int codigoImovel)
			throws SQLException {
		PreparedStatement ps = null;
		
		List<ImovelCaracteristica> listaAllCaracteristica = new ArrayList<ImovelCaracteristica>();
		
		String query = new String(
				"select IMC_CODIGO, IMC_DORMITORIOS_QTDE, IMC_SUITES_QTDE, "+
						"IMC_VAGAS_GARAGEM_QTDE, IMC_PISCINA "+
						"from IMOVEL_CARACTERISTICAS where IMC_CODIGO = select("+
						"IMO_CARACTERISTICA from IMOVEL	where IMO_IMOVEL = ?");
		try {
			ps = DB.getConn().prepareStatement(query);
			ps.setInt(1, codigoImovel);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ImovelCaracteristica ic = new ImovelCaracteristica();
				ic.setCodigo(rs.getInt("IMC_CODIGO"));
				ic.setQtdeDormitorio(rs.getInt("IMC_DORMITORIOS_QTDE"));
				ic.setQtdeSuite(rs.getInt("IMC_SUITE_QTDE"));
				ic.setPiscina(rs.getString("IMC_PISCINA").charAt(0));

				listaAllCaracteristica.add(ic);
			}
		} finally {
			ps.close();
		}
		return listaAllCaracteristica;
	}
}