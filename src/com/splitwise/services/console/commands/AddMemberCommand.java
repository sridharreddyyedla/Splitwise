package com.splitwise.services.console.commands;

import java.util.List;

import org.springframework.stereotype.Service;

import com.splitwise.controllers.GroupController;
import com.splitwise.exceptions.notfound.GroupNotFoundException;
import com.splitwise.exceptions.notfound.UserNotFoundException;
import com.splitwise.services.authentication.ConsoleAuthenticationContext;
import com.splitwise.services.console.ConsoleConfiguration;

@Service
public class AddMemberCommand implements ICommand {
	
	final GroupController groupController;
	final ConsoleAuthenticationContext authContext;
	
	public AddMemberCommand(GroupController groupController, ConsoleAuthenticationContext authContext) {
		this.groupController = groupController;
		this.authContext = authContext;
	}

	@Override
	public boolean isMatch(List<String> input) {
		return input.size()==4 && input.get(1).equalsIgnoreCase(ConsoleConfiguration.ADD_MEMBER);
	}

	@Override
	public void execute(List<String> input) {
		String groupIdString = input.get(2);
		String userIdString = input.get(3);
		Long groupId = -1L;
		Long userId = -1L;
		if(groupIdString.charAt(0)!='g') throw new GroupNotFoundException("Group "+groupIdString+ " is invalid");
		if(userIdString.charAt(0)!='u') throw new UserNotFoundException("User "+userIdString+ " is invalid");
		try {
			groupId = Long.parseLong(groupIdString.substring(1));
		}catch(NumberFormatException e) {
			throw new GroupNotFoundException("Group "+groupIdString+ " is invalid");
		}
		try {
			userId = Long.parseLong(userIdString.substring(1));
		}catch(NumberFormatException e) {
			throw new UserNotFoundException("User "+userIdString+ " is invalid");
		}
		groupController.addMember(authContext, groupId, userId);
		System.out.println("User "+ userId+" has been added to "+ groupId);
	}

}
