package javabasic.designPattern.factory;

interface AbstractFactory {

	public Door createDoor();

	public Wheel createWheel();

}

class BenzFactory implements AbstractFactory {

	public Door createDoor() {
		// TODO Auto-generated method stub
		return new BenzDoor();
	}

	public Wheel createWheel() {
		// TODO Auto-generated method stub
		return new BenzWheel();
	}
}

class AudiFactory implements AbstractFactory {

	public Door createDoor() {
		return new AudiDoor();
	}

	public Wheel createWheel() {
		return new AudiWheel();
	}
}

interface Door {
	// public void createDoor();
}

interface Wheel {

	// public void createWheel();
}

class BenzDoor implements Door {

	public BenzDoor() {
		System.out.println("生产奔驰汽车门");
	}
}

class AudiDoor implements Door {

	public AudiDoor() {
		System.out.println("produce audi door");
	}
}

class BenzWheel implements Wheel {

	public BenzWheel() {
		System.out.println("produce benz wheel");
	}


}

class AudiWheel implements Wheel {

	public AudiWheel() {

		System.out.println("produce audi wheel");
	}

	public static void main(String[] args) {
		AbstractFactory beazFactory = new BenzFactory();
		AbstractFactory audiFactory = new AudiFactory();
		beazFactory.createDoor();
		audiFactory.createWheel();
	}
}