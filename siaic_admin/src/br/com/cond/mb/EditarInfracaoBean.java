package br.com.cond.mb;

import java.sql.SQLException;

import br.com.cond.businesslogic.Infracao;
import br.com.cond.dao.InfracaoDAO;
import br.com.cond.dao.ReuniaoDAO;

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
	
	
}