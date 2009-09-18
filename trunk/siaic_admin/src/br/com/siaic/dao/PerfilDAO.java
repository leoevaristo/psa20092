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

public class PerfilDAO {
	private static PerfilDAO instance;
		 
	private static PerfilDAO getInstance() {
		 if (PerfilDAO.instance == null) {
		  PerfilDAO.instance = new PerfilDAO();
		 }
		 return PerfilDAO.instance;
	}
	// Inserção na tabela Perfil.
	public boolean CadastrarPerfil(Perfil p)
			throws SQLException {
		String query = new String(
				"insert into PERFIL (PRF_PESSOA_CLIENTE, PRF_IMOVEL_CARACTERISCA, PRF_USUARIO) ")
				+ "values (?, ?, ?)";
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);

		
		ps.setInt(1, p.getCodigoPessoaCliente());
		ps.setInt(2, p.getCodigoImovelCaracteristica());
		ps.setInt(3, p.getCodigoUsuario());

		boolean result = ps.executeUpdate() > 0;
		ps.close();
		
		return result;
	}
	//Update do perfil
	public boolean altPerfil(Perfil pAtual, Perfil pNovo) throws SQLException{
		String query = new String(
		"update PERFIL "+
		"set PRF_CODIGO = ?, PRF_PESSOA_CLIENTE = ?, PRF_IMOVEL_CARACTERISTICA = ?, "+
		"PRF_USUARIO = ? "+
		"where PRF_CODIGO = ?");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);
		
		ps.setInt(1, pNovo.getCodigo());
		ps.setInt(2, pNovo.getCodigoPessoaCliente());
		ps.setInt(3, pNovo.getCodigoImovelCaracteristica());
		ps.setInt(4, pNovo.getCodigoUsuario());
		ps.setInt(5, pAtual.getCodigo());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		return result;
	}
	//Deleta o perfil
	public boolean delPerfil(Perfil p) throws SQLException{
		String query = new String(
		"delete from PERFIL where PRF_CODIGO = ? ");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, p.getCodigo());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		
		return result;
	}

}
