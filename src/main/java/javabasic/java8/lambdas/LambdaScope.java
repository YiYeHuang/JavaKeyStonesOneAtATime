package javabasic.java8.lambdas;

public class LambdaScope {

	private static String className = "Class Name";
	
	public interface IComponent{
		String name();
	}
	
	private void testComp(IComponent comp) {
		System.out.println("Name: "+comp.name());
	}
	
	private void testComp(String name){
		IComponent c = () -> name;
		System.out.println("Name: "+c.name());
	}
	
	//don't declare a local variable in lambda!
	
	private void testLocalVariable() {
		final String myName = "Modified Name";;
		
		IComponent cc = () -> {
			return myName;
		};
	}

	public static void main(String[] args) {
		LambdaScope scope = new LambdaScope();
		
		IComponent comp = () -> "Default Name";
		scope.testComp(comp);
		String name = "Main Name";
		IComponent comp1 = () -> {return name;};
		scope.testComp(comp1);
		IComponent comp2 = () -> {return className;};
		scope.testComp(comp2);
	}
	
}
