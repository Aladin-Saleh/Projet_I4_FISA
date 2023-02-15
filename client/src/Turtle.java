import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class Turtle 
{    
    // Constante de direction
    public static final String NORTH    = "N";
    public static final String SOUTH    = "S";
    public static final String EAST     = "E";
    public static final String WEST     = "W";


    private Maze   map;
    private int     x;
    private int     y;

    private Socket  socket;
    private Screen  screen;
    private KeyHandler keyHandler;
    private File config;

    public Turtle(int port)
    {
        try 
        {

            this.map = new Maze();
            this.config = new File("config.txt");
            Scanner myReader = new Scanner(this.config);
            String server_address = "localhost";
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                if(data.contains("Server-address :"))
                {
                    server_address = data.split(":")[1].trim();
                }
            }
            myReader.close();
            System.out.println(server_address);
            this.socket = new Socket(server_address,port);
            Client client   = new Client(this.socket, this.map);
            // this.keyHandler = new KeyHandler(this.map, client);
            this.screen = new Screen(this.map, client);
            
            // new Thread(this.screen.getGUI()).start();

            client.listen();
            client.sendMessage();
        } 
        catch (IOException e)
        {
            System.err.println("Erreur lors de la connexion au serveur");
            e.printStackTrace();
        }
    }
}
