package com.splitwise.services.authentication;

import java.util.Optional;

import com.splitwise.entities.User;

public interface AuthenticationContext {
	Optional<User> getCurrentlyLoggedInUser();
	User getCurrentlyLoggedInUserOrElseThrow();
	boolean isAuthenticated();
	void setUserId(Long userId);
}
