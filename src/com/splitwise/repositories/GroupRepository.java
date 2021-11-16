package com.splitwise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.splitwise.entities.Group;

@Service
public interface GroupRepository extends JpaRepository<Group, Long> {

}
