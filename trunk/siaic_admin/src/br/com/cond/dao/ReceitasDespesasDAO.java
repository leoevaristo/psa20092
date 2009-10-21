package br.com.cond.dao;
/********************************************************************************
 * Title:       SIAIC - Sistema de Administração de Imobiliárias e Condomínion
 * Description: Módulo Condomínios
 * Copyright:   Copyright (c) 2009                                            
 * Company:     Faculdade IDEZ
 * Data:        14/10/2009
 * @author:     Kleiton Vasconcelos Costa
 * @version:    1.0
 ********************************************************************************
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.cond.businesslogic.ReceitaDespesa;
import br.com.siaic.dao.FabricaConexao;

public class ReceitasDespesasDAO {
	private Connection con = null;
	private PreparedStatement pstm = null;
	private ResultSet rst = null;
	
	private final String INSERIR_DESPESA_RECEITA = 
		"INSERT INTO ADMCON_DESPESA_RECEITA(DER_DRD_CODIGO, " +
		"                                   DER_VALOR, " +
		"                                   DER_TIPO, " +
		"                                   DER_DATA, " +
		"                                   DER_CON_CODIGO) " +
		"VALUES(?,?,?,?,?) ";
	
	private final String ALTERAR_RECEITA_DESPESA = 
		"UPDATE ADMCON_DESPESA_RECEITA SET DER_DRD_CODIGO = ?, " +
		"                                  DER_VALOR = ?, " +
		"                                  DER_TIPO = ?, " +
		"                                  DER_DATA = ?, " +
		"                                  DER_CON_CODIGO = ? " +
		"WHERE DER_CODIGO = ? ";
	
	private final String CONSULTA_RECEITA_DEPESA_CODIGO = 
		"SELECT * FROM ADMCON_DESPESA_RECEITA " +
		"WHERE DER_CODIGO = ? ";
		
	public ReceitasDespesasDAO() {
		
	}
	
	public boolean inserirReceitaDespesa(ReceitaDespesa rd) throws Exception {
		boolean inseriu = false;
		int resultado = 0;
		
		try {
			
			// Abre uma conexão com o banco de dados
			FabricaConexao.getInstancia();
			this.con = FabricaConexao.conectar();
			
			pstm = this.con.prepareStatement(this.INSERIR_DESPESA_RECEITA);
			pstm.setInt(1, rd.getTipoRD().getCodigo());
			pstm.setDouble(2, rd.getValor());
			pstm.setString(3, rd.getTipo());
			pstm.setString(4, rd.getData());
			pstm.setInt(5, rd.getCondominio().getCodigo());
			
			resultado = pstm.executeUpdate();
			if(resultado == 0) {
				System.out.println("Registro não inserido. ");
				inseriu = false;
			} else {
				System.out.println("Registro inserido com sucesso. ");
				inseriu = true;
			}
			
		} catch ( SQLException sql ) {
			sql.printStackTrace();
			System.out.println("Erro ao tentar inserir receitas e despesas. " +
					sql.getMessage());
		}
		
		finally {
			
			try {
				
				// Fecha a instrução e a conexão com o banco de dados
				this.pstm.close();
				this.con.close();
				
			} catch ( Exception e ) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		return inseriu;
	}
	
	public Boolean alterarDespesaReceita( ReceitaDespesa novo, ReceitaDespesa atual) throws Exception {
		boolean alterou = false;
		int resultado = 0;
		
		try {
			
			// Abre uma conexão com o banco de dados
			FabricaConexao.getInstancia();
			this.con = FabricaConexao.conectar();
			
			pstm = this.con.prepareStatement(this.ALTERAR_RECEITA_DESPESA);
			pstm.setInt(1, novo.getTipoRD().getCodigo());
			pstm.setDouble(2, novo.getValor());
			pstm.setString(3, novo.getTipo());
			pstm.setString(4, novo.getData());
			pstm.setInt(5, novo.getCondominio().getCodigo());
			pstm.setInt(6, atual.getCodigo());
			
			resultado = pstm.executeUpdate();
			if(resultado == 0) {
				System.out.println("Não foi possível alterar a despesa/receita");
				alterou = false;
			} else {
				System.out.println("Despesas/Receita alterada com sucesso. ");
				alterou = true;
			}
			
		} catch ( SQLException sql ) {
			sql.printStackTrace();
			System.out.println("Erro ao tentar alterer despesa e receitas. " +
					sql.getMessage());
		}
		
		finally {
			
			try {
				
				// Fecha a instrução e a conexão com o banco de dados
				this.pstm.close();
				this.con.close();
				
			} catch ( Exception e ) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		return alterou;
	}
	
	public ReceitaDespesa consultaReceitaDespesaPorCodigo(int codigo) throws Exception {
		ReceitaDespesa rd = null;
		
		try {
			
			// Abre a conexão com o banco de dados
			FabricaConexao.getInstancia();
			this.con = FabricaConexao.conectar();
			
			pstm = this.con.prepareStatement(this.CONSULTA_RECEITA_DEPESA_CODIGO);
			pstm.setInt(1, codigo);
			this.rst = pstm.executeQuery();
			this.rst.next();
			if(this.rst.getInt(1) == 0) {
				System.out.println("Nenhuma rehistro encontrado. ");
			} else {
				rd = new ReceitaDespesa();
				rd.setCodigo(this.rst.getInt(1));
				rd.setTipoRD(new ReceitasDespesasTiposDAO().getReceitasDespesasTiposPorCodigo(
						this.rst.getInt(2)));
				rd.setValor(this.rst.getDouble(3));
				rd.setTipo(this.rst.getString(4));
				rd.setData(this.rst.getString(5));
				rd.setCondominio(new CondominoDAO().getCondominio(this.rst.getInt(6)));
			}
			
		} catch ( SQLException sql ) {
			sql.printStackTrace();
			System.out.println("Erro ao tentar consultar despesa e receitas. " + 
					sql.getMessage());
		}
		
		finally {
			
			try {
				
				// Fecha a instrução e a conexão com o banco de dados
				this.pstm.close();
				this.con.close();
				
			} catch ( Exception e ) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		return rd;
	}
	
	public static void main(String[] args) throws Exception {
		ReceitasDespesasDAO rdd = new ReceitasDespesasDAO();
		ReceitaDespesa rd = new ReceitaDespesa();
		
		rd.setTipoRD(new ReceitasDespesasTiposDAO().getReceitasDespesasTiposPorCodigo(2));
		rd.setCondominio(new CondominoDAO().getCondominio(1));
		rd.setValor(100);
		rd.setTipo("R");
		rd.setData("2009-10-14");
		
		rdd.inserirReceitaDespesa(rd);
	}
}
