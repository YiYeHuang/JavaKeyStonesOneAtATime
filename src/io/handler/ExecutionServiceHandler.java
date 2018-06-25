package io.handler;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ExecutionServiceHandler implements Handler<CallableHandler, Boolean> {

    private ExecutorService tp;

    public ExecutionServiceHandler(int maxWorkerThread) {
        this.tp = Executors.newFixedThreadPool(maxWorkerThread);
    }

    @Override
    public Boolean handle(CallableHandler input) throws ExecutionException, InterruptedException {
        return (Boolean) tp.submit(input).get();
    }

    @Override
    public void close() {
        List<Runnable> runnables = tp.shutdownNow();
        for (Runnable runnable : runnables) {
            runnable.run();
        }
    }
}
