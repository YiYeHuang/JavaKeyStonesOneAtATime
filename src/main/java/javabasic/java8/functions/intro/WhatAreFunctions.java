package javabasic.java8.functions.intro;

// What are functions?

// Check if a movie is classic
interface Movie{
	boolean isClassic(int movieId);
}

interface Person{
	boolean isEmployee (int empId);
}

interface Hospital{
	void admit(Patient patient);
}

interface Predicate<T>{
	boolean test(T t);
}

public class WhatAreFunctions {

}
