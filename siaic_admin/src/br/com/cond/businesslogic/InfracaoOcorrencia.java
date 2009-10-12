package br.com.cond.businesslogic;

import java.sql.Timestamp;


/**
 * 
 * @author George Fernandes Maia
 *
 *
 *  java.util.Date representa data , hora e zona hor�ria de um ponto no tempo.
 *	java.sql.Date representa apenas a data de um ponto no tempo
 *	java.sql.Time representa apenas a hora de um ponto no tempo
 *	java.sql.TimeStamp representa apenas a data e hora de um ponto no tempo
 *
 */

public class InfracaoOcorrencia {
	
	@Override
	public String toString() {
		return "InfracaoOcorrencia [apartamentoInfracaoOcorrencia="
				+ apartamentoInfracaoOcorrencia + ", codigoInfracaoOcorrencia="
				+ codigoInfracaoOcorrencia + ", dataHoraInfracaoOcorrencia="
				+ dataHoraInfracaoOcorrencia + ", infracaoOcorrencia="
				+ infracaoOcorrencia + ", observacaoInfracaoOcorrencia="
				+ observacaoInfracaoOcorrencia + "]";
	}


	public int getCodigoInfracaoOcorrencia() {
		return codigoInfracaoOcorrencia;
	}


	public void setCodigoInfracaoOcorrencia(int codigoInfracaoOcorrencia) {
		this.codigoInfracaoOcorrencia = codigoInfracaoOcorrencia;
	}


	public Timestamp getDataHoraInfracaoOcorrencia() {
		return dataHoraInfracaoOcorrencia;
	}


	public void setDataHoraInfracaoOcorrencia(Timestamp dataHoraInfracaoOcorrencia) {
		this.dataHoraInfracaoOcorrencia = dataHoraInfracaoOcorrencia;
	}


	public String getObservacaoInfracaoOcorrencia() {
		return observacaoInfracaoOcorrencia;
	}


	public void setObservacaoInfracaoOcorrencia(String observacaoInfracaoOcorrencia) {
		this.observacaoInfracaoOcorrencia = observacaoInfracaoOcorrencia;
	}


	public Infracao getInfracaoOcorrencia() {
		return infracaoOcorrencia;
	}


	public void setInfracaoOcorrencia(Infracao infracaoOcorrencia) {
		this.infracaoOcorrencia = infracaoOcorrencia;
	}


	public Apartamento getApartamentoInfracaoOcorrencia() {
		return apartamentoInfracaoOcorrencia;
	}


	public void setApartamentoInfracaoOcorrencia(
			Apartamento apartamentoInfracaoOcorrencia) {
		this.apartamentoInfracaoOcorrencia = apartamentoInfracaoOcorrencia;
	}


	int codigoInfracaoOcorrencia;
	Timestamp dataHoraInfracaoOcorrencia;
	String observacaoInfracaoOcorrencia;
	Infracao infracaoOcorrencia;
	Apartamento apartamentoInfracaoOcorrencia;
	
	public InfracaoOcorrencia(int codigoInfracaoOcorrencia,
			Timestamp dataHoraInfracaoOcorrencia,
			String observacaoInfracaoOcorrencia, Infracao infracaoOcorrencia,
			Apartamento apartamentoInfracaoOcorrencia) {
		super();
		this.codigoInfracaoOcorrencia = codigoInfracaoOcorrencia;
		this.dataHoraInfracaoOcorrencia = dataHoraInfracaoOcorrencia;
		this.observacaoInfracaoOcorrencia = observacaoInfracaoOcorrencia;
		this.infracaoOcorrencia = infracaoOcorrencia;
		this.apartamentoInfracaoOcorrencia = apartamentoInfracaoOcorrencia;
	}
	
	
	public InfracaoOcorrencia(){}
	

}
