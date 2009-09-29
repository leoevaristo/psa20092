package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.endereco.Estado;
import br.com.siaic.dao.EnderecoDAO;


public class ConsultaEstadoBean {
	
	private Estado estado;
	
	
	public ConsultaEstadoBean(){
		estado = new Estado();
	}
	
	
	
	public Estado getEstado() {
		return estado;
	}



	public void setEstado(Estado estado) {
		this.estado = estado;
	}



	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Estado> getTodosEstados() throws SQLException {

		EnderecoDAO dao = new EnderecoDAO();
		
		return dao.getTodosEstados();

	}


}
