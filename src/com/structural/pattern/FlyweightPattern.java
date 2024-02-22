package com.structural.pattern;

import java.util.HashMap;
import java.util.Map;

/*
  Flyweight :
  - The flyweight pattern is based on the idea of reusing existing objects instead of creating new ones 
  	whenever possible. The objects that can be shared are called flyweightss.
  - The objects that use the flyweights are called contexts, and they contain the extrinsic state 
  	that is specific and variable for each instance. 
  - The flyweight pattern separates the intrinsic and extrinsic state of the objects, and provides a factory that 
  	manages the creation and access of the flyweights.
  -	So that flyweight Design Pattern reduces the objects creation, decreases memory footprint and 
  	increase the performance.
  	
  	Advantages :
  	The main benefit of the flyweight pattern is that it can improve the system performance by reducing 
  	the memory usage and the number of objects that need to be created, transferred, and processed.
    Ex:
    If you are designing a text editor that needs to display a large document with different fonts and 
    styles, you can use the flyweight pattern to create a single object for each character with its intrinsic 
    state (such as ASCII code and font size), and use the contexts to store the extrinsic state 
    (such as position and color). This way, you can avoid creating thousands of objects for each character, and 
    save memory and rendering time.
  
   Disavdvantages :
   1) It can increase the complexity of the system design, as you need to separate the intrinsic and 
      extrinsic state of the objects, and implement a factory that manages the flyweights.
   2) It can introduce some overhead in accessing and updating the extrinsic state of the contexts, 
      as you need to pass it as a parameter or store it in an external data structure. Moreover, 
      the flyweight pattern is not suitable for every system, as it depends on the nature and 
      frequency of the objects and their state changes.    
      
   When to use :
   To implement the flyweight pattern, you need to identify the objects that can be shared as flyweights and 
   extract their intrinsic state. Then, create a flyweight class that encapsulates the intrinsic state and 
   defines the common behavior of the flyweights. Additionally, create a flyweight factory that creates and 
   manages the flyweights, and ensures that only one instance of each flyweight exists. 
   Moreover, identify the objects that use the flyweights as contexts, and store their extrinsic state separately.
   Finally, modify the contexts to use the flyweight factory to access and manipulate the flyweights.
   
   Example :
   let's consider a system that needs to display a map with thousands of trees of different types and locations. 
   Without the flyweight pattern, you would need to create an object for each tree with its type and location, 
   which would consume a lot of memory and rendering time. With the flyweight pattern, you can create a flyweight
   class for the tree type, which contains the intrinsic state such as the name, shape, and texture of the tree. 
   Then, you can create a flyweight factory that creates and caches the flyweight objects for each tree type.
   Finally, you can create a context class for the tree location, which contains the extrinsic state 
   such as the coordinates and altitude of the tree. The context class uses the flyweight factory to get 
   the flyweight object for the tree type, and renders the tree accordingly.
 */

interface VehicleI{
	public void start();
	public void stop();
	public Color getColor();
}

class Color{
	private String name;
	
	public Color(String name) {
		this.name = name;
	}
	public String getColorName() {
		return name;
	}
}

class EngineI {
	public void start() {
		System.out.println("Start the Engine");
	}
	public void stop() {
		System.out.println("Stop the Engine");
	}
}

class VehicalFactory{
	private static Map<Color, VehicleI> vehiclesCache  = new HashMap<>();

	public static VehicleI createVehicle(Color color) {
		VehicleI newVehicle = vehiclesCache.computeIfAbsent(color, newColor -> { 
			EngineI newEngine = new EngineI();
	        return new Truck(newEngine, newColor);
	    });
	    return newVehicle;
	}
}

class Truck implements VehicleI{
	private EngineI engine;
	private Color color;
	
	public Truck(EngineI engine,Color color) {
		this.engine = engine;
		this.color = color;
	}
	
	public void start() {
		engine.start();
	}
	public void stop() {
		engine.stop();
	}
	public Color getColor() {
		return new Color(color.getColorName());
	}
}

public class FlyweightPattern {
	public static void main(String[] args) {
		VehicalFactory factory = new VehicalFactory();
		Color color = new Color("Blue");
		VehicleI newVehicle = factory.createVehicle(color);
		System.out.println(newVehicle.getColor().getColorName());
		newVehicle.start();
		
		Color color2 = new Color("Blue");
		VehicleI newVehicle2 = factory.createVehicle(color);
		
		//Both are same object because blue vehicle already created and stored in cache
		System.out.println(newVehicle);
		System.out.println(newVehicle2);
	}
}
