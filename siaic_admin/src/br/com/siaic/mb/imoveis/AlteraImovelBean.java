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
	
//	public void consultaImovel2() {
//		int cod = consultaCodigoImovel;
//		if (cod != -1) {
//			this.imovel = Imovel.getImovel(cod);
//			try {
//				this.imoCar = ImovelCaracteristicaDAO.getInstance()
//						.getImovelCaracteristica(
//								this.imovel.getCaracteristica());
//				this.prop = new ClienteDAO().getClientePorId(this.imovel
//						.getProprietario());
//				this.imoFin = new ImovelFinalidadeDAO()
//						.getImovelFinalidade(this.imovel.getFinalidade());
//				EnderecoDAO edao = new EnderecoDAO();
//				this.endereco = edao.getEnderecoPorCodigo(this.imovel
//						.getEndereco());
//
//				this.bairro = edao.getBairroPorCodigo(this.endereco
//						.getEnderecoBairro().getBairroCodigo());
//				this.cidade = edao.getCidadePorCodigo(this.bairro
//						.getBairroCidade());
//				this.estado = edao.getEstadoPorSigla(this.cidade
//						.getCidadeEstado());
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	private int getCodigoImovelParametroGET() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String aff = req.getParameter("codigoImovel");
		if (aff != null)
			return new Integer(aff).intValue();
		else
			return -1;
	}
	
}
