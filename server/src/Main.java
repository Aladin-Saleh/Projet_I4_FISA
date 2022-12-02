import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        Labyrinth labyrinth = new Labyrinth(10, 10);
        labyrinth.Display();
        ServerSocket serverSocket = new ServerSocket(5000);

        Server server = new Server(serverSocket);
        server.startServer();
    }
}