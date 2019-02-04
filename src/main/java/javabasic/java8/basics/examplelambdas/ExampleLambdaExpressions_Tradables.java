package javabasic.java8.basics.examplelambdas;



import javabasic.java8.domain.Trade;

// Examples using Runnable Lambda Expressions

public class ExampleLambdaExpressions_Tradables {

	
	interface ITradable<Trade>{
		boolean check(Trade t);
	}
	

	private void testTrade(Trade t, ITradable<Trade> tradable) {
		System.out.println("Check :"+tradable.check(t));
	}

		
	public static void main(String[] args) throws Exception {
		ExampleLambdaExpressions_Tradables client = new ExampleLambdaExpressions_Tradables();
	
		// Using ITradable
		
		Trade googleTrade = new Trade("GOOGLE", 2000, "OPEN");
		
		client.testTrade(googleTrade, (trade) ->  trade.getQuantity() > 1000000? true:false);
		
		client.testTrade(googleTrade, (trade) ->  trade.getStatus().equals("OPEN")? true:false);
	}
	
}
