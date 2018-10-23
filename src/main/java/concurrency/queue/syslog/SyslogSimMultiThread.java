package concurrency.queue.syslog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simulate collecting system log and then send to a syslog server
 */
public class SyslogSimMultiThread implements ISyslog {
	private final int QUEUE_SIZE = 200;
	private final int DROP_RATE = 20;
	private final int SIM_SENDING_COST_MAX = 10;

	public volatile AtomicLong processed;
	private final MessageSender sender;
	private final ArrayBlockingQueue<SyslogMessage> messageQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);
	ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
	private final List<SyslogMessage> dropList = new ArrayList<>(DROP_RATE);

	public SyslogSimMultiThread() {
		this.sender = new MessageSender(messageQueue, executor);
		Thread thread = new Thread(sender, "SyslogSim" + sender.toString());
		thread.setDaemon(true);
		processed = new AtomicLong();
		thread.start();
	}

	public void log(String message) {
		if ( sender.isRunning() ) {
			messageQueue.add(new SyslogMessage(message));
		}
	}

	@Override
	public long getCount() {
		return processed.longValue();
	}

	public boolean close() {

		if (messageQueue.isEmpty()) {
			sender.stop();
			executor.shutdownNow();
			System.out.println("stop send messages");
			System.out.println("close the queue, " + processed +" messages processed");
			return true;
		}
		return false;

	}


	private class SyslogMessage {

		public String message;
		SyslogMessage(final String message) {
			this.message = message;
		}
	}

	private class MessageSender implements Runnable {

		private final AtomicBoolean run = new AtomicBoolean(true);
		private final ArrayBlockingQueue<SyslogMessage> messageQueue;

		public MessageSender(final ArrayBlockingQueue<SyslogMessage> messageQueue, ThreadPoolExecutor executor) {
			this.messageQueue = messageQueue;

		}

		@Override
		public void run() {
			try {
				sendMessages();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void sendMessages() throws InterruptedException {
			while (run.get()) {
				if (!messageQueue.isEmpty()) {
					executor.submit(new PollTask(messageQueue, processed));
					processed.incrementAndGet();
				}

				if (messageQueue.remainingCapacity() == 0) {
					dropList.clear();
					messageQueue.drainTo(dropList, DROP_RATE);
					System.out.println("drop 20, message lose");
					dropList.clear();
				}
			}
		}

		private void stop() {
			run.set(false);
		}

		private boolean isRunning() {
			return run.get();
		}
	}

	private class PollTask implements Runnable{
		private Random seed = new Random();
		private final ArrayBlockingQueue<SyslogMessage> messageQueue;

		public PollTask(ArrayBlockingQueue<SyslogMessage> messageQueue, AtomicLong counter) {
			this.messageQueue = messageQueue;
		}

		public void run() {
			try {
				SyslogMessage message = messageQueue.poll();
				int sleep = seed.nextInt(SIM_SENDING_COST_MAX);
				//System.out.println(message.message);
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
