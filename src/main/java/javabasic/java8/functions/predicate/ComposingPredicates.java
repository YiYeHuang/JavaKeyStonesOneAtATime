package javabasic.java8.functions.predicate;

import java.util.List;
import java.util.function.Predicate;

import javabasic.java8.domain.Trade;
import javabasic.java8.util.TradeUtil;

// Composing or chaining Predicates
public class ComposingPredicates {

	private void testPredicates(Trade trade) {
		/* LOGICAL OR */
		
		Predicate<Trade> newTrade = t -> t.getStatus().equals("NEW");
		Predicate<Trade> cancelledTrade = t -> t.getStatus().equals("CANCEL");
		Predicate<Trade> newOrCancelledTrade = newTrade.or(cancelledTrade);

		/* LOGICAL AND and OR */
		Predicate<Trade> bigTrade = t -> t.isBigTrade();
		
		Predicate<Trade> newOrCancelledButBigTrade = newTrade.or(cancelledTrade).and(bigTrade);

		/* NEGATE */
		Predicate<Trade> notANewTrade = newTrade.negate();
		
		/* EQUALS */
		
		List<Trade> trades = TradeUtil.createTrades();
		
		Trade t1 = new Trade("GOOG", 200000, "CANCEL");
		
		Predicate<Trade> p1 = Predicate.isEqual(t1);
		
		for(Trade t : trades){
			if(p1.test(t))
				System.out.println("Matching trade: "+t);
		}
	}

	public static void main(String[] args) {
		ComposingPredicates test = new ComposingPredicates();

	}
}
