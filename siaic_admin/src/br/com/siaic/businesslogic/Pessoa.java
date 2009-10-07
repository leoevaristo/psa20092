package br.com.siaic.businesslogic;



/**
 * 
 * @author Carlos R. N. Junior
 *
 */
public class Pessoa 
{
	
	
	
	private int codigoPessoa;
	
	
	
	
	private String nome;
	
	
	
	
	private String telefone;
	
	
	
	
	private String celular;
	
	
	
	
	private String email;
	
	
	
	
	private String tipoPessoa;
	
	
	
	
	private String sexo;
	
	
	private int EnderecoCodigo;
	

	//private Endereco endereco;

	
	
	







	public int getEnderecoCodigo() {
		return EnderecoCodigo;
	}




	public void setEnderecoCodigo(int enderecoCodigo) {
		EnderecoCodigo = enderecoCodigo;
	}




	public Pessoa()
	{
		//endereco = new Endereco();
	}
	
	
	
	
	/**
	 * 
	 * @return
	 */
	public int getCodigoPessoa() 
	{
		return codigoPessoa;
	}


	
	
	/**
	 * 
	 * @param codigoPessoa
	 */
	public void setCodigoPessoa(int codigoPessoa) 
	{
		this.codigoPessoa = codigoPessoa;
	}	

	
	

	/**
	 * 
	 * @return
	 */
	public String getNome() 
	{
		return nome;
	}



	
	/**
	 * 
	 * @param nome
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}


	

	/**
	 * 
	 * @return
	 */
	public String getTelefone() 
	{
		return telefone;
	}


	

	/**
	 * 
	 * @param telefone
	 */
	public void setTelefone(String telefone)
	{
		this.telefone = telefone;
	}


	

	/**
	 * 
	 * @return
	 */
	public String getCelular()
	{
		return celular;
	}

	


	/**
	 * 
	 * @param celular
	 */
	public void setCelular(String celular) 
	{
		this.celular = celular;
	}



	
	/**
	 * 
	 * @return
	 */
	public String getEmail()
	{
		return email;
	}

	


	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}


	

	/**
	 * 
	 * @return
	 */
	public String getTipoPessoa() 
	{
		return tipoPessoa;
	}


	

	/**
	 * 
	 * @param tipoPessoa
	 */
	public void setTipoPessoa(String tipoPessoa)
	{
		this.tipoPessoa = tipoPessoa;
	}


	

	/**
	 * 
	 * @return
	 */
/*	 public Endereco getEndereco() {
		return endereco;
	}


*/

	/**
	 * 
	 * @param endereco
	 */
	/*public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
	*/
	
	
	
	
	public String getSexo() 
	{
		return sexo;
	}
	
	
	

	public void setSexo(String sexo) 
	{
		this.sexo = sexo;
	}
	
	
	

	
	

	
}
