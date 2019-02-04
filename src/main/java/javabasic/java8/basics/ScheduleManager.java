package javabasic.java8.basics;

public class ScheduleManager {

	private void schedule(Scheduler schedule){
		schedule.schedule();
	}
	public static void main(String[] args) {

		ScheduleManager manager = new ScheduleManager();
		
		//1. using inner class
		Scheduler scheduler = new Scheduler(){

			@Override
			public void schedule() {
				System.out.println("Schedule using inner class");
			}
		};
		manager.schedule(scheduler);
		
		//2. using anonymous class
		manager.schedule(new Scheduler(){
			@Override
			public void schedule() {
				System.out.println("Scheduling the job using anonymous class");
			}
			
		});
		
		// 3. using lambda
		manager.schedule( () -> System.out.println("Using lambda") );
	}

}
