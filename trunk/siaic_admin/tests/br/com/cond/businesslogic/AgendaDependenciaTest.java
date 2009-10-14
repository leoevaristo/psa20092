package br.com.cond.businesslogic;

/**
 * @author José Carlos
 */

import java.sql.Date;
import java.sql.Time;

import junit.framework.TestCase;

public class AgendaDependenciaTest extends TestCase {
	
	public AgendaDependencia agendaDependencia;
	
	public int retornoCodigo;
	public Date retornoData;
	public Time retornoHoraInicio;
	public Time retornoHoraFinal;	
	public Condomino retornoCondomino;
	public Dependencia retornoDependencia;
	public AgendaFinalidade retornoFinalidade;
	public char retornoComparecimento;
	
	
	public AgendaDependenciaTest() {
		agendaDependencia = new AgendaDependencia();
		
		
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void testSetaValores(){
		assertEquals(retornoCodigo, agendaDependencia.getCodigo());
		assertEquals(retornoData, agendaDependencia.getData());
		assertEquals(retornoHoraInicio, agendaDependencia.getHoraInicio());
		assertEquals(retornoHoraFinal, agendaDependencia.getHoraFinal());
		assertEquals(retornoCondomino, agendaDependencia.getCondomino());
		assertEquals(retornoDependencia, agendaDependencia.getDependencia());
		assertEquals(retornoFinalidade, agendaDependencia.getFinalidade());
		assertEquals(retornoComparecimento, agendaDependencia.getComparecimento());
	}
	
}
