package br.com.siaic.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.siaic.businesslogic.ImovelFinalidade;

public class ImovelFinalidadeDAO {
    
	private Query conn;
	
	public ImovelFinalidadeDAO() {
		this.conn = new Query();
	}
	
	public ImovelFinalidade getImovelFinalidade(int codigo) {
	    PreparedStatement ps = this.conn.getPreparedStatement("select * from IMOVEL_FINALIDADE where IMF_CODIGO = ?;");
	    try {
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
			    ImovelFinalidade imf = new ImovelFinalidade(rs.getInt("IMF_CODIGO"));
			    imf.setNome(rs.getString("IMF_NOME"));
			    return imf;
			}
			else
				return null;
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
	}
	
    public List<ImovelFinalidade> getImovelFinalidade() {
    	PreparedStatement ps = this.conn.getPreparedStatement("select * from IMOVEL_FINALIDADE;");
    	List<ImovelFinalidade> lista = new ArrayList<ImovelFinalidade>();
	    try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			    ImovelFinalidade imf = new ImovelFinalidade(rs.getInt("IMF_CODIGO"));
			    imf.setNome(rs.getString("IMF_NOME"));
			    lista.add(imf);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
    
    public void atualizar(ImovelFinalidade imf) {
    	PreparedStatement ps = 
    		this.conn.getPreparedStatement("update IMOVEL_FINALIDADE set IMF_NOME = ? where IMF_CODIGO = ?;");
    	try {
    		ps.setString(1, imf.getNome());
    		ps.setInt(2, imf.getCodigo());
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
    
    public void deletar(int codigo) {
    	PreparedStatement ps = 
    		this.conn.getPreparedStatement("delete from IMOVEL_FINALIDADE where IMF_CODIGO = ?;");
    	try {
    		ps.setInt(1, codigo);
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
	
    public void salvar(ImovelFinalidade imf) {
    	PreparedStatement ps = 
    		this.conn.getPreparedStatement("insert into IMOVEL_FINALIDADE (IMF_NOME) values (?);");
    	try {
    		ps.setString(1, imf.getNome());
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
