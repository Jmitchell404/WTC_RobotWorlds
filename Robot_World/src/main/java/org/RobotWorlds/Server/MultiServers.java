package org.RobotWorlds.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiServers extends Server{

    public MultiServers(Socket socket) throws IOException {
        super(socket);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        ExecutorService executor = null;
        ServerSocket s = new ServerSocket( Server.PORT);
        System.out.println("Server started and running\nWaiting for client connections...");
        executor = Executors.newFixedThreadPool(4);

        while(true) {
            try {
                //halted till client connection
                Socket socket = s.accept();
                System.out.println("Client accepted!\nIP & Port is: " + socket);

                Runnable worker = new Server(socket);
                Thread task = new Thread(worker);
                task.start();
            } catch(IOException ex) {
                ex.printStackTrace();
            }catch(Exception exception) {
                s.close();
                System.exit(-1);
            }
            // catch(ClassNotFoundException classNotFoundException) {
//                classNotFoundException;
//            }
        }
    }
}
