package br.com.siaic.mb.endereco;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.dao.EnderecoDAO;

/**
 * 
 * @author carlos
 *
 */
public class EnderecoBean {
	
	private Endereco endereco;
	private Bairro bairro;
	private Cidade cidade;
	
	

	public Cidade getCidade() {
		return cidade;
	}




	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}




	public Bairro getBairro() {
		return bairro;
	}




	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}




	public Endereco getEndereco() {
		return endereco;
	}
	
	
	

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
	
	public String addEndereco() throws SQLException{
		//TODO
		String r = "";
		
		EnderecoDAO daoEndereco = new EnderecoDAO();
		daoEndereco.adicionarEndereco(endereco);
		
		return r;
	}
	
	
	

	
	
	
	
	

}
