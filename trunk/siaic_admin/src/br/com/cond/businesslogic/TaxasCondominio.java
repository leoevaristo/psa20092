package br.com.cond.businesslogic;

import java.util.Date;

public class TaxasCondominio {
	
	private String mes;
	
	private String ano;
	
	private Apartamento apartamento;
	
	private double valor;
	
	private boolean estaPago;
	
	private Date dataVencimento;
	
	private Date dataPagamento;
	

	
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
	
	public boolean getEstaPago() {
		return estaPago;
	}
	
	public void setEstaPago(boolean estaPago) {
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
	

}
