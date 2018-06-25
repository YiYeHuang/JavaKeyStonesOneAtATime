package io;

import io.handler.ConnectionEchoHandler;
import io.handler.ExecutionServiceHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

public class IOServer {

    ServerSocket server;
    ExecutionServiceHandler handler;

    public IOServer(int port,  int maxWorkerThread) {
        init(port, maxWorkerThread);
    }

    private void init(int port, int maxWorkerThread) {
        try {
            handler = new ExecutionServiceHandler(maxWorkerThread);
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while(true) {

            try {
                Socket s = server.accept();
                handler.handle(new ConnectionEchoHandler(s));
            } catch (IOException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        handler.close();
    }

    public static void main(String[] args) throws InterruptedException {
        IOServer server = new IOServer(8080, 10);
        server.start();
        Thread.sleep(1000000);
        server.close();
    }
}
