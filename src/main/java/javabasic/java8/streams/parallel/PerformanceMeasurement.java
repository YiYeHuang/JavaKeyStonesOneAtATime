package javabasic.java8.streams.parallel;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import javabasic.java8.domain.Trade;
import javabasic.java8.util.TradeUtil;

//Measurement of parallel operations 
public class PerformanceMeasurement {

	public void sumTradesInSerial(){
		List<Trade> trades = TradeUtil.createTrades(100);
		Instant start = Instant.now();
		// Sequential mode
		trades.stream()
		.map(Trade::getQuantity)
		.reduce(Integer::sum);
		
		Instant end = Instant.now();
		Duration d = Duration.between(start, end);
		System.out.printf("%s %d %s" , "Aggregating trades took ", d.toMillis()," ms in Seqential mode");
	}
	
	public void sumTradesInParallell(){
		List<Trade> trades = TradeUtil.createTrades(100);
		Instant start = Instant.now();
		// Parallel code
		// Sequential mode
				trades.stream()
				.parallel()
				.map(Trade::getQuantity)
				.reduce(Integer::sum);
		
		Instant end = Instant.now();
		Duration d = Duration.between(start, end);
		System.out.printf("%s %d %s" , "Aggregating trades took ", d.toMillis()," ms in Parallel mode");
	}

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new PerformanceMeasurement().sumTradesInSerial();
		new PerformanceMeasurement().sumTradesInParallell();
	}

}
