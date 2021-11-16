package com.splitwise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.splitwise.entities.Expense;

@Service
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
