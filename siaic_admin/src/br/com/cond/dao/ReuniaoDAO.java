package br.com.cond.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cond.businesslogic.AgendaDependencia;
import br.com.cond.businesslogic.AgendaFinalidade;
import br.com.cond.businesslogic.Condomino;
import br.com.cond.businesslogic.Dependencia;
import br.com.cond.businesslogic.Reuniao;
import br.com.siaic.dao.DB;

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
				r.setDescricao(rs.getString("REU_DESCRICAO"));
				r.setDependencia(new AgendaDependenciaDAO().getAgendaDependencia(rs.getInt("REU_AGD_CODIGO")));
			}
			ps.close();
			rs.close();

			return r;
		}
		// Insert da tabela reuniao
		public boolean addReuniao(Reuniao r)
				throws SQLException {
			String query = new String(
					"insert into ADMCON_REUNIAO (REU_DESCRICAO, REU_AGD_CODIGO) "
					+ "values (?, ?)");
			PreparedStatement ps;
			ps = DB.getConn().prepareStatement(query);

			ps.setString(1, r.getDescricao());
			ps.setInt(2, r.getDependencia().getCodigo());

			boolean result = ps.executeUpdate() > 0;
			ps.close();

			return result;
		}
		// Update da tabela Reuniao
		public boolean altReuniao(Reuniao rAtual,
				Reuniao rNovo) throws SQLException {
			String query = new String(
					"update ADMCON_REUNIAO "
							+ "set REU_DESCRICAO = ?, REU_AGD_CODIGO = ? "
							+ "where REU_CODIGO = ?");
			PreparedStatement ps;
			ps = DB.getConn().prepareStatement(query);

			ps.setString(1, rNovo.getDescricao());
			ps.setInt(2, rNovo.getDependencia().getCodigo());
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
				r.setDescricao(rs.getString("REU_DESCRICAO"));
				r.setDependencia(new AgendaDependenciaDAO().getAgendaDependencia(rs.getInt("REU_AGD_CODIGO")));

				l.add(r);
			}
			rs.close();
			ps.close();
			return l;
		}
		
		//Pega a dependencia para uma reuniao em aberto
		public List<AgendaDependencia> getDependencia() throws SQLException{
		String query = new String("select AGD_CODIGO, AGD_DATA, AGD_HORA_INICIO, AGD_HORA_FINAL, AGD_CON_CODIGO, "
						+ "AGD_DEP_CODIGO, AGD_AGF_CODIGO, AGD_COMPARECIMENTO from admcon_agenda_dependencia "
						+ "where (AGD_DATA > now()) "
						+ "and (AGD_AGF_CODIGO in (select AGF_CODIGO from admcon_agenda_finalidade where AGF_DESCRICAO like '%reuni%')) "
						+ "and (AGD_CODIGO not in (select REU_AGD_CODIGO from admcon_reuniao))");
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
				d.setCondomino(new CondominoDAO().getCondominio(rs.getInt("AGD_CON_CODIGO")));
				d.setDependencia(new DependenciaDAO().buscaDependencia(rs.getInt("AGD_DEP_CODIGO")));
				d.setFinalidade(new AgendaFinalidadeDAO().buscarAgendaFinalidade(rs.getInt("AGD_AGF_CODIGO")));
				char c = (rs.getString("AGD_COMPARECIMENTO") == null || 
						  rs.getString("AGD_COMPARECIMENTO").isEmpty()) ?
						  '\0' : rs.getString("AGD_COMPARECIMENTO").charAt(0);
				d.setComparecimento(c);
				l.add(d);
			}

			ps.close();
			rs.close();
			
			return l;
		}
		// Listagem da tabela Reuniao e Dependencia pela ordem de data
		public List<Reuniao> getReuniaoListByDate()
				throws SQLException {
			String query = new String("select * from admcon_reuniao, admcon_agenda_dependencia "
									+"where REU_AGD_CODIGO = AGD_CODIGO order by AGD_DATA desc");
			PreparedStatement ps;
			ps = DB.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			List<Reuniao> l = new ArrayList<Reuniao>();
			Reuniao r = null;

			while (rs.next()) {
				r = new Reuniao();
				r.setCodigo(rs.getInt("REU_CODIGO"));
				r.setDescricao(rs.getString("REU_DESCRICAO"));
				r.setDependencia(new AgendaDependenciaDAO().getAgendaDependencia(rs.getInt("REU_AGD_CODIGO")));
				l.add(r);
			}
			rs.close();
			ps.close();
			return l;
		}
		
}


