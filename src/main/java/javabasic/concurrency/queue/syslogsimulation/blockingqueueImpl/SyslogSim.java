package javabasic.concurrency.queue.syslogsimulation.blockingqueueImpl;

import javabasic.concurrency.queue.syslogsimulation.ISyslog;
import javabasic.concurrency.queue.syslogsimulation.SyslogMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Simulate collecting system log and then send to a syslogsimulation server
 */
public class SyslogSim implements ISyslog {
	private final int QUEUE_SIZE = 2000;
	private final int DROP_RATE = 20;
	private final int SIM_SENDING_COST_MAX = 10;


	public volatile long processed;
	private final MessageSender sender;
	private final BlockingQueue<SyslogMessage> messageQueue = new LinkedBlockingDeque<>(QUEUE_SIZE);
	private final List<SyslogMessage> dropList = new ArrayList<>(DROP_RATE);

	public SyslogSim() {
		this.sender = new MessageSender(messageQueue);
		Thread thread = new Thread(sender, "SyslogSim" + sender.toString());
		thread.setDaemon(true);
		thread.start();
	}

	public void log(String message) {
		if ( sender.isRunning() ) {
			try {
				SyslogMessage newMessage = new SyslogMessage();
				newMessage.message = message;
				messageQueue.put(newMessage);
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
		}
	}

	@Override
	public long getCount() {
		return processed;
	}

	public boolean close() {
		if (messageQueue.isEmpty()) {
			sender.stop();
			System.out.println("stop send messages");
			System.out.println("close the queue, " + processed +" messages processed");
			return true;
		}
		return false;
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
//				if (messageQueue.remainingCapacity() == 0) {
//					dropList.clear();
//					messageQueue.drainTo(dropList, DROP_RATE);
//					processed -= 20;
//					System.out.println("queue full drop 20, message lose");
//					dropList.clear();
//				}
			}
		}

		/*
		 * Simulate send to a syslogsimulation server
		 */
		private void doSend(String message) throws InterruptedException {
			// simulation sending cost here
			long cost = 0;
			while(cost < 1000000l) {
				cost ++;
			}

			processed++;

			//System.out.println("sending: " + message);
		}


		private void stop() {
			run.set(false);
		}

		private boolean isRunning() {
			return run.get();
		}
	}
}
