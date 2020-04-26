package dataStructure.ringbuffer.blocking;

public interface OverflowPolicy<T> {
    public void addToFullBuffer(T object, RingBuffer<T> bufferData);
}