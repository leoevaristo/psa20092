	package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Cliente;

/**
 * 
 * @author Carlos R. N. Junior *
 */
public class ClienteDAO {

	/**
	 * 
	 */
	private Connection conexao = null;

	

	/**
	 * 
	 */
	public ClienteDAO() {

		try {

			FabricaConexao.getInstancia();
			this.conexao = FabricaConexao.conectar();

		}

		catch (Exception e) {

			e.printStackTrace();

		}
	}

	/**
	 * 
	 * @param cliente
	 * @throws SQLException
	 */
	public void adicionarCliente(Cliente cliente) throws SQLException {

		String sql = "INSERT INTO PESSOA_CLIENTE( PEC_CODIGO,PEC_CPF,PEC_RG,PEC_CNPJ )"
				+ "VALUES (?, ?, ?,?)";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, cliente.getCodigoPessoa());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getRg());
			ps.setString(4, cliente.getCnpj());

			ps.execute();
			ps.close();

		}finally{
			
			conexao.close();
		}
	}

	/**
	 * 
	 * @param cliente
	 * @throws SQLException
	 */
	public void removerCliente(int idPessoa) throws SQLException {
		
		String sql = " DELETE PESSOA_CLIENTE, PESSOA FROM PESSOA_CLIENTE INNER JOIN PESSOA INNER JOIN ENDERECO "
				+ " WHERE PESSOA_CLIENTE.PEC_CODIGO = ? AND PESSOA_CLIENTE.PEC_CODIGO =PESSOA.PES_CODIGO "
				+ " AND PESSOA.PES_ENDERECO = ENDERECO.END_CODIGO; ";

		

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, idPessoa);
			if (ps.execute())
				System.out.println("Dados apagados!");
			ps.close();
			
		} finally {
			
			conexao.close();
		}
	}

	/**
	 * 
	 * @param cliente
	 * @throws SQLException
	 */
	public void alterarCliente(Cliente cliente) throws SQLException {

		String sql = "UPDATE PESSOA_CLIENTE SET PEC_CPF = ?, PEC_RG = ?, PEC_CNPJ = ?"
				+ " WHERE PEC_CODIGO = ?";

		try {

			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, cliente.getCpf());
			ps.setString(2, cliente.getRg());
			ps.setString(3, cliente.getCnpj());
			ps.setInt(4, cliente.getCodigoPessoa());
			

			ps.execute();
			ps.close();

			}finally{
			
				conexao.close();
			}
	}

	
	
	
	/**
	 * 
	 * @param nome
	 * @return
	 * @throws SQLException
	 */
	public List<Cliente> getClientesPeloNome(String nome) throws SQLException {

		String sql = "SELECT p.PES_ENDERECO, p.PES_NOME, p.PES_TELEFONE, p.PES_CELULAR, p.PES_EMAIL, "
			+ "c.PEC_CODIGO,c.PEC_CPF,c.PEC_RG,c.PEC_CNPJ, "
			+ "e.END_LOGRADOURO,e.END_NOME,e.END_CEP,e.END_BAIRRO,e.END_CODIGO "
			+ "FROM PESSOA p, PESSOA_CLIENTE c, ENDERECO e "
			+ "WHERE p.PES_NOME LIKE ? AND p.PES_ENDERECO = e.END_CODIGO AND p.PES_CODIGO = c.PEC_CODIGO; ";
			

		try{
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, "%" + nome + "%");

			ResultSet rs = ps.executeQuery();

			List<Cliente> listaClientes = new ArrayList<Cliente>();

			while (rs.next()) {
			
				Cliente cliente = new Cliente();
				cliente.setCodigoPessoa(rs.getInt("PEC_CODIGO"));
				cliente.setCpf(rs.getString("PEC_CPF"));
				cliente.setRg(rs.getString("PEC_RG"));
				cliente.setCnpj(rs.getString("PEC_CNPJ"));
				cliente.setNome(rs.getString("PES_NOME"));
				cliente.setEmail(rs.getString("PES_EMAIL"));
				cliente.setTelefone(rs.getString("PES_TELEFONE"));

				listaClientes.add(cliente);

			}

			ps.close();
			rs.close();
		
			return listaClientes;
		
			}finally{
				conexao.close();
		}
		
	}
	

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Cliente> getTodosClientes() throws SQLException {
		// TODO
		
		String sql = "SELECT c.PEC_CODIGO, c.PEC_CPF, c.PEC_RG, c.PEC_CNPJ, "
				+ " p.PES_CODIGO, p.PES_NOME, p.PES_EMAIL, p.PES_TELEFONE, p.PES_CELULAR, p.PES_ENDERECO"
				+ " FROM PESSOA_CLIENTE c, PESSOA p"
				+ "	WHERE c.PEC_CODIGO = p.PES_CODIGO ORDER BY p.PES_CODIGO ASC;";

		try
		{
			PreparedStatement ps = conexao.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<Cliente> listaTodosClientes = new ArrayList<Cliente>();

			while (rs.next()) {

				Cliente cliente = new Cliente();
				cliente.setCodigoPessoa(rs.getInt("PES_CODIGO"));
				cliente.setCpf(rs.getString("PEC_CPF"));
				cliente.setRg(rs.getString("PEC_RG"));
				cliente.setCnpj(rs.getString("PEC_CNPJ"));
				cliente.setNome(rs.getString("PES_NOME"));
				cliente.setEmail(rs.getString("PES_EMAIL"));
				cliente.setTelefone(rs.getString("PES_TELEFONE"));
				

				listaTodosClientes.add(cliente);
			
		}
				return listaTodosClientes;

		
			}finally{
			
				conexao.close();
			}
		
		

	}

	/**
	 * 
	 * @param clienteCodigo
	 * @return
	 * @throws SQLException
	 */
	public Cliente getClientePorId(int clienteCodigo) {
		
		String sql = "SELECT c.PEC_CODIGO, c.PEC_CPF, c.PEC_RG, c.PEC_CNPJ, p.PES_CODIGO, "
			+ "p.PES_NOME, p.PES_EMAIL, p.PES_TELEFONE, p.PES_CELULAR, p.PES_ENDERECO, p.PES_TIPO, p.PES_SEXO "
			+ "FROM PESSOA_CLIENTE c, PESSOA p "
			+ "WHERE c.PEC_CODIGO = ?  AND c.PEC_CODIGO = p.PES_CODIGO ";


		try {			
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, clienteCodigo);

			ResultSet rs = ps.executeQuery();
			rs.first();
			
			Cliente cliente = new Cliente();
			cliente.setCodigoPessoa(rs.getInt("PEC_CODIGO"));
			cliente.setCpf(rs.getString("PEC_CPF"));
			cliente.setRg(rs.getString("PEC_RG"));
			cliente.setCnpj(rs.getString("PEC_CNPJ"));
			cliente.setNome(rs.getString("PES_NOME"));
			cliente.setEmail(rs.getString("PES_EMAIL"));
			cliente.setTelefone(rs.getString("PES_TELEFONE"));
			cliente.setCelular(rs.getString("PES_CELULAR"));
			cliente.setEnderecoCodigo(rs.getInt("PES_ENDERECO"));
			cliente.setTipoPessoa(rs.getString("PES_TIPO"));
			cliente.setSexo(rs.getString("PES_SEXO"));

			ps.close();
			rs.close();

			return cliente;

		} catch (SQLException e) {
			
			e.getLocalizedMessage();
		} finally {

			try {
				conexao.close();
			} catch (SQLException e) {
				
				e.getLocalizedMessage();
			}
		}
		return null;

	}
	public List<Cliente> getClientesPerfilPeloNome(String nome) throws SQLException {

		String sql = "SELECT p.PES_ENDERECO, p.PES_NOME, p.PES_TELEFONE, p.PES_CELULAR, p.PES_EMAIL, "
			+ "c.PEC_CODIGO,c.PEC_CPF,c.PEC_RG,c.PEC_CNPJ, "
			+ "e.END_LOGRADOURO,e.END_NOME,e.END_CEP,e.END_BAIRRO,e.END_CODIGO, per.PRF_PESSOA_CLIENTE "
			+ "FROM PESSOA p, PESSOA_CLIENTE c, ENDERECO e, PERFIL per "
			+ "WHERE p.PES_NOME LIKE ? AND p.PES_ENDERECO = e.END_CODIGO AND p.PES_CODIGO = c.PEC_CODIGO "
			+ "AND c.PEC_CODIGO = per.PRF_PESSOA_CLIENTE;";

		try{
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, "%" + nome + "%");

			ResultSet rs = ps.executeQuery();

			List<Cliente> listaClientes = new ArrayList<Cliente>();

			while (rs.next()) {
			
				Cliente cliente = new Cliente();
				cliente.setCodigoPessoa(rs.getInt("PEC_CODIGO"));
				cliente.setCpf(rs.getString("PEC_CPF"));
				cliente.setRg(rs.getString("PEC_RG"));
				cliente.setCnpj(rs.getString("PEC_CNPJ"));
				cliente.setNome(rs.getString("PES_NOME"));
				cliente.setEmail(rs.getString("PES_EMAIL"));
				cliente.setTelefone(rs.getString("PES_TELEFONE"));

				listaClientes.add(cliente);

			}

			ps.close();
			rs.close();
		
			return listaClientes;
		
			}finally{
				conexao.close();
		}
		
	}
	

}
