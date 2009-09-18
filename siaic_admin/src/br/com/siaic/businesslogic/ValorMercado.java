package br.com.siaic.businesslogic;

import br.com.siaic.businesslogic.endereco.Bairro;

/**************************************************************
 * Title:       SIAIC
 * Description: MÓDULO ADMINISTRATIVO
 * Copyright:   Copyright (c) 2009                                            
 * Company:     FACULDADE IDEZ
 * Data:        18/09/2009
 * @author:     Kleiton Vasconcelos
 * @version:    1.0
 **************************************************************
 */
public class ValorMercado {
	private Bairro bairro = null;
	private double valor = 0;
	
	public ValorMercado() {
		this.bairro = null;
		this.valor = 0;
	}
	
	public ValorMercado(Bairro bairro, double valor) {
		this.bairro = bairro;
		this.valor = this.valor;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
