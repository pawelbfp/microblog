package com.microblog.business;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.microblog.core.Post;
import com.microblog.core.Subscription;
import com.microblog.core.User;
import com.microblog.dao.PostDao;
import com.microblog.dao.SubscriptionDao;
import com.microblog.dao.UserDao;



public class PostManagerTest {

	private static int idPost = 1;
	private static int idUser = 1;
	private static String postBody = "";
	private static Date  postDate = new Date();
	private static String userName = "name";
	
	private PostManager postManager;
	private IMocksControl mockMaker;
	private PostDao postDaoMock;
	private UserDao userDaoMock; 
	private SubscriptionDao subscriptionDaoMock; 
	
	private PostManager postManagerM;
	private PostDao postDaoMockM;
	private UserDao userDaoMockM; 
	private SubscriptionDao subscriptionDaoMockM; 
	
	
	@Before
	public void Init()
	{
		postManager = new PostManager();
		mockMaker = EasyMock.createStrictControl();
		userDaoMock =mockMaker.createMock(UserDao.class);
		postManager.setUserDao(userDaoMock);
		postDaoMock =mockMaker.createMock(PostDao.class);
		postManager.setPostDao(postDaoMock);
		subscriptionDaoMock =mockMaker.createMock(SubscriptionDao.class);
		postManager.setSubscriptionDao(subscriptionDaoMock);
		
	
		postManagerM = new PostManager();
		userDaoMockM = Mockito.mock(UserDao.class);
		postManagerM.setUserDao(userDaoMockM);
		subscriptionDaoMockM = Mockito.mock(SubscriptionDao.class);
		postManagerM.setSubscriptionDao(subscriptionDaoMockM);		
		postDaoMockM =Mockito.mock(PostDao.class);
		postManagerM.setPostDao(postDaoMockM);

	}

//	@Test
//	public void testFindPostById() {
//		
////		EasyMock.expect(postDaoMock.findPostById(idPost)).andReturn(new Post());
////		EasyMock.replay(postDaoMock);
////		postManager.findPostByID(idPost);
////		EasyMock.verify(postDaoMock);
//	}
	
	@Test
	public void testFindPostByIdMockito() {


		Post post = new Post(idUser,postBody,postDate);
		Mockito.when(postDaoMockM.findPostById(idPost)).thenReturn(post);
		User user = new User(idUser,userName,userName);
		Mockito.when(userDaoMockM.findById(idUser)).thenReturn(user);
		Post postRet = postManagerM.findPostByID(idPost);
		
		assertThat(postRet.getIdUser()).isEqualTo(idUser);
	}
	
	@Test
	public void testFindPostByIdMockito2() {


		Post post = new Post(idUser,postBody,postDate);
		Mockito.when(postDaoMockM.findPostById(idPost)).thenReturn(post);
		User user = new User(idUser,userName,userName);
		Mockito.when(userDaoMockM.findById(idUser)).thenReturn(user);
		Post postRet = postManagerM.findPostByID(idPost);
		
		assertThat(postRet.getIdUser()).isEqualTo(idUser);
	}
	
	@Test
	public void testFindPostByIdMockito3() {


		Post post = new Post(idUser,postBody,postDate);
		Mockito.when(postDaoMockM.findPostById(idPost)).thenReturn(post);
		User user = new User(idUser,userName,userName);
		Mockito.when(userDaoMockM.findById(idUser)).thenReturn(user);
		Post postRet = postManagerM.findPostByID(idPost);
		
		assertThat(postRet.getIdUser()).isEqualTo(idUser);
	}			

	@Test
	public void testFindPostsByUser() {
		Post post = new Post(idUser,postBody,postDate);
		List<Post> postList = new ArrayList<Post>();
		postList.add(post);
		User user = new User(idUser,userName,userName);
		
		
		Mockito.when(postDaoMockM.findPostsByUser(idUser)).thenReturn(postList);
		Mockito.when(userDaoMockM.findById(idUser)).thenReturn(user);
		List<Post> resPostList = postManagerM.findPostsByUser(idPost);
		
		assertThat(resPostList).containsExactly(post);
		
	}

	@Test
	public void testFindSubscribedPostsByUser() {

		String subscriberName = "subscriberName";
		String subscribedName = "subscribedName";
		
		Post post = new Post(idUser,postBody,postDate);
		List<Post> postList = new ArrayList<Post>();
		postList.add(post);

		List<Subscription> subscriptionList = new ArrayList<Subscription>();
		subscriptionList.add(new Subscription(idUser,idUser));
		
		
		User userSubscriber = new User(idUser,subscriberName,userName);
		User userSubscribed = new User(idUser,subscribedName,userName);
		
		Mockito.when(userDaoMockM.findByName(subscriberName)).thenReturn(userSubscriber);
		Mockito.when(userDaoMockM.findByName(subscribedName)).thenReturn(userSubscribed);
		Mockito.when(postDaoMockM.findPostsByUser(idUser)).thenReturn(postList);
		Mockito.when(userDaoMockM.findById(idUser)).thenReturn(userSubscriber);
		
		List<Post> resPostList = postManagerM.findSubscribedPostsByUser(subscriberName, subscribedName);
		
		assertThat(resPostList).containsExactly(post);
	}

