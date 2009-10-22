package br.com.cond.mb;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;

import br.com.cond.businesslogic.Infracao;
import br.com.cond.dao.InfracaoDAO;
import br.com.cond.dao.ReuniaoDAO;

public class ConsultaInfracaoBean {
	
	private Connection conexao = null;
	
	private Infracao infracao;
	

	public Infracao getInfracao() {
		return infracao;
	}

	public void setInfracao(Infracao infracao) {
		this.infracao = infracao;
	}


	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Infracao> getTodasAsInfracoes() throws SQLException {

		InfracaoDAO dao = new InfracaoDAO();
		return dao.getTodasAsInfracoes();
		
		
	}
//	public String editarEntrada(){
//		FacesContext context = FacesContext.getCurrentInstance();
//		HttpServletRequest req = (HttpServletRequest) context
//				.getExternalContext().getRequest();
//
//		Integer cod = new Integer(req.getParameter("codigoEntrada"))
//				.intValue();
//		
//		context.getExternalContext().getRes;
//		
//		try {
//			infracao = new InfracaoDAO().getInfracaoId(cod);
//			atual = new ReuniaoDAO().getReuniao(reuniao.getCodigo());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return "editar";
//	}
	
}

