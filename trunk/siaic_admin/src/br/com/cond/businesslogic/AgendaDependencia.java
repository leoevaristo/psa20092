package br.com.cond.businesslogic;

/**
 * @author Jos� Carlos
 * @author Yasmim
 */

import java.sql.Date;
import java.sql.Time;

public class AgendaDependencia {

	private int codigo;
	private Date data;
	private Time horaInicio;
	private Time horaFinal;	
	private Condomino condomino;
	private Dependencia dependencia;
	private AgendaFinalidade finalidade;
	private char comparecimento;
	
	public AgendaDependencia() {
		condomino = new Condomino();
		dependencia = new Dependencia();
		finalidade = new AgendaFinalidade();
	}
	
	public int getCodigo() {
		return codigo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Time getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(Time horaFinal) {
		this.horaFinal = horaFinal;
	}
	public Condomino getCondomino() {
		return condomino;
	}
	public void setCondomino(Condomino condomino) {
		this.condomino = condomino;
	}
	public Dependencia getDependencia() {
		return dependencia;
	}
	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}
	public AgendaFinalidade getFinalidade() {
		return finalidade;
	}
	public void setFinalidade(AgendaFinalidade finalidade) {
		this.finalidade = finalidade;
	}
	public char getComparecimento() {
		return comparecimento;
	}
	public void setComparecimento(char comparecimento) {
		this.comparecimento = comparecimento;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String toString(){
		
		String s = new String();
		s = dependencia.toString()+", "+data.toString()+", �s "+horaInicio.toString();
		return s;
		
	}

}
