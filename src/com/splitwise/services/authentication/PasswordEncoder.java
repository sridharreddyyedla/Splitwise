package com.splitwise.services.authentication;

public interface PasswordEncoder {
	public String encode(String username, String password);
}
