package com.splitwise.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="expenses")
public class Expense extends Auditable{
	private String description;
	private Date date;
	private Double totalAmount;
	private boolean isSettled;
	
	@ManyToOne
	private Group group;
	@ManyToMany
	private Set<User> participants = new HashSet<User>();
	@ElementCollection
	private Map<User, Double> amountsPaid = new HashMap<User, Double>();
	@ElementCollection
	private Map<User, Double> amountsOwed = new HashMap<User, Double>();
	
	public Expense(String description, Date date, Double totalAmount, Set<User> participants) {
		super();
		this.description = description;
		this.date = date;
		this.totalAmount = totalAmount;
		this.participants = participants;
	}
	
	
	public void setAmountsPaid() {
		// check with total amount
		// throw exception if doesn't match
	}
	
}
