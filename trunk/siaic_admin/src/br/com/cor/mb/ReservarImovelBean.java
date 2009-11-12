package br.com.cor.mb;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.dao.ClienteDAO;

public class ReservarImovelBean {
	private int codigo;
	private Cliente cliente;
	private Imovel imovel;
	private Date data;
	private Time tempoReserva;
	
	
	private String campoPesquisa;
	
	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}

	public String getCampoPesquisa() {
		return campoPesquisa;
	}
	
	public List<Cliente> getClientePorNome() throws SQLException {
		ClienteDAO daoCliente = new ClienteDAO();
		return daoCliente.getClientesPeloNome(campoPesquisa);
	}
	
	public void escolheTipoPesquisa() throws SQLException {
		getClientePorNome();
	}
	
	
}
