package br.com.cor.mb;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.dao.ClienteDAO;

public class ReservarImovelBean {
	private int codigo;
	private Imovel imovel;
	private Date data;
	private Time tempoReserva;
	
	private Cliente cliente;
	private String campoPesquisa;
	
	
	public ReservarImovelBean() {
		cliente = new Cliente();
	}
	
	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}

	public String getCampoPesquisa() {
		return campoPesquisa;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public List<Cliente> getClientePorNome() throws SQLException {
		ClienteDAO daoCliente = new ClienteDAO();
		return daoCliente.getClientesPeloNome(campoPesquisa);
	}
	
	public void escolheTipoPesquisa() throws SQLException {
		getClientePorNome();
	}
	
	public List<Cliente> getClientePerfilPorNome() throws SQLException {

		ClienteDAO daoCliente = new ClienteDAO();
		List<Cliente> clientesPerfil = daoCliente.getClientesPerfilPeloNome(campoPesquisa);
	
		if (clientesPerfil.isEmpty()) {
			FacesContext contexto = FacesContext.getCurrentInstance();
			FacesMessage mensagem = new FacesMessage("Nenhum cliente encontrado.");
			contexto.addMessage("formBusca", mensagem);
		} else {
			return clientesPerfil;
		}
		return null;
	}
	
	
}
