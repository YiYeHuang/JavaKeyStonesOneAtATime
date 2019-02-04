package javabasic.java8.interfaces.inheritance;

// Multiple Inheritance

interface Engine{
	default String model(int id){
		return "DEFAULT ENGINE";
	}
}

interface Vehicle extends Engine{
	default String model(int id){
		return "DEFAULT ENGINE";
	}
}
class Car implements Engine, Vehicle{
	
}


public class MultipleInheritance {

	public static void main(String[] args) {
	}

}
