package com.splitwise.controllers;

import org.springframework.stereotype.Service;

import com.splitwise.dtos.UserDTO;
import com.splitwise.entities.User;
import com.splitwise.exceptions.notfound.UserNotFoundException;
import com.splitwise.exceptions.validation.DuplicateUsernameException;
import com.splitwise.repositories.UserRepository;
import com.splitwise.services.authentication.AuthenticationContext;
import com.splitwise.services.authentication.PasswordEncoder;

@Service
public class UserController {
	final UserRepository userRepository;
	final PasswordEncoder passwordEncoder;
	
	public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User register(UserDTO details) {
		User user = new User();
		
		if(userRepository.findByUsername(details.username).isPresent()) {
			throw new DuplicateUsernameException("Username already exists");
		}
		
		user.setUsername(details.username);
		user.setFullname(details.fullname);
		user.setPhoneNo(details.phoneNo);
		user.setHashedSaltedPassword(passwordEncoder.encode(details.username, details.password));
		
		userRepository.save(user);
		return user;
	}
	
	public Long login(String username, String password) {
		String hashedPassword = passwordEncoder.encode(username, password);
		User user = userRepository.findByUsernameAndHashedSaltedPassword(username, hashedPassword);
		if(user==null) throw new UserNotFoundException("Username or password is invalid");
		return user.getId();
	}
	
	public User updatePassword(AuthenticationContext authContext, String password) {
		User user = authContext.getCurrentlyLoggedInUserOrElseThrow();
		user.setHashedSaltedPassword(passwordEncoder.encode(user.getUsername(), password));
		userRepository.save(user);
		return user;
	}
	
}
