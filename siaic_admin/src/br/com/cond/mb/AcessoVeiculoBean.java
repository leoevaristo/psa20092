package br.com.cond.mb;

import br.com.cond.businesslogic.Veiculo;


public class AcessoVeiculoBean {
	
	public AcessoVeiculoBean() {
		this.veiculo = new Veiculo();
		veiculo.setDescricao("Gol Bola");
		veiculo.setCor("Vermelho");
	}

	private boolean liberado;
	private String placa;
	private Veiculo veiculo;
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public boolean isLiberado() {
		return liberado;
	}

	public void setLiberado(boolean liberado) {
		this.liberado = liberado;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
}
