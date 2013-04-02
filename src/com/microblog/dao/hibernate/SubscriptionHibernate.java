package com.microblog.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.microblog.core.Subscription;
import com.microblog.dao.SubscriptionDao;

@Transactional
public class SubscriptionHibernate implements SubscriptionDao {

	protected SessionFactory sessionFactory;

	public SubscriptionHibernate() {

	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Subscription> findSubscribedByUser(int idSubscriber) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Subscription.class);
		criteria.add(Restrictions.eq("subscriptionPK.idSubscriber", idSubscriber));
		return (List<Subscription>) criteria.list();
	}

	public void saveOrUpdate(Subscription subscription) {
		sessionFactory.getCurrentSession().saveOrUpdate(subscription);
	}

	public void saveOrUpdate(List<Subscription> subscriptions) {
		for (Subscription subscription : subscriptions) {
			saveOrUpdate(subscription);
		}
	}

	public void delete(Subscription subscription) {
		sessionFactory.getCurrentSession().delete(subscription);
	}

	public void delete(List<Subscription> subscriptions) {
		for (Subscription subscription : subscriptions) {
			delete(subscription);
		}
	}
}
