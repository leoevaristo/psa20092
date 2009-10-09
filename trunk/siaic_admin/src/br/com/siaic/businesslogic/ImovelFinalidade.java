package br.com.siaic.businesslogic;

import br.com.siaic.dao.ImovelFinalidadeDAO;

public class ImovelFinalidade {
	
    private int codigo;
    private String nome;
    
    public ImovelFinalidade(int codigo) {
    	this.codigo = codigo;
    }
    
    public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public ImovelFinalidade() {
        this.nome = "";
        this.codigo = -1;
    }
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodigo() {
		return codigo;
	}
	
	public static void main(String[] args) {
		ImovelFinalidade imf = new ImovelFinalidadeDAO().getImovelFinalidade(1);
		System.out.println(imf.getNome());
		ImovelFinalidade imf2 = new ImovelFinalidade(1);
//		imf2.setNome("TROCA E VENDA");
//		new ImovelFinalidadeDAO().salvar(imf2);
		imf2.setNome("TROCA, VENDA E PERMUTA.");
//		new ImovelFinalidadeDAO().atualizar(imf2);
		new ImovelFinalidadeDAO().deletar(2);
	}
    
}
