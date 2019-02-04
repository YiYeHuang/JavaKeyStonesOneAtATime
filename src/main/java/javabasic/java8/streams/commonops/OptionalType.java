package javabasic.java8.streams.commonops;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javabasic.java8.domain.Student;
import javabasic.java8.domain.Trade;
import javabasic.java8.util.TradeUtil;
//Optional type
public class OptionalType {
	List<Trade> trades = TradeUtil.createTrades();
	
	private void creatingOptionals() {
		Student student = new Student();
		Optional<Student> studentOptional = Optional.of(student);
		System.out.println(studentOptional.get());
		
		studentOptional.ifPresent(System.out::println);
		student = null;
		Optional<Student> studentOptional2 = Optional.ofNullable(student);
		System.out.println("Student can't be null:"+studentOptional2);
		
	}

	private void ifElseOptionals()  {
		Student student = null;
		Student defaultStudent = new Student();
		defaultStudent.setName("Default John");
		Optional<Student> studentOptional = Optional.ofNullable(student);
		String name = studentOptional.orElse(defaultStudent).getName();
		System.out.println("Get Name: "+name);
		
	}
	
	private void ifElseThrowOptionals() throws Exception {
		Student student = null;
		Optional<Student> studentOptional = Optional.ofNullable(student);
		studentOptional.orElseThrow(Exception :: new);

	}
	
	private void filterMapOptionals() {
		Student st = new Student();
		st.setName("John Lingham");
		
		Optional<Student> stOptional = Optional.of(st);
		stOptional.filter(student -> student.hasTeacher())
			.ifPresent(System.out::println);
		stOptional.map(s -> s.getName()).ifPresent(System.out::println);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void attendeesOptional() {
		List<Integer> attendees = Arrays.asList(22, 34, 56, 19);
		Optional<Integer> attendeesOptional = attendees.stream().reduce(Integer::sum);
		
		System.out.println(attendeesOptional.get());
	}

	private void noAttendeesOptional() {
		List<Integer> attendees = Arrays.asList();
		Optional<Integer> noAttendees = attendees.stream().reduce(Integer::sum);
		
		if(noAttendees.isPresent())
			System.out.println(noAttendees.get());
		
	}

	public static void main(String[] args)  {
		new OptionalType().attendeesOptional();
		new OptionalType().noAttendeesOptional();
		new OptionalType().creatingOptionals();
		try {
			new OptionalType().ifElseOptionals();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			new OptionalType().ifElseThrowOptionals();
		} catch (Exception e) {
			System.out.println("Expected exception:"+e);
		}
		
		new OptionalType().filterMapOptionals();
	}

}
