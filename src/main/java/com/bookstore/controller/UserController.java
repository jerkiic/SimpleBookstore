package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.User;
import com.bookstore.service.UserService;

@RestController
@RequestMapping("/login")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private User user=new User();
	
//	private Set<Role> role= new HashSet<Role>();
	
	@RequestMapping(value="/user/{id}")
	public User loginAsUser(@PathVariable Long id) {
		user = userService.getUser(id);
		return userService.getUser(id);	
	}
	
	@RequestMapping(value="/admin/{id}")
	public User loginAsAdmin(@PathVariable Long id) {
		user = userService.getUser(id);
		return userService.getUser(id);	
	}
	
	@Bean
	@RequestMapping(value="/token")
	public User getToken(){
		return user;
	}
	
}
