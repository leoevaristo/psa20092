package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.ImovelCaracteristica;
import br.com.siaic.businesslogic.Perfil;
import br.com.siaic.businesslogic.Usuario;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.ImovelCaracteristicaDAO;
import br.com.siaic.dao.PerfilDAO;
import br.com.siaic.dao.UsuarioDAO;

/**
 * 
 * @author Yasmim Tamie Hiramoto Pereira
 * @version 1.0
 *  
 */

public class PerfilBean {
	private Perfil perfil;
	
	private static List<SelectItem> corretores = new ArrayList<SelectItem>();
	
	private static List<SelectItem> listaClientes = new ArrayList<SelectItem>();
	
	private static List<SelectItem> listaCaracteristicas = new ArrayList<SelectItem>();
	
	public PerfilBean() {
		perfil = new Perfil();

		
			try {
				setListaCaracteristicas();
				setCorretores();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	public List<SelectItem> getCaracteristicas(){
		return listaCaracteristicas;
	}
	
	public void setListaCaracteristicas() throws SQLException {
		List<ImovelCaracteristica> imoc = ImovelCaracteristicaDAO.getInstance().getImovelCaracteristicaList();
		for (ImovelCaracteristica imoca : imoc) {
			listaCaracteristicas.add(new SelectItem(imoca.getCodigo(),imoca.toString()));
		}
	}
	
	public List<SelectItem> getCorretores() {
		return corretores;
	}
	
	public void setCorretores() throws SQLException {
		
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usu = dao.getTodosCorretores();
		
		for(Usuario usuario : usu)			
			corretores.add(new SelectItem(usuario.getCodigoPessoa(),usuario.getNome()));
					
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public void listarClientes(ValueChangeEvent evento) throws SQLException{
		if(evento.getNewValue() != evento.getOldValue()){
			ClienteDAO dao = new ClienteDAO();
			setListaClientes(dao.getClientesPeloNome(evento.getNewValue().toString()));
		}
	}
	
	public void setListaClientes(List<Cliente> listagemClientes) {
		if(!listaClientes.isEmpty()){			
			listaClientes.clear();
		}
				
		for(Cliente cliente : listagemClientes){
			
			listaClientes.add(new SelectItem(cliente.getCodigoPessoa(), cliente.getNome() + " , " + cliente.getEmail()));
			
		}
	}
	
	public List<SelectItem> getListaClientesSelect() {
		return listaClientes;
	}
	
	
	
	public String addPerfil() throws SQLException{
		String s = "";
		perfil.setCliente(new ClienteDAO().getClientePorId(perfil.getCliente().getCodigoPessoa()));
		perfil.setUsuario(new UsuarioDAO().getUsuarioId(perfil.getUsuario().getCodigoPessoa()));
		perfil.setImovelCaracteristica(ImovelCaracteristicaDAO.getInstance().getImovelCaracteristica(perfil.getImovelCaracteristica().getCodigo()));
		
		s = PerfilDAO.getInstance().addPerfil(perfil) ? "Sucesso" : "Falha";
		
		return s;
	}
	
}
