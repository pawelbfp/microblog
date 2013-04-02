package com.microblog.dao.hibernate;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.microblog.core.Post;
import com.microblog.dao.PostDao;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class PostHibernateTest {

	private static int idPost_init ;
	private static int idUser_1 = 1;
	private static String postBody = "";
	private static Date postDate = new Date();

	@Autowired
	protected PostDao hbPost;

	@Before
	public void Init() {
		Post post = new Post(idUser_1, postBody, postDate);
		hbPost.saveOrUpdate(post);
		idPost_init  = post.getIdPost();	
	}

	@Test
	public void getPostByIdCorreclty() throws Exception {
		Post post = hbPost.findPostById(idPost_init);
		assertThat(post).isNotNull();
		assertThat(post.getIdPost()).isEqualTo(idPost_init);

	}

	@Test
	public void savePostCorreclty() throws Exception {

		Post postExpected = new Post(1, "testBody", new Date());
		hbPost.saveOrUpdate(postExpected);
		Post postRead = hbPost.findPostById(postExpected.getIdPost());
		assertThat(postRead.getBody()).isEqualTo(postExpected.getBody());
		hbPost.delete(postExpected);
	}

	@Test
	public void deleteUserCorreclty() throws Exception {

		Post post1 = new Post(1, "testBody", new Date());
		hbPost.saveOrUpdate(post1);
		Post post2 = hbPost.findPostById(post1.getIdPost());
		assertThat(post2.getBody()).isEqualTo(post1.getBody());
		hbPost.delete(post1);
		Post post3 = hbPost.findPostById(post1.getIdPost());
		assertThat(post3).isNull();
	}

}
