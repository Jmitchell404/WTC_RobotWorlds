package server;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
/**
 * When a client connects the server it generates a thread to handle the client.
 * Through the threads the server can handle multiple clients at the same time.
 * Runnable is implemented on a class whose instances will be executed through a thread.
 */
public class ThreadHandler implements Runnable {

    // Array list of all threads handling separate clients.
    public static final ArrayList<ThreadHandler> clientHandlers = new ArrayList<>();

    // Socket: connection, buffer reader and writer for receiving requests and sending respective responses
    private Socket socket;
    private String robotName;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;


    // Multiple Client handler from the server socket, creating a single client thread to listen on.
    public ThreadHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // When a client connects their robotName is sent.
            this.robotName = bufferedReader.readLine();

            // Adding a new clientHandler to the array, so they can interact with other robots.
            clientHandlers.add(this);
            responseMessage("OddWorld: " + robotName + " has been launched!");
        } catch (IOException e) {
            // Close everything more gracefully.
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /*
     Everything in this method is run on a separate thread. We want to listen for messages
     on a separate thread because listening (bufferedReader.readLine()) is a blocking operation.
     A blocking operation means the caller waits for the callee to finish its operation.
    */
    @Override
    public void run() {
        String messageFromClient;
        // Continue to listen for messages while a connection with the client is still established.
        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                if (messageFromClient.equalsIgnoreCase("quit")){
                    removeClientHandler();
                }

                // Read what the client sent and then send it to the server.
                responseMessage(messageFromClient);
                Server.serverResponse(messageFromClient);

            } catch (IOException e) {
                // Close everything gracefully.
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    /*
     Send a response through each client handler thread so that everyone gets updated.
     Each clientHandler is a connection to a specific client. So for any response that
     is sent, loop through each connection.
    */
    public void responseMessage(String response) {
        for (ThreadHandler clientHandler : clientHandlers) {
            try {
                // You don't want to broadcast the message to all the users just the robot and server.
                if (clientHandler.robotName.equals(robotName)) {
                    clientHandler.bufferedWriter.write(response);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                    Server.serverResponse(response);
                }
            } catch (IOException e) {
                // close everything.
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    // If the client disconnects for any reason remove them from the list so a message isn't sent down a broken connection.
    public void removeClientHandler() {
        clientHandlers.remove(this);
        responseMessage(robotName + " has been terminated");
    }


    // Helper method to close everything
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // The client disconnected or an error occurred so remove them from the list so no message is broadcast.
        removeClientHandler();
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