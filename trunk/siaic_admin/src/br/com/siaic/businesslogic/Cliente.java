package br.com.siaic.businesslogic;

/**
 * 
 * @author Carlos R. N. Junior
 * 
 */
public class Cliente extends Pessoa {

	// private String sexo;

	// private Date dataNascimento;

	private String cpf;

	private String rg;

	private String cnpj;

	// private String razaoSocial;

	// private String inscEstadual;

	// private String site;

	// private String nomeContato;

	// private String nomeFantasia;

	public Cliente() {

	}

	/**
	 * 
	 * @return
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * 
	 * @param cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * 
	 * @return
	 */
	public String getRg() {
		return rg;
	}

	/**
	 * 
	 * @param rg
	 */
	public void setRg(String rg) {
		this.rg = rg;
	}

	/**
	 * 
	 * @return
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * 
	 * @param cnpj
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
