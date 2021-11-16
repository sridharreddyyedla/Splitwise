package com.splitwise.services.console.commands;

import java.util.List;

import org.springframework.stereotype.Service;

import com.splitwise.services.console.ConsoleConfiguration;

@Service
public class ExitCommand implements ICommand {

	@Override
	public boolean isMatch(List<String> input) {
		return input.size()>=1 && input.get(0).equalsIgnoreCase(ConsoleConfiguration.EXIT);
	}

	@Override
	public void execute(List<String> input) {
		System.exit(0);
	}

}
