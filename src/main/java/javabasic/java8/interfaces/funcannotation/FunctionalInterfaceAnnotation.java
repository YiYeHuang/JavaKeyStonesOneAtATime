package javabasic.java8.interfaces.funcannotation;

// @FunctionalInteface annotation
public class FunctionalInterfaceAnnotation {

	@FunctionalInterface
	interface Cruncher {
		int crunch(int i, int j);
	};

	public int cruncherService(int i, int j, Cruncher cruncher){
		return cruncher.crunch(i, j);
	}

	public static void main(String[] args) {
		FunctionalInterfaceAnnotation client = new FunctionalInterfaceAnnotation();

		//Client 1
		int ans = client.cruncherService(3, 4, (p,q) -> p*q);
		System.out.println("Answer: "+ans);
		
		//Client 2
		ans = client.cruncherService(3, 4, (p,q) -> p+q);
		System.out.println("Answer: "+ans);

		//Client 3
		ans = client.cruncherService(3, 4, (p,q) -> p-q);
		System.out.println("Answer: "+ans);

		//Client 4
		ans = client.cruncherService(20, 4, (p,q) -> p/q);
		System.out.println("Answer: "+ans);

		
	}

}
