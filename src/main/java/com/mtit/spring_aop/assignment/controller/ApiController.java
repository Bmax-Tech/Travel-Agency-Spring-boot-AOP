package com.mtit.spring_aop.assignment.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.TransactionException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mtit.spring_aop.assignment.models.Booking;
import com.mtit.spring_aop.assignment.models.Flight;
import com.mtit.spring_aop.assignment.models.User;
import com.mtit.spring_aop.assignment.repository.BookingRepository;
import com.mtit.spring_aop.assignment.repository.FlightRepository;
import com.mtit.spring_aop.assignment.repository.LoggerRepository;
import com.mtit.spring_aop.assignment.repository.UserRepository;

@RestController
public class ApiController {
	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private LoggerRepository loggerRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * ---------------------------------------------------------------------
	 * Request
	 * ---------------------------------------------------------------------
	 */

	/**
	 * Get All Flights
	 * 
	 * @return
	 */
	@RequestMapping(value = "/api/getFlights", method = RequestMethod.POST)
	public List<Flight> getFlights() {
		return this.getAllFlights();
	}

	/**
	 * Make Booking
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/api/saveBooking", method = RequestMethod.POST)
	public Booking saveBooking(@RequestBody String data) {
		JSONObject bookingData = this.parseData(data);

		Booking booking = new Booking();
		booking.setCustomer_name(bookingData.get("customer_name").toString());
		booking.setAddress(bookingData.get("address").toString());
		booking.setContact_no(bookingData.get("contact_no").toString());
		booking.setDeparture_date(bookingData.get("departure_date").toString());
		booking.setBooking_date(bookingData.get("booking_date").toString());
		booking.setEmail(bookingData.get("email").toString());
		booking.setFlight_id(Integer.parseInt(bookingData.get("flight_id").toString()));

		return this.makeBooking(booking);
	}

	/**
	 * Get All Bookings
	 * 
	 * @return
	 */
	@RequestMapping(value = "/api/getBookings", method = RequestMethod.POST)
	public List<Booking> getBookings() {
		return this.getAllBookings();
	}

	/**
	 * Remove Booking
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/api/removeBooking", method = RequestMethod.POST)
	public boolean removeBooking(@RequestBody String data) {
		JSONObject removeData = this.parseData(data);

		Booking booking = new Booking();
		booking.setId(Long.parseLong(removeData.get("booking_id").toString()));
		return this.removeBooking(booking);
	}

	/**
	 * Get All Users List
	 * 
	 * @return
	 */
	@RequestMapping(value = "/api/getAllUsers", method = RequestMethod.POST)
	public List<User> getUsers() {
		return this.getAllUsers();
	}

	/**
	 * Update User Credit Limit for Given user id
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/api/editUserCreditLimit", method = RequestMethod.POST)
	public User editUserCreditLimit(@RequestBody String data) {
		JSONObject userData = this.parseData(data);
		User user = this.getUser(Long.parseLong(userData.get("user_id").toString()));
		user.setCredit_limit(Double.parseDouble(userData.get("credit_limit").toString()));
		return this.editUser(user);
	}

	/**
	 * ---------------------------------------------------------------------
	 * Local Methods
	 * ---------------------------------------------------------------------
	 */

	/**
	 * Convert String into JSONObject
	 * 
	 * @param data
	 * @return
	 */
	private JSONObject parseData(String data) {
		try {
			JSONParser parser = new JSONParser();
			return (JSONObject) parser.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns all flights
	 * 
	 * @return
	 */
	public List<Flight> getAllFlights() {
		Iterable<Flight> flights = flightRepository.findAll();
		List<Flight> data = new ArrayList<Flight>();
		for (Flight flight : flights) {
			data.add(flight);
		}
		return data;
	}

	/**
	 * Save Booking to DB
	 * 
	 * @param booking
	 * @return
	 */
	public Booking makeBooking(Booking booking) {
		bookingRepository.save(booking);
		return booking;
	}

	/**
	 * Remove Booking
	 * 
	 * @param booking
	 * @return
	 */
	public boolean removeBooking(Booking booking) {
		try {
			bookingRepository.delete(booking);
			return true;
		} catch (TransactionException e) {
			// TODO: handle exception
			return false;
		}
	}

	/**
	 * Returns all bookings
	 * 
	 * @return
	 */
	public List<Booking> getAllBookings() {
		Iterable<Booking> bookings = bookingRepository.findAll();
		List<Booking> data = new ArrayList<Booking>();
		for (Booking booking : bookings) {
			data.add(booking);
		}
		return data;
	}

	/**
	 * Returns all flights
	 * 
	 * @return
	 */
	public void writeLog(String text) {

	}

	/**
	 * Returns User
	 * 
	 * @return
	 */
	public User getUser(String username, String password) {
		Iterable<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(this.md5(password))) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Returns user for given id
	 * 
	 * @return
	 */
	public User getUser(Long id) {
		return userRepository.findOne(id);
	}

	/**
	 * Get All Users
	 * 
	 * @return
	 */
	public List<User> getAllUsers() {
		Iterable<User> users = userRepository.findAll();
		List<User> data = new ArrayList<User>();
		for (User user : users) {
			data.add(user);
		}
		return data;
	}

	/**
	 * Edit User Details
	 * 
	 * @param user
	 * @return
	 */
	public User editUser(User user) {
		return userRepository.save(user);
	}

	/**
	 * Generate MD5 Hashed String
	 * 
	 * @param text
	 * @return
	 */
	private String md5(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte byteData[] = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
}
