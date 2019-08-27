package javabasic.concurrency.queue.syslogsimulation.ringbufferimpl;

import com.lmax.disruptor.EventFactory;
import javabasic.concurrency.queue.syslogsimulation.SyslogMessage;

public class MessageFactory implements EventFactory {

	@Override
	public Object newInstance() {
		return new SyslogMessage();
	}
}
