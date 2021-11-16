package com.splitwise.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User  extends Auditable{
	
	private String username;
	private String fullname;
	private String hashedSaltedPassword;
	private String phoneNo;
	@ManyToMany(mappedBy = "members")
	private Set<Group> groups;
	@ManyToMany(mappedBy = "participants")
	private Set<Expense> expenses;
	
	public void setUsername() {
		// check if length >2
	}
	
	@Override
	public String toString() {
		return "User [id=" +  getId() + " username=" + username + ", fullname=" + fullname + ", hashedSaltedPassword="
				+ hashedSaltedPassword + ", phoneNo=" + phoneNo + "]";
	}
	
	
	
}
