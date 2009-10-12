package br.com.cond.businesslogic;

/**
 * 
 * @author Yasmim
 *
 */

public class Reuniao {
	private int codigo;
	private String descrição;
	private int agd_codigo;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescrição() {
		return descrição;
	}
	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}
	public int getAgd_codigo() {
		return agd_codigo;
	}
	public void setAgd_codigo(int agdCodigo) {
		agd_codigo = agdCodigo;
	}
	
}
