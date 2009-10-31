package controlesessao;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensagemUtil {
	
	static public void montarMensagemErro(Exception ex, FacesContext contexto) {

		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				ex.getMessage(), ex.getLocalizedMessage());
		contexto.addMessage("erros", mensagem);
		ex.printStackTrace();

	}

}
