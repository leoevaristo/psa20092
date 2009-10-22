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
	public String toString(){
		/*String s = new String();
		
		s = Integer.toString(qtdeDormitorio)+" quartos, "+
			Integer.toString(qtdeSuite)+" suites, "+
			Integer.toString(qtdeGaragem)+" vagas na garagem, ";
		
		if (piscina == 'S' || piscina == 's' ) {
			s = s+"com piscina";
		} else if (piscina == 'N' || piscina == 'n') {
			s=s+"sem piscina";
		} 
		
		return s;*/
		
		StringBuilder sb = new StringBuilder();
		sb.append("Piscina: ");
		sb.append(this.getPiscina() == 'S' ? "SIM" : "NÃO");
		sb.append(" - Dormitorios: ");
		sb.append(this.getQtdeDormitorio());
		sb.append(" - V. Garagem: ");
		sb.append(this.getQtdeGaragem() != 0 ? this.getQtdeGaragem() : "Não Possui");
		sb.append(" - Suites: ");
		sb.append(this.getQtdeSuite() != 0 ? this.getQtdeSuite() : "Não Possui");
		return sb.toString();
	}
}
