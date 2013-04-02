package com.microblog.dao;

import java.util.List;

import com.microblog.core.Subscription;

public interface SubscriptionDao {

	public List<Subscription> findSubscribedByUser(int idSubscriber) ;

	public void saveOrUpdate(Subscription subscription) ;

	public void saveOrUpdate(List<Subscription> subscriptions);

	public void delete(Subscription subscription) ;

	public void delete(List<Subscription> subscriptions) ;
}
