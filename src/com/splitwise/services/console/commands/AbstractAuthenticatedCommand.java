package com.splitwise.services.console.commands;

import java.util.List;

import com.splitwise.services.authentication.AuthenticationContext;

public abstract class AbstractAuthenticatedCommand implements IAuthenticatedCommand {
	
	AuthenticationContext authenticationContext;

	@Override
	public void setAuthenticationContext(AuthenticationContext authenticationContext) {
		this.authenticationContext = authenticationContext;
	}

}
