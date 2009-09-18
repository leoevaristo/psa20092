package br.com.siaic.businesslogic.endereco;

/**
 * 
 * @author george
 *
 */

public class Endereco {
	

	@Override
	public String toString() {
		return "Endereco [enderecoBairro=" + enderecoBairro + ", enderecoCep="
				+ enderecoCep + ", enderecoCodigo=" + enderecoCodigo
				+ ", enderecoLogradouro=" + enderecoLogradouro
				+ ", enderecoNome=" + enderecoNome + "]";
	}



	public int getEnderecoCodigo() {
		return enderecoCodigo;
	}



	public void setEnderecoCodigo(int enderecoCodigo) {
		this.enderecoCodigo = enderecoCodigo;
	}



	public String getEnderecoLogradouro() {
		return enderecoLogradouro;
	}



	public void setEnderecoLogradouro(String enderecoLogradouro) {
		this.enderecoLogradouro = enderecoLogradouro;
	}



	public String getEnderecoNome() {
		return enderecoNome;
	}



	public void setEnderecoNome(String enderecoNome) {
		this.enderecoNome = enderecoNome;
	}



	public String getEnderecoCep() {
		return enderecoCep;
	}



	public void setEnderecoCep(String enderecoCep) {
		this.enderecoCep = enderecoCep;
	}



	public Bairro getEnderecoBairro() {
		return enderecoBairro;
	}



	public void setEnderecoBairro(Bairro enderecoBairro) {
		this.enderecoBairro = enderecoBairro;
	}



	private int enderecoCodigo;
	private String enderecoLogradouro;
	private String enderecoNome;
	private String enderecoCep;
	private Bairro enderecoBairro;
	
	
	
	public Endereco() {
		super();
	}



	public Endereco(int enderecoCodigo, String enderecoLogradouro,
			String enderecoNome, String enderecoCep, Bairro enderecoBairro) {
		super();
		this.enderecoCodigo = enderecoCodigo;
		this.enderecoLogradouro = enderecoLogradouro;
		this.enderecoNome = enderecoNome;
		this.enderecoCep = enderecoCep;
		this.enderecoBairro = enderecoBairro;
	}
	
	

	
	

}
