package javabasic.java8.basics.prepostexamples;

public class Runnables {

	void testRunnable(Runnable r) {
		new Thread(r).start();
	}

	public static void main(String[] args) {
		Runnables test = new Runnables();

		Runnable oldRunnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("Using a Runnable instance");
			}

		};
		
		test.testRunnable(oldRunnable);
		
		test.testRunnable(new Runnable(){
			@Override
			public void run() {
				System.out.println("Using an Anonymous runnable");
			}
			
		});
		
		test.testRunnable(() -> System.out.println("Using a Lambda"));
	}
}
