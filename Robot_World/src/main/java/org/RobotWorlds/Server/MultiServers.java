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
        System.out.println("Server started and running\nWaiting for client connections...");

        while(true) {
            try {
                //halted till client connection
                Socket socket = s.accept();
                System.out.println("Client accepted!\nIP & Port is: " + socket);

                Runnable runnable = new Server(socket);
                Thread thread = new Thread(runnable);
                thread.start();

            } catch(IOException e) {
                e.printStackTrace();
            }catch(Exception exception) {
                s.close();
                System.exit(-1);
            }
//            catch(ClassNotFoundException classNotFoundException) {
//                classNotFoundException;
//            }
        }
    }
}
