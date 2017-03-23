package com.mtit.spring_aop.assignment.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logger")
public class Logger {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private int user_id;

	@Column
	private String event;

	@Column
	private String message;

	@Column
	private String date_time;

	public Long getId() {
		return id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDatetime() {
		return date_time;
	}

	public void setDatetime(String datetime) {
		this.date_time = datetime;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
