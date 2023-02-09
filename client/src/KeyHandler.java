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
            this.display.repaint();
            Map<String, String> position = new HashMap<String, String>();
            position.put("currentPosition", this.map.getStartX() + "," + this.map.getStartY());

            if(this.key == "")
            {
                this.key = e.getKeyText(e.getKeyCode());
                if (e.getKeyChar() == 'z')
                {
                    System.out.println("North wall is open");
                    // Envoi une requete au serveur pour demander les informations de la case
                    if ((this.map.getStartX() - 1) > -1 && this.map.getStartY() > -1)
                    {
                        System.out.println("Sending request to server");
                        if (!this.map.getMap()[this.map.getStartX() - 1][this.map.getStartY()].getIsKnown())
                        {
                            this.map.getMap()[this.map.getStartX() - 1][this.map.getStartY()].setEastWall(false);
                            this.map.getMap()[this.map.getStartX() - 1][this.map.getStartY()].setWestWall(false);
                            this.map.getMap()[this.map.getStartX() - 1][this.map.getStartY()].setNorthWall(false);
                            this.map.getMap()[this.map.getStartX() - 1][this.map.getStartY()].setSouthWall(false);
                        
                        }
                        
                        position.put("direction", "N");
                        position.put("position", (this.map.getStartX() - 1) + "," + (this.map.getStartY()));
                        this.client.sendMessage(this.jsonHandler.writeJSON(position));
                        this.display.repaint();
                    }
    
                }
                else if (e.getKeyChar() == 's')
                {
                    System.out.println("South wall is open");
                    // Envoi une requete au serveur pour demander les informations de la case
                    if ((this.map.getStartX() + 1) > -1 && this.map.getStartY() > -1)
                    {
                        System.out.println("Sending request to server");
                        if (!this.map.getMap()[this.map.getStartX() + 1][this.map.getStartY()].getIsKnown())
                        {
                            this.map.getMap()[this.map.getStartX() + 1][this.map.getStartY()].setEastWall(false);
                            this.map.getMap()[this.map.getStartX() + 1][this.map.getStartY()].setWestWall(false);
                            this.map.getMap()[this.map.getStartX() + 1][this.map.getStartY()].setNorthWall(false);
                            this.map.getMap()[this.map.getStartX() + 1][this.map.getStartY()].setSouthWall(false);      
        
                        }
                        
                        // Map<String, String> position = new HashMap<String, String>();
                        // position.put("position", this.map.getStartX() + "," + (this.map.getStartY() + 1));
                        position.put("direction", "S");
                        position.put("position", (this.map.getStartX() + 1) + "," + (this.map.getStartY()));
        
                        this.client.sendMessage(this.jsonHandler.writeJSON(position));
                        this.display.repaint();
                    }
                    
        
                }
                else if (e.getKeyChar() == 'd')
                {
                    System.out.println("East wall is open");
                    if ((this.map.getStartX()) > -1 && (this.map.getStartY() + 1) > -1)
                    {
                        System.out.println("Sending request to server");
                        // Envoi une requete au serveur pour demander les informations de la case
                        if (!this.map.getMap()[this.map.getStartX()][this.map.getStartY() + 1].getIsKnown())
                        {
                            this.map.getMap()[this.map.getStartX()][this.map.getStartY() + 1].setEastWall(false);
                            this.map.getMap()[this.map.getStartX()][this.map.getStartY() + 1].setWestWall(false);
                            this.map.getMap()[this.map.getStartX()][this.map.getStartY() + 1].setNorthWall(false);
                            this.map.getMap()[this.map.getStartX()][this.map.getStartY() + 1].setSouthWall(false);

                        }
    
                        // Map<String, String> position = new HashMap<String, String>();
                        position.put("direction", "E");
                        position.put("position", (this.map.getStartX()) + "," + (this.map.getStartY() + 1));
                        this.client.sendMessage(this.jsonHandler.writeJSON(position));
                    }
                    
                }
                else if (e.getKeyChar() == 'q')
                {
                    System.out.println("West wall is open");
                    // Envoi une requete au serveur pour demander les informations de la case
                    if ((this.map.getStartX()) > -1 && (this.map.getStartY() - 1) > -1)
                    {
                        System.out.println("Sending request to server");
                        if (!this.map.getMap()[this.map.getStartX()][this.map.getStartY() - 1].getIsKnown())
                        {
                            this.map.getMap()[this.map.getStartX()][this.map.getStartY() - 1].setEastWall(false);
                            this.map.getMap()[this.map.getStartX()][this.map.getStartY() - 1].setWestWall(false);
                            this.map.getMap()[this.map.getStartX()][this.map.getStartY() - 1].setNorthWall(false);
                            this.map.getMap()[this.map.getStartX()][this.map.getStartY() - 1].setSouthWall(false);
        
                        }
                        
                        // Map<String, String> position = new HashMap<String, String>();
                        // position.put("position", (this.map.getStartX() - 1) + "," + this.map.getStartY());
                        position.put("direction", "W");
                        position.put("position", (this.map.getStartX()) + "," + (this.map.getStartY() - 1));
        
                        this.client.sendMessage(this.jsonHandler.writeJSON(position));
                    }
                    
                }
                this.display.repaint();
            }
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
