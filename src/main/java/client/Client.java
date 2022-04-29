package client;

import configuration.Configuration;
import configuration.ConfigurationManagement;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * A client sends messages to the server, the server spawns a thread to communicate with the client.
 * Each communication with a client is added to an array list so any message sent gets sent to every
 * other client by looping through it.
 */
public class Client {
    // initialize socket and input / output streams
    // A client has a socket to connect to the server and
    // a reader and writer to receive and send messages respectively.
    static Scanner scanner;
    private Socket socket = null;
    private BufferedReader bufferedReader = null;
    private BufferedWriter bufferedWriter = null;
    private String robotName;


    public Client(Socket socket, String robotName) {
        try {
            this.socket = socket;
            this.robotName = robotName;
//      sends output to the socket (sending)
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//      receive response from server (listening)
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }  catch (IOException e) {
            // Gracefully close everything.
            terminateWorld(socket, bufferedReader, bufferedWriter);
        }
    }


    // Run the program.
    public static void main(String[] args) throws IOException {
        // Get a robot name to label a socket connection.
        try {
            scanner = new Scanner(System.in);

            System.out.println("Connected");
            System.out.println("What would you like to name your robot?");

            String robotName = scanner.nextLine();

            while (robotName.isBlank()) {
                System.out.println("What would you like to name your robot?");
                robotName = scanner.nextLine();
            }


            // Create a socket to connect to the server.
            ConfigurationManagement.getInstance().loadConfiguration("src/main/java/configuration/configuration.json");
            Configuration conFig = ConfigurationManagement.getInstance().getConfiguration();
            int port = conFig.getPort();

            Socket socket = new Socket("localhost", port);

            // Pass the socket and give the client a username.
            Client client = new Client(socket, robotName);

            // Infinite loop to read and send messages.
            client.listenForMessage();
            client.sendMessage();
        }catch(IOException ex) {
            ex.printStackTrace();
            System.err.println("Couldn't get I/O for the connection to localhost");
            System.exit(1);
        }
    }

    // Sending a message isn't blocking and can be done without spawning a thread, unlike waiting for a message.
    public void sendMessage() {
        try {
            // Initially send the username of the client.
            bufferedWriter.write(robotName);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            // Create a scanner for user input.
            scanner = new Scanner(System.in);
            // While still connected with the server, scan the terminal and then send the message.
            while (socket.isConnected()) {
                System.out.println((robotName + " - What must I do?").strip().toLowerCase());
                String clientRequest = scanner.nextLine();

                while (clientRequest.isBlank()){
                    System.out.println((robotName + " - What must I do?").strip().toLowerCase());
                    clientRequest = scanner.nextLine();
                }
                bufferedWriter.write(robotName + " : " + clientRequest);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            // Gracefully close everything.
            terminateWorld(socket, bufferedReader, bufferedWriter);
        }
    }

    // Listening for a message is blocking separate thread for listening.
    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String clientRequest;
                    // While there is still a connection with the server, continue to listen for messages on a separate thread.
                    while (socket.isConnected()&& (clientRequest = bufferedReader.readLine()) != null) {

                        // Get requests from clients and respond in the console.
                        System.out.println(clientRequest);
                    }
                }catch (IOException e) {
                    // Close everything gracefully.
                    terminateWorld(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();
    }

    // Helper method to close everything.
    public void terminateWorld(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        /*
        Note:
            To close the outer wrapper as the underlying streams are closed.
            Close the outermost wrapper so that everything gets flushed.
            Closing a socket will also close the socket's InputStream and OutputStream.
            Use shutdownInput() (on socket) to close the input stream.
            Closing the socket will also close the socket's input stream and output stream.
            Close the socket after closing the streams.
        */
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}