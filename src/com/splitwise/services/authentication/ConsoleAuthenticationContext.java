package com.splitwise.services.authentication;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.splitwise.entities.User;
import com.splitwise.exceptions.authentication.InvalidUserException;
import com.splitwise.exceptions.authentication.NotLoggedInException;
import com.splitwise.exceptions.notfound.UserNotFoundException;
import com.splitwise.repositories.UserRepository;

import lombok.Getter;
import lombok.Setter;

@Service
@Setter
@Getter
public class ConsoleAuthenticationContext implements AuthenticationContext {

	UserRepository userRepository;
	Long userId = -1L;
	
	public  ConsoleAuthenticationContext(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public Optional<User> getCurrentlyLoggedInUser() {
		return userRepository.findById(userId);
	}

	@Override
	public User getCurrentlyLoggedInUserOrElseThrow() {
		return getCurrentlyLoggedInUser().orElseThrow( () -> new NotLoggedInException("User is not logged in!"));
	}

	@Override
	public boolean isAuthenticated() {
		return getCurrentlyLoggedInUser().isPresent();
	}
	
	public boolean isCurrentlyLoggedInUser(Long userId) {
		if(!isAuthenticated()) throw new NotLoggedInException("User is not logged in!");
		if(this.userId!=userId) throw new InvalidUserException("Different user is logged in");
		return true;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
