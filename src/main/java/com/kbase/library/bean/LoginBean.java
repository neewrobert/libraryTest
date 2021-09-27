package com.kbase.library.bean;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.kbase.library.dao.UserDao;
import com.kbase.library.model.User;


@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user = new User();

	@Autowired
	UserDao userDao;

	@Resource(name="facesContext")
	FacesContext context;

	public User getUser() {
		return user;
	}

	public String doLogin() {
		System.out.println("fazendo login do usuario "
				+ this.user.getEmail());

		boolean exists = userDao.exists(this.user);
		if (exists) {
			context.getExternalContext().getSessionMap()
					.put("usuarioLogado", this.user);
			return "book?faces-redirect=true";
		}

		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não encontrado"));

		return "login?faces-redirect=true";
	}

	public String logout() {
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
}
