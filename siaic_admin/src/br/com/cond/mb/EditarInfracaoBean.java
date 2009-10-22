package br.com.cond.mb;

import java.sql.SQLException;

import javax.faces.context.FacesContext;

import br.com.cond.businesslogic.Infracao;
import br.com.cond.dao.InfracaoDAO;

public class EditarInfracaoBean {
	private Infracao infracao;
	
	public EditarInfracaoBean(){
		
		infracao = new Infracao();
	}
	

	public Infracao getInfracao() {
		return infracao;
	}

	public void setInfracao(Infracao infracao) {
		this.infracao = infracao;
		
		
		
		
	}

	
	public String editarInfracao() throws SQLException{
		//TODO 
		
		String r = "sucesso";
		
		InfracaoDAO daoInfracao = new InfracaoDAO();
		daoInfracao.alterarInfracao(infracao);
		
		infracao = new  Infracao();
		
		return r;
	}
	
	
	public String voltar(){
	
		return "voltar";
	}
	
	public String editar() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer cod = new Integer(req.getParameter("codigoEntrada"))
				.intValue();
		
		
		try {
			infracao = new InfracaoDAO().getInfracaoId(cod);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "editar";
	}
	
	
}