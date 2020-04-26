package dataStructure.ringbuffer.blocking;

public class RBDiscardPolicy<T> implements OverflowPolicy<T>
{
    @Override
    public void addToFullBuffer(T object, RingBuffer<T> bufferData)
    {
        bufferData.remove();
        bufferData.add(object);
    }
}