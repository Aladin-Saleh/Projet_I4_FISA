import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) throws IOException {
        Maze maze = new Maze(25, 25);
        maze.diplayMaze();

        ServerSocket serverSocket = new ServerSocket(5000);
        Server server = new Server(serverSocket, maze);
        server.startServer();
    }
}