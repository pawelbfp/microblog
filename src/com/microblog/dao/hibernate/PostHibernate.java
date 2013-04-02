package com.microblog.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.microblog.core.Post;
import com.microblog.dao.PostDao;

@Transactional
public class PostHibernate implements PostDao {

	protected SessionFactory sessionFactory;

	public PostHibernate() {

	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	public Post findPostById(int idPost) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Post.class);
		criteria.add(Restrictions.idEq(idPost));
		return (Post) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Post> findPostsByUser(int idUser) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Post.class);
		criteria.add(Restrictions.eq("idUser", idUser));
		return (List<Post>) criteria.list();
	}

	public void saveOrUpdate(Post post) {
		sessionFactory.getCurrentSession().saveOrUpdate(post);
	}

	public void saveOrUpdate(List<Post> posts) {
		for (Post post : posts) {
			saveOrUpdate(post);
		}
	}

	public void delete(Post post) {
		sessionFactory.getCurrentSession().delete(post);
	}
	
	public void delete(int idPost) {
		sessionFactory.getCurrentSession().delete(findPostById(idPost));
	}	

	public void delete(List<Post> posts) {
		for (Post post : posts) {
			delete(post);
		}
	}
}
