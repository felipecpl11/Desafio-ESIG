package controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Usuario;
import service.UsuarioService;

@ManagedBean(name="usuarioBean")
@ViewScoped
public class UsuarioBean {
	
	private Usuario usuario;
	private Boolean usuarioLogado;
	private static UsuarioBean instance;
	
	@PostConstruct
	public void inicializa()
    {
		usuario = new Usuario();
        usuarioLogado = Boolean.FALSE;
        instance = this;
    }

	public void verificarLogin() throws URISyntaxException, IOException {
		System.out.println("Login: " + usuario.getLogin() + "Senha: " + usuario.getSenha());
		String retorno = UsuarioService.Post(usuario.getLogin(), usuario.getSenha());
		if (retorno.equals("200")) {
			usuarioLogado = Boolean.TRUE;
			FacesContext.getCurrentInstance().getExternalContext().redirect("ListarArquivos.xhtml"); 
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao efetuar login, tente novamente."));
		}
	}

	public boolean usuarioLogado() throws IOException {
		if(usuarioLogado) 
			return true;
		else
			return false;
	}	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Boolean usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public static UsuarioBean getInstance() {
		return instance;
	}

	public static void setInstance(UsuarioBean instance) {
		UsuarioBean.instance = instance;
	}
}
