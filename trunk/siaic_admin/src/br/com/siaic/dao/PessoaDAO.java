package br.com.siaic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.siaic.businesslogic.Pessoa;

public class PessoaDAO 
{
	
	
	
	
	private Connection conexao = null;
	
	
	
	
	private static PessoaDAO instancia = null;
	
	
	
	
	private PessoaDAO()
	{
		
		try
		{
			this.conexao = FabricaConexao.getInstancia().conectar();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public static PessoaDAO getInstancia()
	{
		
		if(instancia == null)
		{
			instancia = new PessoaDAO();
		}
		
		return instancia;
		
	}
	
	
	
	
	public void adicionarPessoa(Pessoa pessoa) throws SQLException
	{
		
		String sql = "INSERT INTO PESSOA (PES_NOME, PES_TELEFONE, PES_CELULAR, " 
			       + "PES_EMAIL, PES_TIPO, PES_SEXO, PES_ENDERECO) " 
			       + "VALUES (? , ? , ? , ? , ? , ? , ? );";
		
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ps.setString(1, pessoa.getNome());
		ps.setString(2, pessoa.getTelefone());
		ps.setString(3, pessoa.getCelular());
		ps.setString(4, pessoa.getEmail());
		ps.setString(5, pessoa.getTipoPessoa());
		ps.setString(6, pessoa.getSexo());
		ps.setInt(7, 1);
		
		ps.execute();
		
		setIdPessoa(pessoa);		
		
		ps.close();
		
	}
	
	
	
	
	
	public void setIdPessoa(Pessoa pessoa) throws SQLException
	{
		
		String sql = "SELECT MAX(PES_CODIGO) AS CODIGO FROM PESSOA;";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			
			pessoa.setCodigoPessoa(rs.getInt("CODIGO"));
		
		}				
		
		ps.close();
		
		rs.close();
	}
	

}
