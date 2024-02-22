package com.structural.pattern;

/*
  Bridge Pattern :
  Bridge design pattern  that uses OOP principles to decouple an abstraction from its implementation so that the two can vary independently.
  
  When to use :
  The Bridge design pattern can help you avoid the problems of inheritance, such as tight coupling, code duplication, and inflexibility. 
  By separating the abstraction from the implementation, you can vary them independently and reduce the complexity of your code. 
  The Bridge design pattern can also improve the readability, maintainability, and testability of your web development framework. 
  You can easily swap or add new implementations without modifying the existing abstractions.
  
  Advantages :
  1) The Bridge design pattern can be a great asset to your web development framework, as it increases the modularity and reusability of 
     your code by separating the concerns of the abstraction and the implementation. 
  2) It also enhances the extensibility and scalability of your framework, allowing you to add new abstractions and implementations 
     without breaking the existing ones. Moreover, it can improve the performance and efficiency of your framework by avoiding unnecessary 
     inheritance and subclassing.
  3) It facilitate the testing and debugging by isolating the abstraction and the implementation.

  Disadvantages :
  1) The Bridge design pattern can pose challenges to your web development framework, such as introducing complexity and 
     overhead to your code, requiring more communication and coordination between the abstraction and the implementation, making your code 
     less intuitive and harder to understand, and making it difficult to apply the pattern to existing frameworks that use inheritance 
     extensively. These issues can affect the speed and reliability of your framework.
 
 */

//Here we are creating a bridge between engine and vehicle
interface Engine{
	public String reFill();
}

//Separating abstraction from implementation(we separate the engine from vehicle)
abstract class Vehical{
	Engine engine;
	public Vehical(Engine engine) {
		this.engine = engine;
	}
	abstract public void reFill();
}

class Car extends Vehical{
	Car(Engine engine){
		super(engine);
	}
	public void reFill() {
		System.out.println("Car "+engine.reFill());
	}
}

class Bike extends Vehical{
	public Bike(Engine engine) {
		super(engine);
	}
	public void reFill() {
		System.out.println("Bike "+engine.reFill());
	}
}

class ElectricEngine implements Engine{
	public String reFill() {
		return "Charged with 100%";
	}
}

class PetrolEngine implements Engine{
	public String reFill() {
		return "refilled with 5 liters";
	}
}

public class BridgePattern {
	public static void main(String[] args) {
		//Engine engine = new ElectricEngine();
		Engine engine = new PetrolEngine(); 
		Vehical car = new Car(engine);
		car.reFill();
	}
}
