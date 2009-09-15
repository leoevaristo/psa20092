package beans;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FabricaConexao {
	
	
	/**
	 * Cria conexão única com o banco de dados.
	 * @return Connection
	 */
	public static Connection conectar() throws SQLException{
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/BANCO","LOGIN","SENHA");
		
	}

}
