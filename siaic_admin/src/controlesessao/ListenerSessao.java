package controlesessao;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;
 
public class ListenerSessao implements PhaseListener {
 
	private static final long serialVersionUID = 2L;

	public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
 
    public void beforePhase(PhaseEvent event) {
    }
 
    public void afterPhase(PhaseEvent event) {
        FacesContext fc = event.getFacesContext();
 
        // Check to see if they are on the login page.
        boolean loginPage =
          fc.getViewRoot().getViewId().lastIndexOf("login") > -1 ? true : false;
          
        if (!loginPage && !loggedIn(fc)) {
        	
        	MensagemUtil.montarMensagemErro(
					new Exception("Execute o login."),fc);
        	
            NavigationHandler nh = fc.getApplication().getNavigationHandler();
            nh.handleNavigation(fc, null, "logout");
        }
    }
 
    private boolean loggedIn(FacesContext fc) {
		HttpSession sessao = (HttpSession) fc.getExternalContext().getSession(true);
		return sessao.getAttribute("usuario") != null;
	}

}
