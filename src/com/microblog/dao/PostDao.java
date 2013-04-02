package com.microblog.dao;

import java.util.List;

import com.microblog.core.Post;

public interface PostDao {

	public Post findPostById(int idPost);

	public List<Post> findPostsByUser(int idUser);

	public void saveOrUpdate(Post post);

	public void saveOrUpdate(List<Post> posts);

	public void delete(Post post);
	
	public void delete(int idPost);

	public void delete(List<Post> posts);
}
