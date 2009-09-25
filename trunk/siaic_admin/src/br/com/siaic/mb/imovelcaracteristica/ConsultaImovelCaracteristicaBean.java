package br.com.siaic.mb.imovelcaracteristica;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.businesslogic.ImovelCaracteristica;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.ImovelCaracteristicaDAO;

public class ConsultaImovelCaracteristicaBean {
	
	private ImovelCaracteristica caracteristica;
	private Imovel imovel;
	private Cliente cliente;
	
	public ConsultaImovelCaracteristicaBean(){
		this.imovel = new Imovel();
		this.cliente = new Cliente();
		this.caracteristica = new ImovelCaracteristica();
	}
	
	public ImovelCaracteristica getCaracteristica() {
		return caracteristica;
	}
	public void setCaracteristica(ImovelCaracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<ImovelCaracteristica> getAllCaracteristica() throws SQLException {
		
		return ImovelCaracteristicaDAO.getInstance().getAllCaracteristica(this.imovel.getCodigo());
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
