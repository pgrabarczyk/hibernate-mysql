package com.pgrabarczyk.hibernate.mysql.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pgrabarczyk.hibernate.mysql.model.Address;
import com.pgrabarczyk.hibernate.mysql.model.Computer;
import com.pgrabarczyk.hibernate.mysql.model.Employee;
import com.pgrabarczyk.hibernate.mysql.model.Hardware;
import com.pgrabarczyk.hibernate.mysql.model.Phone;
import com.pgrabarczyk.hibernate.mysql.model.Task;

public abstract class HibernateTest {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Rule public TestName testName = new TestName();
	
	protected SessionFactory sessionFactory;
	protected Session session;
	
	@Before
	public void init() {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Address.class)
				.addAnnotatedClass(Computer.class)
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Hardware.class)
				.addAnnotatedClass(Phone.class)
				.addAnnotatedClass(Task.class)		
				.buildSessionFactory();

		session = sessionFactory.openSession();
	}
	
	@Before
	public void logTestName() {
		log.debug(testName.getClass().getName() + "#" + testName.getMethodName());
	}
	
	@After
	public void finalize() {
		if(session != null ) {
			session.close();
		}
		if(sessionFactory!=null) {
			sessionFactory.close();
		}
	}
}
