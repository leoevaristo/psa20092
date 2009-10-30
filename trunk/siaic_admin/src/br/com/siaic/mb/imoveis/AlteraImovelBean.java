package br.com.siaic.mb.imoveis;

import java.sql.SQLException;

import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.ImovelFinalidadeDAO;

public class AlteraImovelBean extends ImovelBaseBean {
	
	public String atualizaImovel() {
		try {
			ImovelFinalidadeDAO finDao = new ImovelFinalidadeDAO();
			finDao.atualizar(this.getImovelFinalidade());
			this.getImovel().setFinalidade(this.getImovelFinalidade().getCodigo());

			this.getImovel().setProprietario(this.getImovelProprietario().getCodigoPessoa());
			this.getImovel().setCaracteristica(this.getImovelCaracteristica().getCodigo());
			
			EnderecoDAO daoEndereco = new EnderecoDAO();
			daoEndereco.alterarEndereco(this.getImovelEndereco());
			
			this.getImovel().setCaracteristica(this.getImovel().getCaracteristica());
			this.getImovel().atualizar();
			
			return "altera";
		} catch (SQLException e) {
			e.printStackTrace();
			return "erro";
		}
	}
	
}
