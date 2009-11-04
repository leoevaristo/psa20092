package br.com.siaic.mb.imoveis;

import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.ImovelCaracteristicaDAO;
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
	
	public void setImovelRequest(Imovel imovel) throws SQLException {
		this.setImovel(imovel);
		this.setImovelCaracteristica(ImovelCaracteristicaDAO.getInstance().getImovelCaracteristica(this.getImovel().getCaracteristica()));
		this.setImovelProprietario(new ClienteDAO().getClientePorId(this.getImovel().getProprietario()));
		this.setImovelFinalidade(new ImovelFinalidadeDAO().getImovelFinalidade(this.getImovel().getFinalidade()));
		EnderecoDAO edao = new EnderecoDAO();
		this.setImovelEndereco(edao.getEnderecoPorCodigo(this.getImovel().getEndereco()));
		this.setBairro(edao.getBairroPorCodigo(this.getImovelEndereco().getEnderecoBairro().getBairroCodigo()));
		this.setCidade(edao.getCidadePorCodigo(this.getBairro().getBairroCidade()));
		this.setEstado(edao.getEstadoPorSigla(this.getCidade().getCidadeEstado()));
	}
	
}
