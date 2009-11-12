package br.com.siaic.businesslogic;


import java.util.Calendar;

import br.com.siaic.dao.AgendaDAO;
import br.com.siaic.mb.agenda.AgendaBean;

/**
 * Reflete a entidade "dbo.siaic.agenda"
 * 
 * @author Robson R. Vieira da Cunha
 * @version 1.0
 * @see AgendaDAO
 * @see AgendaBean
 * @see Cliente
 * @see Usuario
 * @see Imovel
 */
public class Agenda {

	private int codigo;
	private int codCliente;
	private int codCorretor;
	private int codImovel;
	private Calendar data;
	private String horaInicio;
	private String horaFim;
	private String descricao;

	/**
	 * Construtor de Agenda.
	 * 
	 * @param Corretor
	 *            - objeto {@link Usuario}. N�o pode ser nulo.
	 * @param Cliente
	 *            - objeto {@link Cliente}. N�o pode ser nulo.
	 * @param Imovel
	 *            - objeto {@link Imovel}. N�o pode ser nulo.
	 * @param data
	 *            - data da visita.
	 * @param horaInicio
	 *            - hor�rio do in�cio da visita.
	 * @param horaFim
	 *            - hor�rio do final da visita.
	 * @param descricao
	 *            - descri��o e/ou detalhes da visita.
	 * @throws Exception
	 *             � lan�ada caso algum dos valores obrigat�rios seja nulo.
	 */
	/*
	 * public Agenda(int corretor, Cliente cliente, Imovel imovel, Date data,
	 * Time horaInicio, Time horaFim, String descricao) throws Exception { if
	 * ((corretor == null) || (cliente == null) || (imovel == null)) { throw new
	 * Exception("Campo(s) obrigatório(s) nulo(s)."); } this.codCorretor =
	 * corretor; this.codCliente = cliente; this.imovel = imovel; this.data =
	 * data; this.horaInicio = horaInicio; this.horaFim = horaFim;
	 * this.setDescricao(descricao); }
	 */

	public Agenda() {
		

	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public int getCodCorretor() {
		return codCorretor;
	}

	public void setCodCorretor(int codCorretor) {
		this.codCorretor = codCorretor;
	}

	public int getCodImovel() {
		return codImovel;
	}

	public void setCodImovel(int codImovel) {
		this.codImovel = codImovel;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}


}