package controlesessao;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessaoMB {

	public String getLogin() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		
		return (String)sessao.getAttribute("login");
	}

}
