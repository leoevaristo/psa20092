package br.com.cond.businesslogic;
/**
 * @author José Carlos
 */
import junit.framework.TestCase;

public class AgendaFinalidadeTest extends TestCase {
	
	public AgendaFinalidade agendaFinalidade;
	public int retornoCodigo;
	public String retornoDescricao;
	
	public AgendaFinalidadeTest() {
		agendaFinalidade = new AgendaFinalidade();
	}
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		/* Teste interno */
//		agendaFinalidade.setCodigo(1);
//		agendaFinalidade.setDescricao("descricao");
//		
//		retornoCodigo = 1;
//		retornoDescricao = "descrica";
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void testSetaValores(){
	
		assertEquals(retornoCodigo, agendaFinalidade.getCodigo());
		assertEquals(retornoDescricao, agendaFinalidade.getDescricao());
	}
}
