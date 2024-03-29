package org.richfaces.demo.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.el.ELResolver;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.siaic.businesslogic.Foto;
import br.com.siaic.dao.FotoDAO;

/**
 * @author Ilya Shaikovsky
 * 
 */
public class FileUploadBean {

	private ArrayList<Foto> files = new ArrayList<Foto>();
	private int uploadsAvailable = 5;
	private boolean autoUpload = false;
	private boolean useFlash = false;

	public int getSize() {
		if (getFiles().size() > 0) {
			return getFiles().size();
		} else {
			return 0;
		}
	}

	public FileUploadBean() {
	}

	public void paint(OutputStream stream, Object object) throws IOException {
		stream.write(getFiles().get((Integer) object).getData());
	}

	public void listener(UploadEvent event) throws Exception {
		UploadItem item = event.getUploadItem();
		Foto file = new Foto();
		if (item.isTempFile()) {
			File arq = item.getFile();

			InputStream os = new FileInputStream(arq);
			byte[] data = new byte[(int) arq.length()];
			os.read(data);

			file.setData(data);
			file.setLength(data.length);
			file.setName(item.getFileName());
		
	        Foto.salvaFoto(file,"/fotostemp/");
			files.add(file);
			uploadsAvailable--;
		}
	}

	public String clearUploadData() {
		files.clear();
		setUploadsAvailable(5);
		return "";
	}
	
	private String fotoApagar;
	public String getFotoApagar() {
		return fotoApagar;
	}
	public void setFotoApagar(String fotoApagar) {
		this.fotoApagar = fotoApagar;
	}
	
	private int fotoImovel;
	public int getFotoImovel() {
		return fotoImovel;
	}
	public void setFotoImovel(int fotoImovel) {
		this.fotoImovel = fotoImovel;
	}
	
	public String apagarFoto() {
		for (Foto fh : this.files) {
			if (fh.getName().equals(fotoApagar)) {
				if (fh.getCodigo() != 0)
				    new FotoDAO().apagaFoto(fh);
			}
		}
		this.files = (ArrayList<Foto>)new FotoDAO().getFotos(fotoImovel);
		return "";
	}
	
	public static FileUploadBean getCurrentSession() {
		FacesContext context = FacesContext.getCurrentInstance();  
	    ELResolver resolver = context.getApplication().getELResolver();  
	    return (FileUploadBean) resolver.getValue(context.getELContext(), null, "fileUploadBean");
	}

	public long getTimeStamp() {
		return System.currentTimeMillis();
	}

	public ArrayList<Foto> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<Foto> files) {
		this.files = files;
		for (Foto foto : this.files) {
			try {
				Foto.salvaFoto(foto,"/fotostemp/");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	public boolean isAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(boolean autoUpload) {
		this.autoUpload = autoUpload;
	}

	public boolean isUseFlash() {
		return useFlash;
	}

	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash;
	}
	
	public static String getRealPath(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}

}