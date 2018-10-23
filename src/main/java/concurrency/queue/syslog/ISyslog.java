package concurrency.queue.syslog;

public interface ISyslog {
	void log(String message);

	long getCount();

	boolean close();
}
