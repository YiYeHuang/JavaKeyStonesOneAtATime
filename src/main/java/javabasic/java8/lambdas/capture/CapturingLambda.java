package javabasic.java8.lambdas.capture;

import javabasic.java8.domain.Trade;

public class CapturingLambda {
	Trade trade = new Trade("IBM",20000, "OPEN");
	
	public interface ITrade{
		public boolean check(Trade t); 
	}
	
	public boolean isOpenTrade(){
		ITrade simpleLambda = (t) -> t.isOpen()?true:false;
		return simpleLambda.check(trade);
	}
	
	public boolean checkStatus(String status){
		ITrade statusLambda = (t)->t.getStatus().equals(status)?true:false;
		return statusLambda.check(trade);
	}
	
	public boolean isOpenAndBig(String status, int quantity){
	
		ITrade openAndLargeTradeLambda = (t) ->{
			return (t.getStatus().equals(status)&& t.getQuantity() > quantity)?true:false;
		};
		return openAndLargeTradeLambda.check(trade);
	}
	
	int count = 0;
	public void instanceVariable(int localCount){
		for(int i = 0; i < count; i++){
			ITrade statusLambda = t ->{
					count++;
//					localCount++;
					return t.isOpen();
			};
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		CapturingLambda cl = new CapturingLambda();
		System.out.println("is Open Trade using non-capturing lambda: "+cl.isOpenTrade());
	}

}
