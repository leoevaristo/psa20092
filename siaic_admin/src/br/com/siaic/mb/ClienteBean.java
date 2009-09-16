package br.com.siaic.mb;

import java.sql.SQLException;
import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.dao.ClienteDAO;




public class ClienteBean {
	
	private Cliente cliente;
	
	
	
	public ClienteBean(){
		
		cliente = new Cliente();
	}
	
	
	
	
	public Cliente getCliente() {
		return cliente;
	}




	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}




	public String addCliente() throws SQLException{
		
		ClienteDAO dao = new ClienteDAO();
		
		dao.adicionarCliente(cliente);
		
		return "sucesso";
	}

}
