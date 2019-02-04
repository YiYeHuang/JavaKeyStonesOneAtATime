package javabasic.java8.streams.commonops;
import java.util.List;
import java.util.Optional;

import javabasic.java8.domain.Trade;
import javabasic.java8.util.TradeUtil;

// Finding and Matching methods
public class Finders {

	List<Trade> trades = TradeUtil.createTrades();
	
	private void testFindFirst() {

		Optional<Trade> firstTrade = trades.stream()
			.filter(Trade::isBigTrade)
			.findFirst();
		
		System.out.println("First trade: "+firstTrade.get());
	}
	
	private void testFindAny() {
		Optional<Trade> anyTrade = trades.stream()
				.filter(Trade::isBigTrade)
				.findAny();
			
			System.out.println("First trade: "+anyTrade.get());
		
	}
	
	private void testAnyMatch() {
		boolean rottenTrade = trades.stream().anyMatch(t -> t.getStatus().equals("ROTTEN"));
		System.out.println("Rotten trade?:"+rottenTrade);
	}
	
	private void testAllMatch() {
		boolean ibmTrade = trades.stream()
				.allMatch(t -> t.getInstrument().equals("IBM"));
		
		System.out.println("Rotten trade?:"+ibmTrade);	
	}
	
	private void testNoneMatch() {
		
		boolean cancelledTrade = trades.stream().noneMatch(Trade::isCancelledTrade);
		System.out.println("Is cancelled trade?: "+cancelledTrade);
	}
	
	
	public static void main(String[] args) {
		new Finders().testFindAny();
		new Finders().testFindFirst();
		new Finders().testAnyMatch();
		new Finders().testAllMatch();
		new Finders().testNoneMatch();
	}
}
