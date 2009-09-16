package br.com.siaic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAONotificacao {

	private Query conn;

	public DAONotificacao() {
		this.conn = new Query();
	}
	
	public int getCodigoCaracteristaImovel(int codigoImovel) {
		PreparedStatement ps = this.conn.getPreparedStatement("select IMO_CARACTERISTICA from IMOVEL where IMO_CODIGO = ?");
    	try {
			ps.setInt(0, codigoImovel);
			ResultSet rs = ps.getResultSet();
	    	return rs.getInt("IMO_CARACTERISTICA");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getCodigoPerfil(int codigoCaracteristica) {
		PreparedStatement ps = this.conn.getPreparedStatement("select PRF_CODIGO from PERFIL where PRF_IMOVEL_CARACTERISTICA = ?");
    	try {
			ps.setInt(0, codigoCaracteristica);
			ResultSet rs = ps.getResultSet();
	    	return rs.getInt("PRF_CODIGO");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getClientePerfil(int codigoPerfil) {
		PreparedStatement ps = this.conn.getPreparedStatement("select PRF_PESSOA_CLIENTE from PERFIL where PRF_CODIGO = ?");
    	try {
			ps.setInt(0, codigoPerfil);
			ResultSet rs = ps.getResultSet();
	    	return rs.getInt("PRF_PESSOA_CLIENTE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getCorretorPerfil(int codigoPerfil) {
		PreparedStatement ps = this.conn.getPreparedStatement("select PRF_USUARIO from PERFIL where PRF_CODIGO = ?");
    	try {
			ps.setInt(0, codigoPerfil);
			ResultSet rs = ps.getResultSet();
	    	return rs.getInt("PRF_USUARIO");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void notificar(int codigoCliente, int codigoCorretor, int codigoImovel) {
		PreparedStatement ps = 
			this.conn.getPreparedStatement("insert into AGENDA (AGE_PESSOA_CLIENTE,AGE_PESSOA_USUARIO,AGE_IMOVEL) values (?,?,?)");
    	try {
			ps.setInt(0, codigoCliente);
			ps.setInt(1, codigoCorretor);
			ps.setInt(2, codigoImovel);
			//ps.setString(3, "Agendamento automático, gerado por notificação.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
