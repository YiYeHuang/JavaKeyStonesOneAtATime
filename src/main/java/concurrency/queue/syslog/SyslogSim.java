package concurrency.queue.syslog;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Simulate collecting system log and then send to a syslog server
 */
public class SyslogSim implements ISyslog {
	public volatile long processed;
	private final MessageSender sender;
	private final BlockingQueue<SyslogMessage> messageQueue = new ArrayBlockingQueue<>(200);

	public SyslogSim() {
		this.sender = new MessageSender(messageQueue);
		Thread thread = new Thread(sender, "SyslogSim" + sender.toString());
		thread.setDaemon(true);
		thread.start();
	}

	public void log(String message) {
		if ( sender.isRunning() ) {
			try {
				messageQueue.put(new SyslogMessage(message));
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
		}
	}

	@Override
	public long getCount() {
		return processed;
	}

	public void close() {
		sender.stop();
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
		private final BlockingQueue<SyslogMessage> messageQueue;
		private Random seed = new Random();

		public MessageSender(final BlockingQueue<SyslogMessage> messageQueue) {
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
					SyslogMessage message = messageQueue.poll();
					doSend(message.message);
				}
			}
		}

		/*
		 * Simulate send to a syslog server
		 */
		private void doSend(String message) throws InterruptedException {
			int sleep = seed.nextInt(1);
			// System.out.println(message);
			Thread.sleep(sleep);
			processed++;
		}


		private void stop() {
			run.set(false);
		}

		private boolean isRunning() {
			return run.get();
		}
	}
}
