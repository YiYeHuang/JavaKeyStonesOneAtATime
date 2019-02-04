package javabasic.java8.streams.commonops;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;

import javabasic.java8.domain.Employee;
import javabasic.java8.util.EmployeeUtil;

// Partitioning streams
public class Partitioning {

	List<Employee> employees = EmployeeUtil.createDetailedEmployees();
	
	private void partitionByExecutives() {
		Map<Boolean, List<Employee>> empPartition = 
				employees.stream().collect(Collectors.partitioningBy(Employee::isExecutive));
	
		System.out.println(empPartition);
	}
	
	private void partitioningAndGrouping() {
		Map<Boolean, Map<String, List<Employee>>> execEmployees = 
				employees.stream()
			.collect(partitioningBy((Employee::isExecutive), groupingBy(Employee::getDepartment)));
		
		for(Boolean b: execEmployees.keySet()){
			System.out.println(b+" --> "+execEmployees.get(b));
		}

	}
	
	private void partitioningMultiLevel() {
		Map<Boolean, Map<Boolean, List<Employee>>> execEmployees = 
				employees.stream()
			.collect(partitioningBy((Employee::isExecutive), partitioningBy(Employee::isSenior)));
		
		for(Boolean b: execEmployees.keySet()){
			System.out.println(b+" ==> "+execEmployees.get(b));
		}

	}
	
	public static void main(String[] args) {
		new Partitioning().partitionByExecutives();
		new Partitioning().partitioningAndGrouping();
		new Partitioning().partitioningMultiLevel();
	}
}
