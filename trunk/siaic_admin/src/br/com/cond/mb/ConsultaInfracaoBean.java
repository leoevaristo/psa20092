package br.com.cond.mb;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;

import br.com.cond.businesslogic.Infracao;
import br.com.cond.dao.InfracaoDAO;
import br.com.cond.dao.ReuniaoDAO;

public class ConsultaInfracaoBean {
	
	public List<Infracao> getTodasAsInfracoes() throws SQLException {

		InfracaoDAO dao = new InfracaoDAO();
		return dao.getTodasAsInfracoes();
		
		
	}
	
	public String excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer cod = new Integer(req.getParameter("codigoEntrada"))
				.intValue();
		
		
		try {
			new InfracaoDAO().removerInfracao(cod);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "excluir";
	}
	
	public String voltar(){
		return "voltar";
	}
}

