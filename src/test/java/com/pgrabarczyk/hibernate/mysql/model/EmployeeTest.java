package com.pgrabarczyk.hibernate.mysql.model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.PropertyValueException;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;

import com.pgrabarczyk.hibernate.mysql.model.Employee;

public class EmployeeTest extends HibernateTest {

	@Test
	public void persistTest() {
		Date andrewBirth = new GregorianCalendar(1980, 5, 25).getTime();
		Employee andrew = new Employee("Andrew", "Kowalski", andrewBirth, null);
		session.beginTransaction();
		session.persist(andrew);
		session.getTransaction().commit();
		session.clear();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("firstName", "Andrew"));
		criteria.add(Restrictions.eq("lastName", "Kowalski"));
		@SuppressWarnings("unchecked")
		List<Employee> results = criteria.list();
		Assert.assertEquals(1, results.size());
		Employee andrewFromDB = results.get(0);
		Assert.assertEquals(andrew.getFirstName(), andrewFromDB.getFirstName());
		Assert.assertEquals(andrew.getLastName(), andrewFromDB.getLastName());
		Assert.assertEquals(andrew.getBirthDate(), andrewFromDB.getBirthDate());
	}

	@Test(expected = PropertyValueException.class)
	public void notNullableNamesTest() {
		@SuppressWarnings("unchecked")
		List<Employee> results = session.createCriteria(Employee.class).list();
		Assert.assertEquals(0, results.size());
		
		Date andrewBirth = new GregorianCalendar(1980, 5, 25).getTime();
		Employee andrew = new Employee(null, null, andrewBirth, null);
		
		session.beginTransaction();
		session.persist(andrew);
	}

}
