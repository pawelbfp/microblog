package com.microblog.dblayer;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.microblog.core.User;
import com.microblog.dao.jdbc.UsersPostrgeJdbc;


public class PostgreJdbcUserTest {

	@Test
	public void getUsersCorreclty() throws Exception {
		// given
		UsersPostrgeJdbc users = new UsersPostrgeJdbc();

		// when
		List<User> eval = users.getUsers();

		// then
		assertThat(eval).isNotEqualTo(null);
	}	
}
