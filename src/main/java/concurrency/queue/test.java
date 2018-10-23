package concurrency.queue;

import concurrency.queue.syslog.ISyslog;
import concurrency.queue.syslog.SyslogSim;
import concurrency.queue.syslog.SyslogSimMultiThread;

import java.util.Random;

public class test {

	public static final int SIM_PRODUCER_ROUND = 10000;
	public static final int SIM_PRODUCER_COST = 7;
	private static Random seed = new Random();


	public static void main(String[] args) throws InterruptedException {

//		System.out.println("Test One Thread");
//		test(new SyslogSim());

		System.out.println("Test Multi Thread");
		test(new SyslogSimMultiThread());
	}

	public static void test(ISyslog sg) throws InterruptedException {

		long start = System.currentTimeMillis();
		System.out.println(start);

		Thread producer = new Thread(() -> {
			for (int i = 0; i < SIM_PRODUCER_ROUND; i++) {
				sg.log("test" + i);
				try {
					int sleep = seed.nextInt(SIM_PRODUCER_COST);
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println("finished put");
		});

		producer.start();

		Thread.sleep(1000);

		boolean keepRun = true;
		while (keepRun) {
			keepRun = !sg.close();
		}

		System.out.println("Cost " + (  System.currentTimeMillis() - start));
	}
}
