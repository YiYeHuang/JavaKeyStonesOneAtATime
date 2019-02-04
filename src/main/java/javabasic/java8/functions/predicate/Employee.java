package javabasic.java8.functions.predicate;

public class Employee {

	private String id = null;
	private int ratings = 10;
	public Employee(String empId) {
		this.setId(empId);
	}
	
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int rating) {
		this.ratings = rating;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
