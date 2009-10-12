package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import br.com.cond.businesslogic.AgendaDependencia;
import br.com.cond.businesslogic.Reuniao;
import br.com.cond.dao.ReuniaoDAO;

/**
 * @author Tamie
 */

public class ReuniaoBean {
	private Reuniao reuniao;
	private Reuniao atual;
	private List<SelectItem> reservaList = new ArrayList<SelectItem>();
	private String acao;
	private String msg;
	
	public ReuniaoBean(){
		reuniao = new Reuniao();
		setReservaList();
		acao = "Cadastrar";
	}
	
	public void setReservaList(){
		List<AgendaDependencia> l = null;
		try {
			l = new ReuniaoDAO().getDependencia();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (AgendaDependencia d : l){
			reservaList.add(new SelectItem(d.getCodigo(), d.toString()));
		}
	}

	public String addReuniao() throws SQLException{
		String s = "";
		String nav = "";
		if (acao.equals("Editar")) {
			s = new ReuniaoDAO().altReuniao(atual, reuniao) ? "Sucesso" : "Falha";
			acao = "Cadastrar";
			nav = "editar";
		} else {
			s = new ReuniaoDAO().addReuniao(reuniao) ? "Sucesso" : "Falha";
			nav = "cadastrar";
		}
		if (s.equals("Sucesso")) {
			msg = "Operação realizada com sucesso";
		} else {
			msg = "Operação não realizada";
			nav = "";
		}
		return nav;
	}
	
	public void Limpar() {
		msg = new String("");
		reuniao = new Reuniao();
	}
	public String getMsg(){
		return msg;
	}
	public String getAcao(){
		return acao;
	}
	
	public String Cancelar(){
		Limpar();
		return "Cancelar";
	}
	
	public List<SelectItem> getReservaList() {
		return reservaList;
	}
	
	public Reuniao getReuniao() {
		return reuniao;
	}

	public void setReuniao(Reuniao reuniao) {
		this.reuniao = reuniao;
	}
	
	public void excluiEntrada() throws SQLException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer cod = new Integer(req.getParameter("codigoEntrada"))
				.intValue();

		new ReuniaoDAO().delReuniao(new ReuniaoDAO().getReuniao(cod));
		
	}
	
	public List<Reuniao> getListaReuniao() {
		List<Reuniao> l = null;
		try {
			l = new ReuniaoDAO().getReuniaoListByDate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	public String editarEntrada(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer cod = new Integer(req.getParameter("codigoEntrada"))
				.intValue();
		try {
			reuniao = new ReuniaoDAO().getReuniao(cod);
			atual = new ReuniaoDAO().getReuniao(reuniao.getCodigo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		msg = "";
		acao = "Editar";
		return acao;
	}
	
}
