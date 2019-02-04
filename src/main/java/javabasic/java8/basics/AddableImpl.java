package javabasic.java8.basics;

public class AddableImpl {

	public void test(String s1, String s2, Addable a) {
		String result = a.operate(s1, s2);
		System.out.println("Result: "+result);
	}

	public static void main(String[] args) {
		AddableImpl impl = new AddableImpl();
		String s1 = "Java 8 ", s2 = "Lambdas";

		// 1. Using a class implementation
		Addable a1 = new Addable(){
			@Override
			public String operate(String s1, String s2) {
				return s1.concat(s2);
			}
		};
		impl.test(s1, s2, a1);

		//2. using an anonymous class
		impl.test(s1,s2,new Addable(){
			@Override
			public String operate(String s1, String s2) {
				return s1.concat(s2);
			}
		});
		impl.test(s1,s2, new Addable(){
			@Override
			public String operate(String s1, String s2) {
				return s1.replaceAll(" ", "-");
			}
		});
		// 3. Using lambda expressions
//		(String s1, String s2) -> s1.concat(s2);
		
		// passing the lambda to a test method
		
		impl.test("Hello", "World", (String p, String q) -> p.concat(q));
		
		impl.test("Hello", "World", ( p, q) -> p.concat(q));
		
		impl.test("Hello", "World", ( p, q) -> p.toUpperCase() + "&"+q.toUpperCase());
		
		
		
		
		
		
	}
	
}
