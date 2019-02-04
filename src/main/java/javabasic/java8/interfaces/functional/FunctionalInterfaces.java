package javabasic.java8.interfaces.functional;

// Functional Interfaces
public class FunctionalInterfaces {
	// Functional interface
	@FunctionalInterface
	interface Multiplier {
		int multiply(int i, int j);
	};
	// Lambda expression
	Multiplier multiplierLambda = (p, q) -> p*q;
	public static void main(String[] args) {
		FunctionalInterfaces client = new FunctionalInterfaces();

	}

}
