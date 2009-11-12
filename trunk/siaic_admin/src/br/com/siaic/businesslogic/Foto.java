package br.com.siaic.businesslogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.richfaces.demo.fileupload.FileUploadBean;

public class Foto {

	public Foto() {
		this.Name = "";
		this.mime = "";
		this.data = null;
		this.length = 0;
	}
	
	private int codigo;
	private int imovel;
	private String Name;
	private String mime;
	private long length;
	private byte[] data;
	protected static String pathImg = File.separator + "WEB-INF" + 
	    File.separator + "fotos" + File.separator;
	
	public int getCodigo() {
	    return this.codigo;	
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getImovel() {
		return this.imovel;
	}
	public void setImovel(int imovel) {
		this.imovel = imovel;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
		int extDot = name.lastIndexOf('.');
		if (extDot > 0) {
			String extension = name.substring(extDot + 1);
			if ("bmp".equals(extension)) {
				mime = "image/bmp";
			} else if ("jpg".equals(extension)) {
				mime = "image/jpeg";
			} else if ("gif".equals(extension)) {
				mime = "image/gif";
			} else if ("png".equals(extension)) {
				mime = "image/png";
			} else {
				mime = "image/unknown";
			}
		}
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public String getMime() {
		return mime;
	}
	
	public static String salvaFoto(Foto foto, String pathSalva) throws IOException {
		String path = pathSalva + foto.getName();
		File file = new File(FileUploadBean.getRealPath(path));
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(foto.getData());
		fos.flush();
		fos.close();
		
		return path;
	}

	public static String salvaFoto(Foto foto) throws IOException {
		return Foto.salvaFoto(foto, pathImg);
	}
	
	public static byte[] pegaFoto(String pathRel) throws IOException {
		
		String path = pathRel;
		File file = new File(FileUploadBean.getRealPath(path));
		FileInputStream fis = new FileInputStream(file);
		byte[] bin = new byte[(int)file.length()];
		fis.read(bin);
		fis.close();
		
		return bin;
	}
	
}
