package concurrency.queue.syslogsimulation.ringbufferimpl;

import concurrency.queue.syslogsimulation.ISyslog;

public class SyslogSim_RingBuffer implements ISyslog {

	@Override
	public void log(String message) {

	}

	@Override
	public long getCount() {
		return 0;
	}

	@Override
	public boolean close() {
		return false;
	}
}
