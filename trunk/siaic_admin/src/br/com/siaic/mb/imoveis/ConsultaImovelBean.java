package br.com.siaic.mb.imoveis;

import java.util.List;

import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.dao.ImovelDAO;

public class ConsultaImovelBean {
	
	public List<Imovel> getTodosImoveis() {
		return new ImovelDAO().getImoveis();
	}
	
}
