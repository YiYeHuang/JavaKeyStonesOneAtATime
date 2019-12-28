package javabasic.designPattern.observer;

public class ConcreteObserver implements Observer {
	private String observerState;

	@Override
	public void update(String state) {

		observerState = state;
		System.out.println("State: "+observerState);
	}
}
