package com.kbase.library.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbase.library.model.User;

@Service("userDao")
@Transactional
@Repository
public class UserDao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5177465167136707060L;
	
	@PersistenceContext
	EntityManager manager;

	public boolean exists(User user) {

		TypedQuery<User> query = manager.createQuery(
				" select u from User u "
						+ " where u.email = :pEmail and u.password = :pPassword",
				User.class);

		query.setParameter("pEmail", user.getEmail());
		query.setParameter("pPassword", user.getPassword());
		try {
			@SuppressWarnings("unused")
			User resultado = query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}

		return true;
	}

}
