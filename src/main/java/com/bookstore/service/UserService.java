package com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.User;
import com.bookstore.exception.UserNotFoundException;
import com.bookstore.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public User findUser() {
		return userRepo.findByRole("user").orElseThrow(UserNotFoundException::new);
	}

	public User findAdmin() {
		return userRepo.findByRole("admin").orElseThrow(UserNotFoundException::new);
	}

}
