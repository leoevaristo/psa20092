package br.com.cond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cond.businesslogic.Apartamento;
import br.com.siaic.dao.FabricaConexao;

/**
 * 
 * @author Alain Rosemberg 
 */
public class ApartamentoDAO {

	/**
	 * 
	 */
	private Connection conexao = null;

	



	/**
	 * 
	 */
	public ApartamentoDAO() {

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

	public void adicionarApartamento (Apartamento apartamento) throws SQLException {

		String sql = "INSERT INTO admcon_apartamento (APA_ANDAR, APA_BLOCO)"
				+ "VALUES (?, ?)";

		try {

			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setInt(1, apartamento.getAndar());
			ps.setString(2, apartamento.getBloco());

			ps.execute();

			ps.close();

		}

		catch (Exception e) {

			throw new SQLException("Erro ao inserir dados no banco.");

		}

	}

	public void removerApartamento(int idApartamento) throws SQLException {
	
		String sql = " DELETE  FROM admcon_apartamento WHERE APA_CODIGO = ? ";

	

	try {
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, idApartamento);
		if (ps.execute())
			System.out.println("Dados apagados!");
		ps.close();
		
	} finally {
		
		conexao.close();
	}

	}

	public void alterarApartamento(Apartamento apartamento) throws SQLException {

		String sql = "UPDATE admcon_apartamento SET APA_ANDAR = ?, APA_BLOCO = ? "
				+ " WHERE  APA_CODIGO = ?";

		try {

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, apartamento.getAndar());
			ps.setString(2, apartamento.getBloco());
			ps.setInt(3, apartamento.getCodigoApartamento());

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
	public List<Apartamento> getTodosOsApartamentos() throws SQLException {
		// TODO
		String sql = "SELECT APA_CODIGO, APA_ANDAR, APA_BLOCO "
				+ " FROM admcon_apartamento "
				+ " ORDER BY APA_BLOCO";

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Apartamento> listaTodosApartamentos = new ArrayList<Apartamento>();

		while (rs.next()) {

			Apartamento apartamento = new Apartamento();

		//	regra.setCodigoRegra(rs.getString());
		//	regra.setRegra(rs.getString());
			apartamento.setCodigoApartamento(rs.getInt(1));
			apartamento.setAndar(rs.getInt(2));			
			apartamento.setBloco(rs.getString(3));

			listaTodosApartamentos.add(apartamento);

		}
		conexao.close();

		return listaTodosApartamentos;

	}
	
	
	
	public Apartamento getApartamentoId(int apartamentoCodigo) throws SQLException
	{
		
		String sql = "SELECT APA_CODIGO, APA_ANDAR, APA_BLOCO " 
			+"FROM admcon_apartamento " 
			+"WHERE APA_CODIGO = ?  ";
		
			
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, apartamentoCodigo);
		
		ResultSet rs = ps.executeQuery();
		
		Apartamento apartamento = new Apartamento();
		
		rs.first();
		
		apartamento.setCodigoApartamento(rs.getInt("APA_CODIGO"));
		apartamento.setAndar(rs.getInt("APA_ANDAR"));
		apartamento.setBloco(rs.getString("APA_BLOCO"));
		
		ps.close();
		rs.close();
		
		
		return apartamento;
		
	}
	
	public List<Apartamento> getApartamentoPorBloco(String nbloco) throws SQLException {

		String sql = "SELECT APA_CODIGO,APA_ANDAR,APA_BLOCO "
			+ "FROM admcon_apartamento  "
			+ "WHERE APA_BLOCO LIKE ? ";

		try{
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, "%" + nbloco + "%");

			ResultSet rs = ps.executeQuery();

			List<Apartamento> listaApartamentos = new ArrayList<Apartamento>();

			while (rs.next()) {
			
				Apartamento apartamento = new Apartamento();
				apartamento.setCodigoApartamento(rs.getInt("APA_CODIGO"));
				apartamento.setAndar(rs.getInt("APA_ANDAR"));
				apartamento.setBloco(rs.getString("APA_BLOCO"));

				listaApartamentos.add(apartamento);

			}

			ps.close();
			rs.close();
		
			return listaApartamentos;
		
			}finally{
				conexao.close();
		}
		
	}


	

}

