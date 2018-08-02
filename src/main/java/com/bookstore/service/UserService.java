package com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.User;
import com.bookstore.exception.UserNotFoundException;
import com.bookstore.repository.UserRepository;

@Service
public class UserService{

	@Autowired
	private UserRepository userRepo;
	
	public User getUser(Long id) {
		return userRepo.findById(id).orElseThrow(UserNotFoundException::new);
	}
	
//	public User login(User user) {
//		return user;
//	}
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User userInService = userRepo.findByName(username).orElseThrow(UserNotFoundException::new);
//		return new UserDetailsImpl(userInService);
//	}

}
