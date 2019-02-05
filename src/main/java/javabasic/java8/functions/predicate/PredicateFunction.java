package javabasic.java8.functions.predicate;

import java.util.function.Predicate;

// Function for checking a boolean condition, chained with or, and, test
public class PredicateFunction {

	Predicate<Employee> bonusLambda = (emp)-> emp.getRatings()>10?true:false;
	Predicate<Employee> execLambda = emp -> emp.getId().startsWith("E99")?true:false;
	
	Predicate<String> emptyString = s -> s.isEmpty();
	
	public static void main(String[] args) {
	}

}
