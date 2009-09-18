package br.com.siaic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Imovel;

/**
 * 
 * @author Filipe
 *
 */
public class ImovelDAO {
    private Query conn;
    
    public ImovelDAO() {
    	this.conn = new Query();
    }
    
    /**
     * Retorna o imóvel do código passado, do contrário retorna null.
     * @param codigo
     * @return Imovel ou null.
     */
    public Imovel getImovel(int codigo) {
    	PreparedStatement ps = this.conn.getPreparedStatement("select * from imovel where imo_codigo = ?;");
    	try {
			ps.setInt(1, codigo);
    		ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
	            Imovel imo = new Imovel(rs.getInt("IMO_CODIGO"));
	            imo.setCaracteristica(rs.getInt("IMO_CARACTERISTICA"));
	            imo.setFinalidade(rs.getInt("IMO_FINALIDADE"));
	            imo.setDetalhe(rs.getString("IMO_DETALHE"));
	            imo.setFormaPagamento(rs.getString("IMO_FORMA_PAGAMENTO"));
	            imo.setTipo(rs.getInt("IMO_TIPO"));
	            imo.setValor(rs.getInt("IMO_VALOR"));
	            imo.setValorCondominio(rs.getInt("IMO_VALOR_CONDOMINIO"));
	            imo.setProprietario(rs.getInt("IMO_CLIENTE"));
	            return imo;
			}
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public List<Imovel> getImoveis() {
    	List<Imovel> imos = new ArrayList<Imovel>();
    	PreparedStatement ps = this.conn.getPreparedStatement("select * from imovel;");
    	try {
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
	            Imovel imo = new Imovel(rs.getInt("IMO_CODIGO"));
	            imo.setCaracteristica(rs.getInt("IMO_CARACTERISTICA"));
	            imo.setFinalidade(rs.getInt("IMO_FINALIDADE"));
	            imo.setDetalhe(rs.getString("IMO_DETALHE"));
	            imo.setFormaPagamento(rs.getString("IMO_FORMA_PAGAMENTO"));
	            imo.setTipo(rs.getInt("IMO_TIPO"));
	            imo.setValor(rs.getInt("IMO_VALOR"));
	            imo.setValorCondominio(rs.getInt("IMO_VALOR_CONDOMINIO"));
	            imo.setProprietario(rs.getInt("IMO_CLIENTE"));
	            imos.add(imo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    	return imos;
    }
    
    public void atualiza(Imovel imo) {
    	PreparedStatement ps = this.conn.getPreparedStatement
    	  ("update imovel set " +
    			" IMO_CARACTERISTICA = ?," +
    			" IMO_FINALIDADE = ?," +
    			" IMO_DETALHE = ?," +
    			" IMO_FORMA_PAGAMENTO = ?," +
    			" IMO_TIPO = ?," +
    			" IMO_VALOR = ?," +
    			" IMO_VALOR_CONDOMINIO = ?," +
    			" IMO_CLIENTE = ?" +
    	  		" where imo_codigo = ?;");
    	try {
			ps.setInt(1, imo.getCaracteristica());
			ps.setInt(2, imo.getFinalidade());
			ps.setString(3, imo.getDetalhe());
			ps.setString(4, imo.getFormaPagamento());
			ps.setInt(5, imo.getTipo());
			ps.setDouble(6, imo.getValor());
			ps.setDouble(7, imo.getValorCondominio());
			ps.setInt(8, imo.getProprietario());
    		ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void salva(Imovel imo) {
    	PreparedStatement ps = this.conn.getPreparedStatement
  	  ("insert into imovel (" +
  			" IMO_CARACTERISTICA," +
  			" IMO_FINALIDADE," +
  			" IMO_DETALHE," +
  			" IMO_FORMA_PAGAMENTO," +
  			" IMO_TIPO," +
  			" IMO_VALOR," +
  			" IMO_VALOR_CONDOMINIO," +
  			" IMO_CLIENTE)" +
  	  		" values (?,?,?,?,?,?,?,?);");
  	try {
			ps.setInt(1, imo.getCaracteristica());
			ps.setInt(2, imo.getFinalidade());
			ps.setString(3, imo.getDetalhe());
			ps.setString(4, imo.getFormaPagamento());
			ps.setInt(5, imo.getTipo());
			ps.setDouble(6, imo.getValor());
			ps.setDouble(7, imo.getValorCondominio());
			ps.setInt(8, imo.getProprietario());
  		ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void exclui(Imovel imo) {
    	PreparedStatement ps = this.conn.getPreparedStatement
    	  ("delete from imovel where imo_codigo = ?;");
    	try {
			ps.setInt(1, imo.getCodigo());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
