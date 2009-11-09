package br.com.siaic.mb.imoveis;

import java.sql.SQLException;

import javax.el.ELResolver;
import javax.faces.context.FacesContext;

import org.richfaces.demo.fileupload.FileUploadBean;

import br.com.siaic.businesslogic.Foto;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.FotoDAO;
import br.com.siaic.dao.ImovelFinalidadeDAO;

public class CadastraImovelBean extends ImovelBaseBean {
	
	public String salvaImovel() {
		ImovelFinalidadeDAO finDao = new ImovelFinalidadeDAO();
		finDao.salvar(this.getImovelFinalidade());
		this.getImovel().setFinalidade(this.getImovelFinalidade().getCodigo());

		EnderecoDAO end = new EnderecoDAO();
		try {
			end.adicionarEndereco(this.getImovelEndereco());
			this.getImovel().setEndereco(this.getImovelEndereco().getEnderecoCodigo());
			this.getImovel().salvar();
			this.salvarFotos();
		} catch (SQLException e) {
			e.printStackTrace();
			return "erro";
		}
		return "sucesso";
	}
	
	public void salvarFotos() throws SQLException {
	    FacesContext context = FacesContext.getCurrentInstance();  
	    ELResolver resolver = context.getApplication().getELResolver();  
	    FileUploadBean fileUploadBean = (FileUploadBean) resolver.getValue(context.getELContext(), null, "fileUploadBean");
	    for (Foto fh : fileUploadBean.getFiles()) {
	    	new FotoDAO().salvaFoto(fh, this.getImovel().getCodigo());
	    }
	    fileUploadBean.clearUploadData();
	}
	
}
