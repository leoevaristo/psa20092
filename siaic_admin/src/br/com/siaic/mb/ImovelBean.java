package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.businesslogic.ImovelCaracteristica;
import br.com.siaic.businesslogic.ImovelFinalidade;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.ImovelCaracteristicaDAO;
import br.com.siaic.dao.ImovelDAO;
import br.com.siaic.dao.ImovelFinalidadeDAO;

public class ImovelBean {
    
	private Imovel imovel;
	private Endereco endereco;
	private ImovelCaracteristica imoCar;
	private ImovelFinalidade imoFin;
	private Cliente prop;
	
	private int codigoImovel;
    
    public ImovelBean() {
    	this.imovel = new Imovel();
	    this.endereco = new Endereco();
	    this.imoCar = new ImovelCaracteristica();
	    this.imoFin = new ImovelFinalidade();
	    this.prop = new Cliente();
    }
    
    public ImovelFinalidade getImoFin() {
		return imoFin;
	}

	public Imovel getImovel() {
    	return this.imovel;
    }
    
    public Endereco getEndereco() {
    	return this.endereco;
    }
    
    public int getCodigoImovel() {
    	return this.codigoImovel;
    }
    
    public void setCodigoImovel(int codigoImovel) {
    	this.codigoImovel = codigoImovel;
    }
    
    public String consultaImovel() {
    	this.imovel = Imovel.getImovel(this.getParamCodigoImovel());
    	try {
    		this.imoCar = new ImovelCaracteristicaDAO().getImovelCaracteristica(this.imovel.getCaracteristica());
    		this.prop = new ClienteDAO().getClientePorId(this.imovel.getProprietario());
    		this.imoFin = new ImovelFinalidadeDAO().getImovelFinalidade(this.imovel.getFinalidade());
			this.endereco = new EnderecoDAO().getEnderecoPorCodigo(this.imovel.getEndereco());
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return "altera";
    }
    
    private int getParamCodigoImovel() {
    	FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer codImovel = new Integer(req.getParameter("codigoImovel")).intValue();
		return codImovel;
    }
    
    public String consultaCaracteristica() {
    	return "consultacaracteristica";
    }
    
    public String excluiImovel() {
    	new ImovelDAO().getImovel(this.getParamCodigoImovel()).excluir();
    	return "";
    }
    
    public List<Imovel> getTodosImoveis() {
    	return new ImovelDAO().getImoveis();
    }
    
    public String atualizaImovel() {
    	this.imovel.salvar();
    	return "";
    }

	public ImovelCaracteristica getImoCar() {
		return imoCar;
	}

	public Cliente getProp() {
		return prop;
	}
}
