package com.structural.pattern;
import java.util.ArrayList;
import java.util.List;

/*
  Composite:
  - It allows you to compose objects into tree structures and then work with these structures as if they were individual objects. 
  - It is useful when you want to represent part-whole hierarchies of objects and treat them uniformly. 
  
  When to use :
  Using the Composite pattern makes sense only when the core model of your app can be represented as a tree.
  For example: imagine that you have two types of objects: Folders and files. A Folder can contain several files as well as a number of 
  sub folders. These sub folder can also hold some files or even sub folder, and so on.
  Say you decide to create an file system that uses these classes. file system could contain files with size, as well as 
  sub folder may be or may not be with size and other files.... How would you determine the total size of such an file system?
  
  Advantages :
  1) You can work with complex tree structures more conveniently: use polymorphism and recursion to your advantage.
  2) Open/Closed Principle. You can introduce new element types into the app without breaking the existing code, which now works with 
     the object tree.
  
  Disadvantages :
  1) It might be difficult to provide a common interface for classes whose functionality differs too much. In certain scenarios, 
     you’d need to overgeneralize the component interface, making it harder to comprehend.
  2) The delegation of operations adds additional run-time costs   
 */

//In below example we composed the object into tree structure(file system)
interface FileSystem{
	public double getSize();
}

class File implements FileSystem {
	private final double size;
	public File(double size){
		this.size = size;
	}
	
	public double getSize() {
		return size;
	}
}

class Folder implements FileSystem {
	private double size = 0;
	private List<FileSystem> childrens =  new ArrayList<FileSystem>();
	public Folder(double size){
		this.size = size;
	}
	public void addChild(FileSystem file) {
		childrens.add(file);
	}
	
	public double getSize() {
		double size = 0;
		for (FileSystem fileSystem : childrens) {
			size += fileSystem.getSize();
		}
		return size + this.size;
	}
}
public class CompositePattern {

	public static void main(String[] args) {
		Folder root = new Folder(0.5);
		root.addChild(new File(5));
		root.addChild(new File(2));
		Folder subFolder1 = new Folder(0);
		Folder subFolder2 = new Folder(0);
		Folder subFolder3 = new Folder(0);
		subFolder3.addChild(new File(8));
		subFolder2.addChild(subFolder3);
		subFolder2.addChild(new File(5));
		subFolder1.addChild(subFolder2);
		root.addChild(subFolder1);
		System.out.println("Total size is:"+root.getSize());
	}

}
