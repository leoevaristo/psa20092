package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Carlos R. N. Junior
 */
public class FabricaConexao {

	
	
	
	/**
	 * Construtor da classe FabricaConexao.
	 */
	private FabricaConexao()
	{
		
	}
	
	
	
	
	/**
	 * Cria conex�o �nica com o banco de dados.
	 * @return Connection
	 */
	public static Connection conectar() throws SQLException
	{
		
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");			
			
		}
		
		catch(ClassNotFoundException e)
		{
			
			e.printStackTrace();
			
		}
		
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/SIAIC","root","21Cr4zy12");
		
	}

}
