package br.com.siaic.businesslogic;

import java.sql.SQLException;

import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.ImovelCaracteristicaDAO;
import br.com.siaic.dao.UsuarioDAO;

/**
 * 
 * @author Yasmim Tamie Hiramoto Pereira
 * @version 1.0
 *  
 */

public class Perfil {
	private int codigo;
	private Cliente cliente;
	private ImovelCaracteristica imovelCaracteristica;
	private Usuario usuario;
	
	public Perfil(){
		
	}
	public Perfil(Cliente c, ImovelCaracteristica ic, Usuario u){
		this.cliente = c;
		this.imovelCaracteristica = ic;
		this.usuario = u;
	}
	public Perfil(int codcli, int codic, int codusua) throws SQLException{
		this.cliente = new ClienteDAO().getClientePorId(codcli);
		this.imovelCaracteristica = ImovelCaracteristicaDAO.getInstance().getImovelCaracteristica(codic);
		this.usuario = UsuarioDAO.getInstancia().getUsuarioId(codusua);
	}
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ImovelCaracteristica getImovelCaracteristica() {
		return imovelCaracteristica;
	}
	public void setImovelCaracteristica(ImovelCaracteristica imovelCaracteristica) {
		this.imovelCaracteristica = imovelCaracteristica;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}