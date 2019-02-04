package javabasic.java8.util;

import java.util.ArrayList;
import java.util.List;

import javabasic.java8.domain.Actor;
import javabasic.java8.domain.Movie;

public class MovieUtil {
	public static List<Movie> createMovies() {

		List<Movie> movies = new ArrayList<>();

		Movie movie = new Movie("Gods Must Be Crazy", false,"");
		movies.add(movie);
		movie = new Movie("Fiddler On The Roof", true, "");
		movies.add(movie);
		movie = new Movie("Sound of Music", true, "");
		movies.add(movie);
		movie = new Movie("Ben-Hur", true,"");
		movies.add(movie);
//		movie = new Movie("Jurassic Park", false,"Steven Spielberg");
//		movies.add(movie);
		movie = new Movie("Jurassic Park", false,"Steven Spielberg");
		movies.add(movie);
		movie = new Movie("ET", false,"Steven Spielberg");
		movies.add(movie);
		movie = new Movie("Saving Private Ryan", false,"Steven Spielberg");
		movies.add(movie);
		movie = new Movie("Catch Me If You Can", false,"Steven Spielberg");
		movies.add(movie);
		return movies;

	}

	public static List<Movie> createMoviesAndActors() {

		List<Movie> movies = new ArrayList<>();
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;
		Movie movie = null;

		
		movie = new Movie("Sound Of Music");
		actor = new Actor("Julie Andrews");
		actors.add(actor);
		actor = new Actor("Christopher Plummer");
		actors.add(actor);
		movie.setActors(actors);
		
		movies.add(movie);
		
		movie = new Movie("Jurassic Park");
		actor = new Actor("Sam Neil");
		actors.add(actor);
		actor = new Actor("Jeff Goldblum");
		actors.add(actor);
		movie.setActors(actors);
		
		movies.add(movie);
		
		return movies;

	}

	
}
