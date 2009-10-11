package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;

import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.dao.UsuarioDAO;

public class AcessoBean {
	
	private Usuario usuario;
	
	private Usuario login;
	
	private Usuario Senha;
	




	private String tipoPesquisa;
	
	private String campoPesquisa;
	

	public Usuario getLogin() {
		return login;
	}


	public void setLogin(Usuario login) {
		this.login = login;
	}


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


	
	public AcessoBean(){
		usuario = new Usuario();
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

	
	public String escolheTipoPesquisa() throws SQLException{
		if(tipoPesquisa.equals("administrador")){
			
			getAcessoLogin();
		} /*else if (tipoPesquisa.equals("corretor"))
		{
		  getAcessoCorretor();
		}*/
		return campoPesquisa;
}
	
	public List<Usuario> getAcessoLogin() throws SQLException {
		
		UsuarioDAO daoUsuario = new UsuarioDAO();
		
		return daoUsuario.getAcesso(campoPesquisa);
		
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
