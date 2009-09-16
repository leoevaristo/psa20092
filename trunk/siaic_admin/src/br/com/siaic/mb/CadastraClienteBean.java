package br.com.siaic.mb;

import java.sql.SQLException;
import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.dao.ClienteDAO;




public class CadastraClienteBean 
{
	
	private Cliente cliente;
	
	
	/**
	 * 
	 */
	public CadastraClienteBean()
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

}
