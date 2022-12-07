import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {


    public static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
    
    // private String username;
    private Socket          socket;
    private BufferedWriter  writer;
    private BufferedReader  reader;
    private GameZone        gameZone;


    public ClientHandler(Socket socket,GameZone gameZone)
    {
        this.gameZone   = gameZone;
        try
        {
            this.socket = socket;
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clients.add(this);

        }
        catch (Exception e)
        {
            System.out.println("[ClientHandler] : " + e.getMessage());
        }
    }


    public void broadcast(String s)
    {
        try
        {
            for (ClientHandler client : clients)
            {
                if (client != this)
                {
                    client.writer.write(s);
                    client.writer.newLine();
                    client.writer.flush();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("[ClientHandler] : " + e.getMessage());
            this.close(this.socket, this.reader, this.writer);
        }
    }

    public void sendMessage(String s)
    {
        try
        {
            for (ClientHandler client : clients)
            {
                if (client == this)
                {
                    client.writer.write(s);
                    client.writer.newLine();
                    client.writer.flush();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("[ClientHandler] : " + e.getMessage());
            this.close(this.socket, this.reader, this.writer);
        }
    }

    public void clientLeft()
    {
        this.broadcast("[ClientHandler] : Client left !");
        clients.remove(this);
    }

    public void close(Socket socket, BufferedReader r, BufferedWriter w)
    {
        clientLeft();
        try
        {

            if (r != null)
            {
                r.close();
            }

            if (w != null)
            {
                w.close();
            }

            if (socket != null)
            {
                socket.close();
            }
        }
        catch (IOException e)
        {
            System.out.println("[ClientHandler] : " + e.getMessage());
        }
    }

    @Override
    public void run() {
        
        this.sendMessage(this.gameZone.getRandomEmptyCase()[0] + " " + this.gameZone.getRandomEmptyCase()[1]);
        while (!this.socket.isClosed())
        {
            try
            {
                String s = this.reader.readLine();
                if (s != null)
                {
                    System.out.println("[ClientHandler] : " + s);
                    this.broadcast(s);
                }
                else
                {
                    // Close the current thread
                    this.close(this.socket, this.reader, this.writer);
                    Thread.currentThread().interrupt();
                }
            }
            catch (IOException e)
            {
                System.out.println("[ClientHandler] : " + e.getMessage());
                this.close(this.socket, this.reader, this.writer);
            }
        }
        System.out.println("[ClientHandler] : Client left !");
        close(this.socket, this.reader, this.writer);

        
    }
    
}
