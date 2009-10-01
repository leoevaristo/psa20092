package br.com.siaic.mb;

import br.com.siaic.businesslogic.ValorMercado;

import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.ValorMercadoDAO;

public class ValorMercadoBean {
	
	private ValorMercado valorMercado = null;
	private Bairro bairro = null;
	
	public ValorMercadoBean() {
		this.valorMercado = new ValorMercado();
		this.bairro = new Bairro();
	}

	public ValorMercado getValorMercado() {
		return valorMercado;
	}

	public void setValorMercado(ValorMercado valorMercado) {
		this.valorMercado = valorMercado;
	}
	
	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public String addValorMercado() throws Exception {
		ValorMercadoDAO vmd = new ValorMercadoDAO();
		valorMercado.setBairro(new EnderecoDAO().getBairroPorCodigo(Integer.parseInt(bairro.getBairroNome())));
		System.out.println(valorMercado.getBairro().getBairroCodigo());
		vmd.inserirValorMercado(valorMercado);
		return "sucesso";
	}
}
