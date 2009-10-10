package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.ImovelCaracteristica;
import br.com.siaic.businesslogic.Perfil;
import br.com.siaic.dao.AgendaDAO;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.PerfilDAO;

public class ConsultaPerfilBean {
		
		private Perfil perfil;
		private Cliente cliente;
		
		private static List<SelectItem> listaClientes = new ArrayList<SelectItem>();
		
		private static List<Perfil> listaPerfis = new ArrayList<Perfil>();
		
		public void listarClientes(ValueChangeEvent evento) throws SQLException{
			if(evento.getNewValue() != evento.getOldValue()){
				ClienteDAO dao = new ClienteDAO();
				setListaClientes(dao.getClientesPeloNome(evento.getNewValue().toString()));
			}
		}
		
		public void setListaClientes(List<Cliente> listagemClientes) {
			
			listaClientes.clear();
					
			for(Cliente cliente : listagemClientes){
				
				listaClientes.add(new SelectItem(cliente.getCodigoPessoa(), cliente.getNome() + " , " + cliente.getEmail()));
				
			}
		}
		
		public void Buscar() {
			try {
				listaPerfis = PerfilDAO.getInstance().getPerfil(new ClienteDAO().getClientePorId(perfil.getCliente().getCodigoPessoa()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		public List<Perfil> getListaPerfis() {
			
			return listaPerfis;
			
		}
		
		public void excluiEntrada() throws SQLException {

			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest req = (HttpServletRequest) context
					.getExternalContext().getRequest();

			Integer idEntradaAgenda = new Integer(req.getParameter("codigoEntrada"))
					.intValue();

			PerfilDAO.getInstance().delPerfil(idEntradaAgenda);
			
		}
		
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
		
		
}
