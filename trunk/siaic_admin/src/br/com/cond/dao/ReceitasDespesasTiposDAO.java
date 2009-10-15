package br.com.cond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.org.apache.bcel.internal.generic.NEW;

import br.com.cond.businesslogic.ReceitaDespesaTipos;
import br.com.siaic.dao.FabricaConexao;

public class ReceitasDespesasTiposDAO {
	private Connection con = null;
	private PreparedStatement pstm = null;
	private ResultSet rst = null;
	
	private final String LISTAR_TIPOS = 
		"SELECT * FROM ADMCON_DESPESA_RECEITA_DOMINIO " +
		"ORDER BY DRD_DESCRICAO ";
	
	private final String LOCALIZA_TIPO_POR_CODIGO = 
		"SELECT * FROM ADMCON_DESPESA_RECEITA_DOMINIO " +
		"WHERE DRD_CODIGO = ? ";
	
	public ReceitasDespesasTiposDAO() {
		
	}
	
	public ReceitaDespesaTipos[] listaTipos() throws Exception {
		ReceitaDespesaTipos[] rd = null;
		int qtdTipos = 0;
		
		try {
			
			FabricaConexao.getInstancia();
			// Abre a conex�o com o banco
			this.con = FabricaConexao.conectar();
			pstm = this.con.prepareStatement(this.LISTAR_TIPOS);
			this.rst = pstm.executeQuery();
			if(!(this.rst.next())) {
				System.out.println("Nenhum tipo encontrado. ");
			} else {
				
				do {
					
					qtdTipos +=1;
					
				} while(this.rst.next());
				
				rd = new ReceitaDespesaTipos[qtdTipos];
				
				pstm = this.con.prepareStatement(this.LISTAR_TIPOS);
				this.rst = pstm.executeQuery();
				
				qtdTipos = 0;
				
				while (this.rst.next()) {
					rd[qtdTipos] = this.montaTipos(this.rst);
					qtdTipos++;
				}
			}
			
		} catch ( SQLException sql ) {
			sql.printStackTrace();
			System.out.println("Erro ao tentar listar tipos de receitas e despesas. " +
					sql.getMessage());
		}
		
		finally {
			
			try {
				
				// Fecha a instru��o e a conex�o com o banco
				this.pstm.close();
				this.con.close();
				
			} catch ( Exception e ) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		return rd;
	}
	
	public ReceitaDespesaTipos getReceitasDespesasTiposPorCodigo(int codigo) throws Exception {
		ReceitaDespesaTipos rdt = null;
		
		try {
			
			// Abre a conex�o com o banco de dados
			FabricaConexao.getInstancia();
			this.con = FabricaConexao.conectar();
			
			pstm = this.con.prepareStatement(this.LOCALIZA_TIPO_POR_CODIGO);
			pstm.setInt(1, codigo);
			this.rst = pstm.executeQuery();
			this.rst.next();
			if(this.rst.getInt(1) == 0) {
				System.out.println("Nenhum, registro encontrado. ");
			} else {
				rdt = new ReceitaDespesaTipos();
				rdt.setCodigo(this.rst.getInt(1));
				rdt.setDescricao(this.rst.getString(2));
			}
			
		} catch ( SQLException sql ) {
			sql.printStackTrace();
			System.out.println("Erro ao tentar localizar tipos de receitas e despesas. " +
					sql.getMessage());
		}
		
		finally {
			
			try {
				
				// Fecha a instru��o e ao conex�o com o banco de dados
				this.pstm.close();
				this.con.close();
				
			} catch ( Exception e ) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		return rdt;
	}
	
	private ReceitaDespesaTipos montaTipos( ResultSet rst ) throws Exception {
		int codigo = rst.getInt(1);
		String descricao = rst.getString(2);
		
		return new ReceitaDespesaTipos(codigo, descricao); 
	}
	
	public static void main(String[] args) throws Exception {
		ReceitasDespesasTiposDAO rdd = new ReceitasDespesasTiposDAO();
		ReceitaDespesaTipos[] rdt = null;
		rdt = rdd.listaTipos();
		
		for(int i = 0; i < rdt.length; i++) {
			System.out.println(rdt[i].getDescricao());
		}
	}
}