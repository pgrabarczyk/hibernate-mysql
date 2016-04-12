package com.pgrabarczyk.hibernate.mysql;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pgrabarczyk.hibernate.mysql.model.Address;
import com.pgrabarczyk.hibernate.mysql.model.Computer;
import com.pgrabarczyk.hibernate.mysql.model.ComputerTypeEnum;
import com.pgrabarczyk.hibernate.mysql.model.Employee;
import com.pgrabarczyk.hibernate.mysql.model.Hardware;
import com.pgrabarczyk.hibernate.mysql.model.Phone;
import com.pgrabarczyk.hibernate.mysql.model.Task;


public class Main {
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {

		LOG.debug("Start of Main");

		try (SessionFactory sessionFactory = new Configuration().configure()
				.addAnnotatedClass(Address.class)
				.addAnnotatedClass(Computer.class)
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Hardware.class)
				.addAnnotatedClass(Phone.class)
				.addAnnotatedClass(Task.class)				
				.buildSessionFactory() ) {

			try (Session session = sessionFactory.openSession()) {
				Transaction txn = session.beginTransaction();

				saveObjectToDB(session);

				txn.commit();
				
				session.clear();
				logSavedObjects(session);
			}
		}
	}

	private static void saveObjectToDB(Session session) {
		Address andrewAddress = new Address("Mickiewicza 1", "00-000");
		Address tracyAddress = new Address("Slowackiego 324", "11-325");

		session.persist(andrewAddress);
		session.persist(tracyAddress);

		Date andrewBirth = new GregorianCalendar(1980, 5, 25).getTime();
		Date tracyBirth = new GregorianCalendar(1975, 6, 4).getTime();
		
		Employee andrew = new Employee("Andrew", "Kowalski", andrewBirth, andrewAddress);
		Employee tracy = new Employee("Tracy", "Nowak", tracyBirth, tracyAddress);

		session.persist(andrew);
		session.persist(tracy);
		
		Date pcAndrewUsedFrom = new GregorianCalendar(2001, 1, 1).getTime();
		Date laptopAndrewUsedFrom = new GregorianCalendar(2001, 6, 6).getTime();
		Date laptopTracyUsedFrom = new GregorianCalendar(2002, 2, 2).getTime();
		Hardware pcAndrew = new Computer(pcAndrewUsedFrom, andrew, "Andrew's PC", ComputerTypeEnum.PC);
		Hardware laptopAndrew = new Computer(laptopAndrewUsedFrom, andrew, "Andrew's laptop", ComputerTypeEnum.LAPTOP);
		Hardware laptopTracy = new Computer(laptopTracyUsedFrom, tracy, "Tracy's laptop", ComputerTypeEnum.LAPTOP);
		
		session.persist(pcAndrew);
		session.persist(laptopAndrew);
		session.persist(laptopTracy);
		
		Date monbilePhoneTracyUsedFrom = new GregorianCalendar(2003, 3, 3).getTime();
		Date deskPhoneTracyUsedFrom = new GregorianCalendar(2004, 4, 4).getTime();
		Hardware mobilePhoneTracy = new Phone(monbilePhoneTracyUsedFrom, tracy, "789456123");
		Hardware deskPhoneTracy = new Phone(deskPhoneTracyUsedFrom, tracy, "555111444");
		
		session.persist(mobilePhoneTracy);
		session.persist(deskPhoneTracy);
		
		Task meetingPreparationTask = new Task("Preapre documents for next meeting.");
		Task fixCoffeMachineTask = new Task("Fix coffe machine");
		Task buildAirplaneTask = new Task("Build airplane");
		
		session.persist(meetingPreparationTask);
		session.persist(fixCoffeMachineTask);
		session.persist(buildAirplaneTask);
		
		tracy.getTaskList().add(meetingPreparationTask);
		andrew.getTaskList().add(fixCoffeMachineTask);
		tracy.getTaskList().add(fixCoffeMachineTask);
	}

	private static void logSavedObjects(Session session) {
		@SuppressWarnings("unchecked")
		List<Employee> employeeList = session.createCriteria(Employee.class).list();
		for (Employee employee : employeeList) {
			LOG.debug(employee.toString());
			for(Hardware hardware : employee.getHardwareList() )
				LOG.debug(hardware.toString());
		}
		
		@SuppressWarnings("unchecked")
		List<Task> taskList = session.createCriteria(Task.class).list();
		for (Task task : taskList) {
			LOG.debug(task.toString());
		}
	}
}
