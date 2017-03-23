package com.mtit.spring_aop.assignment.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.mtit.spring_aop.assignment.aspect.BookingLogginAspect;
import com.mtit.spring_aop.assignment.aspect.FlightLogginAspect;
import com.mtit.spring_aop.assignment.aspect.UserLogginAspect;

public class Utility {

	/**
	 * Generate MD5 Hashed String
	 * 
	 * @param text
	 * @return
	 */
	public static String md5(String text) {
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

	/**
	 * Log Action with their ratings
	 * 
	 * @param type
	 * @param action
	 */
	public static void logAction(String type, String action) {
		if (type.equals(Constants.FLIGHT)) {
			if (action.equals(Constants.SEARCH)) {
				FlightLogginAspect.LOGGER.info(
						Constants.FLIGHT + " - " + Constants.SEARCH + " -> RATE = " + Constants.FLIGHT_SEARCH_RATE);
			} else if (action.equals(Constants.NEW)) {
				FlightLogginAspect.LOGGER.info(
						Constants.FLIGHT + " - " + Constants.NEW + " -> RATE = " + Constants.FLIGHT_NEW_RATE);
			}
		} else if (type.equals(Constants.BOOKING)) {
			if (action.equals(Constants.SEARCH)) {
				BookingLogginAspect.LOGGER.info(
						Constants.BOOKING + " - " + Constants.SEARCH + " -> RATE = " + Constants.BOOKING_SEARCH_RATE);
			} else if (action.equals(Constants.REMOVE)) {
				BookingLogginAspect.LOGGER.info(
						Constants.BOOKING + " - " + Constants.REMOVE + " -> RATE = " + Constants.BOOKING_REMOVE_RATE);
			} else if (action.equals(Constants.NEW)) {
				BookingLogginAspect.LOGGER
						.info(Constants.BOOKING + " - " + Constants.NEW + " -> RATE = " + Constants.BOOKING_NEW_RATE);
			}
		} else if (type.equals(Constants.USER)) {
			if (action.equals(Constants.SEARCH)) {
				UserLogginAspect.LOGGER
						.info(Constants.USER + " - " + Constants.SEARCH + " -> RATE = " + Constants.USER_SEARCH_RATE);
			} else if (action.equals(Constants.EDIT)) {
				UserLogginAspect.LOGGER
						.info(Constants.USER + " - " + Constants.EDIT + " -> RATE = " + Constants.USER_EDIT_RATE);
			}
		}
	}
}
