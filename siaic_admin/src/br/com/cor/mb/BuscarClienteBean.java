package br.com.cor.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.businesslogic.endereco.Estado;
import br.com.siaic.dao.EnderecoDAO;

public class BuscarClienteBean {

	private Estado estado;
	private Cidade cidade;
	private Bairro bairro;

	private static List<SelectItem> cidades = new ArrayList<SelectItem>();
	private static List<SelectItem> bairros = new ArrayList<SelectItem>();

	public BuscarClienteBean() {
		estado = new Estado();
		cidade = new Cidade();
		bairro = new Bairro();
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public List<SelectItem> getCidades() {
		return cidades; 
	}

	public static void setCidades(List<Cidade> listCidades) {
		if (!cidades.isEmpty()) {
			cidades.clear();
		}
		for (Cidade cid : listCidades) {
			cidades.add(new SelectItem(cid.getCidadeCodigo(), cid
					.getCidadeNome()));
		}
	}

	public List<SelectItem> getBairros() {
		return bairros;
	}

	public static void setBairros(List<Bairro> listBairros) {
		if (!bairros.isEmpty()) {
			bairros.clear();
		}
		for (Bairro bai : listBairros) {
			bairros.add(new SelectItem(bai.getBairroCodigo(), bai
					.getBairroNome()));
		}
	}

	public void filtraCidadePorEstado(ValueChangeEvent event)
			throws SQLException {

		if (event.getNewValue() != event.getOldValue()) {
			List<Cidade> cid = new ArrayList<Cidade>();
			EnderecoDAO dao = new EnderecoDAO();
			cid = dao.getCidadePorEstado(event.getNewValue().toString());
			setCidades(cid);
		}
	}

	public void filtraBairroPorCidade(ValueChangeEvent event)
			throws SQLException {
		if (event.getNewValue() != event.getOldValue()) {
			List<Bairro> listaBairros = new ArrayList<Bairro>();
			EnderecoDAO daoEndereco = new EnderecoDAO();
			listaBairros = daoEndereco.getBairroPorCidade(event.getNewValue()
					.toString());
			setBairros(listaBairros);
		}
	}
}
