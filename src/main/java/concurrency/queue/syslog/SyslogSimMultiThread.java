package concurrency.queue.syslog;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simulate collecting system log and then send to a syslog server
 */
public class SyslogSimMultiThread implements ISyslog {

	public volatile AtomicLong processed;
	private final MessageSender sender;
	private final ConcurrentLinkedDeque<SyslogMessage> messageQueue = new ConcurrentLinkedDeque<SyslogMessage>();
	ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

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

	public void close() {
		sender.stop();
		executor.shutdownNow();
		System.out.println("stop send messages");
		System.out.println("close");
	}


	private class SyslogMessage {

		public String message;
		SyslogMessage(final String message) {
			this.message = message;
		}
	}

	private class MessageSender implements Runnable {

		private final AtomicBoolean run = new AtomicBoolean(true);
		private final ConcurrentLinkedDeque<SyslogMessage> messageQueue;

		public MessageSender(final ConcurrentLinkedDeque<SyslogMessage> messageQueue, ThreadPoolExecutor executor) {
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
		private final ConcurrentLinkedDeque<SyslogMessage> messageQueue;
		AtomicLong counter;

		public PollTask(ConcurrentLinkedDeque<SyslogMessage> messageQueue, AtomicLong counter) {
			this.messageQueue = messageQueue;
			this.counter = counter;
		}

		public void run() {
			try {
				SyslogMessage message = messageQueue.poll();
				int sleep = seed.nextInt(1);
				System.out.println(message.message);
				Thread.sleep(sleep);
				this.counter.incrementAndGet();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
