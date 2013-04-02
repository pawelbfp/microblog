package com.microblog.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.microblog.core.User;
import com.microblog.dao.UserDao;

@Transactional
public class UserHibernate implements UserDao {

	protected SessionFactory sessionFactory;

	public UserHibernate() {

	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	public User findById(int idUser) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.idEq(idUser));
		return (User) criteria.uniqueResult();
	}
	
	public User findByName(String username){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		return (User) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return (List<User>) criteria.list();
	}

	public void saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	public void saveOrUpdate(List<User> users) {
		for (User user : users) {
			saveOrUpdate(user);
		}
	}

	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	public void delete(List<User> users) {
		for (User user : users) {
			delete(user);
		}
	}
}
