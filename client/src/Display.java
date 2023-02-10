import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.*;
import java.awt.image.BufferedImage;

public class Display extends JComponent {

    private Maze map;
    private Client client;
    private int idleFrameX;
    private int idleFrameY;
    private int direction;

    public Display(Maze map, Client client)

    {
        this.map = map;
        this.client = client;
        this.map.setGUI(this);
        this.setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics p) 
    {
        
        p.setColor(Color.BLACK);
        p.fillRect(0, 0, 960, 960);
        this.map.updateMaze();
        
        if (!this.client.getGameIsOver())
        {
            if (this.map.getMap() == null && this.map.getStartX() == -1 && this.map.getStartY() == -1)
            {
                p.setColor(Color.BLACK);
                p.fillRect(0, 0, 960, 960);
                p.setColor(Color.WHITE);
                p.drawString("Loading map...", 480, 480);
            }
            else
            {
                try
                {
                    BufferedImage directions_spritesheet = ImageIO.read(new File("res/directions.png"));
                    BufferedImage[] directions = {null,null,null,null,null};
                    
                    for(int i = 0; i< 5; i++)
                    {
                        directions[i] = directions_spritesheet.getSubimage(75*i,0, 75, 75);
                    }

                    BufferedImage idleSpriteSheet = ImageIO.read(new File("res/corbeau_spritesheet.png"));
                    BufferedImage player = null;

                    if(this.direction == 0)
                    {
                        this.idleFrameY = 1;
                        player = idleSpriteSheet.getSubimage(32*this.idleFrameX,idleFrameY*32, 32,32);
                    } 
                    else if(this.direction == 2)
                    {
                        this.idleFrameY = 0;
                        player = idleSpriteSheet.getSubimage(32*this.idleFrameX, idleFrameY*32, 32,32);
                    }
                    else
                    {
                        player = idleSpriteSheet.getSubimage(32*this.idleFrameX, idleFrameY*32, 32, 32);
                    }

                    p.drawImage(directions[this.direction],40,820,null);
                    this.direction = 4;

                    // draw map
                    for (int i = 0; i < this.map.getMap().length; i++)
                    {
                        for (int j = 0; j < this.map.getMap()[0].length; j++)
                        {
        
                            if (this.map.getMap()[i][j].getIsOccupied())
                            {
                                p.drawImage(player,j*32, i*32, null);
                                idleFrameX = (idleFrameX+1)%4;
                                System.out.println("South: "+this.map.getMap()[i+1][j].getSouthWall());
                            }
        
                            if (this.map.getMap()[i][j].getEastWall())
                            {
                                p.setColor(Color.WHITE);
                                p.drawLine((j+1)*32, i*32, (j+1)*32, (i+1)*32);
                            }
        
                            if (this.map.getMap()[i][j].getWestWall())
                            {
                                p.setColor(Color.WHITE);
                                p.drawLine(j*32, i*32, j*32, (i+1)*32);
                            }
        
                            if (this.map.getMap()[i][j].getNorthWall())
                            {
                                p.setColor(Color.WHITE);
                                p.drawLine(j*32, i*32, (j+1)*32, i*32);
                            }
        
                            if (this.map.getMap()[i][j].getSouthWall())
                            {
                                p.setColor(Color.WHITE);
                                p.drawLine(j*32, (i+1)*32, (j+1)*32, (i+1)*32);
                            }
                        }
                    }
                }
                catch(Exception e)
                {

                }
            }
        }
        else
        {
            String message = this.client.getAsWin() ? "You win!" : "You lose!";
            p.setColor(Color.BLACK);
            p.fillRect(0, 0, 960, 960);
            p.setColor(Color.WHITE);
            p.drawString(message, 450, 450);
        }
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
    }
}
