package br.com.cond.msg;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensagensUtil {

	public static void empiplharMensagem(Exception e) {
		FacesContext.getCurrentInstance().addMessage("erro",
				new FacesMessage(e.getMessage()));

	}
	
	
	public static void empiplharMensagem(String e) {
		FacesContext.getCurrentInstance().addMessage("alerta", new FacesMessage(e));

	}
}
