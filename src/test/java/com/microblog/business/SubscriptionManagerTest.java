package com.microblog.business;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.microblog.core.Subscription;
import com.microblog.core.User;
import com.microblog.core.web.SubscriptionJson;
import com.microblog.dao.SubscriptionDao;
import com.microblog.dao.UserDao;

public class SubscriptionManagerTest {

	private static int idUser = 1;
	private static int idSubscriber = 1;
	private static int idSubscribed = 2;
	private static int idSubscribedOther = 3;
	private static String userName = "name";
	
	private SubscriptionManager subscriptionManager;
	private UserDao userDaoMock;
	private SubscriptionDao subscriptionDaoMock;
	
	@Before
	public void Init()
	{
		subscriptionManager = new SubscriptionManager();
		userDaoMock = Mockito.mock(UserDao.class);
		subscriptionManager.setUserDao(userDaoMock);
		subscriptionDaoMock = Mockito.mock(SubscriptionDao.class);
		subscriptionManager.setSubscriptionDao(subscriptionDaoMock);
		
	}		
	
	@Test
	public final void testFindSubscribedByUserId() {
		List<Subscription> subscriptionList = new ArrayList<Subscription>();
		Subscription subscription = new Subscription(idSubscriber,idSubscribed);
		subscriptionList.add(subscription);
		
		User user = new User(idSubscribed,userName,userName);
		
		Mockito.when(subscriptionDaoMock.findSubscribedByUser(idSubscriber)).thenReturn(subscriptionList);
		Mockito.when(userDaoMock.findById(idSubscribed)).thenReturn(user);
		
		SubscriptionJson expected = new SubscriptionJson(idSubscribed,userName);		
		List<SubscriptionJson> resList; 
		resList = subscriptionManager.findSubscribedByUserId(idSubscriber);
		
		assertThat(resList).hasSize(1);
		
		SubscriptionJson resSubscription = resList.get(0);  
		
		assertThat(expected.getIdUser()).isEqualTo(resSubscription.getIdUser());
		assertThat(expected.getUserName()).isEqualTo(resSubscription.getUserName());

	}

	@Test
	public final void testFindSubscribedByUserName() {
		List<Subscription> subscriptionList = new ArrayList<Subscription>();
		Subscription subscription = new Subscription(idSubscriber,idSubscribed);
		subscriptionList.add(subscription);
		
		User user = new User(idSubscriber,userName,userName);
		User userSubscribed = new User(idSubscribed,userName,userName);
		
		Mockito.when(userDaoMock.findByName(userName)).thenReturn(user);
		Mockito.when(subscriptionDaoMock.findSubscribedByUser(idSubscriber)).thenReturn(subscriptionList);
		Mockito.when(userDaoMock.findById(idSubscribed)).thenReturn(userSubscribed);

		SubscriptionJson expected = new SubscriptionJson(idSubscribed,userName);		
		List<SubscriptionJson> resList; 
		resList = subscriptionManager.findSubscribedByUserId(idSubscriber);
		
		assertThat(resList).hasSize(1);
		
		SubscriptionJson resSubscription = resList.get(0);  
		
		assertThat(expected.getIdUser()).isEqualTo(resSubscription.getIdUser());
		assertThat(expected.getUserName()).isEqualTo(resSubscription.getUserName());
	}

	@Test
	public final void testFindAvailableByUserId() {
		
		User user1 = new User(idSubscribed,userName,userName);
		User user2 = new User(idSubscribedOther,userName,userName);
		List<User> userList = new ArrayList<User>() ;
		userList.add(user1);
		userList.add(user2);

		List<Subscription> subscriptionList = new ArrayList<Subscription>();
		Subscription subscription = new Subscription(idSubscriber,idSubscribed);
		subscriptionList.add(subscription);
		
		
		
		Mockito.when(userDaoMock.findAll()).thenReturn(userList);
		Mockito.when(subscriptionDaoMock.findSubscribedByUser(idSubscriber)).thenReturn(subscriptionList);
		
		List<SubscriptionJson> resList; 
		resList = subscriptionManager.findAvailableByUserId(idSubscriber);

		
		SubscriptionJson expected = new SubscriptionJson(idSubscribedOther,userName);		
		SubscriptionJson resSubscription = resList.get(0);  
		
		assertThat(expected.getIdUser()).isEqualTo(resSubscription.getIdUser());
		assertThat(expected.getUserName()).isEqualTo(resSubscription.getUserName());
	}

