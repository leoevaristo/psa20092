package br.com.cond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cond.businesslogic.BalanceteMensal;
import br.com.cond.businesslogic.ReceitaDespesa;
import br.com.cond.businesslogic.ReceitaDespesaTipos;
import br.com.siaic.dao.FabricaConexao;


public class GerarBalanceteMensalDAO {
	
private Connection conexao = null;
	
	public GerarBalanceteMensalDAO() {

		try {

			FabricaConexao.getInstancia();
			this.conexao = FabricaConexao.conectar();

		}

		catch (Exception e) {

			e.printStackTrace();

		}
	}

	//metodo que trará como retorno a descrição detalhada de todas as receitas e despesas do mes desejado
	public List<ReceitaDespesa> getReceitas(BalanceteMensal bal) throws SQLException {
			
		String sql = "SELECT DRC_CODIGO, DRC_DESCRICAO, DER_CODIGO,   "
			+ "DER_VALOR, DER_TIPO, DER_DATA "
			+ "FROM ADMCON_DESPESA_RECEITA JOIN ADMCON_DESPESA_RECEITA_DOMINIO"
			+ "WHERE ADMCON_DESPESA_RECEITA.DRC_CODIGO = ADMCON_DESPESA_RECEITA_DOMINIO.DER_DRD_CODIGO"
			+ " AND MONTH(DER_DATA) = ? AND YEAR(DER_DATA)= ? AND DER_TIPO = \"R\""
			+ "ORDER BY DER_DATA";

		try{
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, bal.getMes());
			ps.setString(2, bal.getAno());
			
			ResultSet rs = ps.executeQuery();

			List<ReceitaDespesa> listaReceitas = new ArrayList<ReceitaDespesa>();

			while (rs.next()) {
			
				ReceitaDespesaTipos rcdt = new ReceitaDespesaTipos();
				ReceitaDespesa recDesp = new ReceitaDespesa();
								
				rcdt.setCodigo(rs.getInt(1));
				rcdt.setDescricao(rs.getString(2));
				
				recDesp.setCodigo(rs.getInt(3));
				recDesp.setValor(rs.getDouble(4));
				recDesp.setTipoRD(rcdt);
				recDesp.setData(rs.getString(5));
				recDesp.setCondominio(null);	//nao é relevante saber o responsavel no balancete			
				
				listaReceitas.add(recDesp);
			}

			ps.close();
			rs.close();
		
			return listaReceitas;
		
		}finally{
			conexao.close();
		}
	}
	
	public List<ReceitaDespesa> getDespesas(BalanceteMensal bal) throws SQLException {
		
		String sql = "SELECT DRC_CODIGO, DRC_DESCRICAO, DER_CODIGO,   "
			+ "DER_VALOR, DER_TIPO, DER_DATA "
			+ "FROM ADMCON_DESPESA_RECEITA JOIN ADMCON_DESPESA_RECEITA_DOMINIO"
			+ "WHERE ADMCON_DESPESA_RECEITA.DRC_CODIGO = ADMCON_DESPESA_RECEITA_DOMINIO.DER_DRD_CODIGO"
			+ " AND MONTH(DER_DATA) = ? AND YEAR(DER_DATA)= ? AND DER_TIPO = \"D\""
			+ "ORDER BY DER_DATA";

		try{
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, bal.getMes());
			ps.setString(2, bal.getAno());
			
			ResultSet rs = ps.executeQuery();

			List<ReceitaDespesa> listaDespesas = new ArrayList<ReceitaDespesa>();

			while (rs.next()) {
			
				ReceitaDespesaTipos rcdt = new ReceitaDespesaTipos();
				ReceitaDespesa recDesp = new ReceitaDespesa();
								
				rcdt.setCodigo(rs.getInt(1));
				rcdt.setDescricao(rs.getString(2));
				
				recDesp.setCodigo(rs.getInt(3));
				recDesp.setValor(rs.getDouble(4));
				recDesp.setTipoRD(rcdt);
				recDesp.setData(rs.getString(5));
				recDesp.setCondominio(null);	//nao é relevante saber o responsavel no balancete			
				
				listaDespesas.add(recDesp);
			}

			ps.close();
			rs.close();
		
			return listaDespesas;
		
		}finally{
			conexao.close();
		}
	}
	
	//metodos que retornarao o total mensal em valores, das despesas e receitas do condominio
	public double getTotalReceitas(BalanceteMensal bal) throws SQLException {
				
		String sql = "SELECT SUM(DER_VALOR) "
				+ "FROM ADMCON_DESPESA_RECEITA "
				+ "WHERE MONTH(DER_DATA) = ? AND YEAR(DER_DATA)= ?AND DER_TIPO = \"R\"";

		try{
					
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, bal.getMes());
			ps.setString(2, bal.getAno());								
			ResultSet rs = ps.executeQuery();

			double resultado = rs.getDouble(1);

			ps.close();
			rs.close();
				
			return resultado;
				
		}finally{
				conexao.close();
		}

	}
	
	public double getTotalDespesas(BalanceteMensal bal) throws SQLException {
		
		String sql = "SELECT SUM(DER_VALOR) "
				+ "FROM ADMCON_DESPESA_RECEITA "
				+ "WHERE MONTH(DER_DATA) = ? AND YEAR(DER_DATA)= ?AND DER_TIPO = \"D\"";

		try{
					
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, bal.getMes());
			ps.setString(2, bal.getAno());								
			ResultSet rs = ps.executeQuery();
			
			double resultado = rs.getDouble(1);

			ps.close();
			rs.close();
				
			return resultado;
				
		}finally{
				conexao.close();
		}

	}

}
