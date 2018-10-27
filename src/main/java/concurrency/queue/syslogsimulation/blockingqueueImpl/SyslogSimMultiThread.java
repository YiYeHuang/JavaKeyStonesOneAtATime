package concurrency.queue.syslogsimulation.blockingqueueImpl;

import concurrency.queue.syslogsimulation.ISyslog;
import concurrency.queue.syslogsimulation.SyslogMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simulate collecting system log and then send to a syslogsimulation server
 */
public class SyslogSimMultiThread implements ISyslog {
	private final int QUEUE_SIZE = 5000;
	private final int DROP_RATE = 20;
	private final int SIM_SENDING_COST_MAX = 10;

	private final MessageSender sender;
	private final BlockingQueue<SyslogMessage> messageQueue = new LinkedBlockingDeque<>(QUEUE_SIZE);
	ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);
	private final List<SyslogMessage> dropList = new ArrayList<>(DROP_RATE);

	public SyslogSimMultiThread() {
		this.sender = new MessageSender(messageQueue, executor);
		Thread thread = new Thread(sender, "SyslogSim" + sender.toString());
		thread.setDaemon(true);
		thread.start();
	}

	public void log(String message) throws InterruptedException {
		if ( sender.isRunning() ) {
			SyslogMessage newMessage = new SyslogMessage();
			newMessage.message = message;
			messageQueue.put(newMessage);
		}
	}

	@Override
	public long getCount() {
		return sender.getCount();
	}

	public boolean close() {

		if (messageQueue.isEmpty()) {
			sender.stop();
			executor.shutdownNow();
			System.out.println("stop send messages");
			System.out.println("close the queue, " + getCount() +" messages processed");
			return true;
		}
		return false;

	}

	private class MessageSender implements Runnable {

		private final AtomicBoolean run = new AtomicBoolean(true);
		private final BlockingQueue<SyslogMessage> messageQueue;
		ThreadPoolExecutor executor;
		public AtomicLong processed;

		public MessageSender(
				final BlockingQueue<SyslogMessage> messageQueue,
				ThreadPoolExecutor executor) {
			this.messageQueue = messageQueue;
			this.executor = executor;
			this.processed = new AtomicLong(0);
		}

		public long getCount() {
			return processed.get();
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
					SyslogMessage message = messageQueue.poll();
					executor.submit(new PollTask(message));
					processed.incrementAndGet();
				}

//				if (messageQueue.remainingCapacity() == 0) {
//					dropList.clear();
//					messageQueue.drainTo(dropList, DROP_RATE);
//					System.out.println("drop 20, message lose");
//					dropList.clear();
//				}
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
		private SyslogMessage message;

		public PollTask(SyslogMessage message) {
			this.message = message;
		}

		public void run() {

			// simulation sending cost here
			long cost = 0;
			while(cost < 1000000l) {
				cost ++;
			}
			// System.out.println("sending: " + message.message);
		}
	}
}
