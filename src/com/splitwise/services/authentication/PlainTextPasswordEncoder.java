package com.splitwise.services.authentication;

import org.springframework.stereotype.Service;

@Service
public class PlainTextPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(String username, String password) {
		return "encoded"+password;
	}

}
