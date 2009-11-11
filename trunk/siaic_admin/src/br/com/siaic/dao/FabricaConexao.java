package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Carlos R. N. Junior
 */
public class FabricaConexao {

	private static FabricaConexao instancia = null;

	/**
	 * Construtor da classe FabricaConexao.
	 */
	private FabricaConexao() {

	}

	public static FabricaConexao getInstancia() {

		if (instancia == null) {
			instancia = new FabricaConexao();
		}

		return instancia;
	}

	/**
	 * Cria conex�o �nica com o banco de dados.
	 * 
	 * @return Connection
	 */
	public static Connection conectar() throws SQLException {
		String url = "com.mysql.jdbc.Driver";

		try {

			Class.forName(url);

		}

		catch (ClassNotFoundException e) {

			e.printStackTrace();

		}


		return DriverManager.getConnection("jdbc:mysql://10.20.2.16:3306/siaic",
				"root", "");

//		return DriverManager.getConnection("jdbc:mysql://localhost:3306/SIAIC",
//				"root", "");

		
//		return DriverManager.getConnection("jdbc:mysql://s156.eatj.com:3307/psa20092",
//				"psa20092", "psa20092");
	}

}
