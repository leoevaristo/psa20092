package br.com.siaic.businesslogic;

import java.sql.Date;
import java.sql.Time;

import br.com.siaic.dao.AgendaDAO;
import br.com.siaic.dao.ClienteDAO;
import br.com.siaic.dao.ImovelDAO;
import br.com.siaic.dao.UsuarioDAO;
import br.com.siaic.mb.AgendaBean;

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
	private Cliente cliente;
	private Usuario corretor;
	private Imovel imovel;
	private Date data;
	private Time horaInicio;
	private Time horaFim;
	private String descricao;

	/**
	 * Construtor de Agenda.
	 * 
	 * @param Corretor
	 *            - objeto {@link Usuario}. Não pode ser nulo.
	 * @param Cliente
	 *            - objeto {@link Cliente}. Não pode ser nulo.
	 * @param Imovel
	 *            - objeto {@link Imovel}. Não pode ser nulo.
	 * @param data
	 *            - data da visita.
	 * @param horaInicio
	 *            - horário do início da visita.
	 * @param horaFim
	 *            - horário do final da visita.
	 * @param descricao
	 *            - descrição e/ou detalhes da visita.
	 * @throws Exception
	 *             é lançada caso algum dos valores obrigatórios seja nulo.
	 */
	public Agenda(Usuario corretor, Cliente cliente, Imovel imovel,
			Date data, Time horaInicio, Time horaFim, String descricao)
			throws Exception {
		if ((corretor == null) || (cliente == null)
				|| (imovel == null)) {
			throw new Exception("Campo(s) obrigatório(s) nulo(s).");
		}
		this.corretor = corretor;
		this.cliente = cliente;
		this.imovel = imovel;
		this.data = data;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.setDescricao(descricao);
	}
	
	public Agenda(int codCorretor, int codCliente, int codImovel, Date data,
			Time horaInicio, Time horaFim, String descricao) throws Exception {
		
		ClienteDAO cdao = new ClienteDAO();
		ImovelDAO idao = new ImovelDAO();
		
		Usuario corretor = new UsuarioDAO().getUsuarioId(codCorretor); 
		Cliente cliente = cdao.getClientePorId(codCliente);
		Imovel imovel = idao.getImovel(codImovel);
		
		if ((corretor == null) || (cliente == null)
				|| (imovel == null)) {
			throw new Exception("Campo(s) obrigatório(s) nulo(s).");
		}
		this.corretor = corretor;
		this.cliente = cliente;
		this.imovel = imovel;
		this.data = data;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.setDescricao(descricao);
	}
	
	public Agenda(){
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getCorretor() {
		return corretor;
	}

	public void setCorretor(Usuario corretor) {
		this.corretor = corretor;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Time horaFim) {
		this.horaFim = horaFim;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}