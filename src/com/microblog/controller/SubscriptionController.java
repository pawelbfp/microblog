package com.microblog.controller;

import java.util.List;

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

import com.microblog.business.SubscriptionManager;
import com.microblog.business.UserManager;
import com.microblog.core.User;
import com.microblog.core.web.SubscriptionJson;
import com.microblog.core.web.SubscriptionJsonList;

@Controller
public class SubscriptionController {

	@Autowired
	UserManager userManager;

	@Autowired
	SubscriptionManager subscriptionManager;

	@RequestMapping("/subscription/{username}")
	public String subscribtionUser(@PathVariable String username, ModelMap model) {
		model.addAttribute("userName", username);
		return "subscription";
	}

	@RequestMapping(method = RequestMethod.GET, value = "subscrubedUserNames/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubscriptionJson>> getSubscribedUserNames(@PathVariable String userName) {

		List<SubscriptionJson> subscriptionJson = subscriptionManager.findSubscribedByUserName(userName);
		return new ResponseEntity<List<SubscriptionJson>>(subscriptionJson, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "availableUserNames/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubscriptionJson>> getAvailableUserNames(@PathVariable String userName) {

		List<SubscriptionJson> subscriptionJson = subscriptionManager.findAviableByUserName(userName);
		return new ResponseEntity<List<SubscriptionJson>>(subscriptionJson, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/unsubscribe", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteSubscriptions(@RequestBody SubscriptionJsonList removedList) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			User user = userManager.findByName(auth.getName());
			if (user != null)
				subscriptionManager.deleteList(user.getIdUser(), removedList);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addsubscription", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addPost(@RequestBody SubscriptionJsonList subscribedList) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			User user = userManager.findByName(auth.getName());
			if (user != null)
				subscriptionManager.addList(user.getIdUser(), subscribedList);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
