package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Pessoa;
import br.com.siaic.businesslogic.Usuario;



/**
 * 
 * @author Carlos R. N. Junior
 *
 */
public class PessoaDAO 
{
	
	
	
	
	private Connection conexao = null;
	
	
	/**
	 * 
	 */
	public PessoaDAO()
	{
		
		
		try 
		{
			
			this.conexao = FabricaConexao.conectar();
			
		}
		
		catch (SQLException e) 
		{
			
			e.printStackTrace();
			
		}		
		
	}
	
	
	
	/**
	 * 
	 * @param cliente
	 */
	public void adicionarPessoa(Cliente cliente)
	{
		//TODO
		
	}
	
	/**
	 * 
	 * @param Usuario
	 */
	public void adicionarPessoa(Usuario usuario)
	{
		//TODO
		
	}
	
	
	/**
	 * 
	 * @param pessoa
	 */
	public void removerPessoa(Pessoa pessoa)
	{
		//TODO
		
	}
	
	
	
	/**
	 * 
	 * @param pessoa
	 */
	public void alterarPessoa(Pessoa pessoa)
	{
		//TODO
		
	}
	
	
	
	
	public List<Pessoa> getPessoa()
	{
		//TODO
		return new ArrayList<Pessoa>();
	}



	public static PessoaDAO getInstancia() {
		// TODO Auto-generated method stub
		return null;
	}


}
