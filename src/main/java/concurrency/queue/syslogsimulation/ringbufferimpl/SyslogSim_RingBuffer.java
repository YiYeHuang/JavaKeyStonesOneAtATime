package concurrency.queue.syslogsimulation.ringbufferimpl;

import concurrency.queue.syslogsimulation.ISyslog;
import concurrency.queue.syslogsimulation.SyslogMessage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;

public class SyslogSim_RingBuffer implements ISyslog {

	MessageFactory factory = new MessageFactory();
	public volatile long processed;
	private final int RINGBUFFER_SIZE = 2048;
	WorkerPool<SyslogMessage> workerPool;
	RingBuffer<SyslogMessage> ringBuffer;
	ExecutorService executor;


	public SyslogSim_RingBuffer() {
		executor = Executors.newFixedThreadPool(8);
		ringBuffer = RingBuffer.create(
				ProducerType.SINGLE,
				new MessageFactory(),
				RINGBUFFER_SIZE,
				new YieldingWaitStrategy());

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
