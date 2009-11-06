package br.com.utils;

public class GmapBean {
	
	private String key;
	
	private String latitude;
	
	private String longitude;
	
	private String lugar;
	
	private String zoom;
	
	public String getZoom() {
		return zoom;
	}


	public void setZoom(String zoom) {
		this.zoom = zoom;
	}


	public GmapBean(){
		this.key = "ABQIAAAAefFmfUQY8-CXvsDETHZCsRTZB4wYlL0ce2bo4gp0zVVjG6DOmRRxBXiJRYWVQQsnS14hg4TA8T2mCQ";
		this.zoom = "8";
	}

	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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
	

}
