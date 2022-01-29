import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket ;

public class Server{

    private ServerSocket serverSocket;
    
    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket ;
    }

    public void startServer(){
        int i = 1 ;
        try{
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println(i+" Client has Connected !");
                i = i + 1 ;
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}