package br.com.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConversorData {
	
	
	public static Calendar converterParaCalendar(Date data){
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		formatador.format(data);
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		System.out.println(cal);
		return cal;
		
	}
	
	

}
