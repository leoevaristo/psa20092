package br.com.siaic.businesslogic;

import br.com.siaic.dao.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Filipe M. de Azev�do
 *
 */
public class Notificacao {
	
	/**
	 * <p> Aciona uma notifica��o para um im�vel. </p>
	 * <p> N�o obrigat�riamente ser� agendada e notificada 
	 * uma visita para um corretor. O m�todo ir� buscar
	 * um cliente com um perfil adequado, caso encontre
	 * agendar� uma visita para um corretor. </p> 
	 * @param codigoImovel
	 */
    public void acionar(int codigoImovel) {
        
    }
    
    private static Notificacao instance;
    
    private static Notificacao getInstance() {
    	if (Notificacao.instance == null)
    		Notificacao.instance = new Notificacao();
    	return Notificacao.instance;
    }
    
    private Notificacao() {
    	
    }
}
