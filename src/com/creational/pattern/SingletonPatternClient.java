package com.creational.pattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 	Singleton Pattern:
	The Singleton pattern is a design pattern that restricts the instantiation of a class to a single object. 
	It ensures that only one instance of a class exists in the system and provides a global point of access 
	to that instance.

    When to use Singleton
	1)	There must be exactly one instance of a class, and it must be accessible to clients from a 
		well-known access point.
	2)	When the sole instance should be extensible by subclassing, and clients should be able to use an 
		extended instance without modifying their code.
	
	Problem it solves?
	There are situations where we need to have only one instance of a class throughout the lifetime of an 
	application. For example, a logging class that records all events that occur in the application should 
	only have one instance. If multiple instances of the logging class are created, it can cause problems 
	such as conflicting log entries, loss of log data, and increased memory usage. The Singleton pattern 
	solves this problem by ensuring that only one instance of the class is created and providing a global 
	point of access to it.
	
	Advantages:
	The Singleton pattern has several advantages, including:
	1)	Global point of access: The Singleton pattern provides a single point of access to the object, 
		which can be accessed from anywhere in the system.
	2)	Controlled access: The Singleton pattern allows access to the object to be controlled, as only 
		one instance of the class exists.
	3)	Reduced memory usage: The Singleton pattern reduces memory usage by ensuring that only one instance 
		of the class exists in the system.
	4)	Simplified object creation: The Singleton pattern simplifies object creation by ensuring that only 
		one instance of the class is created and providing a global point of access to it.
	
	Disadvantages
	The Singleton pattern also has some disadvantages, including:
	1)	Tight coupling: The Singleton pattern can lead to tight coupling between classes, as the Singleton 
		class is globally accessible.
	2)	Thread safety: The Singleton pattern can be prone to thread safety issues, as multiple threads can 
		attempt to create or access the Singleton instance simultaneously.
	3)	Testing difficulties: The Singleton pattern can make unit testing difficult, as it is not easy to 
		substitute the Singleton with a mock object during testing.
	
	The non-thread-safe singleton pattern can lead to several problems, including below:
	1)	Multiple instances: In a multithreaded environment, multiple threads can create instances of the 
		Singleton class simultaneously, resulting in multiple instances of the Singleton object. This defeats 
		the purpose of the pattern, as the Singleton should only have one instance.
	2)	Inconsistent state: When multiple threads access the Singleton instance simultaneously, they may 
		modify its state in unpredictable ways. This can lead to inconsistent behavior and difficult-to-debug 
		errors.
	3)	Race conditions: The non-thread-safe Singleton pattern is susceptible to race conditions, where 
		multiple threads try to access or modify the same data simultaneously. This can cause data corruption,
		deadlock, or other issues.
	4)	Deadlocks: Deadlocks can occur when two or more threads are waiting for each other to release a lock, 
		causing them to block indefinitely.
	5)	Reduced performance: Synchronization overhead can cause reduced performance in heavily multithreaded 
		applications.

 * */

//Double Checked Locking based Java implementation of
// singleton design pattern
class JDBC {
	private static volatile JDBC obj = null;

	private JDBC() {}

	public static JDBC getInstance() {
		if (obj == null) {
			// To make thread safe
			synchronized (JDBC.class) {
				// check again as multiple threads
				// can reach above step
				if (obj == null)
					obj = new JDBC();
			}
		}
		return obj;
	}
	public static Connection getConnection()throws ClassNotFoundException, SQLException {  
           
         Connection con=null;  
         Class.forName("com.mysql.cj.jdbc.Driver");  
         con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/hr", "root", "root");  
         return con;  
           
     }  
}

public class SingletonPatternClient {

	public static void main(String[] args) {
		JDBC jdbcObj = JDBC.getInstance();
		try {
			Connection connecton =  jdbcObj.getConnection();
		    System.out.println(connecton);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
