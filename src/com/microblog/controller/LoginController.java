package com.microblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	public final static String LOGIN_PAGE = "login";
	public final static String LOGIN = "login";	
	
	@RequestMapping(value = "/*")	
	public String rootPage() {
		return LOGIN_PAGE;
	}		
	
	@RequestMapping(value = "login")	
	public String loginPage() {
		return LOGIN_PAGE;
	}		
	
}



