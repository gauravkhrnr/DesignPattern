package com.creational.pattern;
/*
 
    Factory Method Pattern:
 
	The factory method pattern is a popular software design pattern that allows you to create objects without 
	specifying their concrete classes. It can help you achieve performance and maintainability goals in your 
	software solution architecture, but it also comes with some trade-offs and challenges.
	Or  define an interface or abstract class for creating an object but let the subclasses decide which class 
	to instantiate.The Factory Method Pattern is also known as Virtual Constructor.
	
	Advantages:
	
	1)	The factory method pattern can improve performance by reducing the number of object creations and 
	dependencies, and by enabling lazy initialization and caching of objects. 
	2)	It can also enhance maintainability by decoupling the client code from the concrete classes, and 
	by allowing you to add new types of objects without modifying the existing code. 
	3)	The factory method pattern can also support the principle of open-closed design, which states that 
	software entities should be open for extension but closed for modification.
	
	Disadvantages:
	
	1)	The factory method pattern can also introduce some drawbacks that can affect performance and 
	maintainability. For example, it can increase the complexity and size of the code, as you need to create 
	a separate factory class or method for each type of object. 
	2)	It can also introduce an extra level of abstraction and indirection, which can make the code harder 
	to understand and debug. Moreover, it can create tight coupling between the factory and the concrete classes, 
	which can make the code less flexible and testable.
	
	When to use:
	When using the factory method pattern, you need to take into account several factors that can impact 
	the performance and maintainability of your software solution architecture. These include the number and 
	variety of objects to create and manage, the frequency and cost of object creation and initialization, 
	the desired level of abstraction and encapsulation, the degree of flexibility and extensibility required,
	as well as the trade-off between performance and readability.

 */

interface MotorVehicle {
	public void build();
}

class MotorCycle implements MotorVehicle {

	@Override
	public void build() {
		System.out.println("Build a bike");
	}
}

class Car implements MotorVehicle {

	@Override
	public void build() {
		System.out.println("Build car");
	}
}

 abstract class MotorVehicleFactory{
	public MotorVehicle  create() {
		MotorVehicle  vehicle = createMotorVehicle();
		vehicle.build();
		return vehicle;
	}
	
	protected abstract MotorVehicle  createMotorVehicle();
}

class MotorCycleFactory extends MotorVehicleFactory{

	@Override
	protected  MotorVehicle  createMotorVehicle() {
		return new MotorCycle();
	}
	
}

class CarFactory extends MotorVehicleFactory{

	@Override
	protected MotorVehicle  createMotorVehicle() {
		return new Car();
	}
	
}

public class FactoryPatternClient {
	public static void main(String[] args) {
		MotorVehicleFactory factory  = new CarFactory();
		factory.create();
	}
}
