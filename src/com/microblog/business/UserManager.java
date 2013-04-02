package com.microblog.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import com.microblog.core.Subscription;
import com.microblog.core.User;
import com.microblog.dao.SubscriptionDao;
import com.microblog.dao.UserDao;

public class UserManager {

	private UserDao userDao;
	private SubscriptionDao subscriptionDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setSubscriptionDao(SubscriptionDao subscriptionDao) {
		this.subscriptionDao = subscriptionDao;
	}

	public User findById(int idUser) {
		return userDao.findById(idUser);
	}

	public User findByName(String username) {
		return userDao.findByName(username);
	}

	public List<String> getSubscribedUserNames(String username) {

		User user = findByName(username);
		List<String> subscriptionUserNames = new ArrayList<String>();
		
		if (user != null) {
			List<Subscription> subscriptionList = subscriptionDao.findSubscribedByUser(user.getIdUser());
			for (Subscription subscr : subscriptionList) {
				subscriptionUserNames.add(findById(subscr.getSubscriptionPK().getIdSubscribed()).getUsername());
			}
		}
		
		return subscriptionUserNames;
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public void saveOrUpdate(User user) {
		userDao.saveOrUpdate(user);
	}

	public void saveOrUpdate(List<User> users) {
		userDao.saveOrUpdate(users);
	}

	public void delete(User user) {
		userDao.delete(user);
	}

	public void delete(List<User> users) {
		userDao.delete(users);
	}
	
	
	public void encodePasswordsAllUsers()
	{
		ShaPasswordEncoder pwdEncoder = new ShaPasswordEncoder(256);
		
		List<User>  users= findAll();
		
		for(User user : users){
			String pass = pwdEncoder.encodePassword(user.getPassword(), user.getUsername());
			user.setPassword(pass);
		}
		saveOrUpdate(users);
		
	}
	
}
