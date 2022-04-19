package org.RobotWorlds.Server;

//for socket
import java.net.*;
//for buffered and printStream
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

// IP Address of server and port number?

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
                    while (true){

                        String MsgFromServer= (bufferedReader.readLine());
                        if (socket.isConnected()){
                            if ( MsgFromServer!= null && !MsgFromServer.equalsIgnoreCase( "quit") ){
                            System.out.println("Response: "+ MsgFromServer);
                            }
                            else if(MsgFromServer==null && MsgFromServer.equalsIgnoreCase("quit"))
                            {
                            System.out.println("Disconnecting: "+socket.isClosed());
                            closingAll(socket,bufferedReader,bufferedWriter);
                            System.exit(1);
                            }
                        }
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



//        try {
//
//            inputStreamReader = new InputStreamReader(socket.getInputStream());
//            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
//
//            out = new PrintStream(socket.getOutputStream());
//            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            bufferedReader = new BufferedReader(inputStreamReader);
//            bufferedWriter = new BufferedWriter(outputStreamWriter);
//
////            because we are taking console input
//
//            out.println("Hello World");
////            out.flush();
//
//            while (true) {
//                String MsgFromServer = bufferedReader.readLine();
//
////                whatever is input is written to server
//                String MsgToServer = scanner.nextLine();
//                bufferedWriter.write(MsgToServer);
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
////            string value returned from server, waiting for response
//
//                System.out.println("Response: "+ MsgFromServer);
//
//
//                if (MsgToServer.equalsIgnoreCase("quit")) {
//                    out.println("good bye");
//                    break;
//                }
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                if (socket != null){socket.close();}
//                if (inputStreamReader != null){socket.close();}
//                if (outputStreamWriter != null){socket.close();}
//                if (bufferedReader != null){socket.close();}
//                if (bufferedWriter != null){socket.close();}
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//        }
    }
}