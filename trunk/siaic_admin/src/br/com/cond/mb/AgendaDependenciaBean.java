package br.com.cond.mb;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.cond.businesslogic.AgendaDependencia;


public class AgendaDependenciaBean {
	
	/*
	 * Atributos
	 * */
	private AgendaDependencia agendaDependencia;
	private String horaInicio;
	private String minInicio;
	private String horaFinal;
	private String minFinal;
	private List<SelectItem> horas; 
	private List<SelectItem> minutos;
	private List<SelectItem> finalidades;
	private boolean comparecimento;
	

	public boolean isComparecimento() {
		
		if(agendaDependencia.getComparecimento() == 'S'){
			return true;
		}else if(agendaDependencia.getComparecimento() == 'N'){
			return false;
		}
		
		return false;
	}

	public void setComparecimento(boolean comparecimento) {
		this.comparecimento = comparecimento;
	}

	/*
	 * Contrutores 
	 */
	public AgendaDependenciaBean() {
		
		agendaDependencia = new AgendaDependencia();
		
		horas = new ArrayList<SelectItem>();
		horas.add(new SelectItem("00", "00"));
		horas.add(new SelectItem("01", "01"));
		horas.add(new SelectItem("02", "02"));
		horas.add(new SelectItem("03", "03"));
		horas.add(new SelectItem("04", "04"));
		horas.add(new SelectItem("05", "05"));
		horas.add(new SelectItem("06", "06"));
		horas.add(new SelectItem("07", "07"));
		horas.add(new SelectItem("08", "08"));
		horas.add(new SelectItem("09", "09"));
		horas.add(new SelectItem("10", "10"));
		horas.add(new SelectItem("11", "11"));
		horas.add(new SelectItem("12", "12"));
		horas.add(new SelectItem("13", "13"));
		horas.add(new SelectItem("14", "14"));
		horas.add(new SelectItem("15", "15"));
		horas.add(new SelectItem("16", "16"));
		horas.add(new SelectItem("17", "17"));
		horas.add(new SelectItem("18", "18"));
		horas.add(new SelectItem("19", "19"));
		horas.add(new SelectItem("20", "20"));
		horas.add(new SelectItem("21", "21"));
		horas.add(new SelectItem("22", "22"));
		horas.add(new SelectItem("23", "23"));
		
		minutos = new ArrayList<SelectItem>();
		minutos.add(new SelectItem("00", "00"));
		minutos.add(new SelectItem("15", "15"));
		minutos.add(new SelectItem("30", "30"));
		minutos.add(new SelectItem("45", "45"));
		
		finalidades = new ArrayList<SelectItem>();
		finalidades.add(new SelectItem("1", "Festas"));
		finalidades.add(new SelectItem("2", "Eventos"));
		finalidades.add(new SelectItem("3", "Reuniões"));
	}
	
	public List<SelectItem> getFinalidades() {
		return finalidades;
	}

	public void setFinalidades(List<SelectItem> finalidades) {
		this.finalidades = finalidades;
	}

	/*
	 * Propriedades  
	 */
	public List<SelectItem> getMinutos() {
		return minutos;
	}

	public void setMinutos(List<SelectItem> minutos) {
		this.minutos = minutos;
	}

	public List<SelectItem> getHoras() {
		return horas;
	}

	public void setHoras(List<SelectItem> horas) {
		this.horas = horas;
	}

	public AgendaDependencia getAgendaDependencia() {
		return agendaDependencia;
	}

	public void setAgendaDependencia(AgendaDependencia agendaDependencia) {
		this.agendaDependencia = agendaDependencia;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getMinInicio() {
		return minInicio;
	}

	public void setMinInicio(String minInicio) {
		this.minInicio = minInicio;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getMinFinal() {
		return minFinal;
	}

	public void setMinFinal(String minFinal) {
		this.minFinal = minFinal;
	}
	
	/*
	 * Métodos
	 */
	
	public void adicionarAgendaDependente(){
		System.out.println(agendaDependencia.getData().getTime());
	}
}
