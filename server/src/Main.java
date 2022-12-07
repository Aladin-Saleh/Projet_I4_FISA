import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) throws IOException {
        Maze Maze = new Maze(50, 50);
        Maze.Display();

        ServerSocket serverSocket = new ServerSocket(5000);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}