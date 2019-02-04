package javabasic.java8.lambdas;

import javabasic.java8.domain.Trade;

public class TradeUtil {

	ITradable<Trade> oldTradable = (Trade tt) -> {
		System.out.print("Checking if big trade..");
		return (tt.getQuantity() > 1000000);
	};
	
	public void testTradeOld(Trade t){
		System.out.println(oldTradable.check(t));
	}

	
	public void testTrade(Trade t, ITradable<Trade> tr){
		System.out.println(tr.check(t));
	}
	
	public static void main(String[] args) {
		TradeUtil util = new TradeUtil();
		Trade t = new Trade("GOOGLE",2000, "OPEN");
		
		util.testTradeOld(t);
		
		ITradable<Trade> tradable = (Trade tt) -> {
			System.out.print("Checking if big trade..");
			return (tt.getQuantity() > 1000000);
		};

		ITradable<Trade> openTrade = (Trade tt) -> {
			System.out.print("Checking if open trade..");
			return (tt.getStatus().equalsIgnoreCase("OPEN"));
		};

		
		util.testTrade(t, tradable);
		util.testTrade(t,openTrade);
		util.testTrade(t, (Trade trade) -> {
			System.out.print("Checking if it's MS instrument..");
			return trade.getInstrument().equals("MS");
		});
	}

}
