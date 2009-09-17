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
			ps.setInt(1, codigoImovel);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
	    	    return rs.getInt("IMO_CARACTERISTICA");
			else
				return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	public int getCodigoPerfil(int codigoCaracteristica) {
		PreparedStatement ps = this.conn.getPreparedStatement("select PRF_CODIGO from PERFIL where PRF_IMOVEL_CARACTERISTICA = ?");
    	try {
			ps.setInt(1, codigoCaracteristica);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			    return rs.getInt("PRF_CODIGO");
			else
				return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getClientePerfil(int codigoPerfil) {
		PreparedStatement ps = this.conn.getPreparedStatement("select PRF_PESSOA_CLIENTE from PERFIL where PRF_CODIGO = ?");
    	try {
			ps.setInt(1, codigoPerfil);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
	    	    return rs.getInt("PRF_PESSOA_CLIENTE");
			else
				return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getCorretorPerfil(int codigoPerfil) {
		PreparedStatement ps = this.conn.getPreparedStatement("select PRF_USUARIO from PERFIL where PRF_CODIGO = ?");
    	try {
			ps.setInt(1, codigoPerfil);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
	    	    return rs.getInt("PRF_USUARIO");
			else
				return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void notificar(int codigoCliente, int codigoCorretor, int codigoImovel) {
		PreparedStatement ps = 
			this.conn.getPreparedStatement("insert into AGENDA (AGE_PESSOA_CLIENTE,AGE_PESSOA_USUARIO,AGE_IMOVEL) values (?,?,?)");
    	try {
			ps.setInt(1, codigoCliente);
			ps.setInt(2, codigoCorretor);
			ps.setInt(3, codigoImovel);
			//ps.setString(4, "Agendamento automático, gerado por notificação.");
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
	
}
