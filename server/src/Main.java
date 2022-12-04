import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        System.out.println("Début de génération du labyrinthe!");
        Labyrinth labyrinth = new Labyrinth(10, 10);
        labyrinth.Display();
        System.out.println("Fin du Labyrinth!");

        ServerSocket serverSocket = new ServerSocket(5000);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}