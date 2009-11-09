package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.businesslogic.endereco.Estado;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.PessoaDAO;

public class ConsultaClienteBean {
	
	
	private Cliente cliente;	
	
	private Endereco endereco;
	
	private Cidade cidade;
	
	private Estado estado;
	
	private Bairro bairro;
	
	private String tipoPesquisa;
	
	private String campoPesquisa;
	
	private static List<SelectItem> estados = new ArrayList<SelectItem>();
	
	private static List<SelectItem> logradouro = new ArrayList<SelectItem>();
	
	private static List<SelectItem> cidades = new ArrayList<SelectItem>();
	
	private static List<SelectItem> bairros = new ArrayList<SelectItem>();
	

	public ConsultaClienteBean(){
		cliente = new Cliente();
		cidade = new Cidade();
		bairro = new Bairro();
		estado = new Estado();
		endereco = new Endereco();
		
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
	public List<Cliente> getTodosClientes() throws SQLException {

		ClienteDAO dao = new ClienteDAO();
		
		return dao.getTodosClientes();

	}
	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String localizarClientePorId() throws SQLException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idPessoa = new Integer(req.getParameter("codigoPessoa")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		ClienteDAO dao = new ClienteDAO();
		setCliente(dao.getClientePorId(idPessoa));
		getEnderecoCliente();
		
		
		
		return "modifica";

	}
	
	

	
	
	public String getEnderecoCliente() throws SQLException{
		
		EnderecoDAO daoEndereco = new EnderecoDAO();
		setEndereco(daoEndereco.getEnderecoPorCodigo(getCliente().getEnderecoCodigo()));
		
		return   getEndereco().getEnderecoLogradouro() +" " + getEndereco().getEnderecoNome() + " CEP: " 
				+ getEndereco().getEnderecoCep();
		
	}
	
	
	/**
	 * 
	 * @throws SQLException
	 */
	public void excluiCliente() throws SQLException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer idPessoa = new Integer(req.getParameter("codigoPessoa"))
				.intValue();

		ClienteDAO dao = new ClienteDAO();
		dao.removerCliente(idPessoa);

	}
	
	
	public String exibeDetalhesCliente() throws SQLException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idPessoa = new Integer(req.getParameter("codigoPessoa")).intValue();
		ClienteDAO dao = new ClienteDAO();
		setCliente(dao.getClientePorId(idPessoa));	
		getEnderecoCliente();	
		
		return "detalhes";
	}
	
	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String updateCliente() throws SQLException {
		// TODO
		
		String r = "sucesso";
		
		PessoaDAO daoPessoa = new PessoaDAO();
		ClienteDAO daoCliente = new ClienteDAO();
		EnderecoDAO daoEndereco = new EnderecoDAO();
		
		daoPessoa.alterarPessoa(cliente);
		daoCliente.alterarCliente(cliente);
		daoEndereco.alterarEndereco(endereco);
		
		destroiSessao();

		return r;

	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public String destroiSessao(){
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove("consultaClienteBean");
		
		return "destruido";
	}


	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}


	public String getTipoPesquisa() {
		return tipoPesquisa;
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
	
	public void escolheTipoPesquisa() throws SQLException{
		
		if(tipoPesquisa.equals("email")){
			
			pesquisaClientePorEmail(campoPesquisa);
		
		}else if(tipoPesquisa.equals("nome")){
			
			getClientePorNome();
			
		}else if(tipoPesquisa.equals("telefone")){
			
			pesquisaClientePorTelefone(campoPesquisa);
		}
		
	}


	private void pesquisaClientePorTelefone(String telefone) {
		// TODO Auto-generated method stub
			
	}


	public List<Cliente> getClientePorNome() throws SQLException {
		
		ClienteDAO daoCliente = new ClienteDAO();
		
		return daoCliente.getClientesPeloNome(campoPesquisa);
		
	}


	private void pesquisaClientePorEmail(String email) {
		// TODO Auto-generated method stub
		
	}


	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}


	public String getCampoPesquisa() {
		return campoPesquisa;
	}


	public static List<SelectItem> getEstados() {
		return estados;
	}


	public static void setEstados(List<SelectItem> estados) {
		ConsultaClienteBean.estados = estados;
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
