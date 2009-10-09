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
		"INSERT INTO VALOR_MERCADO(VAM_BAIRRO, " +
		"                          VAM_VALOR_VENAL) " +
		"VALUES(?,?) ";
	
	private final String CONSULTAR_VALOR_MERCADO = 
		"SELECT VAM_BAIRRO, " +
		"       VAM_VALOR_VENAL " + 
		"FROM VALOR_MERCADO " +
		"WHERE VAM_BAIRRO = ? ";
	
	private final String EXCLUIR_VALOR_MERCADO = 
		"DELETE FROM VALOR_MERCADO " +
		"WHERE VAM_BAIRRO = ? ";
	
	private final String ALTERAR_VALOR_MERCADO = 
		"UPDATE VALOR_MERCADO SET VAM_VALOR_VENAL = ? " +
		"WHERE VAM_BAIRRO = ? ";
	
	public ValorMercadoDAO() {
		
	}
	/************************************************************
	 * Método para inserir um novo valor de mercado
	 * @param valorMercado
	 * @return verdadeiro se o valor foi inserido corretamente
	 * @throws Exception
	 ************************************************************ 
	 */
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
	
	/*******************************************************************
	 * Método para consultar um valor de mercado
	 * @param bairroCodigo
	 * @return dados do valor de mercado
	 * @throws Exception
	 ******************************************************************* 
	 */
	public ValorMercado consultaValorMercadoPorBairro(Bairro bairro) throws Exception {
		
		ValorMercado valorMercado = null;
		
		try {
			
			// Abre a conexão com o banco de dados
			FabricaConexao.getInstancia();
			this.con = FabricaConexao.conectar();
			
			pstm = this.con.prepareStatement(this.CONSULTAR_VALOR_MERCADO);
			pstm.setInt(1, bairro.getBairroCodigo());
			this.rst = pstm.executeQuery();
			if(!(this.rst.next())) {
				System.out.println("Nenhum registro encontrado. ");
				
			} else {
                
				valorMercado = new ValorMercado();
				valorMercado.setBairro(bairro);
				valorMercado.setValor(this.rst.getDouble(2));
			}
			
		} catch ( SQLException sql ) {
			sql.printStackTrace();
			System.out.println("Erro ao tentar consultar valor de mercado. " + 
					sql.getMessage());
		}
		
		finally {
			
			try {
				
				// Fecha a instrução e a conexão com o banco
				this.pstm.close();
				this.con.close();
				
			} catch ( Exception e ) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		return valorMercado;
	}
	
	/*********************************************************************
	 * Método para excluir um valor de mercado
	 * @param bairroCodigo
	 * @return verdadeiro se foi excluído corretamente
	 * @throws Exception
	 ********************************************************************* 
	 */
	public boolean excluirValorMercado(Bairro bairro) throws Exception {
		boolean excluiu = false;
		int resultado = 0;
		
		try {
			
			// Abre a conexão com o banco
			FabricaConexao.getInstancia();
			this.con = FabricaConexao.conectar();
			
			pstm = this.con.prepareStatement(this.EXCLUIR_VALOR_MERCADO);
			pstm.setInt(1, bairro.getBairroCodigo());
			resultado = pstm.executeUpdate();
			if(resultado == 0) {
				System.out.println("O valor de mercado não pode ser excluído. ");
				excluiu = false;
			} else {
				System.out.println("Valor de mercado excluído corretamente. ");
				excluiu = true;
			}
			
		} catch ( SQLException sql ) {
			sql.printStackTrace();
			System.out.println("Erro ao tentar excluir valor de mercado. " +
					sql.getMessage());
		}
		
		finally {
			
			try {
				
				// Fecha a instrução e a conexão com o banco
				this.pstm.close();
				this.con.close();
				
			} catch ( Exception e ) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		return excluiu;
	}
	
	/***************************************************************
	 * Método para alterar um valor de mercado. 
	 * @param valor
	 * @param bairro
	 * @return verdadeiro se o valor alterado corretamente
	 * @throws Exception
	 *************************************************************** 
	 */
	public boolean alterarValorMercado(double valor, int bairro) throws Exception {
		boolean alterou = false;
		int resultado = 0;
		
		try {
			
			// Abre a conexão com o banco de dados
			FabricaConexao.getInstancia();
			this.con = FabricaConexao.conectar();
			
			pstm = this.con.prepareStatement(this.ALTERAR_VALOR_MERCADO);
			pstm.setDouble(1, valor);
			pstm.setInt(2, bairro);
			resultado = pstm.executeUpdate();
			if(resultado == 0) {
				System.out.println("O valor de mercado não pode ser alterado. ");
				alterou = false;
			} else {
				System.out.println("Valor de mercado alterado com sucesso. ");
				alterou = true;
			}
			
			
		} catch ( SQLException sql ) {
			sql.printStackTrace();
			System.out.println("Erro ao tentar alterar valor de mercado. " + 
					sql.getMessage());
		}
		
		finally {
			
			try {
				
				// Fecha a instrução e a conexão com o banco
				this.pstm.close();
				this.con.close();
				
			} catch ( Exception e ) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		return alterou;  
	}
	
	private ValorMercado montaValorMercado( ResultSet rst ) throws SQLException {
		int bairroCodigo = 0;
		double valor = 0;
		Bairro bairro = null;
		
		bairroCodigo = rst.getInt(1);
		valor = rst.getDouble(2);
		bairro = (new EnderecoDAO().getBairroPorCodigo(bairroCodigo)); 
		
		return new ValorMercado(bairro, valor);
	}
	
	public static void main(String[] args) throws Exception {
		ValorMercado vm = null; //new ValorMercado();
		ValorMercadoDAO vmd = new ValorMercadoDAO();
		//vm.setBairro(new EnderecoDAO().getBairroPorCodigo(1));
		//vm.setValor(300000);
		//vmd.inserirValorMercado(vm);
		vm = vmd.consultaValorMercadoPorBairro(new EnderecoDAO().getBairroPorCodigo(2));
		System.out.println(vm.getValor());
		
		//vmd.excluirValorMercado(new EnderecoDAO().getBairroPorCodigo(1));
		
		//vmd.alterarValorMercado(100000, new EnderecoDAO().getBairroPorCodigo(1));
		
	}
}
