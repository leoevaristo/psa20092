package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.businesslogic.endereco.Estado;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.PessoaDAO;
import br.com.siaic.dao.UsuarioDAO;

public class ConsultaEstadoBean {
	
	private Estado estado;
	
	
	public ConsultaEstadoBean(){
		estado = new Estado();
	}
	
	
	
	public Estado getEstado() {
		return estado;
	}



	public void setEstado(Estado estado) {
		this.estado = estado;
	}



	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Estado> getTodosEstados() throws SQLException {

		EnderecoDAO dao = new EnderecoDAO();
		
		return dao.getTodosEstados();

	}
/*	
	

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
		//Integer idPessoa = new Integer(req.getParameter("codigoPessoa")).intValue();
		//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
		
		EnderecoDAO dao = new EnderecoDAO();
		setUsuario(dao.getUsuarioId(idPessoa));	
		getEnderecoUsuario();
		
		
		return "detalhes";
	}
	
	
	

	public String updateUsuario() throws SQLException {
		// TODO
		
		String r = "sucesso";
		
		PessoaDAO daoPessoa = new PessoaDAO();
		UsuarioDAO daoUsuario = new UsuarioDAO();
		
		daoPessoa.alterarPessoa(usuario);
		daoUsuario.alterarUsuario(usuario);

		return r;

	}
	
	
	

	public String destroiSessao(){
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove("usuarioBean");
		
		return "destruido";
	}
*/	

}
