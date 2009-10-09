package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.businesslogic.ImovelCaracteristica;
import br.com.siaic.businesslogic.Perfil;
import br.com.siaic.dao.ImovelCaracteristicaDAO;

public class ConsultaPerfilBean {
		
		private Perfil perfil;
		private Cliente cliente;
		
		public ConsultaPerfilBean(){
			this.cliente = new Cliente();
			this.perfil = new Perfil();
		}
		
		public Perfil getPerfil() {
			return perfil;
		}
		public void setPerfil(Perfil perfil) {
			this.perfil = perfil;
		}
		public Cliente getCliente() {
			return cliente;
		}
		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}
		
		public List<Perfil> getAllPerfil() throws SQLException {
			
			return PerfilDAO.getInstance().getAllPerfil(this.cliente.getCodigoPessoa()());
		}
		
		public String ConsultarImovel(){
			return "ConsultarImovelCaracteristica";
		}
		
		public String SelecionarCaracteristica() throws SQLException{
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
			Integer idIC = new Integer(req.getParameter("codigo")).intValue();
			//String tipoExibicao = new String(req.getParameter("tipoExibicao").toString());		
			
			setCaracteristica(ImovelCaracteristicaDAO.getInstance().getImovelCaracteristica(idIC));
			return "SelecionarCaracteristica";
		}

}
