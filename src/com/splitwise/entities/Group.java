package com.splitwise.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="groups")
public class Group extends Auditable {
	
	private String name;
	
	@OneToOne
	private User admin;	
	@ManyToMany
	private Set<User> members = new HashSet<User>();
	@OneToMany(mappedBy = "group")
	private Set<Expense> expenses = new HashSet<Expense>();
	
	public Group(String name, User admin, Set<User> members) {
		super();
		this.name = name;
		this.admin = admin;
		this.members.addAll(members);
	}

	@Override
	public String toString() {
		return "Group [id=" + getId() + ",name=" + name + ", admin=" + admin + "]";
	}
	
	
}
