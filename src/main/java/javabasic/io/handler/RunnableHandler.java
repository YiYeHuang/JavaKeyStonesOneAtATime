package javabasic.io.handler;

public abstract class RunnableHandler<T, R> implements Handler<T, R>, Runnable {

    private T input;

    public RunnableHandler(T input) {
        this.input = input;
    }

    @Override
    public abstract R handle(T input);

    @Override
    public void run() {
        handle(input);
    }
}
