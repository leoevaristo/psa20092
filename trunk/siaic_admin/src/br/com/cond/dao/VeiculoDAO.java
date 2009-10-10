package br.com.cond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.cond.businesslogic.Veiculo;
import br.com.siaic.dao.FabricaConexao;

/**
 * 
 * @author Alain Rosemberg 
 */
public class VeiculoDAO {

	/**
	 * 
	 */
	private Connection conexao = null;

	



	/**
	 * 
	 */
	public VeiculoDAO() {

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

	public void adicionarVeiculo (Veiculo veiculo) throws SQLException {

		String sql = "INSERT INTO admcon_veiculo (VEI_DESCRICAO, VEI_PLACA, VEI_COR, VEI_APA_CODIGO)"
				+ "VALUES (?, ?, ?, ?)";

		try {

			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, veiculo.getDescricao());
			ps.setString(2, veiculo.getPlaca());
			ps.setString(3, veiculo.getCor());
			ps.setInt(4, veiculo.getCodigoApartamento());

			
			
			
			ps.execute();

			ps.close();

		}

		catch (Exception e) {

			throw new SQLException("Erro ao inserir dados no banco.");

		}

	}

	public void removerveiculo(int idveiculo) throws SQLException {
	
		String sql = " DELETE FROM admcon_veiculo WHERE VEI_CODIGO = ? ";

	

	try {
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, idveiculo);
		if (ps.execute())
			System.out.println("Dados apagados!");
		ps.close();
		
	} finally {
		
		conexao.close();
	}

	}

	public void alterarVeiculos(Veiculo veiculo) throws SQLException {

		String sql = "UPDATE veiculo SET VEI_DESCRICAO, VEI_PLACA, VEI_COR, VEI_APA_CODIGO"
				+ " WHERE VEI_CODIGO = ?";

		try {

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, veiculo.getDescricao());
			ps.setString(2, veiculo.getPlaca());
			ps.setString(3, veiculo.getCor());
			ps.setInt(4, veiculo.getCodigoApartamento());
			ps.setInt(4, veiculo.getCodigoVeiculo());
			
			
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
	public List<Veiculo> getTodosOsVeiculos() throws SQLException {
		// TODO
		String sql = "SELECT ?, ?, ?, ?, "
				+ " FROM veiculo "
				+ "	WHERE ? ORDER BY ?";

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Veiculo> listaTodosAsveiculos = new ArrayList<Veiculo>();

		while (rs.next()) {

			Veiculo veiculo = new Veiculo();

		//	veiculo.setCodigoveiculo(rs.getString());
		//	veiculo.setveiculo(rs.getString());
			

			listaTodosAsveiculos.add(veiculo);

		}
		conexao.close();

		return listaTodosAsveiculos;

	}
	
	
	
	public Veiculo getVeiculoId(int veiculoCodigo) throws SQLException
	{
		
		String sql = "SELECT ? " 
			+"FROM veiculo " 
			+"WHERE ? = ?  ";
		
			
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, veiculoCodigo);
		
		ResultSet rs = ps.executeQuery();
		
		Veiculo veiculo = new Veiculo();
		
		rs.first();
		//veiculo.setCodigoveiculo(rs.getInt());
		//veiculo.setveiculo(rs.getString());
			
		ps.close();
		rs.close();
		
		
		return veiculo;
		
	}
	

}

