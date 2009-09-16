package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Inicializa o drive do mySQL e abre uma conexão com o banco.
 * 
 * @author Robson R. Vieira da Cunha
 * @version 1.0
 * 
 */
public class DB {
	private static final String url = new String(
			"jdbc:mysql://localhost:3306/siaic");
	private static final String user = new String("root");
	private static final String password = new String("root");

	private static boolean inicializado = false;
	private static Connection conn = null;

	/**
	 * Pega uma conexão aberta com o banco de dados. Evita que o drive do mySQL
	 * seja carregado mais de uma vez. Evita que mais de uma conexão com a base
	 * de dados seja aberta.
	 * 
	 * @return {@link Connection} (conexão) aberta com o banco de dados.
	 * @throws SQLException
	 */
	public static Connection getConn() throws SQLException {
		if (!inicializado) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inicializado = true;
		}
		if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(url, user, password);
		}
		return conn;
	}
}