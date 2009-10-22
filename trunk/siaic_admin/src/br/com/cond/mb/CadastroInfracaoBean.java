package br.com.cond.mb;

import java.sql.SQLException;

import br.com.cond.businesslogic.Infracao;
import br.com.cond.dao.InfracaoDAO;

public class CadastroInfracaoBean {
	
	
	private Infracao infracao;
	
	public CadastroInfracaoBean(){
		
		infracao = new Infracao();
	}
	

	public Infracao getInfracao() {
		return infracao;
	}

	public void setInfracao(Infracao infracao) {
		this.infracao = infracao;
		
		
		
		
	}

	
	public String addInfracao() throws SQLException{
		//TODO 
		
		String r = "Sucesso";
		
		InfracaoDAO daoInfracao = new InfracaoDAO();
		daoInfracao.adicionarInfracao(infracao);
		
		infracao = new  Infracao();
		
		return r;
		
		
	}
	
	
	public String voltar(){
	
		return "voltar";
	}
	
	
}