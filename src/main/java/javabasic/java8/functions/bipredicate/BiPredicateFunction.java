package javabasic.java8.functions.bipredicate;

import java.util.function.BiPredicate;

import javabasic.java8.domain.Employee;
import javabasic.java8.domain.Manager;

// TWO argument function: BiPredicate
// Predict with two parameter
public class BiPredicateFunction {

	BiPredicate<Employee, Manager> empManagerPredicate = (emp, manager) -> emp
			.getManager().equals(manager) ? true : false;
	
	BiPredicate<Employee, Manager> managerHasAssistantPredicate = (emp, manager) -> manager
			.getPersonalAssistant().equals(emp) ? true : false;
	
	BiPredicate<Employee, Manager> isPA = empManagerPredicate.and(managerHasAssistantPredicate);
	
	BiPredicate<Employee, Manager> isPA2 = empManagerPredicate.or(managerHasAssistantPredicate);
	
	BiPredicate<Employee, Manager> notAManagerPredicate = empManagerPredicate.negate();

	private void testBiPredicate(Employee emp, Manager manager) {
		boolean isManager = empManagerPredicate.test(emp, manager);
		System.out.println("Is manager? " + isManager);
	}

	private void testNegate(Employee emp, Manager manager) {
	}

	private void testAnd(Employee emp, Manager manager) {
	}

	private void testOr(Employee emp, Manager manager) {
	}

	public static void main(String[] args) {
		Employee emp = new Employee(99);
		Manager manager = new Manager();
		emp.setManager(manager);
		// this returns true because emp's manager is set
		new BiPredicateFunction().testBiPredicate(emp, manager);

		// this returns false, because manager is different
		Manager manager2 = new Manager();
		// new BiPredicateFunction().testBiPredicate(emp, manager2);

		// testNegate
		// new BiPredicateFunction().testNegate(emp, manager);

		// testAnd
		// new BiPredicateFunction().testAnd(emp, manager);

	}

}
