package com.splitwise.services.console.commands;

import java.util.List;

import org.springframework.stereotype.Service;

import com.splitwise.exceptions.authentication.InvalidUserException;
import com.splitwise.exceptions.authentication.NotLoggedInException;
import com.splitwise.exceptions.notfound.UserNotFoundException;
import com.splitwise.services.authentication.ConsoleAuthenticationContext;
import com.splitwise.services.console.handlers.CommandHandler;

@Service
public class AuthenticatedCommandHandler extends CommandHandler implements ICommand {
	
	ConsoleAuthenticationContext authContext;

	public AuthenticatedCommandHandler(ConsoleAuthenticationContext authenticationContext) {
		this.authContext = authenticationContext;
	}

	@Override 
	public boolean isMatch(List<String> input) {
		System.out.println("Matching: ");
		if(input.size()<1) return false;
		try {
			String firstInput = input.get(0);
			System.out.println(firstInput);
			if(firstInput.charAt(0)!='u') return false;
			Long.parseLong(firstInput.substring(1));
		}catch(NumberFormatException e) {
			return false;
		}
		return super.matches(input);
	}
	
	@Override
	public void execute(List<String> input) {
		String userIdString = input.get(0);
		long userId = Long.parseLong(userIdString.substring(1));
		if(authContext.isCurrentlyLoggedInUser(userId)) {
			ICommand command = getCommand(input);
			command.execute(input);
		}
	}

}
