package com.splitwise.services.console.handlers;

import java.util.ArrayList;
import java.util.List;

import com.splitwise.exceptions.console.UnsupportedCommandException;
import com.splitwise.services.console.commands.ICommand;

public class CommandHandler implements ICommandHandler {

	List<ICommand> commands = new ArrayList<ICommand>();
	
	public void register(ICommand command) {
		commands.add(command);
	}
	
	public ICommand getCommand(List<String> input) {
		for(ICommand command: commands) {
			if(command.isMatch(input)) {
				return command;
			}
		}
		throw new UnsupportedCommandException("Provided command is not supported");
	}

	@Override
	public void execute(List<String> input) {
		getCommand(input).execute(input);
	}
	
	public boolean matches(List<String> input) {
		return commands.stream().anyMatch( c -> c.isMatch(input));
	}

}
