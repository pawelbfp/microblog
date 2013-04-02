package com.microblog.dao.hibernate;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.microblog.core.User;
import com.microblog.dao.UserDao;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserHibernateTest {

	@Autowired
	protected UserDao hbUser;

	@Test
	public void getUserByIdCorreclty() throws Exception {

		assertThat(hbUser).isNotNull();
		User user = hbUser.findById(1);
		assertThat(user).isNotNull();
		assertThat(user.getIdUser()).isEqualTo(1);

	}

	@Test
	public void saveUserCorreclty() throws Exception {
	
			assertThat(hbUser).isNotNull();
			User user1 = new User(5,"testName","testPwd");
			hbUser.saveOrUpdate(user1);
			User user2 = hbUser.findById(user1.getIdUser());
			assertThat(user2.getUsername()).isEqualTo(user2.getUsername());
			hbUser.delete(user1);
	}
	
	
	@Test
	public void deleteUserCorreclty() throws Exception {
	
			assertThat(hbUser).isNotNull();
			User user1 = new User(5,"testName","testPwd");
			hbUser.saveOrUpdate(user1);
			User user2 = hbUser.findById(user1.getIdUser());
			assertThat(user2.getUsername()).isEqualTo(user2.getUsername());
			hbUser.delete(user1);
			User user3 = hbUser.findById(user1.getIdUser());
			assertThat(user3).isNull();
	}	

}
