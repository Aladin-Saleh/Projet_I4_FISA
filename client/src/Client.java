import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client
{
    private BufferedReader bReader;
    private BufferedWriter bWriter;
    private BufferedReader readInput;
    private Socket         socket;
    private JSONHandler    jsonHandler;
    private Cell[][]       map;

    public Client(Socket socket, Cell[][] map)
    {
        try
        {
            this.map            = map;
            this.jsonHandler    = new JSONHandler();
            this.socket         = socket;
            this.bReader        = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bWriter        = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.readInput      = new BufferedReader(new InputStreamReader(System.in));
        } 
        catch (Exception e)
        {
            System.out.println("[Client]: " + e.getMessage());
            close(this.socket, this.bReader, this.bWriter);
        }
    }

    public void close(Socket socket,BufferedReader bR,BufferedWriter bW)
    {
        try
        {
            if (socket != null)
            {
                socket.close();
            }
            if (bR != null)
            {
                bR.close();
            }
            if (bW != null)
            {
                bW.close();
            }
            if (readInput != null)
            {
                readInput.close();
            }
        }
        catch (Exception e)
        {
            System.out.println("[Client]: " + e.getMessage());
        }
    }

    public void sendMessage()
    {
        try 
        {
            //Ecoute de l'entrée de client.
            //Scanner sc = new Scanner(System.in);
            while(this.socket.isConnected())
            {
                String msgSend = this.readInput.readLine();
                bWriter.write("("+this.socket.getInetAddress()+") : " + msgSend);
                bWriter.newLine();
                bWriter.flush();
            }
        }
        catch (IOException err) 
        {
            err.printStackTrace();
            close(this.socket,this.bReader,this.bWriter);
        }
    }


    public void listen()
    {
        new Thread(
            new Runnable(){

                @Override
                public void run()
                {
                    String message;
                    while (socket.isConnected())
                    {
                        try
                        {
                            message = bReader.readLine();
                            if (message != null)
                            {
                                System.out.println(jsonHandler.readJSON(message).get("position"));
                                System.out.println(jsonHandler.readJSON(message).get("maze"));
                                if (jsonHandler.readJSON(message).get("maze") != null)
                                {
                                    int xLength = Integer.parseInt(jsonHandler.readJSON(message).get("maze").toString().split(",")[0], 10);
                                    int yLength = Integer.parseInt(jsonHandler.readJSON(message).get("maze").toString().split(",")[1], 10);
                                    map = new Cell[xLength][yLength];

                                    for (int i = 0; i < xLength; i++)
                                    {
                                        for (int j = 0; j < yLength; j++)
                                        {
                                            map[i][j] = new Cell(i, j);
                                        }
                                    }
                                }

                                if (jsonHandler.readJSON(message).get("position") != null)
                                {
                                    int x = Integer.parseInt(jsonHandler.readJSON(message).get("position").toString().split(",")[0], 10);
                                    int y = Integer.parseInt(jsonHandler.readJSON(message).get("position").toString().split(",")[1], 10);
                                    // map[x][y].setTurtle(true);
                                }
                                
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println("[Client]: " + e.getMessage());
                            close(socket, bReader, bWriter);
                        }
                    }
                }
            }
        ).start();
    }
}
