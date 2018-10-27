package concurrency.queue.syslogsimulation.ringbufferimpl;

import concurrency.queue.syslogsimulation.SyslogMessage;

import java.util.Random;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class MessageHandler implements WorkHandler<SyslogMessage> {

	private Random seed = new Random();
	private final int SIM_SENDING_COST_MAX = 10;

	@Override
	public void onEvent(SyslogMessage message) throws Exception {
		doSend(message.message);
	}

	/**
	 * 	simulation sending cost here
	 */
	private void doSend(String message) throws InterruptedException {
		long cost = 0;
		while(cost < 1000000l) {
			cost ++;
		}

		//System.out.println("Consume sequence: " + sequence + " sending: " + message);
	}
}

