package br.com.siaic.mb.endereco;

import java.sql.SQLException;

import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.businesslogic.endereco.Estado;
import br.com.siaic.dao.EnderecoDAO;

public class CidadeBean {
	
	private Cidade cidade;
	private Estado estado;
	

	public CidadeBean()
	{
	  cidade = new Cidade();	
	  estado = new Estado();
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public Cidade getCidade() {
		return cidade;
	}


	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public String addCidade() throws SQLException
	{
	  
      EnderecoDAO daoCidade = new EnderecoDAO();
      daoCidade.adicionarCidade(cidade);
      
      return "sucesso";
      
		
	}

}
