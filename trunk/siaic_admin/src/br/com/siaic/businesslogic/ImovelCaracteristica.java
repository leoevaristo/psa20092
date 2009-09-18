package br.com.siaic.businesslogic;

/**
 * 
 * @author Yasmim Tamie Hiramoto Pereira
 * @version 1.0
 * 
 */

public class ImovelCaracteristica {
	private int codigo;
	private int qtdeDormitorio;
	private int qtdeSuite;
	private int qtdeGaragem;
	private char piscina;

	public ImovelCaracteristica(int qtdeDormitorio, int qtdeSuite,
			int qtdeGaragem, char piscina) {
	
		this.qtdeDormitorio = qtdeDormitorio;
		this.qtdeSuite = qtdeSuite;
		this.qtdeGaragem = qtdeGaragem;
		this.piscina = piscina;
	}
	public ImovelCaracteristica(){

	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getQtdeDormitorio() {
		return qtdeDormitorio;
	}
	public void setQtdeDormitorio(int qtdeDormitorio) {
		this.qtdeDormitorio = qtdeDormitorio;
	}
	public int getQtdeSuite() {
		return qtdeSuite;
	}
	public void setQtdeSuite(int qtdeSuite) {
		this.qtdeSuite = qtdeSuite;
	}
	public int getQtdeGaragem() {
		return qtdeGaragem;
	}
	public void setQtdeGaragem(int qtdeGaragem) {
		this.qtdeGaragem = qtdeGaragem;
	}
	public char getPiscina() {
		return piscina;
	}
	public void setPiscina(char piscina) {
		this.piscina = piscina;
	}
}
