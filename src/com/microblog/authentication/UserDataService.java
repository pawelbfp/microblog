package com.microblog.authentication;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.microblog.core.User;
import com.microblog.dao.UserDao;

public class UserDataService implements UserDetailsService  {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}		
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User for username " + username + "was not found.");
		}
	 	    
	    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
		
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, authorities);
	}

}
