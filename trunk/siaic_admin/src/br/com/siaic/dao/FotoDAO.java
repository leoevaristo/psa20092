package br.com.siaic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Foto;
import br.com.siaic.businesslogic.Imovel;

public class FotoDAO {
	private Query conn;

	public FotoDAO() {
		this.conn = new Query();
	}

	public List<Foto> getFotos(int codigoImovel) {
		List<Foto> fotos = new ArrayList<Foto>();
		PreparedStatement ps = this.conn
				.getPreparedStatement("SELECT * FROM FOTO WHERE FOT_IMO_CODIGO = ?;");
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Foto foto = new Foto();
				foto.setData(rs.getBytes("FOT_DATA"));
				foto.setLength(rs.getInt("FOT_TAMANHO"));
				foto.setName(rs.getString("FOT_NOME"));
				fotos.add(foto);
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
		return fotos;
	}

	public void salvaFoto(Foto foto, int codigoImovel) throws SQLException {
		PreparedStatement ps = this.conn
				.getPreparedStatement("insert into FOTO values (?,?,?,?);");
		try {
			ps.setInt(1, codigoImovel);
			ps.setString(2, foto.getName());
			ps.setLong(3, foto.getLength());
			ps.setBytes(4, foto.getData());
			
			ps.execute();
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
	
}
