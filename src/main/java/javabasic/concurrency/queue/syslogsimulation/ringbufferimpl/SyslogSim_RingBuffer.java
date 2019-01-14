package javabasic.concurrency.queue.syslogsimulation.ringbufferimpl;

import com.lmax.disruptor.*;
import javabasic.concurrency.queue.syslogsimulation.ISyslog;
import javabasic.concurrency.queue.syslogsimulation.SyslogMessage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.dsl.ProducerType;

public class SyslogSim_RingBuffer implements ISyslog {
	public volatile long processed;
	private final int RINGBUFFER_SIZE = 2048;

	MessageFactory factory = new MessageFactory();
	WorkerPool<SyslogMessage> workerPool;
	RingBuffer<SyslogMessage> ringBuffer;
	ExecutorService executor;
	WaitStrategy waitStra;


	public SyslogSim_RingBuffer(WaitMethod inputWaitType) {
		executor = Executors.newFixedThreadPool(8);

		switch(inputWaitType) {
			case BLOCKING:
				waitStra = new BlockingWaitStrategy();
				break;
			case BUSYSPIN:
				waitStra = new BusySpinWaitStrategy();
				break;
			case YIELDING:
				waitStra = new YieldingWaitStrategy();
				break;
			case SLEEPING:
				waitStra = new SleepingWaitStrategy();
				break;
			default:
				waitStra = new BlockingWaitStrategy();
		}

		ringBuffer = RingBuffer.create(
				ProducerType.SINGLE,
				factory,
				RINGBUFFER_SIZE,
				waitStra);

		workerPool = new WorkerPool<SyslogMessage>(
				ringBuffer,
				ringBuffer.newBarrier(),
				new IgnoreExceptionHandler(),
				new MessageHandler());

		workerPool.start(executor);
	}

	@Override
	public void log(String message) {
		long sequence = ringBuffer.next();
		SyslogMessage newMessage = ringBuffer.get(sequence);
		newMessage.message = message;
		ringBuffer.publish(sequence);
		processed++;
	}

	@Override
	public long getCount() {
		return 0;
	}

	@Override
	public boolean close() {
		workerPool.halt();
		executor.shutdown();
		System.out.println("stop send messages");
		System.out.println("close the queue, " + processed +" messages processed");
		return true;
	}
}
