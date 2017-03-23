package com.mtit.spring_aop.assignment.repository;

import org.springframework.data.repository.CrudRepository;

import com.mtit.spring_aop.assignment.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
}
