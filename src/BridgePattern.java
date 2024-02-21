interface Engine{
	public String reFill();
}

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
