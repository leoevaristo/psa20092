package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.businesslogic.endereco.Estado;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.PessoaDAO;

public class CadastraVeiculoBean {
	
	
	private Endereco endereco;
	
	
	private Bairro bairro;
	
	
	private Cidade cidade;
	
	
	private Estado estado;
	
	
	private Cliente cliente;
	
	
	private static List<SelectItem> logradouro = new ArrayList<SelectItem>();
	
	private static List<SelectItem> cidades = new ArrayList<SelectItem>();
	
	
	
	
	

	
	
	
	public CadastraVeiculoBean(){
		
		cliente = new Cliente();
		endereco = new Endereco();
		cidade = new Cidade();
		estado = new Estado();
		bairro = new Bairro();
		
		if(logradouro.isEmpty())
		setLogradouro();
		
	
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


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}
	
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String addCliente() throws SQLException {
		// TODO
		
		String r = "sucesso";

		ClienteDAO daoCliente = new ClienteDAO();

		PessoaDAO daoPessoa = new PessoaDAO();
		
		EnderecoDAO daoEndereco = new EnderecoDAO();
		
		daoEndereco.adicionarEndereco(endereco);
		
		cliente.setEnderecoCodigo(endereco.getEnderecoCodigo());
		
		daoPessoa.adicionarPessoa(cliente);
		daoCliente.adicionarCliente(cliente);	
		

		return r;

	}
	
	
	public void setLogradouro(){
		
		logradouro.add(new SelectItem("Rua","Rua"));	
		logradouro.add(new SelectItem("Avenida", "Avenida"));
		logradouro.add(new SelectItem("Estrada", "Estrada"));
		logradouro.add(new SelectItem("Praça", "Praça"));
		logradouro.add(new SelectItem("Travessa", "Travessa"));
		logradouro.add(new SelectItem("Alameda", "Alameda"));
		logradouro.add(new SelectItem("Parque", "Parque"));
		
	}
	
	
	public List<SelectItem> getLogradouro(){
		return logradouro;
	}
	
	
	public void limpaCampos(){
		
		//TODO
	}
	
	public void filtraCidadePorEstado(ValueChangeEvent event) throws SQLException{
		
		
		if(event.getNewValue() != event.getOldValue()){
			List<Cidade> cid = new ArrayList<Cidade>();
			EnderecoDAO dao = new EnderecoDAO();
			cid = dao.getCidadePorEstado(event.getNewValue().toString());
			setCidades(cid);
		
		}
	}
	
	public  List<SelectItem> getCidades()  {
		return cidades;
	}


	public static  void setCidades(List<Cidade> listCidades) {
		
		if(!cidades.isEmpty()){
			cidades.clear();
		}
		
		for(Cidade cid : listCidades){
			cidades.add(new SelectItem(cid.getCidadeCodigo(),cid.getCidadeNome()));
		}
	}
	


	

}
