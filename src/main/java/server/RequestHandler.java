package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class RequestHandler implements Runnable {

    protected static final int PORT = 4000;
    private Socket client = null;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public RequestHandler(Socket socket) throws IOException {

        try {
            this.client = socket;

            String clientHostMachine = socket.getInetAddress().getHostName();
            System.out.println("Connection from " + clientHostMachine);


            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String command = bufferedReader.readLine();

            SendResponse(command);

        }catch(IOException ex){
            closing(socket,bufferedReader,bufferedWriter);
        }
    }

    public void SendResponse(String MsgFromClient){
        try {
            bufferedWriter.write(MsgFromClient);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch(IOException ex){
            closing(client, bufferedReader, bufferedWriter);
        }catch (Exception ex) {
            System.out.println("Exception in Thread Run. Exception : " + ex);
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

    @Override
    public void run() {
      try {
          String MsgFromClient;
          while ((MsgFromClient = bufferedReader.readLine()) != null) {
              System.out.println("Received message from " + Thread.currentThread().getName() + " : " + MsgFromClient);
              SendResponse(MsgFromClient);
          }
      }catch (IOException e) {
          System.out.println("I/O exception: " + e);
      } catch (Exception ex) {
          System.out.println("Exception in Thread Run. Exception : " + ex);
      }
    }

}
