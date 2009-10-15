package br.com.cond.businesslogic;


/**
 * 
 * @author Alain Rosemberg
 * @author Robson (12/10/2009 - 13h)
 *
 */

public class Condomino {

	private int codigo;
	private String nome;
	private Character sexo;
	private String dataNasc;
	private Condomino responsavel;
	private Apartamento apartamento;
	private static boolean createResp = true;

	public Condomino() {
		if (createResp) {
			createResp = false;
			responsavel = new Condomino();
			createResp = true;
		}
		apartamento = new Apartamento();
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Character getSexo() {
		return sexo;
	}
	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}
	public String getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	public Condomino getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Condomino responsavel) {
		this.responsavel = responsavel;
	}
	public Apartamento getApartamento() {
		return apartamento;
	}
	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}
	
	public String getToString() {
		String s = "";
		if (apartamento.getCodigoApartamento() != 0) {
			s = nome + ", " + apartamento.getAndar();
			if (!(apartamento.getBloco() == null)) {
				if (!apartamento.getBloco().equals("")) {
					s = s + " " + apartamento.getBloco();
				}
			}
		}
		return s;
	}


}
