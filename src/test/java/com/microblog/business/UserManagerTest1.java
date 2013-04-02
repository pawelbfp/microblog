package com.microblog.business;


import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserManagerTest1 {
	
	@Autowired
	protected UserManager userManager;
	
	@Test
	public void updatePasswordCorrectly() throws Exception {

		assertThat(userManager).isNotNull();

		
		
		//userManager.encodePasswordsAllUsers();
		

	}	

}