	@Test
	public final void testFindAviableByUserName() {

		
		User user  = new User(idSubscriber,userName,userName);
		User user1 = new User(idSubscribed,userName,userName);
		User user2 = new User(idSubscribedOther,userName,userName);
		List<User> userList = new ArrayList<User>() ;
		userList.add(user1);
		userList.add(user2);

		List<Subscription> subscriptionList = new ArrayList<Subscription>();
		Subscription subscription = new Subscription(idSubscriber,idSubscribed);
		subscriptionList.add(subscription);
		
	
		Mockito.when(userDaoMock.findByName(userName)).thenReturn(user);
		Mockito.when(userDaoMock.findAll()).thenReturn(userList);
		Mockito.when(subscriptionDaoMock.findSubscribedByUser(idSubscriber)).thenReturn(subscriptionList);
		
		List<SubscriptionJson> resList; 
		resList = subscriptionManager.findAvailableByUserId(idSubscriber);

		
		SubscriptionJson expected = new SubscriptionJson(idSubscribedOther,userName);		
		SubscriptionJson resSubscription = resList.get(0);  
		
		assertThat(resList).onProperty("idUser").containsExactly(expected.getIdUser());
		
		assertThat(expected.getIdUser()).isEqualTo(resSubscription.getIdUser());
		assertThat(expected.getUserName()).isEqualTo(resSubscription.getUserName());		
	}

	@Test
	public final void testCheckSubscribed() {
		List<Subscription> subscriptionList = new ArrayList<Subscription>();
		Subscription subscription = new Subscription(idSubscriber,idSubscribed);
		subscriptionList.add(subscription);
		
		Boolean result; 
		
		result = subscriptionManager.checkSubscribed(subscriptionList, idSubscribed);
				
		assertThat(result).isTrue();
	}

	@Test
	public final void testSaveOrUpdateSubscription() {
		Subscription subscription = new Subscription();
		subscriptionManager.saveOrUpdate(subscription);
		Mockito.verify(subscriptionDaoMock).saveOrUpdate(subscription);
	}

	@SuppressWarnings("unchecked")
	@Test
	public final void testAddList() {
		List<SubscriptionJson> subscriptionsJson = new ArrayList<SubscriptionJson>();
		subscriptionManager.addList(idUser,subscriptionsJson);
		
		Mockito.verify(subscriptionDaoMock).saveOrUpdate(Mockito.anyList());

	}

	@Test
	public final void testSaveOrUpdateListOfSubscription() {
		List<Subscription> subscriptionList = new ArrayList<Subscription>();
		subscriptionManager.saveOrUpdate(subscriptionList);
		Mockito.verify(subscriptionDaoMock).saveOrUpdate(subscriptionList);
	}

	@Test
	public final void testDeleteSubscription() {
		Subscription subscription = new Subscription();
		subscriptionManager.delete(subscription);
		Mockito.verify(subscriptionDaoMock).delete(subscription);
	}

	@SuppressWarnings("unchecked")
	@Test
	public final void testDeleteList() {

		List<SubscriptionJson> subscriptionsJson = new ArrayList<SubscriptionJson>();
		subscriptionManager.deleteList(idUser,subscriptionsJson);
		
		Mockito.verify(subscriptionDaoMock).delete(Mockito.anyList());
	}

	@Test
	public final void testDeleteListOfSubscription() {
		List<Subscription> subscriptionList = new ArrayList<Subscription>();
		subscriptionManager.delete(subscriptionList);
		Mockito.verify(subscriptionDaoMock).delete(subscriptionList);
	}

}
