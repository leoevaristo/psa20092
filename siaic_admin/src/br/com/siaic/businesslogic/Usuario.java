package br.com.siaic.businesslogic;



/**
 * 
 * @author Alain Rosemberg
 *
 */

public class Usuario extends Pessoa {

	private String CRECI;

	private String login;

	private String senha;

	public String getCRECI() {
		return CRECI;
	}

	public void setCRECI(String creci) {
		CRECI = creci;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
