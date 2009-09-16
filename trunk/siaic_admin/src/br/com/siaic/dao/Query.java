package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
	public Query() {
		try {
			this.conexao = FabricaConexao.conectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection conexao;

	public PreparedStatement getPreparedStatement(String statementString) {
		try {
			return this.conexao.prepareStatement(statementString);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Statement getStatament() {
		try {
			return this.conexao.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
