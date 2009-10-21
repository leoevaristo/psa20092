package br.com.cond.mb;

import java.sql.SQLException;

import br.com.cond.businesslogic.Veiculo;
import br.com.cond.dao.VeiculoDAO;


public class AcessoVeiculoBean {
	
	public AcessoVeiculoBean() {

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
	
	public String verificarAcesso() {
		VeiculoDAO vdao = new VeiculoDAO();
		try {
			this.veiculo = vdao.getVeiculoPelaPlaca(this.placa);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.liberado = this.veiculo != null;
		return "";
	}

}
