package br.com.cond.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import br.com.cond.businesslogic.AgendaDependencia;
import br.com.cond.businesslogic.AgendaFinalidade;
import br.com.cond.businesslogic.Condomino;
import br.com.cond.businesslogic.Dependencia;
import br.com.cond.businesslogic.Reuniao;
import br.com.siaic.businesslogic.ImovelCaracteristica;
import br.com.siaic.dao.DB;
import br.com.siaic.dao.ImovelCaracteristicaDAO;

/**
 * @author Yasmim
 */

public class ReuniaoDAO {

		

		// get da tabela Reuniao
		public Reuniao getReuniao(int codigo)
				throws SQLException {
			String query = new String(
					"select * from ADMCON_REUNIAO where REU_CODIGO = ?");
			PreparedStatement ps;
			ps = DB.getConn().prepareStatement(query);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();

			Reuniao r = null;

			if (rs.first()) {
				r = new Reuniao();
				r.setCodigo(rs.getInt("REU_CODIGO"));
				r.setDescrição(rs.getString("REU_DESCRIÇÃO"));
				r.setAgd_codigo(rs.getInt("REU_AGD_CODIGO"));
			}
			ps.close();
			rs.close();

			return r;
		}
		// Insert da tabela reuniao
		public boolean addReuniao(Reuniao r)
				throws SQLException {
			String query = new String(
					"insert into ADMCON_REUNIAO (REU_DESCRICAO, REU_AGD_CODIGO) ")
					+ "values (?, ?)";
			PreparedStatement ps;
			ps = DB.getConn().prepareStatement(query);

			ps.setString(2, r.getDescrição());
			ps.setInt(3, r.getAgd_codigo());

			boolean result = ps.executeUpdate() > 0;
			ps.close();

			return result;
		}
		// Update da tabela Reuniao
		public boolean altReuniao(Reuniao rAtual,
				Reuniao rNovo) throws SQLException {
			String query = new String(
					"update ADMCON_REUNIAO "
							+ "REU_DESCRICAO = ?, REU_AGD_CODIGO = ? "
							+ "where REU_CODIGO = ?");
			PreparedStatement ps;
			ps = DB.getConn().prepareStatement(query);

			ps.setString(1, rNovo.getDescrição());
			ps.setInt(2, rNovo.getAgd_codigo());
			ps.setInt(3, rAtual.getCodigo());

			boolean result = ps.executeUpdate() > 0;
			ps.close();
			return result;
		}

		// Delete da tabela reuniao
		public boolean delReuniao(Reuniao r)
				throws SQLException {
			String query = new String(
					"delete from ADMCON_REUNIAO where REU_CODIGO = ? ");
			PreparedStatement ps;
			ps = DB.getConn().prepareStatement(query);
			ps.setInt(1, r.getCodigo());

			boolean result = ps.executeUpdate() > 0;
			ps.close();

			return result;
		}
		// Listagem da tabela Reuniao
		public List<Reuniao> getReuniaoList()
				throws SQLException {
			String query = new String("select * from ADMCON_REUNIAO");
			PreparedStatement ps;
			ps = DB.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			List<Reuniao> l = new ArrayList<Reuniao>();
			Reuniao r = null;

			while (rs.next()) {
				r = new Reuniao();
				r.setCodigo(rs.getInt("REU_CODIGO"));
				r.setDescrição(rs.getString("REU_DESCRICAO"));
				r.setAgd_codigo(rs.getInt("REU_AGD_CODIGO"));

				l.add(r);
			}
			rs.close();
			ps.close();
			return l;
		}
		
		//Pega a dependencia para uma reuniao em aberto
		public List<AgendaDependencia> getDependencia() throws SQLException{
			String query = new String("select * from ADMCON_AGENDA_DEPENDENCIA "
									+ "where AGD_DATA < getdate()");
			PreparedStatement ps;
			ps = DB.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			List<AgendaDependencia> l = new ArrayList<AgendaDependencia>();
			AgendaDependencia d = null;
			
			

			while (rs.next()) {
				d = new AgendaDependencia();
				d.setCodigo(rs.getInt("AGD_CODIGO"));
				d.setData(rs.getDate("AGD_DATA"));
				d.setHoraInicio(rs.getTime("AGD_HORA_INICIO"));
				d.setHoraFinal(rs.getTime("AGD_HORA_FINAL"));	
				d.setCondomino(new Condomino());
				d.setDependencia(new Dependencia());
				d.setFinalidade(new AgendaFinalidade());
				d.setComparecimento(rs.getString("AGD_COMPARECIMENTO").charAt(0));
				l.add(d);
			}

			ps.close();
			rs.close();
			
			return l;
		}
		
}


