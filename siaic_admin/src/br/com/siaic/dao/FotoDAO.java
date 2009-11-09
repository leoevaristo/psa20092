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
				foto.setImovel(rs.getInt("FOT_IMO_CODIGO"));
				foto.setCodigo(rs.getInt("FOT_CODIGO"));
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

	public void salvaFoto(Foto foto) throws SQLException {
		PreparedStatement ps = this.conn
				.getPreparedStatement("insert into FOTO (FOT_IMO_CODIGO,FOT_NOME,FOT_TAMANHO,FOT_DATA) values (?,?,?,?);");
		try {
			ps.setInt(1, foto.getImovel());
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

	public void apagaFoto(Foto foto) {
		PreparedStatement ps = this.conn
				.getPreparedStatement("delete from FOTO where FOT_CODIGO = ?, FOT_IMO_CODIGO = ?;");
		try {
			ps.setInt(1, foto.getCodigo());
			ps.setInt(2, foto.getImovel());
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

	public void apagaFotos(int codigoImovel) {
		PreparedStatement ps = this.conn
				.getPreparedStatement("delete from FOTO where FOT_IMO_CODIGO = ?;");
		try {
			ps.setInt(1, codigoImovel);
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
