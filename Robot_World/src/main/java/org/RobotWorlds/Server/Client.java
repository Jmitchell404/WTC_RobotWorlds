package org.RobotWorlds.Server;

//for socket
import java.net.*;
//for buffered and printStream
import java.io.*;
import java.util.Scanner;

public class Client {
    /* makes a connection to our server,
writes a string,
gets the response string
and then exit.*/

    public static void main(String[] args) {
/*        what connects machines.
        the machines must have information about each other's network
        connection.
 */
        Socket socket = null;
//        read data from a source (reading characters from server)
        InputStreamReader inputStreamReader = null;
//        send messages
        OutputStreamWriter outputStreamWriter = null;
//        reads large block an array at a time, can flush when full,response or receive messages
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 5000);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

//            because we are taking console input
            Scanner scanner = new Scanner(System.in);

            out.println("Hello Kiddo");
            out.flush();

            while (true) {
                String MsgToServer = scanner.nextLine();
                bufferedWriter.write(MsgToServer);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                String MsgFromServer = bufferedReader.readLine();
//            string value returned from server, waiting for response

                System.out.println("Response: "+ MsgFromServer);

                if (MsgToServer.equalsIgnoreCase("quit")) {
                    out.println("good bye");
                    break;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (socket != null){socket.close();}
                if (inputStreamReader != null){socket.close();}
                if (outputStreamWriter != null){socket.close();}
                if (bufferedReader != null){socket.close();}
                if (bufferedWriter != null){socket.close();}
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}