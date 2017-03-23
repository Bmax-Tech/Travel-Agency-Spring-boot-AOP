package com.mtit.spring_aop.assignment.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.mtit.spring_aop.assignment.common.Constants;
import com.mtit.spring_aop.assignment.common.Utility;

@Aspect
@Component
public class BookingLogginAspect {
	public static final Logger LOGGER = LogManager.getLogger(BookingLogginAspect.class.getName());

	/*
	 * -------------------------------------------------------------------------
	 * ---------------------- Before Section
	 * -------------------------------------------------------------------------
	 */
	@Before("execution(* com.mtit.spring_aop.assignment.controller.ApiController.saveBooking(String))")
	public void beforeSaveBooking() {
		LOGGER.info("+++++ saveBooking - before +++++");
		Utility.logAction(Constants.BOOKING, Constants.NEW);
	}

	@Before("execution(* com.mtit.spring_aop.assignment.controller.ApiController.getBookings())")
	public void beforeGetBookings() {
		LOGGER.info("+++++ getBookings - before +++++");
		Utility.logAction(Constants.BOOKING, Constants.SEARCH);
	}

	@Before("execution(* com.mtit.spring_aop.assignment.controller.ApiController.removeBooking(String))")
	public void beforeRemoveBooking() {
		LOGGER.info("+++++ removeBooking - before +++++");
		Utility.logAction(Constants.BOOKING, Constants.REMOVE);
	}

	/*
	 * -------------------------------------------------------------------------
	 * ---------------------- After Section
	 * -------------------------------------------------------------------------
	 */
	@After("execution(* com.mtit.spring_aop.assignment.controller.ApiController.saveBooking(String))")
	public void afterSaveBooking() {
		LOGGER.info(":::::: saveBooking - after ::::::");
	}

	@After("execution(* com.mtit.spring_aop.assignment.controller.ApiController.getBookings())")
	public void afterGetBookings() {
		LOGGER.info(":::::: getBookings - after ::::::");
	}

	@After("execution(* com.mtit.spring_aop.assignment.controller.ApiController.removeBooking(String))")
	public void afterRemoveBooking() {
		LOGGER.info(":::::: removeBooking - after ::::::");
	}

	/*
	 * -------------------------------------------------------------------------
	 * ---------------------- After Return Section
	 * -------------------------------------------------------------------------
	 */
	@AfterReturning(pointcut = "execution(* com.mtit.spring_aop.assignment.controller.ApiController.saveBooking(String))", returning = "result")
	public void afterReturnSaveBooking(JoinPoint joinPoint, Object result) {
		LOGGER.info("###### saveBooking - afterReturn = " + result.toString() + " ######");
	}

	@AfterReturning(pointcut = "execution(* com.mtit.spring_aop.assignment.controller.ApiController.getBookings())", returning = "result")
	public void afterReturnGetBookings(JoinPoint joinPoint, Object result) {
		LOGGER.info("###### getBookings - afterReturn = " + result.toString() + " ######");
	}

	@AfterReturning(pointcut = "execution(* com.mtit.spring_aop.assignment.controller.ApiController.removeBooking(String))", returning = "result")
	public void afterReturnRemoveBooking(JoinPoint joinPoint, Object result) {
		LOGGER.info("###### removeBooking - afterReturn = " + result.toString() + " ######");
	}

}