package br.com.siaic.businesslogic;



/**
 * 
 * @author Alain Rosemberg
 *
 */

/**
 * @author Alan
 *
 */
public class Usuario 
{
	
	private int codigoPessoa;

	private String CRECI;
	
	private String login;
		
	private String senha;
	
	
		
	public int getCodigoPessoa() {
		return codigoPessoa;
	}


	public void setCodigoPessoa(int codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}


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
