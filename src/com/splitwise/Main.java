package com.splitwise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.splitwise.controllers.UserController;
import com.splitwise.dtos.UserDTO;
import com.splitwise.exceptions.SplitwiseException;
import com.splitwise.services.authentication.PlainTextPasswordEncoder;
import com.splitwise.services.console.commands.AddGroupCommand;
import com.splitwise.services.console.commands.AddMemberCommand;
import com.splitwise.services.console.commands.AuthenticatedCommandHandler;
import com.splitwise.services.console.commands.ExitCommand;
import com.splitwise.services.console.commands.LoginCommand;
import com.splitwise.services.console.commands.RegisterCommand;
import com.splitwise.services.console.commands.UpdatePasswordCommand;
import com.splitwise.services.console.handlers.CommandHandler;
import com.splitwise.services.console.handlers.ICommandHandler;

@SpringBootApplication
@EnableJpaAuditing
public class Main implements CommandLineRunner{
	final RegisterCommand registerCommand;
	final LoginCommand loginCommand;
	final UpdatePasswordCommand updatePasswordCommand;
	final AddGroupCommand addGroupCommand;
	final AddMemberCommand addMemberCommand;
	final ExitCommand exitCommand;
	final AuthenticatedCommandHandler authenticatedCommandHandler;
	
	public Main(AuthenticatedCommandHandler authenticatedCommandHandler,
			RegisterCommand registerCommand,
			LoginCommand loginCommand,
			UpdatePasswordCommand updatePasswordCommand,
			AddGroupCommand addGroupCommand,
			AddMemberCommand addMemberCommand,
			ExitCommand exitCommand) {
		this.registerCommand = registerCommand;
		this.loginCommand = loginCommand;
		this.updatePasswordCommand = updatePasswordCommand;
		this.addGroupCommand = addGroupCommand;
		this.addMemberCommand = addMemberCommand;
		this.exitCommand = exitCommand;
		this.authenticatedCommandHandler = authenticatedCommandHandler;
	}
	
	public static void main(String args[]) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		ICommandHandler commandHandler = new CommandHandler();
		commandHandler.register(registerCommand);
		commandHandler.register(loginCommand);
		commandHandler.register(exitCommand);
		commandHandler.register(authenticatedCommandHandler);
		authenticatedCommandHandler.register(updatePasswordCommand);
		authenticatedCommandHandler.register(addGroupCommand);
		authenticatedCommandHandler.register(addMemberCommand);
		while(true) {
			System.out.print("> ");
			List<String> input = Arrays.asList(sc.nextLine().split(" "));
			try {
				commandHandler.execute(input);
			}catch(SplitwiseException e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
}
