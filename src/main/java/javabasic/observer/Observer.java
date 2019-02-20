package javabasic.observer;

import java.util.ArrayList;
import java.util.List;

public interface Observer {
	void update(String state);
}

abstract class Subject {
	/**
	 * 用来保存注册的观察者对象
	 */
	private List<Observer> list = new ArrayList<Observer>();
	/**
	 * 注册观察者对象
	 * @param observer    观察者对象
	 */
	public void attach(Observer observer){
		list.add(observer);
		System.out.println("Attached an observer");
	}
	/**
	 * 删除观察者对象
	 * @param observer    观察者对象
	 */
	public void detach(Observer observer){
		list.remove(observer);
	}
	/**
	 * 通知所有注册的观察者对象
	 */
	public void nodifyObservers(String newState){
		for(Observer observer : list){
			observer.update(newState);
		}
	}
}

class ConcreteSubject extends Subject{

	private String state;

	public String getState() {
		return state;
	}

	public void change(String newState){
		state = newState;
		System.out.println("主题状态为：" + state);
		//状态发生改变，通知各个观察者
		this.nodifyObservers(state);
	}
}

class ConcreteObserver implements Observer {
	//观察者的状态
	private String observerState;

	@Override
	public void update(String state) {
		/**
		 * 更新观察者的状态，使其与目标的状态保持一致
		 */
		observerState = state;
		System.out.println("状态为："+observerState);
	}
}

class Client {

	public static void main(String[] args) {
		//创建主题对象
		ConcreteSubject subject = new ConcreteSubject();
		//创建观察者对象
		Observer observer = new ConcreteObserver();
		//将观察者对象登记到主题对象上
		subject.attach(observer);
		//改变主题对象的状态
		subject.change("new state");
	}
}
