package javabasic.java8.basics.prepostexamples;

import java.util.Comparator;

import javabasic.java8.domain.Trade;

public class Comparators {

	private void testComparators(Comparator<Trade> bigNumber) {
		Trade googleTrade = new Trade("GOOGLE",2000, "OPEN");
		Trade appleTrade = new Trade("APPLE",2300, "OPEN");
		int bigTradeQuantity = bigNumber.compare(googleTrade,appleTrade);
		
		System.out.println("Big Traded Quantity: "+bigTradeQuantity);
		
	}
	
	public static void main(String[] args) {
	
		Comparators comp = new Comparators();
		
		Comparator<Trade> bigNumber = new Comparator<Trade>(){
			@Override
			public int compare(Trade t1, Trade t2) {
				return t1.getQuantity()> t2.getQuantity() ? t1.getQuantity():t2.getQuantity();
			}
			
		};
		comp.testComparators(bigNumber);
		
		comp.testComparators((t1, t2) -> t1.getQuantity()> t2.getQuantity() ? t1.getQuantity():t2.getQuantity());
	}
}
