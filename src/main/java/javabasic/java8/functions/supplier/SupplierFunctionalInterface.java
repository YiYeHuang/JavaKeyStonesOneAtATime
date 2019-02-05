package javabasic.java8.functions.supplier;

import java.util.List;
import java.util.function.Supplier;

import javabasic.java8.domain.Trade;
import javabasic.java8.util.TradeUtil;

/* Supplier Functional Interface */
// no input, output data
public class SupplierFunctionalInterface {
	Supplier<String[]> fruitSupplier = () -> new String[]{"Apple", "Orange"};

	// could used to create date steam
	Supplier<List<Trade>> tradeSupplier = () -> TradeUtil.createTrades();
	private void testSupplier() {
		String[] fruits = fruitSupplier.get();
		for(String fruit: fruits){
			System.out.println("Fruit is: "+fruit);
		}
		
		List<Trade> trades = tradeSupplier.get();
		for(Trade trade: trades){
			System.out.println("Trade is: "+trade);
		}
		
	}

	public static void main(String[] args) {
		new SupplierFunctionalInterface().testSupplier();
	}

}
