package br.com.siaic.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.siaic.businesslogic.Imovel;
import br.com.siaic.businesslogic.endereco.Endereco;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.ImovelDAO;

public class SelectImoveis {
	
	private static List<SelectItem> listaImoveis = new ArrayList<SelectItem>();
	
	private List<Integer> imoveisSelecionados;
	
	public SelectImoveis(){
		if(listaImoveis.isEmpty())
			try {
				setListaImoveis();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public List<SelectItem> getListaImoveis() {
		return listaImoveis;
	}

	public void setListaImoveis() throws SQLException {
		
		ImovelDAO daoImovel = new ImovelDAO();
		EnderecoDAO daoEndereco = new EnderecoDAO();		
		
		
		List<Imovel> imoveis = daoImovel.getImoveis();
		
		
		for(Imovel imo : imoveis){
			
			Endereco end = daoEndereco.getEnderecoPorCodigo(imo.getEndereco());
			String nomeEnd = end.getEnderecoNome();
			String logEnd = end.getEnderecoLogradouro();
			String cepEnd = end.getEnderecoCep();
			String bairroEnd = end.getEnderecoBairro().getBairroNome();
			
			listaImoveis.add((new SelectItem(imo.getCodigo(),logEnd + " " + nomeEnd + " " + bairroEnd + " " + cepEnd)));
		}
		
	}

	public void setImoveisSelecionados(List<Integer> imoveisSelecionados) {
		this.imoveisSelecionados = imoveisSelecionados;
	}

	public List<Integer> getImoveisSelecionados() {
		
	
		return imoveisSelecionados;
	}

}
