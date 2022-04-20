package org.RobotWorlds.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    protected static final int PORT = 5000;
    private Socket socket = null;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String robotName;

    public Server(Socket socket) throws IOException {
         /*        what connects machines.
     the machines must have information about each other's network
     connection.*/
        try {
            this.socket = socket;

            String clientMachine = socket.getInetAddress().getHostName();
            System.out.println("Connection from " + clientMachine);

            /*sending responds to client, outputToServer, managed*/
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            /*receiving responds from client,inputToServer*/
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.robotName = bufferedReader.readLine();

            Respond("Robot launched ");

        }catch(IOException ex){
            closing(socket,bufferedReader,bufferedWriter);
        }
    }

    public void Respond(String MsgFromClient){
//        sending response
        try {
            System.out.println(robotName+": " + MsgFromClient);
            bufferedWriter.write("Received message ");
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch(IOException ex){
            closing(socket, bufferedReader, bufferedWriter);
        }
    }
    public void closing(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter){
        try {
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if (socket != null){
                socket.close();
            }
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        try {
            String MsgFromClient;
            while ((MsgFromClient = bufferedReader.readLine()) != null) {
                Respond(MsgFromClient);

                if (MsgFromClient.equalsIgnoreCase("quit")) {
                    System.out.println("Shutting down server");
                    closing(socket,bufferedReader,bufferedWriter);
                    System.exit(0);
                    break;
                }
            }
        }catch(IOException ex){
            closing(socket,bufferedReader,bufferedWriter);
        }
    }
}