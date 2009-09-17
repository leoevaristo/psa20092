package br.com.siaic.businesslogic.endereco;

/**
 * 
 * @author george
 *
 */

public class Endereco {
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Endereco [enderecoBairro=" + enderecoBairro + ", enderecoCep="
				+ enderecoCep + ", enderecoLogradouro=" + enderecoLogradouro
				+ ", enderecoNome=" + enderecoNome + "]";
	}
	/**
	 * @return the enderecoLogradouro
	 */
	public String getEnderecoLogradouro() {
		return enderecoLogradouro;
	}
	/**
	 * @param enderecoLogradouro the enderecoLogradouro to set
	 */
	public void setEnderecoLogradouro(String enderecoLogradouro) {
		this.enderecoLogradouro = enderecoLogradouro;
	}
	/**
	 * @return the enderecoNome
	 */
	public String getEnderecoNome() {
		return enderecoNome;
	}
	/**
	 * @param enderecoNome the enderecoNome to set
	 */
	public void setEnderecoNome(String enderecoNome) {
		this.enderecoNome = enderecoNome;
	}
	/**
	 * @return the enderecoCep
	 */
	public String getEnderecoCep() {
		return enderecoCep;
	}
	/**
	 * @param enderecoCep the enderecoCep to set
	 */
	public void setEnderecoCep(String enderecoCep) {
		this.enderecoCep = enderecoCep;
	}
	/**
	 * @return the enderecoBairro
	 */
	public Bairro getEnderecoBairro() {
		return enderecoBairro;
	}
	/**
	 * @param enderecoBairro the enderecoBairro to set
	 */
	public void setEnderecoBairro(Bairro enderecoBairro) {
		this.enderecoBairro = enderecoBairro;
	}
	private String enderecoLogradouro;
	private String enderecoNome;
	private String enderecoCep;
	private Bairro enderecoBairro;

	
	
	public Endereco(String enderecoLogradouro, String enderecoNome,
			String enderecoCep, Bairro enderecoBairro) {
		super();
		this.enderecoLogradouro = enderecoLogradouro;
		this.enderecoNome = enderecoNome;
		this.enderecoCep = enderecoCep;
		this.enderecoBairro = enderecoBairro;
	}
	
	
	

}
