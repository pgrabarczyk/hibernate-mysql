package com.pgrabarczyk.hibernate.mysql.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Task {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private String description;

	@ManyToMany(mappedBy = "taskList")
	private Set<Employee> employeeList = new LinkedHashSet<>();

	public Task() {

	}

	public Task(String description) {
		super();
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(Set<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", description=" + description + "]";
	}

}
