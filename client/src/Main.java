import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Hello World!");
        Socket socket;
        

        socket          = new Socket(InetAddress.getLocalHost(),5000);
        Client client   = new Client(socket);
        client.listen();
        client.sendMessage();


    }
}