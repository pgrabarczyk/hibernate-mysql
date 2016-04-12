package com.pgrabarczyk.hibernate.mysql.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Phone extends Hardware {
	
	@Column
	private String phoneNumber;

	public Phone() {
		
	}
	
	public Phone(Date usedFrom, Employee employee, String phoneNumber) {
		super(usedFrom, employee);
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Phone [id=" + getId() + ", usedFrom=" + getUsedFrom() + ", employee=" + getEmployee().getId() + ", phoneNumber=" + phoneNumber + "]";
	}	
	
}
