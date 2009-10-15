package br.com.cond.businesslogic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TaxasCondominio {
	
	private String mes;
	
	private String ano;
	
	private Apartamento apartamento;
	
	private double valor;
	
	private boolean estaPago;
	
	private Calendar dataVencimento;
	
	private Calendar dataPagamento;
	

	
	public TaxasCondominio(){
		dataVencimento = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		dataVencimento.set(Calendar.YEAR, 2010);
		System.out.println(formato.format(dataVencimento.getTime()));
		
		
		
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
	
	public boolean isEstaPago() {
		return estaPago;
	}
	
	public void setEstaPago(boolean estaPago) {
		this.estaPago = estaPago;
	}
	
	public Calendar getDataVencimento() {
		return dataVencimento;
	}
	
	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public Calendar getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	

}
