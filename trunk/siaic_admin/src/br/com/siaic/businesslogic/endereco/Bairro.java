package br.com.siaic.businesslogic.endereco;

/**
 * 
 * @author george
 *
 */

public class Bairro {

	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bairro [bairroCidade=" + bairroCidade + ", bairroCodigo="
				+ bairroCodigo + ", bairroNome=" + bairroNome + "]";
	}


	/**
	 * @return the bairroCodigo
	 */
	public int getBairroCodigo() {
		return bairroCodigo;
	}


	/**
	 * @param bairroCodigo the bairroCodigo to set
	 */
	public void setBairroCodigo(int bairroCodigo) {
		this.bairroCodigo = bairroCodigo;
	}


	/**
	 * @return the bairroNome
	 */
	public String getBairroNome() {
		return bairroNome;
	}


	/**
	 * @param bairroNome the bairroNome to set
	 */
	public void setBairroNome(String bairroNome) {
		this.bairroNome = bairroNome;
	}


	/**
	 * @return the bairroCidade
	 */
	public Cidade getBairroCidade() {
		return bairroCidade;
	}


	/**
	 * @param bairroCidade the bairroCidade to set
	 */
	public void setBairroCidade(Cidade bairroCidade) {
		this.bairroCidade = bairroCidade;
	}


	private int bairroCodigo;
	private String bairroNome;
	private Cidade bairroCidade;
	
	
	public Bairro(int bairroCodigo, String bairroNome, Cidade bairroCidade) {
		super();
		this.bairroCodigo = bairroCodigo;
		this.bairroNome = bairroNome;
		this.bairroCidade = bairroCidade;
	}
	
	
	
	
}
