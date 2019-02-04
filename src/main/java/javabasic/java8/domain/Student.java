package javabasic.java8.domain;

public class Student {
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	private String name = null;
	private Teacher teacher = null;
	
	public boolean hasTeacher() {
		return true;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", teacher=" + teacher + "]";
	}
	

}
