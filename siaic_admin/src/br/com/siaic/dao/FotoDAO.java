package br.com.siaic.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.siaic.businesslogic.Foto;

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
			ps.setInt(1, codigoImovel);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Foto foto = new Foto();
				foto.setImovel(rs.getInt("FOT_IMO_CODIGO"));
				foto.setCodigo(rs.getInt("FOT_CODIGO"));
				foto.setLength(rs.getInt("FOT_TAMANHO"));
				foto.setName(rs.getString("FOT_NOME"));
				
				String path = rs.getString("FOT_PATH");
				foto.setData(Foto.pegaFoto(path));
				
				fotos.add(foto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
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
				.getPreparedStatement("insert into FOTO (FOT_IMO_CODIGO,FOT_NOME,FOT_TAMANHO,FOT_PATH) values (?,?,?,?);");
		try {
			ps.setInt(1, foto.getImovel());
			ps.setString(2, foto.getName());
			ps.setLong(3, foto.getLength());
			String path = Foto.salvaFoto(foto);
			ps.setString(4, path);

			ps.execute();
		} catch (Exception e) {
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
