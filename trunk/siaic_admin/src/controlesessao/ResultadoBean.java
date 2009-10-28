package controlesessao;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class ResultadoBean {

	private String login;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String deslogar() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		sessao.removeAttribute("login");

		return "voltar";
	}

}
