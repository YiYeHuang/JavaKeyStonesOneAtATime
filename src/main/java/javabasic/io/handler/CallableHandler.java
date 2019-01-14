package javabasic.io.handler;

import java.util.concurrent.Callable;

public abstract class CallableHandler<T, R> implements Handler<T, R>, Callable<R> {
    private T input;

    public CallableHandler(T input) {
        this.input = input;
    }

    @Override
    public abstract R handle(T input);

    @Override
    public R call() throws Exception {
        return handle(input);
    }
}
