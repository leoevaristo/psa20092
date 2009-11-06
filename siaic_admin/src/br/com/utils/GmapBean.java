package br.com.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GmapBean {

	private String key = "ABQIAAAAefFmfUQY8-CXvsDETHZCsRTZB4wYlL0ce2bo4gp0zVVjG6DOmRRxBXiJRYWVQQsnS14hg4TA8T2mCQ";

	private String latitude;

	private String longitude;

	private String lugar;

	private String zoom;

	private String url = "http://maps.google.com/maps/geo?";
	
	private String dadosEndereco;

	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

	public GmapBean() {

	}

	public String getKey() {
		return key;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDadosEndereco() {
		return dadosEndereco;
	}

	public void setDadosEndereco(String dadosEndereco) {
		this.dadosEndereco = dadosEndereco;
	}

	public void transformaEndereco() {

		String endereco = getUrl() + "&q=" + getLugar().replace(" ", "+") + "&output=csv&oe=utf8&sensor=false" + getKey();
		String resultado = "";
		String dados[];
		try {
			URL url = new URL(endereco);
			InputStreamReader isr;
			isr = new InputStreamReader(url.openStream());
			BufferedReader bf = new BufferedReader(isr);

			while (isr.ready()) {
				resultado = bf.readLine();
			}
			
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		
		setDadosEndereco(resultado);
		dados = resultado.split(",");	
		setLatitude(dados[2]);
		setLongitude(dados[3]);
		
		
	}

}
