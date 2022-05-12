package org.robotworlds;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

    protected static final int PORT = 5000;
    private Socket client = null;


    public SocketServer(Socket socket){
        this.client = socket;
        String server = socket.getInetAddress().getHostName();
        System.out.println("The IP address is : "+ client);
    }

    /*define a port to run on
    automatic get IP
     */
}

