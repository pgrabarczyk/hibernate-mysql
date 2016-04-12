package com.pgrabarczyk.hibernate.mysql.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table
public class Computer extends Hardware {
	
	@Column
	private String computerName;
	
	@Enumerated(EnumType.STRING)
	private ComputerTypeEnum computerType;

	public Computer() {
		
	}
	
	public Computer(Date usedFrom, Employee employee, String computerName, ComputerTypeEnum computerType) {
		super(usedFrom, employee);
		this.computerName = computerName;
		this.computerType = computerType;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	
	public ComputerTypeEnum getComputerType() {
		return computerType;
	}

	public void setComputerType(ComputerTypeEnum computerType) {
		this.computerType = computerType;
	}
	
	@Override
	public String toString() {
		return "Computer [id=" + getId() + ", usedFrom=" + getUsedFrom() + ", employee=" + getEmployee().getId() + ", computerName=" + computerName + 
				", computerType=" + computerType.name() + "]";
		
	}	
	
}
