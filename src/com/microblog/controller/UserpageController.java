package com.microblog.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microblog.business.PostManager;
import com.microblog.business.UserManager;
import com.microblog.core.Post;
import com.microblog.core.User;

@Controller
public class UserpageController {

	public final static String USER_PAGE = "userpage";
	public final static String USER_NAME = "username";

	@Autowired
	UserManager userManager;

	@Autowired
	PostManager postManager;

	@RequestMapping(value = "userpage/{username}")
	public String userpage(@PathVariable String username, ModelMap model, HttpServletRequest req) throws SQLException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			req.setAttribute("authUser", auth.getName());

			User user = userManager.findByName(username);
			if (user != null) {
				req.setAttribute(USER_NAME, user.getUsername());
				model.addAttribute("userName", user.getUsername());
			}
		}

		return USER_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST, value = "posts/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addPost(@RequestBody String postBody) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			User user = userManager.findByName(auth.getName());
			if (user != null)
				postManager.save(new Post(user.getIdUser(), postBody,null));
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
