package br.com.siaic.mb.endereco;

/**
 * @author george
 */

import java.sql.SQLException;

import br.com.siaic.businesslogic.endereco.Estado;
import br.com.siaic.dao.DAONotificacao;
import br.com.siaic.dao.EnderecoDAO;

public class EstadoBean {
	
	
	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	private Estado estado;

	public EstadoBean(Estado estado) {
		this.estado = estado;
	}
	
	public boolean adicionarEstado(Estado estado){
	
		boolean retorno = false;
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		try {
			retorno = enderecoDAO.adicionarEstado(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	
	public boolean alterarEstado(Estado estado){
		boolean retorno = false;
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		try {
			retorno = enderecoDAO.alterarEstado(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public Estado localizar(String estadoSigla){
		Estado estado = null;
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		try {
			estado = enderecoDAO.getEstadoPorSigla(estadoSigla);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return estado;
	}
	
	

}
