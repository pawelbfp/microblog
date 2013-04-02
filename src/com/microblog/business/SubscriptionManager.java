package com.microblog.business;

import java.util.ArrayList;
import java.util.List;

import com.microblog.core.Subscription;
import com.microblog.core.User;
import com.microblog.core.web.SubscriptionJson;
import com.microblog.dao.SubscriptionDao;
import com.microblog.dao.UserDao;

public class SubscriptionManager {

	private UserDao userDao;
	private SubscriptionDao subscriptionDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}	

	public void setSubscriptionDao(SubscriptionDao subscriptionDao) {
		this.subscriptionDao = subscriptionDao;
	}
	
	public List<SubscriptionJson> findSubscribedByUserId(int idSubscriber) 
	{
		List<SubscriptionJson> subscrListJson = new ArrayList<SubscriptionJson>();
		List<Subscription> subscrList = subscriptionDao.findSubscribedByUser(idSubscriber);
		if (subscrList!=null)
		{
			subscrListJson = new ArrayList<SubscriptionJson>();
			for ( Subscription subsc : subscrList)
			{
				int idUser = subsc.getSubscriptionPK().getIdSubscribed();
				subscrListJson.add(new SubscriptionJson(idUser,userDao.findById(idUser).getUsername()));
			}
		}
		
		return subscrListJson;
	};
	
	public List<SubscriptionJson> findSubscribedByUserName(String userName)
	{
		User user = userDao.findByName(userName);
		return findSubscribedByUserId(user.getIdUser());
	}


	public List<SubscriptionJson> findAvailableByUserId(int idSubscriber) 
	{
		List<User> userList = userDao.findAll(); 
		ArrayList<SubscriptionJson> subscrListJson = new ArrayList<SubscriptionJson>();
		List<Subscription> subscribedList = subscriptionDao.findSubscribedByUser(idSubscriber);
		
		for ( User user : userList)
		  if (!checkSubscribed(subscribedList, user.getIdUser()) && user.getIdUser()!=idSubscriber)
			  subscrListJson.add(new SubscriptionJson(user.getIdUser(),user.getUsername()));
			
		
		return subscrListJson;
	};

	public List<SubscriptionJson> findAviableByUserName(String userName)
	{
		User user = userDao.findByName(userName);
		return findAvailableByUserId(user.getIdUser());
	}
	
	
	public boolean checkSubscribed(List<Subscription> subscribedList, int userId)
	{
		
		for ( Subscription subsc : subscribedList)
			if (subsc.getSubscriptionPK().getIdSubscribed() ==userId)
				return true;
		return false;
	}
	
	

	public void saveOrUpdate(Subscription subscription) 
	{
		subscriptionDao.saveOrUpdate(subscription);
	};
	

	public void addList(int idUser,List<SubscriptionJson> subscriptionsJson)
	{
		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();  
		for ( SubscriptionJson subsc : subscriptionsJson)
		{
			subscriptions.add(new Subscription(idUser, subsc.getIdUser()));
		}
		
		subscriptionDao.saveOrUpdate(subscriptions);

	};
	
	
	public void saveOrUpdate(List<Subscription> subscriptions)
	{
		subscriptionDao.saveOrUpdate(subscriptions);
	};
	

	public void delete(Subscription subscription)
	{
		subscriptionDao.delete(subscription);
	};
	
	public void deleteList(int idUser,List<SubscriptionJson> subscriptionsJson) 
	{
		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();  
		for ( SubscriptionJson subsc : subscriptionsJson)
		{
			subscriptions.add(new Subscription(idUser, subsc.getIdUser()));
		}
		
		subscriptionDao.delete(subscriptions);
	};

	
	
	public void delete(List<Subscription> subscriptions) 
	{
		subscriptionDao.delete(subscriptions);
	};
	
}
