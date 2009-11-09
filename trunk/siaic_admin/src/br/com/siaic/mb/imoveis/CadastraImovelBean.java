package br.com.siaic.mb.imoveis;

import java.io.IOException;
import java.sql.SQLException;

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
	    FileUploadBean fileUploadBean = FileUploadBean.getCurrentSession();
	    for (Foto fh : fileUploadBean.getFiles()) {
	    	fh.setImovel(this.getImovel().getCodigo());
	    	
	    	try {
				new FotoDAO().salvaFoto(fh);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	    }
	    fileUploadBean.clearUploadData();
	}
	
}
