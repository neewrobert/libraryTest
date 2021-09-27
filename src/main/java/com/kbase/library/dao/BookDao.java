package com.kbase.library.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.kbase.library.model.Book;

@Repository
public class BookDao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -200182184763626548L;

	@PersistenceContext
	EntityManager manager;

	private DAO<Book> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Book>(this.manager, Book.class);
	}

	public void add(Book t) {
		dao.add(t);
	}

	public void remove(Book t) {
		dao.remove(t);
	}

	public void update(Book t) {
		dao.update(t);
	}

	public List<Book> getAll() {
		return dao.getAll();
	}

	public Book findById(String id) {
		return manager.find(Book.class, id);
	}


}
