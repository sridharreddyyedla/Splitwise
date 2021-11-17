package com.splitwise.services.console.commands;

import java.util.List;

import org.springframework.stereotype.Service;

import com.splitwise.controllers.UserController;
import com.splitwise.exceptions.authentication.NotLoggedInException;
import com.splitwise.services.console.ConsoleConfiguration;

@Service
public class LogoutCommand implements ICommand {
	UserController userController;
	AuthenticatedCommandHandler authCommandHandler;
	public LogoutCommand(AuthenticatedCommandHandler authCommandHandler, UserController userController) {
		this.authCommandHandler = authCommandHandler;
		this.userController = userController;
	}

	@Override
	public boolean isMatch(List<String> input) {
		return input.size()==1 && input.get(0).equalsIgnoreCase(ConsoleConfiguration.LOGOUT);
	}

	@Override
	public void execute(List<String> input) {
		if(authCommandHandler.authContext.getUserId()!=-1) {
			authCommandHandler.authContext.setUserId(-1L);
			System.out.println("User has been logged out");
		}else {
			throw new NotLoggedInException("No user is logged in");
		}
	}

}
