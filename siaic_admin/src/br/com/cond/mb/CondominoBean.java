package br.com.cond.mb;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.Condomino;
import br.com.cond.dao.ApartamentoDAO;
import br.com.cond.dao.CondominoDAO;

/**
 * 
 * @author Robson
 *
 */

public class CondominoBean {
	private Condomino condomino;
	private Condomino atual;
	private List<Condomino> condominosList = null;
	
	private String filtro;
	private String valor;
	
	private String msg;
	private String acao;
	
	public CondominoBean() {
		condomino = new Condomino();
		condominosList = new ArrayList<Condomino>();
		acao = "Cadastrar";
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
	
	public String getAcao() {
		return acao;
	}
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
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
	
	public List<Condomino> getCondominosListConsulta() {
		return condominosList;
	}
	
	public void setCondominosList() throws SQLException {
		condominosList = new CondominoDAO().getTodasOsCondominos(filtro, valor);
	}
	
	//Acoes
	public void buscar() {
		try {
			setCondominosList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void limpar() {
		condomino = new Condomino();
		condominosList.clear();
		filtro = "";
		valor = "";
		msg = "";
	}
	
	public String cancelar() throws SQLException{
		String nav = "";
		if (acao.equals("Editar")) {
			acao = "Cadastrar";
			nav = "Editar";
			setCondominosList();
		} else {
			nav = "Cadastrar";
			limpar();
		}
		return nav;
	}
	
	public String cadastrar() throws SQLException, ParseException {
		String s = "";
		String nav = "";
		if (acao.equals("Editar")) {
			s = new CondominoDAO().update(atual, condomino) ? "Sucesso" : "Falha";
			acao = "Cadastrar";
			nav = "Editar";
			condominosList.clear();
			setCondominosList();
		} else {
			try {
				s = new CondominoDAO().inserir(condomino) ? "Sucesso" : "Falha";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (s.equals("Sucesso")) {
			msg = "Operação realizada com sucesso";
		} else {
			msg = "Operação não realizada";
			nav = "";
		}
		return nav;
	}
	
	public String editar(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();

		Integer cod = new Integer(req.getParameter("codigoEntrada")).intValue();
		try {
			condomino = new CondominoDAO().getCondominio(cod);
			atual = new CondominoDAO().getCondominio(condomino.getCodigo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		msg = "";
		acao = "Editar";
		return acao;
	}
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();

		Integer cod = new Integer(req.getParameter("codigoEntrada")).intValue();

		try {
			new CondominoDAO().deletar(new CondominoDAO().getCondominio(cod));
			condominosList.clear();
			setCondominosList();
		} catch (SQLException e) {
			msg = "Operação não realizada. Esse registro esta sendo usado.";
			e.printStackTrace();
		}	
	}

}