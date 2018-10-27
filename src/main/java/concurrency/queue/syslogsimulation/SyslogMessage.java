package concurrency.queue.syslogsimulation;

import java.util.Random;

public class SyslogMessage {

	public String message;
	private static Random seed = new Random();

	public SyslogMessage() {
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
