package com.pgrabarczyk.hibernate.mysql.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Hardware {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column
	private Long id;
	
	@Column
	private Date usedFrom;
	
	@ManyToOne
	private Employee employee;
	
	public Hardware() {
		
	}

	public Hardware(Date usedFrom, Employee employee) {
		super();
		this.usedFrom = usedFrom;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUsedFrom() {
		return usedFrom;
	}

	public void setUsedFrom(Date usedFrom) {
		this.usedFrom = usedFrom;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Hardware [id=" + id + ", usedFrom=" + usedFrom + ", employee=" + employee + "]";
	}

}
