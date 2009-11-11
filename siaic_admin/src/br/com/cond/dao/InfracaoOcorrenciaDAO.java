package br.com.cond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import br.com.cond.businesslogic.InfracaoOcorrencia;
import br.com.siaic.dao.FabricaConexao;


/**
 * 
 * @author George Fernandes Maia
 *
 */


public class InfracaoOcorrenciaDAO {
	private Connection conexao = null;

	public InfracaoOcorrenciaDAO() {}
	
	public boolean adicionarInfracaoOcorrencia(InfracaoOcorrencia infracaoOcorrencia) throws SQLException{
		boolean retorno = false;
		PreparedStatement ps = null;
		String sql = "INSERT INTO admcon_apartamento_infracao( API_CODIGO, API_DATA_HORA, " +
				"API_OBSERVACAO, API_INF_CODIGO, API_APA_CODIGO) VALUES (null, ?,?,?,?)";
		
		try {
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			ps = conexao.prepareStatement(sql);
			ps.setTimestamp(1, infracaoOcorrencia.getDataHoraInfracaoOcorrencia() );
			ps.setString(2, infracaoOcorrencia.getObservacaoInfracaoOcorrencia() );
			ps.setInt(3, infracaoOcorrencia.getInfracao().getCodigoInfracao());
			ps.setInt(4, infracaoOcorrencia.getApartamentoInfracaoOcorrencia().getCodigoApartamento() );

			ps.execute();
			retorno = true;
		}//try
		catch (Exception e) {
			throw new SQLException("Erro ao inserir dados no banco. " + e);
		}//catch()
		finally{
			ps.close();
			conexao.close();
		}//finally

		return retorno;
	}

	
	
	public InfracaoOcorrencia getInfracaoOcorrenciaPorCodigo(int codigoInfracaoOcorrenciaParametro) throws SQLException
	{
		String sql = 		"SELECT * " + 
							"FROM " + 
								"admcon_apartamento_infracao " +
							"WHERE " +
								"API_CODIGO = ?";
		
		FabricaConexao.getInstancia();
		conexao = FabricaConexao.conectar();
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, codigoInfracaoOcorrenciaParametro);
		
		ResultSet rs = ps.executeQuery();
		
		InfracaoOcorrencia infracaoOcorrencia = new InfracaoOcorrencia();
		
		rs.next();
		infracaoOcorrencia.setCodigoInfracaoOcorrencia(rs.getInt(1));
		infracaoOcorrencia.setDataHoraInfracaoOcorrencia(rs.getTimestamp(2));
		infracaoOcorrencia.setObservacaoInfracaoOcorrencia(rs.getString(3));
		infracaoOcorrencia.setInfracao(
				new InfracaoDAO().getInfracaoId( rs.getInt(4) ) );
		infracaoOcorrencia.setApartamentoInfracaoOcorrencia( 
				new ApartamentoDAO().getApartamentoId( rs.getInt(5)));
		
		
		ps.close();
		rs.close();
		conexao.close();
		
		return infracaoOcorrencia;
	}	
	
	
	
	public ArrayList<InfracaoOcorrencia> getInfracaoOcorrenciaPorApartamento(int apartamentoCodigoParametro) 
	throws SQLException
	{
		String sql = "select * from admcon_apartamento_infracao where api_apa_codigo = ?";
		
		FabricaConexao.getInstancia();
		conexao = FabricaConexao.conectar();
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, apartamentoCodigoParametro);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<InfracaoOcorrencia> infracaoOcorrenciaLista = new ArrayList<InfracaoOcorrencia>();
		
		rs.next();
		
		do
		{
			
			
			infracaoOcorrenciaLista.add(new InfracaoOcorrencia(rs.getInt(1), 
																rs.getTimestamp(2), 
																rs.getString(3), 
																new InfracaoDAO().getInfracaoId( rs.getInt(4)), 
																new ApartamentoDAO().getApartamentoId( rs.getInt(5) )));
			
		}while(rs.next());
		
		ps.close();
		rs.close();
		conexao.close();
		
		return infracaoOcorrenciaLista;
	}
	
	
	public boolean alterarInfracaoOcorrencia(InfracaoOcorrencia infracaoOcorrencia) throws SQLException {

		boolean retorno = false; 
		String sql =    "UPDATE " + 
							"admcon_apartamento_infracao " + 
						"SET " +
							"API_DATA_HORA = ?, " + 
							  "API_OBSERVACAO = ?, " +
							  "API_INF_CODIGO = ?, " +
							  "API_APA_CODIGO = ? " +
						"WHERE " +
							"API_CODIGO = ? ";
		try {
			
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setTimestamp(1, infracaoOcorrencia.getDataHoraInfracaoOcorrencia() );
			ps.setString(2, infracaoOcorrencia.getObservacaoInfracaoOcorrencia());
			ps.setInt(3, infracaoOcorrencia.getInfracao().getCodigoInfracao() );
			ps.setInt(4, infracaoOcorrencia.getApartamentoInfracaoOcorrencia().getCodigoApartamento() );
			ps.setInt(5, infracaoOcorrencia.getCodigoInfracaoOcorrencia() );
			ps.executeUpdate();
			ps.close();
			conexao.close();
			retorno = true;
			
		}

		catch (Exception e) {

			throw new SQLException("Nï¿½o foi possï¿½vel realizar a atualizaï¿½ï¿½o" + e);
		}
		return retorno;  
	}
	
	
	
	
	
	
	public boolean removerInfracaoOcorrencia(int infracaoOcorrenciaCodigoParametro) throws SQLException {

		boolean retorno = false; 
		String sql =  "DELETE FROM admcon_apartamento_infracao WHERE API_CODIGO = ? ";
		try {
			
			FabricaConexao.getInstancia();
			conexao = FabricaConexao.conectar();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, infracaoOcorrenciaCodigoParametro );
			ps.execute();
			ps.close();
			conexao.close();
			retorno = true;
			
		}

		catch (Exception e) {

			throw new SQLException("Nï¿½o foi possï¿½vel realizar a remoção" + e);
		}
		return retorno;  
	}
	
	
	
	
	
	
	
	public static void main(String[] args) throws SQLException, ParseException {
		
		System.out.println(new InfracaoOcorrenciaDAO().getInfracaoOcorrenciaPorApartamento(902).size() );
		
		
	}

}
