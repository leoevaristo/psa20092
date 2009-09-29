package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.PessoaDAO;
import br.com.siaic.dao.UsuarioDAO;

public class ConsultaUsuarioBean {
	
	private Usuario usuario;
	
	
	private Endereco endereco;
	
	private String tipoPesquisa;
	
	private String campoPesquisa;
	
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}


	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}


	public String getCampoPesquisa() {
		return campoPesquisa;
	}


	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}


	
	public ConsultaUsuarioBean(){
		usuario = new Usuario();
		endereco = new Endereco();
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
	public List<Usuario> getTodosUsuarios() throws SQLException {

		UsuarioDAO dao = new UsuarioDAO();
		
		return dao.getTodosUsuarios();

	}
	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String UsuarioId() throws SQLException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idPessoa = new Integer(req.getParameter("codigoPessoa")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		UsuarioDAO dao = new UsuarioDAO();
		setUsuario(dao.getUsuarioId(idPessoa));
		
		
		
		return "modifica";

	}
	
	

	
	
	public String getEnderecoUsuario() throws SQLException{
		
		EnderecoDAO daoEndereco = new EnderecoDAO();
		setEndereco(daoEndereco.getEnderecoPorCodigo(getUsuario().getEnderecoCodigo()));
		
		return   getEndereco().getEnderecoLogradouro() +" " + getEndereco().getEnderecoNome() + " CEP: " 
				+ getEndereco().getEnderecoCep();
		
	}
	
	
	/**
	 * 
	 * @throws SQLException
	 */
	public void excluiUsuario() throws SQLException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer idPessoa = new Integer(req.getParameter("codigoPessoa"))
				.intValue();

		UsuarioDAO dao = new UsuarioDAO();
		dao.removerUsuario(idPessoa);

	}
	
	
	public String exibeDetalhesUsuarios() throws SQLException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer idPessoa = new Integer(req.getParameter("codigoPessoa")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		UsuarioDAO dao = new UsuarioDAO();
		setUsuario(dao.getUsuarioId(idPessoa));	
		getEnderecoUsuario();
		
		
		return "detalhes";
	}
	
	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String updateUsuario() throws SQLException {
		// TODO
		
		String r = "sucesso";
		
		PessoaDAO daoPessoa = new PessoaDAO();
		UsuarioDAO daoUsuario = new UsuarioDAO();
		
		daoPessoa.alterarPessoa(usuario);
		daoUsuario.alterarUsuario(usuario);

		return r;

	}
	
	
	public List<Usuario> getUsuarioPorNome() throws SQLException {
		
		UsuarioDAO daoUsuario = new UsuarioDAO();
		
		return daoUsuario.getUsuarioPeloNome(campoPesquisa);
		
	}
	
	public void escolheTipoPesquisa() throws SQLException{
		
		if(tipoPesquisa.equals("nome")){
			
			getUsuarioPorNome();
		}
	
	}

	
	
	/**
	 * 
	 * @return
	 */
	public String destroiSessao(){
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove("usuarioBean");
		
		return "destruido";
	}
	

}
