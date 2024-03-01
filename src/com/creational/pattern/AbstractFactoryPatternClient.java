package com.creational.pattern;

/*
 	Abstract Factory Pattern :
 	Interface or abstract class for creating families of related (or dependent) objects but without 
 	specifying their concrete sub-classes.
	After our fatory class, two new vehicle brand companies are interested in our system: NextGen and 
	FutureVehicle. These new companies build not only fuel-only vehicles but also electric vehicles. 
	Each company has its vehicle design.Our Factory calss is not ready to address these new scenarios. 
	We must support electric vehicles and consider that each company has its design. To resolve these 
	problems, we can use the Abstract Factory Pattern. 
	
	When to use :
	1)	This pattern is commonly used when we start using the Factory Method Pattern, and we need to evolve 
		our system to a more complex system. It centralizes the product creation code in one place.
	2)	Multiple families of related products: When your system needs to be configured with multiple 
		families of related products, and you want to ensure that the products from one family are compatible 
		with the products from another family.
	3)	Flexibility and extensibility: If you need to allow for variations or extensions in the products or 
		their families, the Abstract Factory pattern provides a way to introduce new product variants without 
		modifying existing client code.
	4)	Encapsulation of creation logic: The pattern encapsulates the creation of objects, making it easier 
		to change or extend the creation process without affecting the client code.
	5)	Consistency across product families: If you want to enforce consistency among the products created by
	 	different factories, the Abstract Factory pattern can help maintain a uniform interface.
	
	Advantages:
	The Abstract Factory pattern has several benefits that make it useful for software design. 
	1)	 It promotes loose coupling between the client code and the concrete products, as the client only 
		interacts with the abstract interfaces and not the implementation details.
	2)	It supports the principle of open/closed, as you can introduce new variants of products without 
		breaking the existing code. 
	3)	It helps you avoid hard-coded dependencies and create objects that are compatible with each other.
	4)	Exchanging Product Families easily:
	•	The class of a concrete factory appears only once in an application, that is where it’s instantiated.
	•	This makes it easy to change the concrete factory an application uses.
	•	It can use various product configurations simply by changing the concrete factory.
	•	Because an abstract factory creates a complete family of products, the whole product family changes 
		at once.
	5)	Promoting consistency among products:
	•	When product objects in a family are designed to work together, it’s important that an application 
		use objects from only one family at a time. AbstractFactory makes this easy to enforce
	
	
	Disadvantages:
	1)	Complexity:
	•	Abstract Factory can introduce additional complexity to the codebase.
	•	Having multiple factories and abstract product interfaces may be overkill for simpler projects.
	2)	Rigidity with New Product Types:
	•	Adding new product types (classes) to the system can be challenging.
	•	You might need to modify not just the concrete factories but also the abstract factory interface, 
		potentially impacting existing code.
	3)	Increased Number of Classes:
	•	As you introduce more abstract factories and product families, the number of classes in your system 
		can grow rapidly.
	•	This can make the code harder to manage and understand, particularly for smaller projects.
	4)	Dependency Inversion Principle Violation:
	•	In some cases, the Abstract Factory pattern may lead to a violation of the Dependency Inversion 
		Principle, especially if client code directly depends on concrete factory implementations rather than 
		the abstract interfaces.
	5)	Limited Extensibility:
	•	Extending the abstract factory hierarchy or introducing new product families might require 
		modifications to multiple parts of the code, potentially leading to cascading changes and making the 
		system less extensible.
	6)	Not Ideal for Simple Systems:
	•	The Abstract Factory pattern may be overkill for smaller, less complex systems where the overhead of 
		defining abstract factories and products outweighs the benefits of the pattern.

 * */

interface ElectricVehicle{
	public void build();
}

//Abstract factory class
abstract class Corporation {
	public abstract MotorVehicle createMotorVehicle();
	public abstract ElectricVehicle createElectricVehicle();
}

class FutureVehicleMotorCycle implements MotorVehicle {
	public void build() {
		System.out.println("Build future motor cycle");
	}
}

class FutureVehicalElectricCar implements ElectricVehicle {
	public void build() {
		System.out.println("Build future electrical car");
	}
}

class NextGenMotorCycle implements MotorVehicle {
	public void build() {
		System.out.println("Build next gen motor cycle");
	}
}

class NextGenElectricCar implements ElectricVehicle {
	public void build() {
		System.out.println("Build next gen electrical car");
	}
}

class FutureVehicleCorporation extends Corporation {

	@Override
	public MotorVehicle createMotorVehicle() {
		return new FutureVehicleMotorCycle();
	}

	@Override
	public ElectricVehicle createElectricVehicle() {
		return new FutureVehicalElectricCar();
	}
}

class NextGenCorporation extends Corporation {

	@Override
	public MotorVehicle createMotorVehicle() {
		return new NextGenMotorCycle();
	}

	@Override
	public ElectricVehicle createElectricVehicle() {
		return new NextGenElectricCar();
	}
}


public class AbstractFactoryPatternClient {

	public static void main(String[] args) {
		Corporation futureCorporation = new FutureVehicleCorporation();
		futureCorporation.createElectricVehicle().build();
		futureCorporation.createMotorVehicle().build();
		System.out.println("----------------------------------------------------------------------");
		Corporation nextgenCorporation = new NextGenCorporation();
		nextgenCorporation.createElectricVehicle().build();
		nextgenCorporation.createMotorVehicle().build();
	}

}
