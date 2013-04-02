package com.microblog.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microblog.business.PostManager;
import com.microblog.business.UserManager;
import com.microblog.core.Post;
import com.microblog.core.PostJson;

@Controller
public class PostViewer {

	@Autowired
	UserManager userManager;

	@Autowired
	PostManager postManager;
	
	@RequestMapping("/userpage/postViewer")
	public String userPosts(){
		return "postViewer";
	}
	
	@RequestMapping("/subscribed/postViewer")
	public String subscribedPosts(){
		return "postViewer";
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "posts/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostJson>> getUserPosts(@PathVariable String userName) {

		List<PostJson> postsJson = createJsonPostList(postManager.findPostsByUser(userManager.findByName(userName).getIdUser()));

		return new ResponseEntity<List<PostJson>>(postsJson, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "posts/{userName}/{subscribedUserName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostJson>> getSubcribedPosts(@PathVariable String userName, @PathVariable String subscribedUserName) {

		List<PostJson> posts = createJsonPostList(postManager.findSubscribedPostsByUser(userName, subscribedUserName));

		return new ResponseEntity<List<PostJson>>(posts, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "subscribedUsernames/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getSubscribedUserNames(@PathVariable String userName) {

		return new ResponseEntity<List<String>>(userManager.getSubscribedUserNames(userName), HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="posts/{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable Integer postId) {
		
		postManager.delete(postId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	/**
	 * @param 
	 * @return
	 */
	private List<PostJson> createJsonPostList(List<Post> postList) {
		List<PostJson> posts = new ArrayList<PostJson>();

		for (Post post : postList) {
			PostJson postJson = new PostJson();
			postJson.setIdPost(post.getIdPost());
			postJson.setUserName(post.getUserName());
			postJson.setBody(post.getBody());
			DateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			postJson.setDate(dateFormatter.format(post.getDate()));

			posts.add(postJson);
		}
		return posts;
	}

}
