package com.structural.pattern;

/*
 	Decorator :
 	A Decorator pattern can be used to attach additional responsibilities to an object 
 	either statically or dynamically. 
 	It is also known as Wrapper class
 	
 	Advantage :
 	1) The decorator pattern also allows you to compose different decorators at runtime, creating flexible and 
 	   dynamic combinations of behaviors.So better than static inheritance.
 	2) It enhances the extensibility of the object, because changes are made by coding new classes.
 	3) which states that software entities should be open for extension but closed for modification. 
 	   This means that you can extend the functionality of an existing object without changing its code 
 	   or breaking its clients.For example, Ex: you can wrap a file stream object with a compression decorator 
 	   and an encryption decorator, depending on the user's preferences or security requirements. 
 	4) It avoids the need for creating complex subclasses or inheritance hierarchies, which can lead to 
 	   code duplication and maintenance issues.
 	   
 	Disadvantage :
 	1) it can introduce a lot of small objects in your system, which can increase the memory usage and 
 	   the complexity of debugging.
 	2) You also need to make sure that the decorators are compatible with the original object's interface and 
 	   behavior, otherwise you might encounter unexpected errors or inconsistencies.
 	3) The decorator pattern can make your code less readable and understandable, especially if you use too 
 	   many decorators or nest them too deeply. You might also lose some type information or functionality of 
 	   the original object, since the decorators only expose the common interface and hide the specific details.
 	   
 	 When to use :
 	 1) The decorator pattern is an ideal solution when you need to add or modify functionality of an object 
 	    without altering its core behavior or structure, or when creating different variations of an object's 
 	    behavior without creating a multitude of subclasses or inheritance hierarchies.
 	    EX: Examples of scenarios where the decorator pattern can be applied include adding logging, caching, or 
 	    validation to an existing service or component; adding encryption, compression, or encoding to an 
 	    existing data stream or file; and adding graphical effects, borders, or scrollbars to a user interface 
 	    component.   
 	          
 */

interface Pizza {
	public void prepare();
}

class BasePizza implements Pizza {
	public BasePizza() {
	}

	public void prepare() {
		System.out.println("Base pizza prepared");
	}
}

abstract class PizzaDecorator implements Pizza {
	private Pizza pizza;

	public PizzaDecorator(Pizza pizza) {
		this.pizza = pizza;
	}

	public void prepare() {
		pizza.prepare();
	}
}

class PepperoniPizza extends PizzaDecorator {
	PepperoniPizza(Pizza pizza) {
		super(pizza);
	}

	public void prepare() {
		super.prepare();
		System.out.println("Adding pepperoni on pizza");
	}
}

class CapsicumPizza extends PizzaDecorator {
	CapsicumPizza(Pizza pizza) {
		super(pizza);
	}

	public void prepare() {
		super.prepare();
		System.out.println("Adding capsicum on pizza");
	}
}

class OnionPizza extends PizzaDecorator {
	OnionPizza(Pizza pizza) {
		super(pizza);
	}

	public void prepare() {
		super.prepare();
		System.out.println("Adding onion on pizza");
	}
}

public class DecoratorPattern {
	public static void main(String[] args) {
		Pizza pizza = new OnionPizza(new PepperoniPizza(new BasePizza()));
		pizza.prepare();
	}

}
