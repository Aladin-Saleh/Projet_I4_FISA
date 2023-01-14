import java.net.InetAddress;
import java.net.Socket;

public class Turtle 
{    
    // Constante de direction
    public static final String NORTH    = "N";
    public static final String SOUTH    = "S";
    public static final String EAST     = "E";
    public static final String WEST     = "W";

    // Matrice vide de 10x10
    private int[][] matrix = new int[10][10];

    // Position de la tortue
    private int x = 0;
    private int y = 0;

    // Direction de la tortue
    private String direction;
    
    private Client client;

    public Turtle() 
    {
        this.direction = NORTH;

        // Initialisation de la matrice
        for (int i = 0; i < this.matrix.length; i++) 
        {
            for (int j = 0; j < this.matrix[i].length; j++) 
            {
                this.matrix[i][j] = 0;
            }
        }

        try
        {
            this.client = new Client(new Socket(InetAddress.getLocalHost(),5000));
            this.client.listen();
            this.client.sendMessage();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Turtle(int port)
    {
        this.direction = NORTH;

        // Initialisation de la matrice
        for (int i = 0; i < this.matrix.length; i++) 
        {
            for (int j = 0; j < this.matrix[i].length; j++) 
            {
                this.matrix[i][j] = 0;
            }
        }

        try
        {
            this.client = new Client(new Socket(InetAddress.getLocalHost(),port));
            this.client.listen();
            this.client.sendMessage();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
