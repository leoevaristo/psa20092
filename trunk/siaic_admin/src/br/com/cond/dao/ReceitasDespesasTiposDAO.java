package br.com.cond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

import br.com.cond.businesslogic.ReceitaDespesaTipos;
import br.com.cond.businesslogic.Veiculo;
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
	
	public List<ReceitaDespesaTipos> listaTipos() throws Exception {
		ReceitaDespesaTipos rd = null;
		List<ReceitaDespesaTipos> listaTodosTipos = null;
		
		try {
			
			FabricaConexao.getInstancia();
			// Abre a conexão com o banco
			this.con = FabricaConexao.conectar();
			pstm = this.con.prepareStatement(this.LISTAR_TIPOS);
			this.rst = pstm.executeQuery();
			if(!(this.rst.next())) {
				System.out.println("Nenhum tipo encontrado. ");
			} else {
				
				listaTodosTipos = new ArrayList<ReceitaDespesaTipos>();
				
				while (this.rst.next()) {
					rd = new ReceitaDespesaTipos();
					rd.setCodigo(this.rst.getInt(1));
					rd.setDescricao(this.rst.getString(2));
					listaTodosTipos.add(rd);
				}
			}
			
		} catch ( SQLException sql ) {
			sql.printStackTrace();
			System.out.println("Erro ao tentar listar tipos de receitas e despesas. " +
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
		
		return listaTodosTipos;
	}
	
	public ReceitaDespesaTipos getReceitasDespesasTiposPorCodigo(int codigo) throws Exception {
		ReceitaDespesaTipos rdt = null;
		
		try {
			
			// Abre a conexão com o banco de dados
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
				
				// Fecha a instrução e ao conexão com o banco de dados
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
		
		ArrayList<ReceitaDespesaTipos> = rdd.listaTipos();
		ReceitaDespesaTipos rdt;

			
		for(int i = 0; i < rdd.size(); i++)
		{
			rdt = rdd.get(i);
			sysout(rdt.metodo);
			
		}
	



	}
}
