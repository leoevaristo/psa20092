package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Cliente;





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
		
		String sql = "INSERT INTO PESSOA_CLIENTE( PEC_CODIGO,PEC_NOME,PEC_CPF,PEC_RG,PEC_CNPJ )" +
				"VALUES (?, ?, ?,?,?)";
		
		try{
		
			PreparedStatement ps = conexao.prepareStatement ( sql );
		
			ps.setInt(1, cliente.getCodigo());
			ps.setString(2, cliente.getSobreNome());
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
	
	
	
	
	public void alterarCliente ( Cliente cliente ) 
	{
		//TODO
		
	}
	
	
	
	
	public List < Cliente > getClientes ( String nome )
	{
		//TODO
		return new ArrayList < Cliente > ();
	}
	
	
	
	
	public Cliente getClientes ()
	{
		//TODO
		return new Cliente();
	}
	
	
	
	
	

}
