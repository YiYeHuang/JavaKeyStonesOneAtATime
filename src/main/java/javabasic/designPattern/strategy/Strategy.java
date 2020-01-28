package javabasic.designPattern.strategy;

public interface Strategy {

	public double calcPrice(double booksPrice);
}

class PrimaryMemberStrategy implements Strategy {
	@Override
	public double calcPrice(double booksPrice) {

		System.out.println("Regular member no discount");
		return booksPrice;
	}
}

class IntermediateMemberStrategy implements Strategy {
	@Override
	public double calcPrice(double booksPrice) {

		System.out.println("low rank member 10%");
		return booksPrice * 0.9;
	}
}

class AdvancedMemberStrategy implements Strategy {
	@Override
	public double calcPrice(double booksPrice) {

		System.out.println("high rank member 20%");
		return booksPrice * 0.8;
	}
}

class Price {
	//持有一个具体的策略对象
	private Strategy strategy;

	public Price(Strategy strategy){
		this.strategy = strategy;
	}

	public double quote(double booksPrice){
		return this.strategy.calcPrice(booksPrice);
	}
}

class Client {

	public static void main(String[] args) {

		Strategy strategy = new AdvancedMemberStrategy();

		Price price = new Price(strategy);

		double quote = price.quote(300);
		System.out.println("Final price" + quote);
	}
}