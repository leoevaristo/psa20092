package br.com.cond.businesslogic;

import java.util.Date;

public class TaxasCondominio {
	
	private String mes;
	
	private String ano;
	
	private Apartamento apartamento;
	
	private double valor;
	
	private Character estaPago;
	
	private Date dataVencimento;
	
	private Date dataPagamento;
	
	private int diasAtraso;

	
	public TaxasCondominio(){
		
		
		
		
	}
	
	public String getMes() {
		return mes;
	}
	
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	public String getAno() {
		return ano;
	}
	
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	public Apartamento getApartamento() {
		return apartamento;
	}
	
	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public Character getEstaPago() {
		return estaPago;
	}

	public void setEstaPago(Character estaPago) {
		this.estaPago = estaPago;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}
	
	public void setDataVencimento(Date dataVencimento) {		
		this.dataVencimento = dataVencimento;
	}
	
	public Date getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public int getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(int diasAtraso) {
		this.diasAtraso = diasAtraso;
	}
	

}
