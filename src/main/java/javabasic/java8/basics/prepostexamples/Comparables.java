package javabasic.java8.basics.prepostexamples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Comparables {

	class Car implements Comparable<Car>{

		private int mileage = 0;
		
		public Car(int miles){
			this.mileage = miles;
		}
		@Override
		public int compareTo(Car car) {
			System.out.println("Sorting..");
			if(this.getMileage() < car.getMileage())
				return 0;
			else 
				return 1;
		}

		public int getMileage() {
			return mileage;
		}

		public void setMileage(int mileage) {
			this.mileage = mileage;
		}
		@Override
		public String toString() {
			return "Car [mileage=" + mileage + "]";
		}
	}
	private void testComparable() {
		List<Car> cars = new ArrayList<Car>();
		
		Car c = new Car(30000);
		Car d = new Car(20020);
		cars.add(c);
		cars.add(d);
		System.out.println("Before sorting: "+cars);
		
		Collections.sort(cars);
		
		System.out.println("After sorting: "+cars);
		
	}

	public static void main(String[] args) {
		new Comparables().testComparable();
	}

}
