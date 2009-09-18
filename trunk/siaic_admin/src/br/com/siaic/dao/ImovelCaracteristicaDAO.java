package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.siaic.businesslogic.ImovelCaracteristica;
import br.com.siaic.businesslogic.Perfil;

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

	// Select geral da tabela Imovel_Caracteristicas.
	public ImovelCaracteristica getImovelCaracteristica(int codigo)
			throws SQLException {
		String query = new String(
				"select * from imovel_caracteristicas where IMC_CODIGO = ?");
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
		}
		ps.close();
		rs.close();
		
		return ic;
	}

	// Inserção na tabela Imovel_Caracteristicas.
	public boolean CadastrarCaracteristica(ImovelCaracteristica ic)
			throws SQLException {
		String query = new String(
				"insert into IMOVEL_CARACTERISTICAS (IMC_CODIGO, IMC_DORMITORIOS_QTDE, IMC_SUITES_QTDE, IMC_VAGAS_GARAGEM_QTDE)")
				+ "values (?, ?, ?, ?, ?)";
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);

		ps.setInt(1, ic.getCodigo());
		ps.setInt(2, ic.getQtdeDormitorio());
		ps.setInt(3, ic.getQtdeSuite());
		ps.setInt(4, ic.getQtdeGaragem());
		ps.setString(5, Character.toString(ic.getPiscina()));

		ps.execute();
		ps.close();
		
		return false;
	}
	// Select do das caracteristicas de imovel atraves do pessoa_cliente.
	public ImovelCaracteristica buscaImovelCaracteristica(int codigo)throws SQLException {
		String query = new String(
				"select * from IMOVEL_CARACTERISTICAS where IMC_CODIGO = "+
				"(select PRF_IMOVEL_CARACTERISTICA from PERFIL where PRF_PESSOA_CLIENTE = ?)");
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
		}
		ps.close();
		rs.close();
		
		return ic;
	}
	//Deleta a caracteristica do imovel passando o codigo do perfil
	public void delImovel(Perfil perfil) throws SQLException{
		String query = new String(
		"delete from IMOVEL_CARACTERISTICAS where IMC_CODIGO in "
		+ "(select PRF_IMOVEL_CARACTERISTICA from PERFIL where PRF_CODIGO = ?)");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);
		ps.execute();	
		ps.close();
	}
}
