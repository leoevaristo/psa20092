package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.List;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.PessoaDAO;

public class ClienteBean {

	/**
	 * @author Alain Rosemberg
	 */
	private Cliente cliente;

	/**
	 * 
	 */
	public ClienteBean() {

		cliente = new Cliente();

	}

	/**
	 * 
	 * @return
	 */
	public Cliente getCliente() {

		return cliente;

	}

	/**
	 * 
	 * @param cliente
	 */
	public void setCliente(Cliente cliente) {

		this.cliente = cliente;

	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String addCliente() throws SQLException {
		// TODO
		String r = "";

		PessoaDAO.getInstancia().adicionarPessoa(cliente);

		ClienteDAO.getInstancia().adicionarCliente(cliente);

		return r;

	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String updateCliente() throws SQLException {
		// TODO
		String r = "";

		ClienteDAO.getInstancia().adicionarCliente(cliente);

		return r;

	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Cliente> ExibirTodosClientes() throws SQLException {

		return ClienteDAO.getInstancia().getTodosClientes();

	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Cliente localizarClientePorId() throws SQLException {

		return ClienteDAO.getInstancia().getClientePorId(
				getCliente().getCodigoPessoa());

	}

}
