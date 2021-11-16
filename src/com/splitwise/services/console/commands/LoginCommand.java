package com.splitwise.services.console.commands;

import java.util.List;

import org.springframework.stereotype.Service;

import com.splitwise.controllers.UserController;
import com.splitwise.exceptions.notfound.UserNotFoundException;
import com.splitwise.services.authentication.ConsoleAuthenticationContext;
import com.splitwise.services.console.ConsoleConfiguration;

@Service
public class LoginCommand implements ICommand{

	AuthenticatedCommandHandler authCommandHandler;
	UserController userController;
	
	public LoginCommand(AuthenticatedCommandHandler authenticatedCommandHandler, UserController userController) {
		this.authCommandHandler = authenticatedCommandHandler;
		this.userController = userController;
	}

	@Override
	public boolean isMatch(List<String> input) {
		return input.size()==3 && input.get(0).equalsIgnoreCase(ConsoleConfiguration.LOGIN);
	}

	@Override
	public void execute(List<String> input) {
		try {
			Long userId = userController.login(input.get(1), input.get(2));			
			if(userId!=-1) {
				authCommandHandler.authContext.setUserId(userId);
				System.out.println("User "+ userId +" logged in");
			}
		}catch(UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
