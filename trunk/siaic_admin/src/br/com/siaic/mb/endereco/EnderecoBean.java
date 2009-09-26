package br.com.siaic.mb.endereco;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.businesslogic.endereco.Estado;
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
	private Estado estado;
	
	
	public EnderecoBean()
	{
	  endereco = new Endereco();
	  bairro = new Bairro();
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
