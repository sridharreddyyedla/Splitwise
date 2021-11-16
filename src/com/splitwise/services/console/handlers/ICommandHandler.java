package com.splitwise.services.console.handlers;

import java.util.List;

import com.splitwise.services.console.commands.ICommand;

public interface ICommandHandler {
	public void register(ICommand command);
	public void execute(List<String> input);
}
