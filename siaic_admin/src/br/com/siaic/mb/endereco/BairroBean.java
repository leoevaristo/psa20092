package br.com.siaic.mb.endereco;

import java.sql.SQLException;

import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.dao.EnderecoDAO;

public class BairroBean {
	
	
	private Bairro bairro;
	

	public BairroBean()
	{
	  bairro = new Bairro();
	  
	}


	public Bairro getBairro() {
		return bairro;
	}


	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
	
	
	public String addBairro() throws SQLException
	{
	  
      EnderecoDAO daoBairro = new EnderecoDAO();
      daoBairro.adicionarBairro(bairro);      
      
      return "sucesso";
      
		
	}

}
