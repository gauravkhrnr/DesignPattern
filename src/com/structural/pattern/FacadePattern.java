package com.structural.pattern;

/*
    Facade:
	Facade is a structural design pattern that provides a simplified interface to a library a framework or 
	any other complex set of classes
	
	Advantage : 
	1) We can reduce the third party complexity by providing interface 
	2) Besides a much simpler interface, there’s one more benefit of using this design pattern. 
	   It decouples a client implementation from the complex subsystem.
	
	Disadvantages :
	1) The facade pattern doesn’t force us to unwanted tradeoffs, because it only adds additional layers
	   of abstraction.
	2) Sometimes the pattern can be overused in simple scenarios, which will lead to redundant implementations.
	   
	When to uses :
	1) When you want to provide simple interface to a complex sub-system.
	2) When several dependencies exist between clients and the implementation classes of an abstraction.
	
	Best practice :
	1) First, you should identify the subsystems that have a high degree of complexity, diversity, or 
	variability, and that can benefit from being encapsulated behind a facade. 
	2) Second, you should design the facade to provide a simple, consistent, and coherent interface that meets 
	the needs and expectations of the clients. 
	3) Third, you should ensure that the facade does not expose or depend on any internal details or 
	implementation of the subsystems, and that it does not add any unnecessary functionality or logic. 
	4)Fourth, you should document and communicate the purpose, scope, and limitations of the facade and 
	the subsystems, and make them clear and transparent to the clients.
	
	
 */

interface MobileShop{
	public void getModel();
	public void getPrice();
}

class Iphone implements MobileShop {
	public void getModel() {
		System.out.println("Iphone 14");
	}
	public void getPrice() {
		System.out.println("70000");
	}
}

class Samsung implements MobileShop {
	public void getModel() {
		System.out.println("Galaxy tab");
	}
	public void getPrice() {
		System.out.println("45000");
	}
}

class Oneplus implements MobileShop {
	public void getModel() {
		System.out.println("Oneplas AZ");
	}
	public void getPrice() {
		System.out.println("35000");
	}
}

//Facade class
class ShopKeeper {
	private Iphone iphone;
	private Samsung samsung;
	private Oneplus oneplus;
	
	public ShopKeeper() {
		this.iphone = new Iphone();
		this.samsung = new Samsung();
		this.oneplus = new Oneplus();
	}
	
	public void iphoneSale() {
		iphone.getModel();
		iphone.getPrice();
	}
	
	public void samsungSale() {
		samsung.getModel();
		samsung.getPrice();
	}
	
	public void oneplusSale() {
		oneplus.getModel();
		oneplus.getPrice();
	}
}

public class FacadePattern {

	public static void main(String[] args) {
		ShopKeeper shopKeeper =  new ShopKeeper();
		shopKeeper.iphoneSale();
		shopKeeper.samsungSale();
		shopKeeper.oneplusSale();
	}

}
