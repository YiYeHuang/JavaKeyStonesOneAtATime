package javabasic.java8.streams.commonops;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;

import javabasic.java8.domain.Trade;
import javabasic.java8.util.TradeUtil;
// Reducing Operation
public class Reducing {
	List<Trade> trades = TradeUtil.createTrades();
	List<Integer> ints = Arrays.asList();

	private void tradeInstrumentsList() {
		Optional<String> instList = trades.stream()
			.map(Trade::getInstrument)
		.reduce((a,b)-> a+","+b);
		
		System.out.println(instList);
	}
	
	private void schoolStaff() {
		List<Integer> ints = Arrays.asList(11, 13, 12, 15);
		int staff = ints.stream().reduce(9, (i,j) -> i+j);
		System.out.println("Total staff: "+staff);
		
	}

	private void tradeQuantitySum() {
		Optional<Integer> totalQuantity = 
				trades.stream()
					  .map(Trade::getQuantity)
					  .reduce((a,b) -> a+b);
			System.out.println("Total quantity: "+totalQuantity.get());
	}
	
	public static void main(String[] args) {
		new Reducing().schoolStaff();
		new Reducing().tradeQuantitySum();
		new Reducing().tradeInstrumentsList();
	}

}
