package za.co.wethinkcode.robotworld;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends RequestHandler{

  public static Map<String,RequestHandler> clients = new ConcurrentHashMap<>();


  public Server(Socket socket)throws IOException{
    super(socket);
  }

  @SuppressWarnings("InfiniteLoopStatement")
  public static void main(String[] args) throws IOException {

    System.out.println("Server started and running");
    ExecutorService executor = null;

    try (ServerSocket serverSocket = new ServerSocket(RequestHandler.PORT)) {
      executor = Executors.newFixedThreadPool(8);
      System.out.println("Waiting for client connections...");

      while (true) {
        //halted till client connection
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client accepted!\nIP & Port is: " + clientSocket);
        RequestHandler worker = new RequestHandler(clientSocket);
        executor.execute(worker);
        Thread task = new Thread(worker);
        task.start();
      }

    }catch (IOException e) {
       System.out.println("Exception caught when trying to listen on port "
	+ RequestHandler.PORT + " or listening for a connection");
       System.out.println(e.getMessage());
    }
    finally {
        if (executor != null) {
	executor.shutdown();
	}
    }
  }
}

