import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
public class KeyHandler implements KeyListener 
{

    private Maze map;
    private Client client; 
    private JSONHandler jsonHandler;
    private Display display;

    private String key;

    public KeyHandler(Maze map, Client client, Display display)
    {
        this.map = map;
        this.client = client;
        this.display = display;
        this.jsonHandler = new JSONHandler();
        this.key = "";
        System.out.println("KeyHandler created");
    }


    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key pressed " + e.getKeyChar());

        if (this.display != null) 
        {
            Map<String, String> position = new HashMap<String, String>();
            position.put("currentPosition", this.map.getStartX() + "," + this.map.getStartY());

            if(this.key == "")
            {
                this.display.setDirection(1);
                System.out.println("North wall is open");
                // Envoi une requete au serveur pour demander les informations de la case
                if ((this.map.getStartX() - 1) > -1 && this.map.getStartY() > -1)
                {
                    System.out.println("Sending request to server");
                    position.put("direction", "N");
                    position.put("position", (this.map.getStartX() - 1) + "," + (this.map.getStartY()));
                    this.client.sendMessage(this.jsonHandler.writeJSON(position));
                }

            }
            else if (e.getKeyChar() == 's')
            {
                this.display.setDirection(3);
                System.out.println("South wall is open");
                // Envoi une requete au serveur pour demander les informations de la case
                if ((this.map.getStartX() + 1) > -1 && this.map.getStartY() > -1)
                {
                    System.out.println("Sending request to server");
                    position.put("direction", "S");
                    position.put("position", (this.map.getStartX() + 1) + "," + (this.map.getStartY()));
    
                    this.client.sendMessage(this.jsonHandler.writeJSON(position));
                }    
            }
            else if (e.getKeyChar() == 'd')
            {
                this.display.setDirection(2);   
                System.out.println("East wall is open");
                if ((this.map.getStartX()) > -1 && (this.map.getStartY() + 1) > -1)
                {
                    System.out.println("Sending request to server");
                    position.put("direction", "E");
                    position.put("position", (this.map.getStartX()) + "," + (this.map.getStartY() + 1));
                    this.client.sendMessage(this.jsonHandler.writeJSON(position));
                }
                
            }
            else if (e.getKeyChar() == 'q')
            {
                this.display.setDirection(0);
                System.out.println("West wall is open");
                // Envoi une requete au serveur pour demander les informations de la case
                if ((this.map.getStartX()) > -1 && (this.map.getStartY() - 1) > -1)
                {
                    System.out.println("Sending request to server");
                    position.put("direction", "W");
                    position.put("position", (this.map.getStartX()) + "," + (this.map.getStartY() - 1));
    
                    this.client.sendMessage(this.jsonHandler.writeJSON(position));
                }   
            }
            // this.display.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        this.key = "";
    }
    
}
