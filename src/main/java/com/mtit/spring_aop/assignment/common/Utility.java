package com.mtit.spring_aop.assignment.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
}
