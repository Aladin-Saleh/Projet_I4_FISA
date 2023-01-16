import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
    
    private ServerSocket serverSocket;
    private int          maxClient; 
    private GameZone     gameZone;
    private Screen       screen;
    private Maze         maze;
    private Display      display;

    public Server(ServerSocket ss)
    {
        this.serverSocket   = ss;
        this.maxClient      = 4;
        this.gameZone       = new GameZone(29);
        this.screen         = new Screen(this.gameZone,29);
    }

    public Server(ServerSocket ss, Maze maze)
    {
        this.maze           = maze;
        this.serverSocket   = ss;
        this.display        = new Display(this.maze);
        this.maxClient      = 4;
        this.gameZone       = new GameZone(29);
        this.screen         = new Screen(29, this.display);
    }

    // Lance le serveur
    public void startServer()
    {
        try
        {
            while (!this.serverSocket.isClosed())
            {

                System.out.println("[Server] : Waiting for client ...");
                if (this.maxClient != 0)
                {
                    Socket socket = this.serverSocket.accept();    
                    Thread thread = new Thread(new ClientHandler(socket,this.maze, this.display));

                    this.maxClient--;
                    System.out.println("[Server] : Client connected !");

                    thread.start();
                    System.out.println("[Server] : Client started!");

                }

            }


        }
        catch (IOException e)
        {
            System.out.println("[Server] : " + e.getMessage());
            this.closeServer();
        }
    }

    // Fermer le serveur
    public void closeServer()
    {
        try
        {
            this.serverSocket.close();
            System.out.println("[Server] : Server closed !");
        }
        catch (IOException e)
        {
            System.out.println("[Server] : " + e.getMessage());
        }
    }


}
