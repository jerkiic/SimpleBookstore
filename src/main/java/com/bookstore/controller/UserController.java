package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
	
	@RequestMapping(value="/user")
	public User loginAsUser() {
		user = userService.findUser();
		return userService.findUser();	
	}
	
	@RequestMapping(value="/admin")
	public User loginAsAdmin() {
		user = userService.findAdmin();
		return userService.findAdmin();	
	}
	
	@Bean
	@RequestMapping(value="/token")
	public User getToken(){
		return user;
	}
}
