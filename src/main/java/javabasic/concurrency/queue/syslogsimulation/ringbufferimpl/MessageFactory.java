package javabasic.concurrency.queue.syslogsimulation.ringbufferimpl;

import javabasic.concurrency.queue.syslogsimulation.SyslogMessage;

import com.lmax.disruptor.EventFactory;

public class MessageFactory implements EventFactory {

	@Override
	public Object newInstance() {
		return new SyslogMessage();
	}
}
