package javabasic.concurrency.queue.syslogsimulation.ringbufferimpl;


public enum WaitMethod {
    BLOCKING,
    BUSYSPIN,
    SLEEPING,
    YIELDING
}