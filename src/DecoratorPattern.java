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
