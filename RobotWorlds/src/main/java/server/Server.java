package server;

import configuration.Configuration;
import configuration.ConfigurationManagement;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            System.out.println("    > Scanning for robots..");
            // Listen for connections (clients to server) on specified port.
            while (!serverSocket.isClosed()) {
//                TimeUnit.SECONDS.sleep(5);
                // Closed in the Client Handler.
                Socket socket = serverSocket.accept();
//               add jason object ROBOT : LAUNCHED
                ThreadHandler clientHandler = new ThreadHandler(socket);
                Thread thread = new Thread(clientHandler);
                /*
                 The start method begins the execution of a thread.
                 When you call start() the run method is called.
                 The operating system schedules the threads.
                */
                thread.start();
            }
        } catch (IOException e) {
            closeServerSocket();
        }
    }

    // Close the server socket gracefully.
    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serverResponse(String message){
        System.out.println(message);
    }

    // Run the program.
    public static void main(String[] args) throws IOException {
        ConfigurationManagement.getInstance().loadConfiguration("src/main/java/configuration/configuration.json");
        Configuration conFig = ConfigurationManagement.getInstance().getConfiguration();
        int port = conFig.getPort();
        String host = conFig.getHost();
        System.out.println("Scanning the galaxy....The world " + host + " star number "+ port +" was located");
        System.out.println("Conditions are suitable for droid inhabitants");
        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(serverSocket);
        server.startServer();
    }

}

