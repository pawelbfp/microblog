package com.microblog.dao;

import java.util.List;

import com.microblog.core.User;

public interface UserDao {

	public User findById(int idUser);
	
	public User findByName(String username);

	public List<User> findAll();

	public void saveOrUpdate(User user);

	public void saveOrUpdate(List<User> users);

	public void delete(User user);

	public void delete(List<User> users);
}
