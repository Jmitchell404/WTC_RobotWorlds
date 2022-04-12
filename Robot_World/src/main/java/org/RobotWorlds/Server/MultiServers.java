package org.RobotWorlds.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServers {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        /*
         * Running multiple threads from server for more than one
         * client sockets to connect.
         */
        ServerSocket s = new ServerSocket(Server.PORT);
        System.out.println("org.RobotWorlds.Server.Server running & waiting for client connections.");

        /*
         * The code block below attempts to connect a socket to the
         * server this generates a thread to a runnable target
         */
        while (true) {
            try {
                Socket socket = s.accept();
                System.out.println("Connection: " + socket);

                Runnable r = new Server(socket);
                Thread task = new Thread(r);
                task.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
