package controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import service.ServiceArquivo;

@ManagedBean(name="uploadAvancadoBean")
@ViewScoped
public class UploadAvancadoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private List<File> arquivos = new ArrayList<>();
    
    @PostConstruct
	public void postConstruct() {
		UsuarioBean usuarioBean = UsuarioBean.getInstance();
		try {
			if (usuarioBean == null || !usuarioBean.getUsuarioLogado()) {
				System.out.println("Entrei3");
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			}
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
    
    public List<File> getArquivos() {
        return arquivos;
    }
}
