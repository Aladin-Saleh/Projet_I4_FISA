import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
    
    private ServerSocket serverSocket;
    private int          maxClient; 


    public Server(ServerSocket ss)
    {
        this.serverSocket   = ss;
        this.maxClient      = 4;
    }


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
                    Thread thread = new Thread(new ClientHandler(socket));

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
