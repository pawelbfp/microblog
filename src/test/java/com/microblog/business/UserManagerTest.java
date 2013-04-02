package com.microblog.business;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.microblog.core.Subscription;
import com.microblog.core.User;
import com.microblog.dao.SubscriptionDao;
import com.microblog.dao.UserDao;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.fest.assertions.Assertions.assertThat;

public class UserManagerTest {

	private static int idUser = 1;
	private static String userName = "name";
	
	private UserManager userManager;
	private UserDao userDaoMock;
	private SubscriptionDao subscriptionDaoMock;
	
	@Before
	public void Init()
	{
		userManager = new UserManager();
		userDaoMock = Mockito.mock(UserDao.class);
		userManager.setUserDao(userDaoMock);
		subscriptionDaoMock = Mockito.mock(SubscriptionDao.class);
		userManager.setSubscriptionDao(subscriptionDaoMock);
		
	}	
	
	@Test
	public final void testFindById() {
		
		Mockito.when(userDaoMock.findById(idUser)).thenReturn(new User(idUser,userName,userName));
		User user = userManager.findById(idUser);
		assertThat(user.getIdUser()).isEqualTo(idUser);
		
	}

	@Test
	public final void testFindByName() {
		
		Mockito.when(userDaoMock.findByName(userName)).thenReturn(new User(idUser,userName,userName));
		User user = userManager.findByName(userName);
		assertThat(user.getIdUser()).isEqualTo(idUser);
	}

	@Test
	public final void testGetSubscribedUserNames() {
		User user = new User(idUser,userName,userName);
		Mockito.when(userDaoMock.findByName(userName)).thenReturn(user);
		List<Subscription> subscriptionList  = new ArrayList<Subscription>();
		subscriptionList.add(new Subscription(idUser, idUser));
		Mockito.when(subscriptionDaoMock.findSubscribedByUser(idUser)).thenReturn(subscriptionList);
		Mockito.when(userDaoMock.findById(idUser)).thenReturn(user);

		List<String> list  = userManager.getSubscribedUserNames(userName);
		
		assertThat(list).containsExactly(userName);
	}
	
	@Test
	public final void testGetSubscribedUserNames_noUser() {
		Mockito.when(userDaoMock.findByName(userName)).thenReturn(null);

		List<String> list  = userManager.getSubscribedUserNames(userName);
		
		Assertions.assertThat(list).isEmpty();
	}
	

	@Test
	public final void testFindAll() {
		userManager.findAll();
		Mockito.verify(userDaoMock).findAll();
	}

	@Test
	public final void testSaveOrUpdateUser() {

		User user = new User();
		userManager.saveOrUpdate(user);
		Mockito.verify(userDaoMock).saveOrUpdate(user);

	}

	@Test
	public final void testSaveOrUpdateListOfUser() {
		List<User> users = new ArrayList<User>();
		userManager.saveOrUpdate(users);
		Mockito.verify(userDaoMock).saveOrUpdate(users);

	}

	@Test
	public final void testDeleteUser() {
		User user = new User();
		userManager.delete(user);
		Mockito.verify(userDaoMock).delete(user);

	}

	@Test
	public final void testDeleteListOfUser() {
		List<User> users = new ArrayList<User>();
		userManager.delete(users);
		Mockito.verify(userDaoMock).delete(users);
	}

}
