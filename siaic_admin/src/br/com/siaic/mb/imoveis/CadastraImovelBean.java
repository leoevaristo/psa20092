package br.com.siaic.mb.imoveis;

import java.sql.SQLException;

import javax.faces.context.FacesContext;

import br.com.siaic.dao.EnderecoDAO;
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
		} catch (SQLException e) {
			e.printStackTrace();
			return "erro";
		}
		return "sucesso";
	}
	
}
