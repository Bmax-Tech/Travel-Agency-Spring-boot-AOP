package com.mtit.spring_aop.assignment.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mtit.spring_aop.assignment.common.LoginSession;
import com.mtit.spring_aop.assignment.common.Utility;
import com.mtit.spring_aop.assignment.models.User;
import com.mtit.spring_aop.assignment.repository.UserRepository;

@Controller
public class ViewController {

	@Autowired
	private UserRepository userRepository;

	static final Logger LOGGER = LogManager.getLogger(ViewController.class.getName());

	@RequestMapping("/")
	public String register() {
		LoginSession.logout();
		return "login";
	}

	@RequestMapping(value = "/authlogin", method = RequestMethod.POST)
	public ModelAndView authLogin(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		LOGGER.info("Authenticate User = " + username);
		User user = this.getUser(username, password);
		if (user != null) {
			LoginSession.createSession(user);
			return new ModelAndView("redirect:/home");
		} else {
			return new ModelAndView("redirect:/");
		}
	}

	@RequestMapping("/home")
	public String home() {
		if (LoginSession.checkSession()) {
			return "home";
		} else {
			return "login";
		}
	}
	
	@RequestMapping("/bookings")
	public String bookings() {
		if (LoginSession.checkSession()) {
			return "bookings";
		} else {
			return "login";
		}
	}
	
	@RequestMapping("/users")
	public String users() {
		if (LoginSession.checkSession()) {
			return "users";
		} else {
			return "login";
		}
	}
	
	
	
	/**
	 * get user
	 * 
	 * @return
	 */
	public User getUser(String username, String password) {
		Iterable<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(Utility.md5(password))) {
				return user;
			}
		}
		return null;
	}
}
