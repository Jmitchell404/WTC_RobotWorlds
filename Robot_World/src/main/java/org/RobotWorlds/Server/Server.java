package org.RobotWorlds.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    protected static final int PORT = 5000;


    private final PrintStream out;
    Socket socket = null;
    //        read data from a source (reading characters from server)
    InputStreamReader inputStreamReader;
    //        send messages
    OutputStreamWriter outputStreamWriter;
    //        reads large block an array at a time, can flush when full
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    //    waits and listens for connections
//    ServerSocket serverSocket = null;

    public Server(Socket socket) throws IOException {

           /*        what connects machines.
     the machines must have information about each other's network
     connection.
*/
        String clientMachine = socket.getInetAddress().getHostName();
        System.out.println("Connection from " + clientMachine);

//        These will allow for two-way communication to and from the server.
        out = new PrintStream(socket.getOutputStream());
        /*sending responds to client, outputToServer*/
        outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());


        /*receiving responds from client,inputToServer*/
        BufferedReader in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        inputStreamReader = new InputStreamReader(socket.getInputStream());


        bufferedReader = new BufferedReader(inputStreamReader);
        bufferedWriter = new BufferedWriter(outputStreamWriter);

        System.out.println("Waiting for client...");
    }

    public void run() {
        try {
            String MsgFromClient;
            while ((MsgFromClient = bufferedReader.readLine()) != null) {

//              turning streams into usable data,sends data back and forward

                System.out.println(MsgFromClient);
                bufferedWriter.write("Thanks robot, message received");
                bufferedWriter.newLine();
                bufferedWriter.flush();

//                    if (MsgFromClient.equalsIgnoreCase("quit")){break;}
                /*disconnects everything*/

                if ("quit".equalsIgnoreCase(MsgFromClient)) {
                    out.println("good bye");
                    System.out.println("Shutting down server");
                    System.exit(0);
                    break;
                }
            }


            socket.close();
            inputStreamReader.close();
            outputStreamWriter.close();
            bufferedReader.close();
            bufferedWriter.close();
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}