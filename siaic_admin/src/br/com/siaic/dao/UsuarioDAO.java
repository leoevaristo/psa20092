package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Usuario;




/**
 * 
 * @author Alain Rosemberg
 *  *
 */
public class UsuarioDAO {
	
	
	
	
	private Connection conexao = null;
	
	
	
	
	
	public UsuarioDAO()
	{
		
		try
		{
			
			this.conexao = FabricaConexao.conectar();
		
		}
		
		catch ( Exception e )
		{
			
			e.printStackTrace();
		
		}
	}
	
	
	
	
	public void adicionarUsuario ( Usuario usuario ) throws SQLException 
	{
		
		String sql = "INSERT INTO PESSOA_USUARIOS( PEU_CODIGO,PEU_CRECI,PEU_LOGIN,PEU_SENHA)" 
					+ "VALUES (?, ?, ?,?)";
		
		try{
		
			PreparedStatement ps = conexao.prepareStatement ( sql );
		
			ps.setInt(1, usuario.getCodigoPessoa());
			ps.setString(2, usuario.getCRECI());
			ps.setString(3, usuario.getLogin());
			ps.setString(4, usuario.getSenha());
			
		
			ps.execute();
		
			ps.close();
		
		}
		
		catch ( Exception e )
		{
			
			throw new SQLException ( "Erro ao inserir dados no banco." );
			
		}	
		
	}
	
	
	
	
	public void removerCliente ( Usuario usuario )
	{
		 
	}
	
	
	
	
	public void alterarCliente ( Usuario usuario) throws SQLException 
	{
		
		String sql = "UPDATE PESSOA_USUARIO SET PEU_CRECI = ?, PEU_LOGIN = ?, PEU_SENHA = ?" 
					+" WHERE PEU_CODIGO = ?";
		
		try
		{
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, usuario.getCRECI());
			ps.setString(2, usuario.getLogin());
			ps.setString(3, usuario.getSenha());
			ps.setInt(4, usuario.getCodigoPessoa());
			
			ps.executeUpdate();
			ps.close();
			
		}
		
		catch(Exception e)
		{
			
			throw new SQLException("Não foi possível alterar o banco de dados.");
		
		}
	}
	
	
	
	
/*	public List < Cliente > getClientesPeloNome ( String nome ) throws SQLException
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
	
	*/
	
	
	
	
	
	
	

}
