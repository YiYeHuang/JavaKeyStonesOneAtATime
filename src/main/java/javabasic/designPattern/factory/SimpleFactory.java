package javabasic.designPattern.factory;

interface Board{
	void createBoard();
}

class GigabyteBoard implements Board{
	public void createBoard() {
		System.out.println("produce GigabyteBoard");
	}
}

class AsusBoard implements Board{
	public void createBoard() {
		System.out.println("produce AsusBoard");
	}
}

public class SimpleFactory {
	public static Board  factory(String type){

		if (type.equalsIgnoreCase("gigabyte")){
			return new GigabyteBoard();
		} else if(type.equalsIgnoreCase("asus")){
			return new AsusBoard();
		}
		return null;
	}
}