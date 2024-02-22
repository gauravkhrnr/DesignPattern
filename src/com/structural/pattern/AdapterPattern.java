package com.structural.pattern;

/*
  Adapter:
  Acts as a connector between two incompatible interfaces
  or Converts the interface of a class into another interface that a client wants
  
  Advantage :
  1) It allows two or more previously incompatible objects to interact
  2) It allows re-useability of existing functionality.
  3) Adapter compared to Class Adapter is loose coupling of client and Adaptee
  
  Disadvantage :
  1) One of the drawbacks of using the adapter pattern is that it introduces additional overhead and 
     complexity to your code. The adapter class acts as an intermediary between the two interfaces, 
     which means that it has to perform extra operations to convert the data, handle exceptions, and 
     delegate calls. This can increase the memory usage, execution time, and maintenance cost of your code.
  
  3) It creates coupling and dependency between the two interfaces. The adapter class depends on both the 
     original interface and the adapted interface, which means that any changes in either of them can affect 
     the adapter class and the rest of the code that uses it. For example, if the original interface adds a 
     new method or modifies an existing one, the adapter class may need to be updated or extended to reflect 
     the change. Similarly, if the adapted interface changes its specification or implementation, the adapter 
     class may need to be modified or replaced to ensure compatibility.
  
  4) It can make testing and debugging more difficult. The adapter class adds another layer of abstraction and 
     indirection to your code, which can make it harder to trace the source of errors, identify the root cause 
     of problems, or verify the correctness of the output. Moreover, the adapter class may have its own logic 
     and behavior that need to be tested and validated separately from the original and adapted interfaces. 
     This can increase the scope and complexity of your testing and debugging process.
  
  When to use :
  1) When an object needs to utilize an existing class with an incompatible interface.
  2) When you want to create a reusable class that cooperates with classes which don't have compatible 
     interfaces.
     
  Example scenarios :

	1) The adapter design pattern is useful in situations where existing code needs to be reused with a new 
	   system or library that has a different interface. For example, if an application is designed to work 
	   with a specific database library, but a new library with a different interface is introduced, an adapter 
	   class can be used to translate between the two interfaces without having to modify the existing 
	   application code.
	
	2) Another suitable scenario to use the adapter design pattern is when working with legacy code that 
	   cannot be easily modified. An adapter class can be created to adapt the legacy code to a new system 
	   without having to modify the existing codebase.
 */


class AnalyticLibrary{
	public void displayGraph(CustomLibraryObject customLibraryObject) {
		System.out.println("Displayed graph based on the custom object");
	}
}

class CustomLibraryObject{
	private String jsonData;
	CustomLibraryObject(String jsonData){
		this.jsonData = jsonData;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
}

interface DataVisulizer{
	public void displayGraph(String jsonData); 
}

class LibraryAdapter implements DataVisulizer{ 
	AnalyticLibrary analyticLibrary; //adaptee
	public LibraryAdapter(AnalyticLibrary analyticLibrary){
		this.analyticLibrary = analyticLibrary;
	}
	
	public void displayGraph(String jsonData) {
		CustomLibraryObject customLibraryObject =  getCustomLibraryObject(jsonData);
		analyticLibrary.displayGraph(customLibraryObject);
	}
	
	private CustomLibraryObject getCustomLibraryObject(String jsonData) {
		//convert json data to library object
		return new CustomLibraryObject(jsonData);
	}
}

public class AdapterPattern {
	public static void main(String[] args) {
		String jsonData = "{}";
		AnalyticLibrary library = new AnalyticLibrary();
		LibraryAdapter adapter = new LibraryAdapter(library);
		adapter.displayGraph(jsonData);
	}
}
