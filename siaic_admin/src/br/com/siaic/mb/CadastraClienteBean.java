package br.com.siaic.mb;

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

/**
 * Classe responsável por manipular os dados do cliente
 * e registrá-los no banco de dados.
 * @author carlos
 *
 */
public class CadastraClienteBean {
	
	/**
	 * Representa um objeto do tipo Endereco
	 */
	private Endereco endereco;
	
	/**
	 * Representa um objeto do tipo Bairro
	 */
	private Bairro bairro;
	
	/**
	 * Representa um objeto do tipo Cidade
	 */
	private Cidade cidade;
	
	/**
	 * Representa um objeto do tipo Estado
	 */
	private Estado estado;
	
	/**
	 * Representa um objeto do tipo Cliente
	 */
	private Cliente cliente;
	
	/**
	 * Representa uma lista de objetos do tipo SelectItem
	 */
	private static List<SelectItem> logradouro = new ArrayList<SelectItem>();
	
	/**
	 * Representa uma lista de objetos do tipo SelectItem
	 */
	private static List<SelectItem> cidades = new ArrayList<SelectItem>();
	
	/**
	 * Representa uma lista de objetos do tipo SelectItem
	 */
	private static List<SelectItem> bairros = new ArrayList<SelectItem>();
	
	
	/**
	 * Construtor da classe CadastraClienteBean
	 */
	public CadastraClienteBean(){
		
		cliente = new Cliente();
		endereco = new Endereco();
		cidade = new Cidade();
		estado = new Estado();
		bairro = new Bairro();
		
		if(logradouro.isEmpty())
		setLogradouro();
	
	}
	
	/**
	 * Método getter da propriedade bairro 
	 * @return Bairro
	 */
	public Bairro getBairro() {
		return bairro;
	}

	/**
	 * Método setter da propriedade bairro
	 * @param bairro
	 */
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	/**
	 * Método getter da propriedade cidade
	 * @return Cidade
	 */
	public Cidade getCidade() {
		return cidade;
	}

	/**
	 * Método setter da propriedade cidade
	 * @param cidade
	 */
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	/**
	 * Método getter da propriedade estado
	 * @return Estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * Método setter da propriedade estado
	 * @param estado
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	/**
	 * Método getter da propriedade endereco
	 * @return Endereco
	 */
	public Endereco getEndereco() {
		return endereco;
	}
	
	/**
	 * Método setter da propriedade endereco 
	 * @param endereco
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	/**
	 * Método getter da propriedade cliente
	 * @return Cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}
	
	/**
	 * Método setter da propriedade cliente 
	 * @param cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * Método que registra um novo cliente no sistema
	 * @return String
	 * @throws SQLException
	 */
	public String addCliente() throws SQLException {
				
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
	
	/**
	 * Método que preenche uma lista de SelectItem
	 * contendo dados de logradouros pertencentes
	 * a um endereço
	 */
	public void setLogradouro(){
		
		logradouro.add(new SelectItem("Rua","Rua"));	
		logradouro.add(new SelectItem("Avenida", "Avenida"));
		logradouro.add(new SelectItem("Estrada", "Estrada"));
		logradouro.add(new SelectItem("Praça", "Praça"));
		logradouro.add(new SelectItem("Travessa", "Travessa"));
		logradouro.add(new SelectItem("Alameda", "Alameda"));
		logradouro.add(new SelectItem("Parque", "Parque"));
		
	}
	
	/**
	 * Retorna uma lista de SelectItem 
	 * contendo dados de logradouros pertencentes
	 * a um endereço
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getLogradouro(){
		return logradouro;
	}
	
	
	public void limpaCampos(){
		this.cliente = new Cliente();
		this.endereco = new Endereco();
		this.cidade = new Cidade();
		this.estado = new Estado();
		this.bairro = new Bairro();
		
		if(!logradouro.isEmpty())
		setLogradouro();
		
	}
	
	public String cancelar(){
		
		return "cancelar";
	}
	
	/**
	 * Método que filtra automaticamente as cidades
	 * pertencentes a um determinado estado
	 * @param event
	 * @throws SQLException
	 */
	public void filtraCidadePorEstado(ValueChangeEvent event) throws SQLException{		
		
		if(event.getNewValue() != event.getOldValue()){
			List<Cidade> cid = new ArrayList<Cidade>();
			EnderecoDAO dao = new EnderecoDAO();
			cid = dao.getCidadePorEstado(event.getNewValue().toString());
			setCidades(cid);		
		}
	}
	
	public void filtraBairroPorCidade(ValueChangeEvent event) throws SQLException{
		if(event.getNewValue() != event.getOldValue()){
			List<Bairro> listaBairros = new ArrayList<Bairro>();
			EnderecoDAO daoEndereco = new EnderecoDAO();
			listaBairros = daoEndereco.getBairroPorCidade(event.getNewValue().toString());
			setBairros(listaBairros);
		}
	}
	
	/**
	 * Retorna uma Lista de SelectItem contendo
	 * nome de cidades
	 * @return List<SelectItem>
	 */
	public  List<SelectItem> getCidades()  {
		return cidades;
	}

	/**
	 * Método que recebe uma lista do tipo Cidade
	 * e preenhce uma lista do tipo SelectItem com esses dados
	 * @param listCidades
	 */
	public static void setCidades(List<Cidade> listCidades) {
		
		if(!cidades.isEmpty()){
			cidades.clear();
		}
		
		for(Cidade cid : listCidades){
			cidades.add(new SelectItem(cid.getCidadeCodigo(),cid.getCidadeNome()));
		}
	}

	public  List<SelectItem> getBairros() {
		return bairros;
	}

	public static void setBairros(List<Bairro> listBairros) {
		if(!bairros.isEmpty()){
			bairros.clear();
		}
		for(Bairro bai : listBairros){
			bairros.add(new SelectItem(bai.getBairroCodigo(),bai.getBairroNome()));
		}
	}
}
