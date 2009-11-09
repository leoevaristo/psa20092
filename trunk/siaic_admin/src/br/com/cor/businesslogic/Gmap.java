package br.com.cor.businesslogic;

public class Gmap {

	private String key = "ABQIAAAAefFmfUQY8-CXvsDETHZCsRTZB4wYlL0ce2bo4gp0zVVjG6DOmRRxBXiJRYWVQQsnS14hg4TA8T2mCQ";

	private String latitude;

	private String longitude;

	private String lugar;

	private String zoom;

	private String url = "http://maps.google.com/maps/geo?";
	
	private String dadosEndereco;
	
	private String cidade;
	
	private String bairro;



	public Gmap() {
		this.cidade = "João Pessoa";
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

}
