package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.siaic.businesslogic.ValorMercado;
import br.com.siaic.businesslogic.endereco.Bairro;

/**************************************************************
 * Title:       SIAIC
 * Description: MÓDULO ADMINISTRATIVO
 * Copyright:   Copyright (c) 2009                                            
 * Company:     FACULDADE IDEZ
 * Data:        18/09/2009
 * @author:     Kleiton Vasconcelos
 * @version:    1.0
 **************************************************************
 */
public class ValorMercadoDAO {
	private Connection con = null;
	private PreparedStatement pstm = null;
	private ResultSet rst = null;
	
	private final String INSERIR_VALOR_MERCADO = 
		"INSERT INTO(VAM_BAIRRO, " +
		"            VAM_VALOR) " +
		"VALUES(?,?) ";
	
	public ValorMercadoDAO() {
		
	}
	
	public boolean inserirValorMercado(ValorMercado valorMercado) throws Exception {
		boolean inserir = false;
		int resultado = 0;
		
		try {
			
			FabricaConexao.getInstancia();
			
			// Abre uma conexão com o banco
			this.con = FabricaConexao.conectar();
			
			pstm = this.con.prepareStatement(this.INSERIR_VALOR_MERCADO);
			pstm.setInt(1,valorMercado.getBairro().getBairroCodigo());
			pstm.setDouble(2, valorMercado.getValor());
			resultado = pstm.executeUpdate();
			if(resultado == 0) {
				System.out.println("Não foi possível inserir o valor de marcado. ");
				inserir = false;
			} else {
				System.out.println("Valor de Marcado inserido com sucesso. ");
				inserir = true;
			}
			
		} catch ( SQLException sql ) {
			sql.printStackTrace();
			System.out.println("Erro ao tentar inserir valor de mercado. " + 
					sql.getMessage());
		}
		
		finally {
			
			try {
				
				// Fecha  a instrução e a conexão com o banco
				this.pstm.close();
				this.con.close();
				
			} catch ( Exception e ) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		return inserir;
	}
	
	public static void main(String[] args) {
	}
}
