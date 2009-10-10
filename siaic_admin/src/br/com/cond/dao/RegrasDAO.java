package br.com.cond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cond.businesslogic.Regras;
import br.com.siaic.dao.FabricaConexao;

/**
 * 
 * @author Alain Rosemberg 
 */
public class RegrasDAO {

	/**
	 * 
	 */
	private Connection conexao = null;

	



	/**
	 * 
	 */
	public RegrasDAO() {

		try
		{
			FabricaConexao.getInstancia();
			this.conexao = FabricaConexao.conectar();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public void adicionarRegras (Regras regra) throws SQLException {

		String sql = "INSERT INTO admcon_regras (REG_DESCRICAO)"
				+ "VALUES (?)";

		try {

			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, regra.getRegra());

			ps.execute();

			ps.close();

		}

		catch (Exception e) {

			throw new SQLException("Erro ao inserir dados no banco.");

		}

	}

	public void removerRegra(int idRegra) throws SQLException {
	
		String sql = " DELETE FROM admcon_regras WHERE REG_CODIGO = ? ";

	

	try {
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, idRegra);
		if (ps.execute())
			System.out.println("Dados apagados!");
		ps.close();
		
	} finally {
		
		conexao.close();
	}

	}

	public void alterarRegras(Regras regra) throws SQLException {

		String sql = "UPDATE admcon_regras SET REG_DESCRICAO "
				+ " WHERE  REG_CODIGO = ? ";

		try {

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, regra.getCodigoRegra());

			ps.executeUpdate();
			ps.close();

		}

		catch (Exception e) {

			throw new SQLException("Nao foi possivel alterar o banco de dados.");

		}
	}

	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Regras> getTodasAsRegras() throws SQLException {
		// TODO
		String sql = "SELECT REG_CODIGO, REG_DESCRICAO "
				+ " FROM admcon_regras "
				+ "	ORDER BY REG_CODIGO";

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Regras> listaTodosAsRegras = new ArrayList<Regras>();

		while (rs.next()) {

			Regras regra = new Regras();

			regra.setCodigoRegra(rs.getInt("REG_CODIGO"));
			regra.setRegra(rs.getString("REG_DESCRICAO"));
			

			listaTodosAsRegras.add(regra);

		}
		conexao.close();

		return listaTodosAsRegras;

	}
	
	
	public List<Regras> getRegraPelaRegra(String nregra) throws SQLException {

		String sql = "SELECT REG_CODIGO,REG_DESCRICAO "
			+ "FROM admcon_regras "
			+ "WHERE REG_CODIGO LIKE ? ";

		try{
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, "%" + nregra + "%");

			ResultSet rs = ps.executeQuery();

			List<Regras> listaRegras = new ArrayList<Regras>();

			while (rs.next()) {
			
				Regras regra = new Regras();
				regra.setCodigoRegra(rs.getInt("REG_CODIGO"));
				regra.setRegra(rs.getString("REG_DESCRICAO"));

				listaRegras.add(regra);

			}

			ps.close();
			rs.close();
		
			return listaRegras;
		
			}finally{
				conexao.close();
		}
		
	}

	
	
	public Regras getRegraId(int regraCodigo) throws SQLException
	{
		
		String sql = "SELECT REG_CODIGO,REG_DESCRICAO " 
			+"FROM admcon_regras " 
			+"WHERE REG_CODIGO = ?  ";
		
			
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, regraCodigo);
		
		ResultSet rs = ps.executeQuery();
		
		Regras regra = new Regras();
		
		rs.first();
		regra.setCodigoRegra(rs.getInt("REG_CODIGO"));
		regra.setRegra(rs.getString("REG_DESCRICAO"));
			
		ps.close();
		rs.close();
		
		
		return regra;
		
	}
	

}

