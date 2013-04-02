package com.microblog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SubscribedController {
	
	@RequestMapping("/subscribed")
	public String printHello(ModelMap model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("userName",auth.getName());
		return "subscribed";
	}
	
	@RequestMapping("/subscribed/{username}")
	public String printHello2(@PathVariable String username, ModelMap model){
		model.addAttribute("userName",username);
		return "subscribed";
	}	

}
