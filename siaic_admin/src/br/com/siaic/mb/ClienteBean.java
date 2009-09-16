package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.dao.ClienteDAO;

public class ClienteBean 
{

	private Cliente cliente;
	
	
	/**
	 * 
	 */
	public ClienteBean()
	{
		
		cliente = new Cliente();
		
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public Cliente getCliente() 
	{
		
		return cliente;
		
	}



	/**
	 * 
	 * @param cliente
	 */
	public void setCliente(Cliente cliente)
	{
		
		this.cliente = cliente;
		
	}



	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String addCliente() throws SQLException
	{	
		//TODO
		String r = "";
		
		ClienteDAO dao = new ClienteDAO();
		
		dao.adicionarCliente(cliente);
		
		return r;
		
	}
	
	
	
	
	public String updateCliente() throws SQLException
	{
		//TODO
		String r ="";
		
		ClienteDAO dao = new ClienteDAO();
		
		dao.alterarCliente(cliente);
		
		
		return r;
	}
	
	
	
	
	public List<Cliente> ExibirTodosClientes()
	{
		//TODO
			
		ClienteDAO dao = new ClienteDAO();
		
		return dao.getTodosClientes();	
			
		
	}
}
