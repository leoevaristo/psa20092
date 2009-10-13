package br.com.cond.dao;
/**
 * @author José Carlos
 */
import java.sql.SQLException;

import br.com.cond.businesslogic.AgendaFinalidadeTest;
import junit.framework.TestCase;

public class AgendaFinalidadeDAOTest extends TestCase {
	
	private AgendaFinalidadeDAO agendaFinalidadeDAO;
	
	public AgendaFinalidadeDAOTest() {
		agendaFinalidadeDAO = new AgendaFinalidadeDAO();
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void testAdicionarAgendaFinalidade() throws SQLException{
		
		AgendaFinalidadeTest agendaFTest = new AgendaFinalidadeTest();
		
		agendaFTest.agendaFinalidade.setCodigo(1);
		agendaFTest.agendaFinalidade.setDescricao("descricaoFinaldade");
		agendaFTest.retornoCodigo = 1;
		agendaFTest.retornoDescricao = "descricaoFinaldade";
		
		agendaFTest.testSetaValores();
		
		assertEquals(false, agendaFinalidadeDAO.adicionarAgendaFinalidade(agendaFTest.agendaFinalidade));
		
	}
	
	public void testAlterarAgendaFinalidade() throws SQLException{
		AgendaFinalidadeTest agendaFTest = new AgendaFinalidadeTest();
		
		agendaFTest.agendaFinalidade.setCodigo(1);
		agendaFTest.agendaFinalidade.setDescricao("descricaoFinaldadeAlterada");
		agendaFTest.retornoCodigo = 1;
		agendaFTest.retornoDescricao = "descricaoFinaldadeAlterada";
		
		agendaFTest.testSetaValores();
		
		assertEquals(false, agendaFinalidadeDAO.alterarAgendaFinalidade(agendaFTest.agendaFinalidade));
	}
}
