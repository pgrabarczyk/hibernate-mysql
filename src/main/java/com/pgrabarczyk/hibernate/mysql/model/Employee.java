package com.pgrabarczyk.hibernate.mysql.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Employee {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column
	private Long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(name = "date_of_birth")
	private Date birthDate;
	
	@OneToOne
	@JoinColumn(unique = true)
	private Address address;
	
	@OneToMany(mappedBy = "employee")
	private List<Hardware> hardwareList = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "Employee_Task", joinColumns = { 
			@JoinColumn(name = "employee_id", nullable = false ) }, 
			inverseJoinColumns = { @JoinColumn(name = "task_id",  nullable = false ) })
	private Set<Task> taskList = new LinkedHashSet<>();
	
	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, Date birthDate, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Hardware> getHardwareList() {
		return hardwareList;
	}

	public void setHardwareList(List<Hardware> hardwareList) {
		this.hardwareList = hardwareList;
	}

	public Set<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(Set<Task> taskList) {
		this.taskList = taskList;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", address=" + address + "]";
	}


}
