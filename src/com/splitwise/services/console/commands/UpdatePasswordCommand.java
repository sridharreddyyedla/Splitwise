package com.splitwise.services.console.commands;

import java.util.List;

import org.springframework.stereotype.Service;

import com.splitwise.controllers.UserController;
import com.splitwise.entities.User;
import com.splitwise.services.authentication.ConsoleAuthenticationContext;
import com.splitwise.services.console.ConsoleConfiguration;

@Service
public class UpdatePasswordCommand implements ICommand {
	
	final UserController userController;
	final ConsoleAuthenticationContext authContext;
	
	public UpdatePasswordCommand(UserController userController, ConsoleAuthenticationContext authContext) {
		this.userController = userController;
		this.authContext = authContext;
	}

	@Override
	public boolean isMatch(List<String> input) {
		return (input.size()==3 && input.get(1).equalsIgnoreCase(ConsoleConfiguration.UPDATE_PASSWORD));
	}

	@Override
	public void execute(List<String> input) {
		User user = userController.updatePassword(authContext, input.get(2));
		System.out.println("Password has been updated for user "+input.get(0));
		System.out.println(user.toString());
	}

}
