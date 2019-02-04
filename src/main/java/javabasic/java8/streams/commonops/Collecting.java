package javabasic.java8.streams.commonops;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toMap;

import javabasic.java8.domain.Movie;
import javabasic.java8.domain.Trade;
import javabasic.java8.util.MovieUtil;
import javabasic.java8.util.TradeUtil;

// Collecting operations
public class Collecting {
	List<Trade> trades = TradeUtil.createTrades();
	
	private void collectIntoAList() {
		List<Trade> tradeCollection = 
				trades.stream().collect(toList());
		
		System.out.println(tradeCollection);
	}
	private void collectIntoASet() {
		Set<Trade> tradeCollection = 
				trades.stream().collect(toSet());
		
		System.out.println(tradeCollection);
	}
	private void collectIntoToAMap() {
		List<Movie> movies = MovieUtil.createMovies();
		
		Map<String, Movie> movieMap = 
				movies.stream().collect(toMap(Movie::getName, movie -> movie));
		System.out.println(movieMap);
	}
	
	public static void main(String[] args) {
		new Collecting().collectIntoAList();
		new Collecting().collectIntoASet();
		new Collecting().collectIntoToAMap();
	}

}
