package br.com.cor.mb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.siaic.businesslogic.Cliente;
import br.com.siaic.businesslogic.endereco.Bairro;
import br.com.siaic.businesslogic.endereco.Cidade;
import br.com.siaic.businesslogic.endereco.Estado;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.EnderecoDAO;
import br.com.siaic.dao.FabricaConexao;

public class BuscarClienteBean {

	private String nome;
		
	private Estado estado;
	private Cidade cidade;
	private Bairro bairro;
	
	private int quartos;
	private int suites;
	private int garagens;
	private char piscina;

	private static List<SelectItem> cidades = new ArrayList<SelectItem>();
	private static List<SelectItem> bairros = new ArrayList<SelectItem>();

	private List<Cliente> clientes;
	
	public BuscarClienteBean() {
		estado = new Estado();
		cidade = new Cidade();
		bairro = new Bairro();
		clientes = new ArrayList<Cliente>();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	
	public int getQuartos() {
		return quartos;
	}

	public void setQuartos(int quartos) {
		this.quartos = quartos;
	}

	public int getSuites() {
		return suites;
	}

	public void setSuites(int suites) {
		this.suites = suites;
	}

	public int getGaragens() {
		return garagens;
	}

	public void setGaragens(int garagens) {
		this.garagens = garagens;
	}

	public char getPiscina() {
		return piscina;
	}

	public void setPiscina(char piscina) {
		this.piscina = piscina;
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
			List<Bairro> listaBairros = new ArrayList<Bairro>();
			EnderecoDAO dao = new EnderecoDAO();
			if (event.getNewValue() != null) {
				cid = dao.getCidadePorEstado(event.getNewValue().toString());
			}
			setCidades(cid);
			setBairros(listaBairros);
		}
	}

	public void filtraBairroPorCidade(ValueChangeEvent event)
			throws SQLException {
		if (event.getNewValue() != event.getOldValue()) {
			List<Bairro> listaBairros = new ArrayList<Bairro>();
			EnderecoDAO daoEndereco = new EnderecoDAO();
			if (event.getNewValue() != null) {
				listaBairros = daoEndereco.getBairroPorCidade(event.getNewValue()
						.toString());
			}
			setBairros(listaBairros);
		}
	}
	
	public void buscar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select PEC_CODIGO from PESSOA_CLIENTE join PESSOA on PEC_CODIGO = PES_CODIGO where ");
		if (nome ==  null) {
			nome = new String("");
		}
		sql.append("PES_NOME like '%"+nome+"%'");
		
		System.out.println(bairro.getBairroCodigo());
		if (bairro.getBairroCodigo() != 0) {
			sql.append(" and PES_ENDERECO in (select END_CODIGO from ENDERECO where END_BAIRRO = ");
			sql.append(bairro.getBairroCodigo());
		}
		System.out.println(sql.toString());
		PreparedStatement ps = FabricaConexao.getInstancia().conectar().prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		
		ClienteDAO dao = new ClienteDAO();
		
		
		while (rs.next()) {
			clientes.add(dao.getClientePorId(rs.getInt(1)));
		}
		
		
	}
	
	
	public List<Cliente> getClientes() throws SQLException {
		return clientes;
	}
}
