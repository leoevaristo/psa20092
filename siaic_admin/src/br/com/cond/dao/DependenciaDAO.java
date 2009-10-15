package br.com.cond.dao;
/**
 * @author José Carlos
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cond.businesslogic.Dependencia;
import br.com.siaic.dao.FabricaConexao;


public class DependenciaDAO {

	private Connection conn;

	public DependenciaDAO() {
		try {
		
			conn = FabricaConexao.getInstancia().conectar();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean adicionarDependencia(Dependencia dependencia) throws SQLException{
		boolean flag;
		String sql = "insert into admcon_dependencia(dep_descricao, dep_reservavel) values(?, ?);";
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dependencia.getDescricao());
			ps.setString(2, "" + dependencia.getReservavel());
			flag = ps.execute();
			ps.close();
			
			return flag;
		}
		finally{
			conn.close();
		}
	}
	
	public boolean alterarDependencia(Dependencia dependencia) throws SQLException{
		boolean flag;
		String sql = "update admcon_dependencia set " +
				"dep_descricao = ?, " +
				"dep_reservavel = ? " +
				"where dep_codigo = ?";
		
		PreparedStatement ps;
		
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1, dependencia.getDescricao());
			ps.setString(2, ""+ dependencia.getReservavel());
			ps.setInt(3, dependencia.getCodigo());
			
			flag = ps.execute();
			ps.close();
			
			return flag;
		}
		finally{
			conn.close();
		}
	}
	
	public boolean removerDependencia(Dependencia dependencia) throws SQLException{
		boolean flag;
		String sql = "delete from admcon_dependencia where dep_codigo = ?";
		
		PreparedStatement ps;
		
		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dependencia.getCodigo());
			
			flag = ps.execute();
			ps.close();
			
			return flag;
		}finally{
			conn.close();
		}
		
	}

	public Dependencia buscaDependencia(int codigo) throws SQLException{

		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Dependencia dep = new Dependencia();
		sql = "select dep_codigo, dep_descricao, dep_reservavel from admcon_dependencia where dep_codigo = ?";
		
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.first()){
				dep.setCodigo(rs.getInt("dep_codigo"));
				dep.setDescricao(rs.getString("dep_descricao"));
				dep.setReservavel(rs.getString("dep_reservavel").charAt(0));
			}
			
			ps.close();
			rs.close();
			
			return dep;
		}
		finally{
			conn.close();
		}

	}
	public List<Dependencia> buscaDependencia(Dependencia dependencia) throws SQLException{
		String sql;
		StringBuilder where = new StringBuilder();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if (dependencia.getCodigo() != 0){
			if (where.length() == 0) {
				where.append("dep_codigo = " + dependencia.getCodigo());
			}else{
				where.append(" and dep_codigo = " + dependencia.getCodigo());
			}
		}
		
		if ( dependencia.getDescricao() != null){
			if (where.length() == 0) {
				where.append("dep_descricao like '" + dependencia.getDescricao() + "'");
			}else{
				where.append(" and dep_descricao like '" + dependencia.getDescricao() + "'");
			}
		}
		
		if ( dependencia.getReservavel() != '\u0000'){
			if (where.length() == 0) {
				where.append("dep_reservavel = '" + dependencia.getReservavel() + "'");
			}else{
				where.append(" and dep_reservavel = '" + dependencia.getReservavel() + "'");
			}
		}
		
		if (where.length() == 0) {
			sql = "select dep_codigo, dep_descricao, dep_reservavel from admcon_dependencia";
		}else{
			sql = "select dep_codigo, dep_descricao, dep_reservavel from admcon_dependencia where " + where.toString();
		}
		
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			List<Dependencia> listaDependencia = new ArrayList<Dependencia>();
			
			while(rs.next()){
				Dependencia dep = new Dependencia();
				dep.setCodigo(rs.getInt("dep_codigo"));
				dep.setDescricao(rs.getString("dep_descricao"));
				dep.setReservavel(rs.getString("dep_reservavel").charAt(0));
				listaDependencia.add(dep);
			}
			
			ps.close();
			rs.close();
			
			return listaDependencia;
		}
		finally{
			conn.close();
		}

	}
}