	@Test
	public void testFindAllSubscribedPostsByUser_correctData() {

		String subscriberName = "subscriberName";
		String subscribedName = "subscribedName";
		
		Post post = new Post(idUser,postBody,postDate);
		List<Post> postList = new ArrayList<Post>();
		postList.add(post);
		
		User userSubscriber = new User(idUser,subscriberName,userName);
		User userSubscribed = new User(idUser,subscribedName,userName);
		
		Mockito.when(userDaoMockM.findByName(subscriberName)).thenReturn(userSubscriber);
		Mockito.when(userDaoMockM.findByName(subscribedName)).thenReturn(userSubscribed);
		Mockito.when(postDaoMockM.findPostsByUser(idUser)).thenReturn(postList);
		Mockito.when(userDaoMockM.findById(idUser)).thenReturn(userSubscriber);
		
		List<Post> resPostList = postManagerM.findSubscribedPostsByUser(subscriberName, subscribedName);
		
		//Mockito.verify(subscriptionDaoMockM).findSubscribedByUser(idUser);
		
		assertThat(resPostList).containsExactly(post);
	}

	@Test
	public void testFindAllSubscribedPostsByUser_noSubscriber() {

		String subscriberName = "subscriberName";
		String subscribedName = "subscribedName";
		
		Mockito.when(userDaoMockM.findByName(subscriberName)).thenReturn(null);
		List<Post> resPostList = postManagerM.findSubscribedPostsByUser(subscriberName, subscribedName);
		
		assertThat(resPostList).isEmpty();
	}

	@Test
	public void testFindAllSubscribedPostsByUser_noSubscribed() {

		String subscriberName = "subscriberName";
		String subscribedName = "subscribedName";

		Post post = new Post(idUser,postBody,postDate);
		List<Post> postList = new ArrayList<Post>();
		postList.add(post);
		
		List<Subscription> subscriptionList = new ArrayList<Subscription>();
		subscriptionList.add(new Subscription(idUser,idUser));
		
		User userSubscriber = new User(idUser,subscriberName,userName);
		
		Mockito.when(userDaoMockM.findByName(subscriberName)).thenReturn(userSubscriber);
		Mockito.when(userDaoMockM.findByName(subscribedName)).thenReturn(null);
		Mockito.when(subscriptionDaoMockM.findSubscribedByUser(idUser)).thenReturn(subscriptionList);
		Mockito.when(postDaoMockM.findPostsByUser(idUser)).thenReturn(postList);
		Mockito.when(userDaoMockM.findById(idUser)).thenReturn(userSubscriber);
		
		List<Post> resPostList = postManagerM.findSubscribedPostsByUser(subscriberName, subscribedName);
		
		assertThat(resPostList).containsExactly(post);
	}	
	
	
	@Test
	public void testSave() {
		
		Post post = new Post();
		
		postDaoMock.saveOrUpdate(post);
		EasyMock.replay(postDaoMock);
		postManager.save(post);
		EasyMock.verify(postDaoMock);		
	}

	@Test
	public void testSaveOrUpdatePost() {
		Post post = new Post();
		
		postDaoMock.saveOrUpdate(post);
		EasyMock.replay(postDaoMock);
		postManager.saveOrUpdate(post);
		EasyMock.verify(postDaoMock);

	}

	@Test
	public void testSaveOrUpdateListOfPost() {
		List<Post> postList = new ArrayList<Post>();
		
		postDaoMock.saveOrUpdate(postList);
		EasyMock.replay(postDaoMock);
		postManager.saveOrUpdate(postList);
		EasyMock.verify(postDaoMock);
	}

	@Test
	public void testDeletePost() {
		
		Post post = new Post();
		
		postDaoMock.delete(post);
		EasyMock.replay(postDaoMock);
		postManager.delete(post);
		EasyMock.verify(postDaoMock);

	}

	@Test
	public void testDeleteInt() {

		
		postDaoMock.delete(idPost);
		EasyMock.replay(postDaoMock);
		postManager.delete(idPost);
		EasyMock.verify(postDaoMock);

	}

	@Test
	public void testDeleteListOfPost() {
		List<Post> postList = new ArrayList<Post>();
		
		postDaoMock.delete(postList);
		EasyMock.replay(postDaoMock);
		postManager.delete(postList);
		EasyMock.verify(postDaoMock);

	}

}
