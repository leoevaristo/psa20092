package controlesessao;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class LogoutMB {

	public String executarLogout() {

		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		sessao.removeAttribute("usuario");
		return "logout";
	}
}
