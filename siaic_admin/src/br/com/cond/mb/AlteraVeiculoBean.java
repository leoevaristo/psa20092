package br.com.cond.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cond.businesslogic.Apartamento;
import br.com.cond.businesslogic.Veiculo;
import br.com.cond.dao.ApartamentoDAO;
import br.com.cond.dao.VeiculoDAO;


public class AlteraVeiculoBean {
	
	
	private Apartamento apartamento;
	
	private Veiculo veiculo;

	
	private static List<SelectItem> listaApartamentos = new ArrayList<SelectItem>();
	
    private static int codigoGambiMasterFii;
	
	public AlteraVeiculoBean(){
		
		VeiculoDAO vdao = new VeiculoDAO();
		try {
			veiculo = vdao.getVeiculoId(codigoGambiMasterFii);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		apartamento = new Apartamento();
	    
		if (listaApartamentos.isEmpty()) {
			ApartamentoDAO apartDao = new ApartamentoDAO();
			try {
				for (Apartamento ap : apartDao.getTodosOsApartamentos()) {
					StringBuilder label = new StringBuilder();
					label.append("Nº: ");
					label.append(ap.getNumero());
					label.append(" - Andar ");
					label.append(ap.getAndar());
					label.append(" - Bloco ");
					label.append(ap.getBloco());
					listaApartamentos.add(new SelectItem(ap.getCodigoApartamento(), label.toString()));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public String alteraVeiculo2() {
		codigoGambiMasterFii = this.getCodigoParametro();
		return "altera";
	}
	
	private int getCodigoParametro() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        String codigo = req.getParameter("codigoAlteraVeiculo");
		if (codigo != null) {
            Integer idveiculo = new Integer(codigo).intValue();
            return idveiculo;
		} else return 0;
	}
	
	public Apartamento getApartamento() {
		return apartamento;
	}

    

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}



	public Veiculo getVeiculo() {
		return veiculo;
	}



	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}



	public List<SelectItem> getApartamentos() {
		return listaApartamentos;
	}



	public static void setApartamentos(List<SelectItem> apartamentos) {
		AlteraVeiculoBean.listaApartamentos = apartamentos;
	}

    public String voltar() {
    	return "sucesso";
    }

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String alteraVeiculo() throws SQLException {
        VeiculoDAO vdao = new VeiculoDAO();
        vdao.alterarVeiculos(this.veiculo);
        
		return "sucesso";
	}

}
