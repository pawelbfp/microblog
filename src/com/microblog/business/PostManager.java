package com.microblog.business;

import java.util.ArrayList;
import java.util.List;

import com.microblog.core.Post;
import com.microblog.core.Subscription;
import com.microblog.core.User;
import com.microblog.dao.PostDao;
import com.microblog.dao.SubscriptionDao;
import com.microblog.dao.UserDao;

public class PostManager {

	private PostDao postDao;
	private UserDao userDao;
	private SubscriptionDao subscriptionDao;

	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setSubscriptionDao(SubscriptionDao subscriptionDao) {
		this.subscriptionDao = subscriptionDao;
	}
	

	public Post findPostByID(int idPost) {
		Post post = postDao.findPostById(idPost);
		return setUsernameToPostOwner(post);
	}

	public List<Post> findPostsByUser(int idUser) {
		return setUsernamesToPostOwner(postDao.findPostsByUser(idUser));
	}

	/**
	 * @param subscriberName 
	 * @param subscribedName - when null function returns posts of all subscribed users, when not null only of given
	 * @return
	 */
	public List<Post> findSubscribedPostsByUser(String subscriberName, String subscribedName) {
		List<Subscription> subscriptionList = null;
		List<Post> postList = new ArrayList<Post>();

		User userSubscriber = userDao.findByName(subscriberName);

		if (userSubscriber != null) {
			
			User userSubscribed = userDao.findByName(subscribedName);

			if (userSubscribed != null) {
				subscriptionList = new ArrayList<Subscription>();
				subscriptionList.add(new Subscription(userSubscriber.getIdUser(), userSubscribed.getIdUser()));
			} else
				subscriptionList = subscriptionDao.findSubscribedByUser(userSubscriber.getIdUser());

			for (Subscription subscription : subscriptionList) {
				List<Post> posts = postDao.findPostsByUser(subscription.getSubscriptionPK().getIdSubscribed());
				postList.addAll(posts);
			}
		}
		return setUsernamesToPostOwner(postList);

	}

	public void save(Post post) {
		postDao.saveOrUpdate(post);
	}	
	
	public void saveOrUpdate(Post post) {
		postDao.saveOrUpdate(post);
	}

	public void saveOrUpdate(List<Post> posts) {
		postDao.saveOrUpdate(posts);
	}

	public void delete(Post post) {
		postDao.delete(post);
	}

	public void delete(int idPost){
		postDao.delete(idPost);
	}
	
	public void delete(List<Post> posts) {
		postDao.delete(posts);
	}

	private Post setUsernameToPostOwner(Post post) {
		User user = userDao.findById(post.getIdUser());
		post.setUserName(user.getUsername());
		return post;
	}

	private List<Post> setUsernamesToPostOwner(List<Post> posts) {
		for (Post post : posts)
			post = setUsernameToPostOwner(post);
		return posts;
	}

}
