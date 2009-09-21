package br.com.siaic.businesslogic;

import java.sql.SQLException;
import java.util.List;

import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.ImovelCaracteristicaDAO;
import br.com.siaic.dao.ImovelDAO;

/**
 * 
 * @author Filipe
 *
 */
public class Imovel {
    
	private ImovelDAO imoDAO;
	
	private int codigo;
    private int caracteristica;
    private int finalidade;
    private String detalhe;
    private String formaPagamento;
    private int tipo;
    private double valor;
    private double valorCondominio;
    private int proprietario;
    private int endereco;
    
    public int getEndereco() {
		return endereco;
	}

	public void setEndereco(int endereco) {
		this.endereco = endereco;
	}

	public Imovel() {
    	this.imoDAO = new ImovelDAO();
    }
    
    public Imovel(int codigoImovel) {
    	this.imoDAO = new ImovelDAO();
    	this.codigo = codigoImovel;
    }
    
	public int getCodigo() {
		return codigo;
	}
	public int getCaracteristica() {
		return caracteristica;
	}
	public void setCaracteristica(int caracteristica) {
		this.caracteristica = caracteristica;
	}
	public int getFinalidade() {
		return finalidade;
	}
	public void setFinalidade(int finalidade) {
		this.finalidade = finalidade;
	}
	public String getDetalhe() {
		return detalhe;
	}
	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getValorCondominio() {
		return valorCondominio;
	}
	public void setValorCondominio(double valorCondominio) {
		this.valorCondominio = valorCondominio;
	}
	public int getProprietario() {
		return proprietario;
	}
	public void setProprietario(int proprietario) {
		this.proprietario = proprietario;
	}
	
	public void salvar() {
		this.imoDAO.salva(this);
	}
	
	public void excluir() {
		this.imoDAO.exclui(this);
	}
	
	public void atualizar() {
	    this.imoDAO.atualiza(this);	
	}
	
	/**
	 * Recupera do banco de dados todos os imóveis.
	 * @return List<T> de imóveis.
	 */
	public static List<Imovel> getImovel() {
		return new ImovelDAO().getImoveis();
	}
	
	public static Imovel getImovel(int codigo) {
		return new ImovelDAO().getImovel(codigo);
	}
	
	public static void main(String[] args) {
		Imovel imo = new ImovelDAO().getImovel(1);
		System.out.println(imo.getFormaPagamento());
		try {
			System.out.println(new ImovelCaracteristicaDAO().getImovelCaracteristica(imo.getFinalidade()).getPiscina());
			System.out.println(new EnderecoDAO().getEnderecoPorCodigo(imo.getEndereco()).getEnderecoNome());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		Imovel imoe = new Imovel(1);
		imoe.setCaracteristica(1);
		imoe.setDetalhe("Au au");
		imoe.setEndereco(1);
		imoe.setFinalidade(1);
		imoe.setFormaPagamento("Só pra testar");
		imoe.setProprietario(0);
		imoe.setTipo(1);
		imoe.setValor(900000);
		imoe.setValorCondominio(0);
		
        new ImovelDAO().atualiza(imoe);	
	}
}
