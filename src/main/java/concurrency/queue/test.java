package concurrency.queue;

import concurrency.queue.syslog.ISyslog;
import concurrency.queue.syslog.SyslogSim;

import java.util.Random;

public class test {

	public static int ROUND = 10000;
	private static Random seed = new Random();


	public static void main(String[] args) {

		test(new SyslogSim());
		//test(new SyslogSimMultiThread());
	}

	public static void test(ISyslog sg) {

		long start = System.currentTimeMillis();
		System.out.println(start);

		Thread producer = new Thread(() -> {
			for (int i = 0; i < ROUND; i++) {
				sg.log("test" + i);
				try {
					int sleep = seed.nextInt(10);
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println("finished put");
		});

		producer.start();
		while (sg.getCount() != ROUND -1) {
		}
		sg.close();
		System.out.println(System.currentTimeMillis() - start);
	}
}
