package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.Condomino;
import br.com.cond.dao.ApartamentoDAO;
import br.com.cond.dao.CondominoDAO;

/**
 * 
 * @author Robson
 *
 */

public class CadastrarCondominoBean {
	private Condomino condomino;
	private String msg;
	private String acao;
	
	public CadastrarCondominoBean() {
		condomino = new Condomino();
	}

	public Condomino getCondomino() {
		return condomino;
	}

	public void setCondomino(Condomino condomino) {
		this.condomino = condomino;
	}
	
	public String getMsg() {
		return msg;
	}
	
	//Complementares
	public List<SelectItem> getApartamentosList() throws SQLException {
		List<SelectItem> apartamentoLista = new ArrayList<SelectItem>();
		ApartamentoDAO dao = new ApartamentoDAO();
		List<Apartamento> l = dao.getTodosOsApartamentos();

		for (Apartamento a : l) {
			String s = Integer.toString(a.getAndar());
			if (!(a.getBloco() == null)) {
				if (!a.getBloco().equals("")) {
					s = s + ", " + a.getBloco();
				}
			}
			apartamentoLista.add(new SelectItem(a.getCodigoApartamento(), s));
		}
		return apartamentoLista;
	}
	
	public List<SelectItem> getCondominosList() throws SQLException {
		List<SelectItem> condominoLista = new ArrayList<SelectItem>();
		CondominoDAO dao = new CondominoDAO();
		List<Condomino> l = dao.getTodasOsCondominos();

		for (Condomino c : l) {
			condominoLista.add(new SelectItem(c.getCodigo(), c.getNome()));
		}
		return condominoLista;
	}
	
	//Acoes
	public void limpar() {
		condomino = new Condomino();
		msg = "";
	}
	public String cadastrar() {
		String s = "";
		String nav = "";
//		if (acao.equals("Editar")) {
//			s = new ReuniaoDAO().altReuniao(atual, reuniao) ? "Sucesso" : "Falha";
//			acao = "Cadastrar";
//			nav = "Editar";
//		} else {
			try {
				s = new CondominoDAO().inserir(condomino) ? "Sucesso" : "Falha";
			} catch (Exception e) {
				e.printStackTrace();
			}
//		}
		if (s.equals("Sucesso")) {
			msg = "Operação realizada com sucesso";
		} else {
			msg = "Operação não realizada";
			nav = "";
		}
		return nav;
	}
	
}

	