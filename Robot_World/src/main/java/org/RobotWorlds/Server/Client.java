package org.RobotWorlds.Server;

//for socket
import java.net.*;
//for buffered and printStream
import java.io.*;
import java.util.Scanner;

public class Client {

    // initialize socket and input / output streams
    private Socket socket = null;
    private BufferedReader bufferedReader = null;
    private BufferedWriter bufferedWriter = null;
    private String name;

    //    constructor to instantiate properties
    public Client(Socket socket, String name){
        try{
            this.socket = socket;
//      sends output to the socket (sending)
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//      receive response from server (listening)
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.name = name;
        }
        catch(IOException ex){
            closingAll(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendRequest(){
        try {
            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String MsgToServer = scanner.nextLine();
                bufferedWriter.write(MsgToServer);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }
        catch(IOException ex){
            closingAll(socket, bufferedReader, bufferedWriter);
        }
    }

    public void listeningForRespond(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String MsgFromClient;
                    while (socket.isConnected()) {
                        MsgFromClient = bufferedReader.readLine();
                        System.out.println("Response: "+ MsgFromClient);
//
//                        if (MsgFromClient.equalsIgnoreCase("quit")){
//                            System.out.println("Disconnected "+ socket.isClosed() );
//                            closingAll(socket, bufferedReader, bufferedWriter);
//                            System.exit(-1);
//                        }
                    }
                }
                catch(IOException ex){
                    closingAll(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();
    }

    public void closingAll(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter){
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

    public static void main(String[] args) throws IOException {
/*      what connects machines.
        the machines must have information
        about each other's network
        connection.
 */
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Connected");
//      takes input from terminal
            System.out.println("What would you like to name your robot?");
            String name = scanner.nextLine();
            Socket socket = new Socket("localhost", Server.PORT);


            Client client = new Client(socket,name);
            client.listeningForRespond();
            client.sendRequest();
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}