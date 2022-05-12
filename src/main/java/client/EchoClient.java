package za.co.wethinkcode.robotworld;

import org.json.simple.JSONObject;
import za.co.wethinkcode.robotworld.commands.Command;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
* A client sends messages to the server,
 * the server spawns a thread to communicate with the client.
* Each communication with a client is added to an array list
 * so any message sent gets sent to every
* other client by looping through it.
*/

public class EchoClient {

    static Scanner scanner;
    private Socket socket = null;
    private BufferedReader bufferedReader = null;
    private BufferedWriter bufferedWriter = null;

    public EchoClient(Socket socket){
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(IOException ex){
            closingAll(socket, bufferedReader, bufferedWriter);
        }
    }

    public void SendRequest(JSONObject request){
        try {
            bufferedWriter.write(String.valueOf(request));
            bufferedWriter.newLine();
            bufferedWriter.flush();

//            Scanner scanner = new Scanner(System.in);
//            while (socket.isConnected()) {
//                System.out.println((name + " - What must I do?").strip().toLowerCase());
//                String MsgToServer = scanner.nextLine();
//
//                while (MsgToServer.isBlank()){
//                System.out.println((name + " - What must I do?").strip().toLowerCase());
//                MsgToServer = scanner.nextLine();
//                }
//
//                bufferedWriter.write(MsgToServer);
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
//            }
        }
        catch(IOException ex){
            closingAll(socket, bufferedReader, bufferedWriter);
        }
    }

    public String listeningForResponse(){


        try{
            String response = this.bufferedReader.readLine();
            return response;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    String MsgFromServer;
//                    while (socket.isConnected() && ((MsgFromServer = bufferedReader.readLine()) != null)) {
//                        return MsgFromServer;
//
////                        System.out.println("Response : " + MsgFromServer  );
//                    }
//                }
//                catch(IOException ex){
//                    closingAll(socket, bufferedReader, bufferedWriter);
//                }
//            }
//        }).start();


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


    public static void main(String[] args) throws UnknownHostException,IOException {
/*      what connects machines.
        the machines must have information
        about each other's network
        connection.
 */
//        try {

        Socket socket = new Socket("localhost", 5000);
        EchoClient client = new EchoClient(socket);
        scanner = new Scanner(System.in);
        System.out.println("Connected");

        System.out.println("Please launch your robot <launch> <robot kind> <robot name>");
//        String commands = scanner.nextLine();

        while (true){
            try {
                String commands = scanner.nextLine();
                Command command = Command.create(commands);
                //            returns JSONObject
                JSONObject jsonObject = command.execute();
                //            System.out.println(Arrays.toString(arg));

                client.SendRequest(jsonObject);
                String response = client.listeningForResponse();
                command.handleResponse(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        catch(IOException ex) {
//            ex.printStackTrace();
//            System.err.println("Couldn't get I/O for the connection to localhost");
//            System.exit(1);
//        }
    }
}
