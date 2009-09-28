package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.List;

import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.dao.EnderecoDAO;

public class ConsultaCidadeBean {
	
	private Cidade cidade;
	
	
	public ConsultaCidadeBean(){
		cidade = new Cidade();
	}
	
	
	
	public Cidade getCidade() {
		return cidade;
	}



	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}



	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Cidade> getTodasCidades() throws SQLException {

		EnderecoDAO dao = new EnderecoDAO();
		
		return dao.getTodasCidades();

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
