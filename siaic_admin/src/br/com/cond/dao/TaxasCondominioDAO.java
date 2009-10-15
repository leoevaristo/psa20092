package br.com.cond.dao;

import java.sql.Connection;

import br.com.siaic.dao.FabricaConexao;

public class TaxasCondominioDAO {
	
	
	private Connection conexao = null;
	
	public TaxasCondominioDAO(){
		
		try {
			FabricaConexao.getInstancia();
			this.conexao = FabricaConexao.conectar();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void addTaxaCondominio(int codigoApartamento){
		
		
		
	}
	
	public void setarPagamentoTaxa(int codigoApartamento){
		
	}
	
	

}
