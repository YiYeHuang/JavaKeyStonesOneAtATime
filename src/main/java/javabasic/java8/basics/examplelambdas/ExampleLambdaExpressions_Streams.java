package javabasic.java8.basics.examplelambdas;

import java.util.Arrays;
import java.util.List;

import javabasic.java8.domain.Trade;

public class ExampleLambdaExpressions_Streams {

	public void testTradesStream(){
		List<Trade> trades = Arrays.asList(
				new Trade("APPLE",2000,"OPEN"), 
				new Trade("GOGGLE",40000,"SETTLED"),
				new Trade("MICRO",30000,"CLOSED"),
				new Trade("UNI",20000,"OPEN"));
			
		Trade bigTrade = trades.stream()
				.filter(this:: getQuantity)
				.filter(t -> t.getStatus().equals("OPEN"))
				.findFirst()
				.get();
			
		System.out.println("Big trade:"+bigTrade);
	}
	
	private boolean getQuantity(Trade t){
		return t.getQuantity() > 100000? true:false;
	}

	public static void main(String[] args) throws Exception {
		ExampleLambdaExpressions_Streams client = new ExampleLambdaExpressions_Streams();

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		numbers.forEach(e -> System.out.println(e));
		
		long count = numbers.stream()
				.filter(i -> i > 3)
				.count();

		System.out.println("Count: "+count);
		
		client.testTradesStream();
	}
}
