package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.siaic.businesslogic.ImovelCaracteristica;
import br.com.siaic.businesslogic.Perfil;

/**
 * 
 * @author Yasmim Tamie Hiramoto Pereira
 * @version 1.0
 *  
 */

public class PerfilDAO {
	private static PerfilDAO instance;
		 
	private static PerfilDAO getInstance() {
		 if (PerfilDAO.instance == null) {
		  PerfilDAO.instance = new PerfilDAO();
		 }
		 return PerfilDAO.instance;
	}

}
