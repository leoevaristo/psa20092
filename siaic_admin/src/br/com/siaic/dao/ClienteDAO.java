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
 * @author Carlos R. N. Junior
 *  *
 */
public class ClienteDAO {
	
	
	
	
	private Connection conexao = null;
	
	
	
	
	
	public ClienteDAO()
	{
		
		try
		{
			
			this.conexao = FabricaConexao.conectar();
		
		}
		
		catch ( SQLException e )
		{
			
			e.printStackTrace();
		
		}
	}
	
	
	
	
	public void adicionarCliente ( Cliente cliente ) throws SQLException 
	{
		
		String sql = "INSERT INTO PESSOA_CLIENTE( PEC_CODIGO,PEC_CPF,PEC_RG,PEC_CNPJ )" +
				"VALUES (?, ?, ?,?)";
		
		try{
		
			PreparedStatement ps = conexao.prepareStatement ( sql );
		
			ps.setInt(1, cliente.getCodigo());
			ps.setString(3, cliente.getCpf());
			ps.setString(4, cliente.getRg());
			ps.setString(5, cliente.getCnpj());
			
		
			ps.execute();
		
			ps.close();
		
		}
		
		catch ( SQLException e )
		{
			
			throw new SQLException ( "Erro ao inserir dados no banco." );
			
		}	
		
	}
	
	
	
	
	public void removerCliente ( Cliente cliente )
	{
		 
	}
	
	
	
	
	public void alterarCliente ( Cliente cliente ) throws SQLException 
	{
		
		String sql = "UPDATE PESSOA_CLIENTE SET PEC_CPF = ?, PEC_RG = ?, PEC_CNPJ = ? WHERE PEC_CODIGO = ?";
		
		try
		{
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, cliente.getCpf());
			ps.setString(2, cliente.getRg());
			ps.setString(3, cliente.getCnpj());
			ps.setInt(4, cliente.getCodigo());
			
			ps.executeUpdate();
			ps.close();
			
		}
		
		catch(SQLException e)
		{
			
			throw new SQLException("Não foi possível alterar o banco de dados.");
		
		}
	}
	
	
	
	
	public List < Cliente > getClientesPeloNome ( String nome ) throws SQLException
	{
		
		String sql = "SELECT p.PES_ENDERECO, p.PES_NOME, p.PES_TELEFONE, p.PES_CELULAR, p.PES_EMAIL," 
				+ "c.PEC_CODIGO,c.PEC_CPF,c.PEC_RG,c.PEC_CNPJ" 
				+ "e.END_LOGRADOURO,e.END_NOME,e.END_CEP,e.END_BAIRRO,e.END_CODIGO "
				+ "FROM PESSOA p, PESSOA_CLIENTE c, ENDERECO e" 
				+ "WHERE p.PES_NOME LIKE ? AND p.PES_ENDERECO = e.END_CODIGO AND p.PES_CODIGO = c.PEC_CODIGO;";
		
					
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, nome);
			
			ResultSet rs = ps.executeQuery();
			
			List<Cliente> listaClientes = new ArrayList<Cliente>();
			
			while(rs.next())
			{
				Cliente cliente = new Cliente();
				
				cliente.setCodigo(rs.getInt("PEC_CODIGO"));
				cliente.setCpf(rs.getString("PEC_CPF"));
				cliente.setRg(rs.getString("PEC_RG"));
				cliente.setCnpj(rs.getString("PEC_CNPJ"));
				
				listaClientes.add(cliente);
				
			}
			
			ps.close();
			rs.close();
			
		
		
		
		return listaClientes;
		
	}
	
	
	
	
	public List<Cliente> getTodosClientes ()
	{
		//TODO
		return new ArrayList<Cliente>();
	}
	
	
	
	
	
	
	
	
	

}
