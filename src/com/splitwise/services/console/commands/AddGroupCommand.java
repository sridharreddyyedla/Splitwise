package com.splitwise.services.console.commands;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.splitwise.controllers.GroupController;
import com.splitwise.entities.Group;
import com.splitwise.entities.User;
import com.splitwise.exceptions.notfound.UserNotFoundException;
import com.splitwise.services.authentication.AuthenticationContext;
import com.splitwise.services.authentication.ConsoleAuthenticationContext;
import com.splitwise.services.console.ConsoleConfiguration;

@Service
public class AddGroupCommand implements ICommand {
	
	final ConsoleAuthenticationContext authenticationContext;
	final GroupController groupController;
	
	public AddGroupCommand(ConsoleAuthenticationContext authenticationContext, GroupController groupController) {
		this.authenticationContext = authenticationContext;
		this.groupController = groupController;
	}
	
	@Override
	public boolean isMatch(List<String> input) {
		return input.size()>=3 && input.get(1).equalsIgnoreCase(ConsoleConfiguration.ADD_GROUP);
	}

	@Override
	public void execute(List<String> input) {
		String groupName = input.get(2);
		List<Long> participantIds = new ArrayList<>();
		for(int i=3;i<input.size();i++) {
			if(input.get(i).charAt(0)!='u') throw new UserNotFoundException("User "+ input.get(i) +" not found");
			try {
				participantIds.add(Long.parseLong(input.get(i).substring(1)));
			}catch(NumberFormatException e) {
				throw new UserNotFoundException("User "+ input.get(i) +" not found");
			}			
		}
		Group group = groupController.addGroup(authenticationContext, groupName, participantIds);
		System.out.println("Created group: "+group.toString());
	}

}
