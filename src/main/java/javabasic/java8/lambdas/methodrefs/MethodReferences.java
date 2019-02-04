package javabasic.java8.lambdas.methodrefs;


interface IMovie{
	public boolean check(int id);
}

public class MethodReferences {

	// Static method reference usage
	public void testMovieStaticMethodRef(){
		IMovie m1 = (i) -> i< 100?true:false;
		IMovie m2 = MethodReferences::isClassic;
	}
	
	// Instance method reference usage
	private void testMovieInstanceMethodRef() {
		MethodReferences ref = new MethodReferences();
		IMovie m1 = (i) -> i> 10 && i < 100?true:false;
		IMovie m2 = ref:: isTop10;
	}
	private void testMovieArbitaryObjectMethod(){
		IMovie m1 = SomeMethodReferences::isComedy;
	}
	
	public boolean isTop10(int movieId){
		return true;
	}
	public static boolean isClassic(int movieId){
		return true;
	}
	
	public static void main(String[] args) {
		MethodReferences client = new MethodReferences();
	}
 
}
class SomeMethodReferences{
	public static boolean isComedy(int i){
		return false;
	}
}