package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import model.File;
import service.ServiceArquivo;

@ManagedBean(name="fileBean")
@ViewScoped
public class FileBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	File arquivo;

	 @PostConstruct
	    public void postConstruct() {
	    	UsuarioBean usuarioBean = UsuarioBean.getInstance();
	    	try {
	    		if(usuarioBean == null || !usuarioBean.getUsuarioLogado())
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
	public void upload(FileUploadEvent event) throws IOException, URISyntaxException {
		UploadedFile uploadedFile = event.getFile();
		 ServiceArquivo.Post(uploadedFile);
	        FacesContext.getCurrentInstance().addMessage(null, 
			        new FacesMessage("Upload completo", "O arquivo " + uploadedFile.getFileName() + " foi salvo!"));
	}
	
	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}
	
}
