package com.mtit.spring_aop.assignment.repository;

import org.springframework.data.repository.CrudRepository;

import com.mtit.spring_aop.assignment.models.Logger;

public interface LoggerRepository extends CrudRepository<Logger, Long> {
	
}
