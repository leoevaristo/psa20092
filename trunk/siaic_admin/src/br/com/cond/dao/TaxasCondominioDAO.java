package br.com.cond.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ajax4jsf.resource.Java2Dresource;
import org.apache.naming.java.javaURLContextFactory;

import br.com.cond.businesslogic.TaxasCondominio;
import br.com.siaic.dao.FabricaConexao;

public class TaxasCondominioDAO {

	private Connection conexao = null;

	public TaxasCondominioDAO() {
		FabricaConexao.getInstancia();
	}

	public boolean addTaxaCondominio(TaxasCondominio taxas) {
		try {
			this.conexao = FabricaConexao.conectar();
		} catch (SQLException e1) {
			e1.getLocalizedMessage();
		}

		if (!checarExistenciaTaxa(taxas.getMes(), taxas.getAno(), taxas.getApartamento().getCodigoApartamento())) {
			return false;
		} else {

			String sql = "INSERT INTO ADMCON_TAXA_CONDOMINIO_FATOS "
					+ "(COF_APA_CODIGO, COF_MES, COF_ANO, COF_VALOR,"
					+ " COF_DATA_VENCIMENTO) VALUES(?, ?, ?, ?, ?);";

			try {
				PreparedStatement ps = this.conexao.prepareStatement(sql);
				ps.setInt(1, taxas.getApartamento().getCodigoApartamento());
				ps.setString(2, taxas.getMes());
				ps.setString(3, taxas.getAno());
				ps.setDouble(4, taxas.getValor());
				ps.setTimestamp(5, new java.sql.Timestamp(taxas
						.getDataVencimento().getTime()));
				ps.execute();
				ps.close();

			} catch (SQLException e) {
				e.getLocalizedMessage();
			} finally {
				try {
					this.conexao.close();
				} catch (SQLException e) {
					e.getLocalizedMessage();
				}
			}
		}
		return true;
	}

	public void setarPagamentoTaxa(int codigoApartamento) {

	}

	public boolean checarExistenciaTaxa(String mes, String ano, int codigoApartamento) {
		String sql = "SELECT DISTINCT COF_MES, COF_ANO, COF_APA_CODIGO FROM ADMCON_TAXA_CONDOMINIO_FATOS"
				+ " WHERE COF_MES = ? AND COF_ANO = ? AND COF_APA_CODIGO = ?  ;";

		try {
			PreparedStatement ps = this.conexao.prepareStatement(sql);
			ps.setString(1, mes);
			ps.setString(2, ano);
			ps.setInt(3, codigoApartamento);

			ResultSet rs = ps.executeQuery();
			if(rs.first()){
				if (rs.getString("COF_MES").equals(mes) && rs.getString("COF_ANO").equals(ano) && rs.getInt("COF_APA_CODIGO") ==  codigoApartamento) {
					return false;
				} 
			}else{
				return true;
				}			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;		
	}
}
