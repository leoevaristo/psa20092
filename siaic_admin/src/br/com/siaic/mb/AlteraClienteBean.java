package br.com.siaic.mb;

import java.sql.SQLException;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.dao.ClienteDAO;

public class AlteraClienteBean 
{

	private Cliente cliente;
	
	
	/**
	 * 
	 */
	public AlteraClienteBean()
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
	
	
	
	
	public String updateCliente() throws SQLException
	{
		//TODO
		String r ="";
		
		ClienteDAO dao = new ClienteDAO();
		
		dao.alterarCliente(cliente);
		
		
		return r;
	}

}
