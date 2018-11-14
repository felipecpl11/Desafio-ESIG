package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import pojo.Content;
import service.ServiceArquivo;

@ManagedBean(name = "listarArquivosBean")
@ViewScoped
public class ListarArquivosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Content> arquivos = new ArrayList<>();

	@PostConstruct
	public void postConstruct() {
		UsuarioBean usuarioBean = UsuarioBean.getInstance();
		try {
			if (usuarioBean == null || !usuarioBean.getUsuarioLogado()) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			}
			else
				ListarArquivos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ListarArquivos() throws IOException {
		arquivos = ServiceArquivo.GetAll();
	}

	public List<Content> getArquivos() {
		return arquivos;
	}

}
