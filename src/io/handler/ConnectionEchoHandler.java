package io.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionEchoHandler extends CallableHandler<Socket, Boolean>{

    public ConnectionEchoHandler(Socket input) {
        super(input);
    }

    @Override
    public Boolean handle(Socket incomingSocket) {
        String logInfo = incomingSocket.toString();
        System.out.println("Connected to " + logInfo);
        try (incomingSocket;
             InputStream in = incomingSocket.getInputStream();
             OutputStream out = incomingSocket.getOutputStream()){
            int data;
            while ((data=in.read()) != -1) {
                out.write(transForm(data));
            }
            in.transferTo(out);

            return true;
        } catch (IOException e) {
            System.out.println("Connection ERROR: " + logInfo);
            return false;
        } finally {
            System.out.println("Disconnected: " + logInfo);
        }
    }

    @Override
    public void close() {
        // Do nothing
    }

    private static int transForm(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data;
    }
}
