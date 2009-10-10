package br.com.siaic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Perfil;

/**
 * 
 * @author Yasmim Tamie Hiramoto Pereira
 * @version 1.0
 *  
 */

public class PerfilDAO {
	private static PerfilDAO instance;
	
	private static ClienteDAO cdao = new ClienteDAO();
	private static UsuarioDAO udao = new UsuarioDAO();
		 
	public static PerfilDAO getInstance() {
		 if (PerfilDAO.instance == null) {
		  PerfilDAO.instance = new PerfilDAO();
		 }
		 return PerfilDAO.instance;
	}
	//Busca de perfil por cliente
	public List<Perfil> getPerfil(Cliente c) throws SQLException{
		String query = "select * from PERFIL where PRF_PESSOA_CLIENTE = ?";
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);
		ps.setInt(1, c.getCodigoPessoa());
		ResultSet rs = ps.executeQuery();
		
		List<Perfil> l = new ArrayList<Perfil>();
		
		Perfil p = null;
		while (rs.next()) {
			p = new Perfil();
			p.setCodigo(rs.getInt("PRF_CODIGO"));
			p.setCliente(cdao.getClientePorId(rs.getInt("PRF_PESSOA_CLIENTE")));
			p.setImovelCaracteristica(ImovelCaracteristicaDAO.getInstance().getImovelCaracteristica(rs.getInt("PRF_IMOVEL_CARACTERISTICA")));
			p.setUsuario(udao.getUsuarioId(rs.getInt("PRF_USUARIO")));
			l.add(p);
		}
		rs.close();
		ps.close();
		return l;
	}
			
	// Insert da tabela Perfil.
	public boolean addPerfil(Perfil p)
			throws SQLException {
		String query = new String(
				"insert into PERFIL (PRF_PESSOA_CLIENTE, PRF_IMOVEL_CARACTERISTICA, PRF_USUARIO) ")
				+ "values (?, ?, ?)";
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);

		ps.setInt(1, p.getCliente().getCodigoPessoa());
		ps.setInt(2, p.getImovelCaracteristica().getCodigo());
		ps.setInt(3, p.getUsuario().getCodigoPessoa());

		boolean result = ps.executeUpdate() > 0;
		ps.close();
		
		return result;
	}
	//Update da tabela Perfil
	public boolean altPerfil(Perfil pAtual, Perfil pNovo) throws SQLException{
		String query = new String(
		"update PERFIL "+
		"set PRF_CODIGO = ?, PRF_PESSOA_CLIENTE = ?, PRF_IMOVEL_CARACTERISTICA = ?, "+
		"PRF_USUARIO = ? "+
		"where PRF_CODIGO = ?");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);
		
		ps.setInt(1, pNovo.getCodigo());
		ps.setInt(2, pNovo.getCliente().getCodigoPessoa());
		ps.setInt(3, pNovo.getImovelCaracteristica().getCodigo());
		ps.setInt(4, pNovo.getUsuario().getCodigoPessoa());
		ps.setInt(5, pAtual.getCodigo());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		return result;
	}
	//Delete da tabela Perfil
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
	// Listagem da tabela Perfil
	public List<Perfil> getPerfilList()
			throws SQLException {
		String query = new String("select * from perfil");
		PreparedStatement ps;
		ps = DB.getConn().prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		List<Perfil> l = new ArrayList<Perfil>();
		Perfil p = null;

		while (rs.next()) {
			p = new Perfil();
			p.setCodigo(rs.getInt("PRF_CODIGO"));
			p.setCliente(new ClienteDAO().getClientePorId(rs.getInt("PRF_PESSOA_CLIENTE")));
			p.setImovelCaracteristica(ImovelCaracteristicaDAO.getInstance().getImovelCaracteristica(rs.getInt("PRF_IMOVEL_CARACTERISTICA")));
			p.setUsuario(new UsuarioDAO().getUsuarioId(rs.getInt("PRF_USUARIO")));
			l.add(p);
		}
		rs.close();
		ps.close();
		return l;
	}
}