package br.com.cond.dao;

import java.util.List;

import junit.framework.TestCase;
import br.com.cond.businesslogic.Dependencia;
import br.com.cond.businesslogic.DependenciaTest;
import br.com.cond.dao.DependenciaDAO;

public class DependenciaDAOTest extends TestCase {

	private DependenciaDAO depenDao;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		depenDao = new DependenciaDAO();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void testAdicionarDependencia() throws Exception{
		
		DependenciaTest depenTes = new DependenciaTest();
		depenTes.testValores(1, "descricaoDao", 'S');
		
		assertEquals(false, depenDao.adicionarDependencia(depenTes.dep));
	}
	
	public void testAtualizaDependencia() throws Exception{
		
		DependenciaTest depenTes = new DependenciaTest();
		depenTes.testValores(3, "alteracaoDao", 'N');
		
		assertEquals(false, depenDao.alterarDependencia(depenTes.dep));
	
	}

	public void testRemoveDependencia() throws Exception{
		
		DependenciaTest depenTes = new DependenciaTest();
		depenTes.testValores(2, "alteracaoDao", 'N');
		
		assertEquals(false, depenDao.removerDependencia(depenTes.dep));
	
	}
	
	public void testBuscaDependencia() throws Exception{
	
		DependenciaTest depenTes = new DependenciaTest();
		depenTes.testValores(0, "descricaoDao", '\u0000');
		
		List<Dependencia> listaDep = this.depenDao.buscaDependencia(depenTes.dep);
		
		assertEquals(3, listaDep.size());
		
	}

}
