package javabasic.io.handler;

import java.util.concurrent.ExecutionException;

public interface Handler<T, R> {

    /**
     * Handle Action
     */
    R handle(T input) throws ExecutionException, InterruptedException;

    void close();
}
