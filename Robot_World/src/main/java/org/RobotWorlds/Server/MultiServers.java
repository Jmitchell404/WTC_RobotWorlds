package org.RobotWorlds.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServers extends Server{

    public MultiServers(Socket socket) throws IOException {
        super(socket);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        ServerSocket s = new ServerSocket( Server.PORT);
        System.out.println("Server running & waiting for client connections.");
        while(true) {
            try {
                Socket socket = s.accept();
                System.out.println("Connection: " + socket);

                Runnable r = new Server(socket);
                Thread task = new Thread(r);
                task.start();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
