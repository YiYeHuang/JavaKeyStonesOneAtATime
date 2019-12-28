package javabasic.designPattern.strategy;

public interface Strategy {
	/**
	 * 计算图书的价格
	 * @param booksPrice    图书的原价
	 * @return    计算出打折后的价格
	 */
	public double calcPrice(double booksPrice);
}

class PrimaryMemberStrategy implements Strategy {
	@Override
	public double calcPrice(double booksPrice) {

		System.out.println("对于初级会员的没有折扣");
		return booksPrice;
	}
}

class IntermediateMemberStrategy implements Strategy {
	@Override
	public double calcPrice(double booksPrice) {

		System.out.println("对于中级会员的折扣为10%");
		return booksPrice * 0.9;
	}
}

class AdvancedMemberStrategy implements Strategy {
	@Override
	public double calcPrice(double booksPrice) {

		System.out.println("对于高级会员的折扣为20%");
		return booksPrice * 0.8;
	}
}

class Price {
	//持有一个具体的策略对象
	private Strategy strategy;
	/**
	 * 构造函数，传入一个具体的策略对象
	 * @param strategy    具体的策略对象
	 */
	public Price(Strategy strategy){
		this.strategy = strategy;
	}

	/**
	 * 计算图书的价格
	 * @param booksPrice    图书的原价
	 * @return    计算出打折后的价格
	 */
	public double quote(double booksPrice){
		return this.strategy.calcPrice(booksPrice);
	}
}

class Client {

	public static void main(String[] args) {
		//选择并创建需要使用的策略对象
		Strategy strategy = new AdvancedMemberStrategy();
		//创建环境
		Price price = new Price(strategy);
		//计算价格
		double quote = price.quote(300);
		System.out.println("图书的最终价格为：" + quote);
	}
}