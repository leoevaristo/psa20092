package br.com.cor.businesslogic;

import java.util.Date;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Imovel;

public class ReservarImovel {
	private int codigo;
	private Cliente cliente;
	private Imovel imovel;
	private int tempoReserva;
	private Date data;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public int getTempoReserva() {
		return tempoReserva;
	}
	public void setTempoReserva(int tempoReserva) {
		this.tempoReserva = tempoReserva;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
