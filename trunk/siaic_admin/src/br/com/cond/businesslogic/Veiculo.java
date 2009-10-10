package br.com.cond.businesslogic;
/**
 * 
 * @author Alain Rosemberg
 *
 */

public class Veiculo {

	private int codigoVeiculo;
	
	private String Descricao;
	
	private String Placa;

	private String Cor;
	
	private Integer codigoApartamento;

	public int getCodigoVeiculo() {
		return codigoVeiculo;
	}

	public void setCodigoVeiculo(int codigoVeiculo) {
		this.codigoVeiculo = codigoVeiculo;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public String getPlaca() {
		return Placa;
	}

	public void setPlaca(String placa) {
		Placa = placa;
	}

	public String getCor() {
		return Cor;
	}

	public void setCor(String cor) {
		Cor = cor;
	}

	public Integer getCodigoApartamento() {
		return codigoApartamento;
	}

	public void setCodigoApartamento(Integer codigoApartamento) {
		this.codigoApartamento = codigoApartamento;
	}



}
