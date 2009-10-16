package br.com.cond.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;

import br.com.cond.businesslogic.AgendaDependenciaTest;
import br.com.cond.businesslogic.AgendaFinalidade;
import br.com.cond.businesslogic.Condomino;
import br.com.cond.businesslogic.Dependencia;
import junit.framework.TestCase;

public class AgendaDependenciaDAOTest extends TestCase {

	private AgendaDependenciaDAO agendaDependenciaDAO;
	
	public AgendaDependenciaDAOTest() {
		agendaDependenciaDAO = new AgendaDependenciaDAO();
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
	
//	public void testAdicionarAgendaDependencia() throws SQLException{
//		
//		AgendaDependenciaTest agendaDepenTest = new AgendaDependenciaTest();
//		
//		agendaDepenTest.agendaDependencia.setCodigo(1);
//		Date data = new Date(Calendar.getInstance().getTimeInMillis());
//		agendaDepenTest.agendaDependencia.setData(data);
//		agendaDepenTest.agendaDependencia.setHoraInicio(new Time(data.getTime()));
//		agendaDepenTest.agendaDependencia.setHoraFinal(new Time(data.getTime()));
//		
//		Condomino condomino = new Condomino();
//		condomino.setCodigo(1);
//		agendaDepenTest.agendaDependencia.setCondomino(condomino);
//		
//		Dependencia dependencia = new Dependencia();
//		dependencia.setCodigo(1);
//		agendaDepenTest.agendaDependencia.setDependencia(dependencia);
//		
//		AgendaFinalidade agendaFinalidade = new AgendaFinalidade();
//		agendaFinalidade.setCodigo(1);
//		agendaDepenTest.agendaDependencia.setFinalidade(agendaFinalidade);
//		
//		agendaDepenTest.agendaDependencia.setComparecimento('s');
//		
//		agendaDepenTest.retornoCodigo = 1;
//		agendaDepenTest.retornoData = data;
//		agendaDepenTest.retornoHoraInicio = new Time(data.getTime());
//		agendaDepenTest.retornoHoraFinal = new Time(data.getTime());
//		agendaDepenTest.retornoCondomino = condomino;
//		agendaDepenTest.retornoDependencia = dependencia;
//		agendaDepenTest.retornoFinalidade = agendaFinalidade;
//		agendaDepenTest.retornoComparecimento = 's';
//		
//		
//		agendaDepenTest.testSetaValores();
//		
//		assertEquals(false, agendaDependenciaDAO.adicionarAgendaDependencia(agendaDepenTest.agendaDependencia));
//	}
	
//	public void testAlterarAgendaDependencia() throws SQLException{
//		
//		AgendaDependenciaTest agendaDepenTest = new AgendaDependenciaTest();
//		
//		agendaDepenTest.agendaDependencia.setCodigo(5);
//		Date data = new Date(Calendar.getInstance().getTimeInMillis());
//		agendaDepenTest.agendaDependencia.setData(data);
//		agendaDepenTest.agendaDependencia.setHoraInicio(new Time(data.getTime()));
//		agendaDepenTest.agendaDependencia.setHoraFinal(new Time(data.getTime()));
//		
//		Condomino condomino = new Condomino();
//		condomino.setCodigo(1);
//		agendaDepenTest.agendaDependencia.setCondomino(condomino);
//		
//		Dependencia dependencia = new Dependencia();
//		dependencia.setCodigo(1);
//		agendaDepenTest.agendaDependencia.setDependencia(dependencia);
//		
//		AgendaFinalidade agendaFinalidade = new AgendaFinalidade();
//		agendaFinalidade.setCodigo(1);
//		agendaDepenTest.agendaDependencia.setFinalidade(agendaFinalidade);
//		
//		agendaDepenTest.agendaDependencia.setComparecimento('N');
//		
//		agendaDepenTest.retornoCodigo = 5;
//		agendaDepenTest.retornoData = data;
//		agendaDepenTest.retornoHoraInicio = new Time(data.getTime());
//		agendaDepenTest.retornoHoraFinal = new Time(data.getTime());
//		agendaDepenTest.retornoCondomino = condomino;
//		agendaDepenTest.retornoDependencia = dependencia;
//		agendaDepenTest.retornoFinalidade = agendaFinalidade;
//		agendaDepenTest.retornoComparecimento = 'N';
//		
//		
//		agendaDepenTest.testSetaValores();
//		
//		assertEquals(false, agendaDependenciaDAO.alterarAgendaDependencia(agendaDepenTest.agendaDependencia));
//	}
	
	public void testRemoverAgendaDependencia() throws SQLException{
		
		AgendaDependenciaTest agendaDepenTest = new AgendaDependenciaTest();
		
		agendaDepenTest.agendaDependencia.setCodigo(5);
		Date data = new Date(Calendar.getInstance().getTimeInMillis());
		agendaDepenTest.agendaDependencia.setData(data);
		agendaDepenTest.agendaDependencia.setHoraInicio(new Time(data.getTime()));
		agendaDepenTest.agendaDependencia.setHoraFinal(new Time(data.getTime()));
		
		Condomino condomino = new Condomino();
		condomino.setCodigo(1);
		agendaDepenTest.agendaDependencia.setCondomino(condomino);
		
		Dependencia dependencia = new Dependencia();
		dependencia.setCodigo(1);
		agendaDepenTest.agendaDependencia.setDependencia(dependencia);
		
		AgendaFinalidade agendaFinalidade = new AgendaFinalidade();
		agendaFinalidade.setCodigo(1);
		agendaDepenTest.agendaDependencia.setFinalidade(agendaFinalidade);
		
		agendaDepenTest.agendaDependencia.setComparecimento('N');
		
		agendaDepenTest.retornoCodigo = 5;
		agendaDepenTest.retornoData = data;
		agendaDepenTest.retornoHoraInicio = new Time(data.getTime());
		agendaDepenTest.retornoHoraFinal = new Time(data.getTime());
		agendaDepenTest.retornoCondomino = condomino;
		agendaDepenTest.retornoDependencia = dependencia;
		agendaDepenTest.retornoFinalidade = agendaFinalidade;
		agendaDepenTest.retornoComparecimento = 'N';
		
		
		agendaDepenTest.testSetaValores();
		
		assertEquals(false, agendaDependenciaDAO.removerAgendaDependencia(agendaDepenTest.agendaDependencia));
	}
	
}
