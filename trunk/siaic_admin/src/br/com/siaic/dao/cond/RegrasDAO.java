package br.com.siaic.dao.cond;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.businesslogic.cond.Regras;
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

		String sql = "INSERT INTO REGRAS( ?)"
				+ "VALUES (?)";

		try {

			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setInt(1, regra.getCodigoRegra());
			ps.setString(2, regra.getRegra());

			ps.execute();

			ps.close();

		}

		catch (Exception e) {

			throw new SQLException("Erro ao inserir dados no banco.");

		}

	}

	public void removerUsuario(int idRegra) throws SQLException {
	
		String sql = " DELETE REGRA ";

	

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

		String sql = "UPDATE REGRA SET ?, ?, ?"
				+ " WHERE  = ?";

		try {

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, regra.getCodigoRegra());
			ps.setString(2, regra.getRegra());

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
		String sql = "SELECT ?, ?, ?, ?, "
				+ " FROM REGRA "
				+ "	WHERE ? ORDER BY ?";

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Regras> listaTodosAsRegras = new ArrayList<Regras>();

		while (rs.next()) {

			Regras regra = new Regras();

		//	regra.setCodigoRegra(rs.getString());
		//	regra.setRegra(rs.getString());
			

			listaTodosAsRegras.add(regra);

		}
		conexao.close();

		return listaTodosAsRegras;

	}
	
	
	
	public Regras getRegraId(int regraCodigo) throws SQLException
	{
		
		String sql = "SELECT ? " 
			+"FROM REGRA " 
			+"WHERE ? = ?  ";
		
			
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, regraCodigo);
		
		ResultSet rs = ps.executeQuery();
		
		Regras regra = new Regras();
		
		rs.first();
		//regra.setCodigoRegra(rs.getInt());
		//regra.setRegra(rs.getString());
			
		ps.close();
		rs.close();
		
		
		return regra;
		
	}
	

}

