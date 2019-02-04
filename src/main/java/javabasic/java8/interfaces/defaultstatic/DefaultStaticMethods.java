package javabasic.java8.interfaces.defaultstatic;

@FunctionalInterface
interface Employee {
	// abstract method
	Employee find(int id);

	// default method
	default boolean isExec(int id){
		// logic to find if exec goes here..
		return true;
	}
	
	// static method
	static String getDefaultCountry(){
		return "UK";
	}
}

// Default and Static Methods
public class DefaultStaticMethods {

	public static void main(String[] args) {
		
		class EmployeeImpl implements Employee{

			@Override
			public Employee find(int id) {
				boolean executive = isExec(id);
				return null;
			}
			
		}
		
		EmployeeImpl impl = new EmployeeImpl();
		impl.isExec(1234);
		
		// accessing static method
		String defaultCountry = Employee.getDefaultCountry();
		
	}

}









