package com.splitwise.services.console.commands;

import java.util.List;

import org.springframework.stereotype.Service;

import com.splitwise.controllers.UserController;
import com.splitwise.dtos.UserDTO;
import com.splitwise.entities.User;
import com.splitwise.services.console.ConsoleConfiguration;

@Service
public class RegisterCommand implements ICommand {
	
	final UserController userController;
	
	public RegisterCommand(UserController userController) {
		this.userController = userController;
	}

	public boolean isMatch(List<String> input) {
		return (input.size()>=5 && input.get(0).equalsIgnoreCase(ConsoleConfiguration.REGISTER));
	}
	
	@Override
	public void execute(List<String> input) {
		UserDTO details = new UserDTO();
		details.username = input.get(1);
		details.fullname = input.get(2);
		details.phoneNo = input.get(3);
		details.password = input.get(4);
		User user = userController.register(details);
		System.out.println("Created user: "+user.toString());
	}
	
}
