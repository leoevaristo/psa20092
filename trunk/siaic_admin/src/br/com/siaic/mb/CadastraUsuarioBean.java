package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.businesslogic.endereco.Estado;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.PessoaDAO;
import br.com.siaic.dao.UsuarioDAO;

public class CadastraUsuarioBean {
	
	
	private Endereco endereco;
	
	
	private Bairro bairro;
	
	
	private Cidade cidade;
	
	
	private Estado estado;
	
	
	private Usuario usuario;
	
	private static List<SelectItem> logradouro = new ArrayList<SelectItem>();
	
	/**
	 * Representa uma lista de objetos do tipo SelectItem
	 */
	private static List<SelectItem> cidades = new ArrayList<SelectItem>();
	
	/**
	 * Representa uma lista de objetos do tipo SelectItem
	 */
	private static List<SelectItem> bairros = new ArrayList<SelectItem>();
	

	
	
	
	public CadastraUsuarioBean(){
		
		usuario = new Usuario();
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
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
		

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String addUsuario() throws SQLException {
		// TODO
		
		String r = "sucesso";

		UsuarioDAO daoUsuario = new UsuarioDAO();
		PessoaDAO daoPessoa = new PessoaDAO();
		EnderecoDAO daoEndereco = new EnderecoDAO();
		
		daoEndereco.adicionarEndereco(endereco);
		usuario.setEnderecoCodigo(endereco.getEnderecoCodigo());
		daoPessoa.adicionarPessoa(usuario);
		daoUsuario.adicionarUsuario(usuario);
		
		destroiSessao();

		return r;
	}
	
	public String destroiSessao(){
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove("consultaUsuarioBean");
		
		return "destruido";
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
		this.usuario = new Usuario();
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
