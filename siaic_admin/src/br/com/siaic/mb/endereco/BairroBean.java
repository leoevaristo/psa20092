package br.com.siaic.mb.endereco;

import java.sql.SQLException;

import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.dao.EnderecoDAO;

public class BairroBean {
	
	private Cidade cidade;
	private Bairro bairro;
	

	public BairroBean()
	{
	  bairro = new Bairro();
	  cidade = new Cidade();	
	}


	public Bairro getBairro() {
		return bairro;
	}


	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}



	public Cidade getCidade() {
		return cidade;
	}


	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public String addBairro() throws SQLException
	{
	  
      EnderecoDAO daoBairro = new EnderecoDAO();
      daoBairro.adicionarBairro(bairro);      
      
      return "sucesso";
      
		
	}

}
