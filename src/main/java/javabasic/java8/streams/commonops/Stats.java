package javabasic.java8.streams.commonops;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;
// Range, Min, Max and Average functions

public class Stats {
	IntStream streamOfInts = IntStream.range(1, 100);

	private void range() {
		IntStream ints = IntStream.rangeClosed(10, 20);
		ints.forEach(System.out::println);
	}
	//Min, Max and Average
	private void testMaxAndMin() {
		IntStream ints = IntStream.rangeClosed(10, 20);
		OptionalInt max = ints.max();
		System.out.println("Max: "+max.getAsInt());
		
		ints = IntStream.rangeClosed(10, 20);
		OptionalInt min = ints.min();
		System.out.println("Min: "+min.getAsInt());
		
		ints = IntStream.rangeClosed(10, 20);
		OptionalDouble avg = ints.average();
		System.out.println("Min: "+avg.getAsDouble());
		
		
	}

	public static void main(String[] args) {
		new Stats().range();
		new Stats().testMaxAndMin();
	}
}
