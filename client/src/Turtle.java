import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Turtle 
{    
    // Constante de direction
    public static final String NORTH    = "N";
    public static final String SOUTH    = "S";
    public static final String EAST     = "E";
    public static final String WEST     = "W";

    public static LogsHandler errorLogs;  
    public static LogsHandler successLogs;

    private Maze   map;
    private int     x;
    private int     y;

    private Socket      socket;
    private Screen      screen;
    private KeyHandler  keyHandler;



    public Turtle(int port)
    {
        try 
        {
            errorLogs   = new LogsHandler("./logs/","error_logs_synchro_extrabat.log");
            successLogs = new LogsHandler("./logs/","success_logs_synchro_extrabat.log");

            this.map = new Maze();
            this.socket = new Socket(InetAddress.getLocalHost(),port);
            Client client   = new Client(this.socket, this.map);
            // this.keyHandler = new KeyHandler(this.map, client);
            this.screen = new Screen(this.map, client);
            
            // new Thread(this.screen.getGUI()).start();

            client.listen();
            client.sendMessage();
        } 
        catch (IOException e)
        {   
            this.errorLogs.write("Erreur lors de la connexion au serveur");
            System.err.println("Erreur lors de la connexion au serveur");
            e.printStackTrace();
        }
    }
}
