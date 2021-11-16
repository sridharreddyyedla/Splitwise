package com.splitwise.services.console.commands;

import java.util.List;

public interface ICommand {
	public boolean isMatch(List<String> input);
	public void execute(List<String> input);
}
