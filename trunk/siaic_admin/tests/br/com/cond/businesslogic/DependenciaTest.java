package br.com.cond.businesslogic;
/**
 * @author José Carlos
 */
import junit.framework.TestCase;
import br.com.cond.businesslogic.Dependencia;

public class DependenciaTest extends TestCase {
	public Dependencia dep;

	public DependenciaTest() throws Exception {
		setUp();
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dep = new Dependencia();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	/**
	 * 
	 * @param codigo
	 * @param descricao
	 * @param reservavel
	 * inseri os valores no Objeto Dependencia e testa se os valores que retorna nos "gets" são os mesmos que foram setados
	 */
	public void testValores(int codigo, String descricao,	char reservavel) {
		setValodesDependencia(codigo, descricao, reservavel);

		assertEquals(codigo, dep.getCodigo());
		assertEquals(descricao, dep.getDescricao());
		assertEquals(reservavel, dep.getReservavel());
	}
	/**
	 * 
	 * @param codigo
	 * @param descricao
	 * @param reservavel
	 * seta os valores no objeto Dependencia
	 */
	public void setValodesDependencia(int codigo, String descricao,	char reservavel) {
		dep.setCodigo(codigo);
		dep.setDescricao(descricao);
		dep.setReservavel(reservavel);
	}

}
