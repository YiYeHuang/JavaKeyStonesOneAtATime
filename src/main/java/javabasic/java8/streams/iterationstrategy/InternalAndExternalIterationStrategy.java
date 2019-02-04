package javabasic.java8.streams.iterationstrategy;

import java.util.ArrayList;
import java.util.List;

import javabasic.java8.domain.Movie;
import javabasic.java8.util.MovieUtil;

// Internal and External iteration strategies
public class InternalAndExternalIterationStrategy {
	List<Movie> movies = MovieUtil.createMovies();
	List<Movie> top2Classics = new ArrayList<Movie>(10);

	private void testExternalIteration1() {
		// Logic for finding top 2 classic movies
		for(Movie m: movies){
//			System.out.println("Inside external iteration");
			if(m.isClassic()){
				top2Classics.add(m);
			}
		}
		System.out.println("Top 2 classics (Ext Iteration): " + top2Classics);
	}
	private void testInternalIteration1() {
		movies.parallelStream()
		.filter(Movie::isClassic)
		.map(Movie::getName)
		.limit(2)
		.forEach(System.out::println);
		
	}

	private void testInternalIteration2() {

	}

	private void testInternalIteration3() {
	}

	public static void main(String[] args) {
		new InternalAndExternalIterationStrategy().testInternalIteration1();
		new InternalAndExternalIterationStrategy().testInternalIteration2();
		new InternalAndExternalIterationStrategy().testInternalIteration3();
		new InternalAndExternalIterationStrategy().testExternalIteration1();
	}

}
