package javabasic.java8.interfaces.inheritingbehaviour;

// Inheriting behavior from multiple interfaces

interface Engine{
	default String make(){
		return "DEFAULT MAKE";
	}
}

interface Vehicle{
	default String model(){
		return "DEFAULT MODEL";
	}
}

class Car implements Engine, Vehicle{
	String makeAndModel(){
		return Engine.super.make()+
			Vehicle.super.model();
	}
}

public class InheritingBehaviour {

	public static void main(String[] args) {

	}

}
