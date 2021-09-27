package com.kbase.library.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.kbase.library.dao.AuthorDao;
import com.kbase.library.model.Author;

@ManagedBean
@ViewScoped
public class AuthorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8097939171143632736L;

	private Author author = new Author();

	@Autowired
	private AuthorDao dao;

	private Integer authorId;

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public void loadAuthorById() {
		this.author = this.dao.findById(authorId);
	}

	@Transactional
	public String save() {

		if (this.author.getId() == null) {
			this.dao.add(this.author);
		} else {
			this.dao.update(this.author);
		}

		this.author = new Author();

		return "book?faces-redirect=true";
	}

	@Transactional
	public void remove(Author author) {
		this.dao.remove(author);
	}

	public List<Author> getAuthors() {
		return this.dao.getAll();
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	

}
