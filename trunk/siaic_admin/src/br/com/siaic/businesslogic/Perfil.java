package br.com.siaic.businesslogic;

/**
 * 
 * @author Yasmim Tamie Hiramoto Pereira
 * @version 1.0
 *  
 */

public class Perfil {
	private int codigo;
	private int codigoPessoaCliente;
	private int codigoImovelCaracteristica;
	private int codigoUsuario;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCodigoPessoaCliente() {
		return codigoPessoaCliente;
	}
	public void setCodigoPessoaCliente(int codigoPessoaCliente) {
		this.codigoPessoaCliente = codigoPessoaCliente;
	}
	public int getCodigoImovelCaracteristica() {
		return codigoImovelCaracteristica;
	}
	public void setCodigoImovelCaracteristica(int codigoImovelCaracteristica) {
		this.codigoImovelCaracteristica = codigoImovelCaracteristica;
	}
	public int getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
}
