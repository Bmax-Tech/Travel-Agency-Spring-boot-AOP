package com.mtit.spring_aop.assignment.repository;

import org.springframework.data.repository.CrudRepository;

import com.mtit.spring_aop.assignment.models.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long> {

}
