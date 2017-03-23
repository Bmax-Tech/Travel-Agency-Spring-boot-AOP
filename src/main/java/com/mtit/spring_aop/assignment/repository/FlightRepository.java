package com.mtit.spring_aop.assignment.repository;

import org.springframework.data.repository.CrudRepository;

import com.mtit.spring_aop.assignment.models.Flight;

public interface FlightRepository extends CrudRepository<Flight, Long> {
	
}
