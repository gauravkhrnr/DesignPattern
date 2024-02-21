package com.structural.pattern;
import java.util.ArrayList;
import java.util.List;

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
