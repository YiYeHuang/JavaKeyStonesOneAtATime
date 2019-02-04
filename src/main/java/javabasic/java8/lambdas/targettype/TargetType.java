package javabasic.java8.lambdas.targettype;

// Target Typing Functionality Example

public class TargetType {
	private static String domain = "@madhusudhan.com";
	
	//(String name) -> name + domain;
	
	public interface Email{
		String constructEmail (String name);
	}
	
	Email email = (String name) -> name + domain;
	
	public String getEmail(String name, Email email){
		String emailAddress = email.constructEmail(name);
		System.out.printf("%s's Email is %s\n",name,emailAddress);
		return emailAddress;
	}
	
	public static void main(String[] args) {
		TargetType tt = new TargetType();
		tt.getEmail("mkonda", (String name) -> name + domain);
		
	}

}
