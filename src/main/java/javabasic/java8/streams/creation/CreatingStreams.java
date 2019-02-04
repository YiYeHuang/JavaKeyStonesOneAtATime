package javabasic.java8.streams.creation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import javabasic.java8.domain.Movie;
import javabasic.java8.util.MovieUtil;

//Creating Streams
public class CreatingStreams {

	// From Collections
	private void testCollectionStreams() {
		List<Movie> movies = MovieUtil.createMovies();
		
		movies.stream().forEach(System.out::println);
	}	
	// Empty stream
	private void testEmptyStreams() {
		Stream<Movie> moviesEmptyStream = Stream.empty();
		System.out.println("Empty stream:"+moviesEmptyStream.count());
	}
	// From values/arrays
	private void testStreamsFromValues() {
		Stream<String> movieNames = Stream.of("Gods Must Be Crazy", "Fiddler On The Roof");
		System.out.println(movieNames.count());
		
		String[] movieNames2 = {"Gods Must Be Crazy", "Fiddler On The Roof"};
		Stream<String> mNames2 = Stream.of(movieNames2);
		
	}
	
	// From a file
	private void testFileStream() {
		try {
			Stream<String> fileLines = Files.lines(Paths.get("/Users/mkonda/tmp.txt"));
			fileLines.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void testGenerateIterateStreams() {
		Stream<Double> randomStream = Stream.generate(Math::random);
//		randomStream.forEach(System.out::println);
		
		//iterate
		Stream<Integer> wholeNumbers = Stream.iterate(1,i -> i+1);
//		wholeNumbers.forEach(System.out::println);
		
	}
	public static void main(String[] args) {
		new CreatingStreams().testEmptyStreams();
		new CreatingStreams().testStreamsFromValues();
		new CreatingStreams().testCollectionStreams();
		new CreatingStreams().testGenerateIterateStreams();
		new CreatingStreams().testFileStream();
	}

}
