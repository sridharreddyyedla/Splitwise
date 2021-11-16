package com.splitwise.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.splitwise.entities.Group;
import com.splitwise.entities.User;
import com.splitwise.exceptions.notfound.GroupNotFoundException;
import com.splitwise.exceptions.notfound.UserNotFoundException;
import com.splitwise.exceptions.validation.CannotModifyGroupException;
import com.splitwise.repositories.GroupRepository;
import com.splitwise.repositories.UserRepository;
import com.splitwise.services.authentication.AuthenticationContext;

@Service
public class GroupController {
	final UserRepository userRepository;
	final GroupRepository groupRepository;
	
	public GroupController(GroupRepository groupRepository, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.groupRepository = groupRepository;
	}
	
	public Group addGroup(AuthenticationContext authContext, String name, List<Long> participantIds) {
		User user = authContext.getCurrentlyLoggedInUserOrElseThrow();
		Set<User> participants = participantIds
				.stream()
				.map((id)-> userRepository
						.findById(id)
						.orElseThrow(()-> new UserNotFoundException("User "+id.toString()+" not found")))
				.collect(Collectors.toSet());
		participants.add(user);
		Group group = new Group(name,user,participants);
		groupRepository.save(group);
		return group;
	}
	
	public void addMember(AuthenticationContext authContext, Long groupId, Long userId) {
		User loggedInUser = authContext.getCurrentlyLoggedInUserOrElseThrow();
		Group group = groupRepository.findById(groupId).orElseThrow( () -> new GroupNotFoundException("Invalid group: g"+groupId));
		User newUser = userRepository.findById(userId).orElseThrow( () -> new UserNotFoundException("Invalid User: u"+userId));
		if(!group.getAdmin().equals(loggedInUser)) throw new CannotModifyGroupException("Group can't be modified");
		group.getMembers().add(newUser);
		groupRepository.save(group);
	}
}
