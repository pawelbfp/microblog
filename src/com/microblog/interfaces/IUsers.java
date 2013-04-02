package com.microblog.interfaces;

import java.util.List;

import com.microblog.core.User;

public interface IUsers {

	List<User> getUsers();
	List<User> getSubscribedUsers(User user);
	List<User> getNotSubscribedUsers(User user);	
	void subscribeUser(User user,User userSubscribed);
	void subscribeAllUsers(User user);
	void unsubscribeUser(User user,User userUnSubscribed);
	void unsubscribeAllUsers(User user);
	
}
