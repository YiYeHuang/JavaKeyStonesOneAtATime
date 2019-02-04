package javabasic.java8.lambdas.scope;

public class LambdaScope extends SuperScope{

	private String member = "GRANDPA";

	interface Family {
		String who(String member);
	}

	public void testMember(String member) {
		System.out.println("Local member:" + member);
		System.out.println("Family member " + this.member);
		System.out.println("Family member " + super.member);
		
		
		Family familyLambda = (familyMember) -> {
			System.out.println("Local lambda member: " + familyMember);
			System.out.println("Local lambda member: " + member);
			System.out.println("Local lambda this.member: " + this.member);
			System.out.println("Local lambda super.member: " + super.member);
			return familyMember;
		};
		
		familyLambda.who(member);
	}

	public static void main(String[] args) {
		new LambdaScope().testMember("SON");
	}

}
