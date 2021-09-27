package com.kbase.library.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.kbase.library.dao.AuthorDao;
import com.kbase.library.dao.BookDao;
import com.kbase.library.model.Author;
import com.kbase.library.model.Book;

@ManagedBean
@ViewScoped
public class BookBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2401959144061766621L;

	private Book book = new Book();

	private Integer authorId;

	private List<Book> books;

	@Autowired
	BookDao bookDao;

	@Autowired
	AuthorDao authorDao;

	@Resource(name="facesContext")
	FacesContext context;

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public Book getBook() {
		return book;
	}

	public List<Book> getBooks() {
		if (this.books == null) {
			this.books = bookDao.getAll();
		}
		return books;
	}

	public List<Author> getAuthors() {
		return authorDao.getAll();
	}

	public List<Author> getBooksAuthors() {
		return this.book.getAuthors();
	}

	public void loadbookById() {
		this.book = bookDao.findById(this.book.getIsbn());
	}

	public void insertAuthor() {
		Author author = authorDao.findById(this.authorId);
		this.book.addAuthor(author);
	}

	// begin
	@Transactional
	public void save() {

		if (book.getAuthors().isEmpty()) {
			context.addMessage("Author", new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}

		if (this.book.getIsbn() == null) {
			bookDao.add(this.book);
			this.books = bookDao.getAll();
		} else {
			bookDao.update(this.book);
		}

		this.book = new Book();
	}

	// commit

	@Transactional
	public void remove(Book book) {
		bookDao.remove(book);
		this.books = bookDao.getAll();
	}

	public void removeBookAuthor(Author author) {
		this.book.removeAuthor(author);
	}

	public void load(Book Book) {
		this.book = this.bookDao.findById(Book.getIsbn());
	}

	public String formAuthor() {
		return "author?faces-redirect=true";
	}

	public void startsWithDigitOne(FacesContext fc, UIComponent component, Object value) throws ValidatorException {

		
		if(value == null) {
			throw new ValidatorException(new FacesMessage("ISBN nao pode ser nulo"));
		}
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN deveria começar com 1"));
		}

	}
}
