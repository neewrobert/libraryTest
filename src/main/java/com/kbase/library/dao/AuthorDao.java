package com.kbase.library.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.kbase.library.model.Author;


@Repository
public class AuthorDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4251586504237654946L;
	
	@PersistenceContext
	EntityManager em;

	private DAO<Author> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Author>(this.em, Author.class);
	}

	public void add(Author t) {
		dao.add(t);
	}

	public void remove(Author t) {
		dao.remove(t);
	}

	public void update(Author t) {
		dao.update(t);
	}

	public List<Author> getAll() {
		return dao.getAll();
	}

	public Author findById(Integer authorId) {
		return dao.findById(authorId);
	}

}
