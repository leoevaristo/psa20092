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
		String url = "com.mysql.jdbc.Driver";
		Connection c = null;
		
		try
		{
			
			Class.forName(url);		
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/SIAIC","USUARIO","SENHA");
			
		}
		
		catch(ClassNotFoundException e)
		{
			
			e.printStackTrace();
			
		}
		
		
		return c;
		
	}
	
	
	
	

}
