package concurrency.queue.syslogsimulation;

public interface ISyslog {
	void log(SyslogMessage message);

	long getCount();

	boolean close();
}
