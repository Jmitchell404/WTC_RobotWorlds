package org.RobotWorlds.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Server implements Runnable{


    public static final int PORT = 5000;
    private final BufferedReader in;
    private final PrintStream out;
    private final String clientMachine;

    public Server(Socket socket) throws IOException {
        /*
         * The runnable generates a socket for the client to connect from a
         * local machine if the client is able
         * to establish a stable connection to the server
         * a socket is generated to host the client on the server
         */
        clientMachine = socket.getInetAddress().getHostName();
        System.out.println("Connection from " + clientMachine);

        out = new PrintStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        System.out.println("Waiting for client...");
    }

    public void run() {
        /*
         * The runnable generates a socket for the client
         * to connect to if the client connects successfully
         * the socket is occupied by the client. If the connection
         * is not successful the server cannot run and terminates.
         */
        try {
            String messageFromClient;
            while ((messageFromClient = in.readLine()) != null) {
                System.out.println("Message \"" + messageFromClient + "\" from " + clientMachine);
                out.println("Thanks for this message: " + messageFromClient);
            }
        } catch (IOException ex) {
            System.out.println("Shutting down single client server");
        } finally {
            closeQuietly();
        }
    }

    private void closeQuietly() {
         /*
          This method closes the client socket disconnecting
          from the thread from the server.
         */
        try {
            in.close();
            out.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}