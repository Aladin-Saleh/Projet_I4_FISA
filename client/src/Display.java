import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import  java.awt.event.*;

public class Display extends JComponent{

    private Maze map;
    private Client client;
    private int idleFrameX;
    private int idleFrameY;
    private int direction;
    private JLabel button_volume_down;
    private JLabel button_volume_up;
    private JLabel button_volume_mute;
    private boolean muted;

    public Display(Maze map, Client client)
    {
        this.map = map;
        this.client = client;
        this.map.setGUI(this);
        this.setDoubleBuffered(true);
        this.muted = this.client.getMusicHandler().getVolume() > 0f;
        try
        {
            BufferedImage sound_spritesheet = ImageIO.read(new File("res/sound_spritesheet.png"));
            this.button_volume_down = new JLabel(new ImageIcon(sound_spritesheet.getSubimage(37, 0, 37, 35)));
            this.button_volume_down.setBounds(755, 820, 37, 35);
            this.add(button_volume_down);
            this.button_volume_down.addMouseListener(new MouseInputAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) 
                {
                    if(client.getMusicHandler().getVolume() >= 0.1f)
                    {
                        client.getMusicHandler().setVolume(client.getMusicHandler().getVolume()-0.1f);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) 
                {
                    button_volume_down.setIcon(new ImageIcon(sound_spritesheet.getSubimage(37,35,37, 35)));
                }

                @Override
                public void mouseExited(MouseEvent e) 
                {
                    button_volume_down.setIcon(new ImageIcon(sound_spritesheet.getSubimage(37,0,37, 35)));
                }
            });

            this.button_volume_up = new JLabel(new ImageIcon(sound_spritesheet.getSubimage(74, 0, 37, 35)));
            this.button_volume_up.setBounds(860, 820, 37, 35);
            this.add(button_volume_up);
            this.button_volume_up.addMouseListener(new MouseInputAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) 
                {
                    if(client.getMusicHandler().getVolume() <1f)
                    {
                        client.getMusicHandler().setVolume(client.getMusicHandler().getVolume()+0.1f);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) 
                {
                    button_volume_up.setIcon(new ImageIcon(sound_spritesheet.getSubimage(74,35,37, 35)));
                }

                @Override
                public void mouseExited(MouseEvent e) 
                {
                    button_volume_up.setIcon(new ImageIcon(sound_spritesheet.getSubimage(74,0,37, 35)));
                }
            });

            this.button_volume_mute = new JLabel(new ImageIcon(sound_spritesheet.getSubimage(0, 0,37,35)));
            this.button_volume_mute.setBounds(710, 820, 37, 35);
            this.add(button_volume_mute);
            this.button_volume_mute.addMouseListener(new MouseInputAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) 
                {
                    muted = !muted;
                    if(muted)
                    {
                        client.getMusicHandler().mute();
                        button_volume_mute.setIcon(new ImageIcon(sound_spritesheet.getSubimage(111,0,37, 35)));
                    }
                    else
                    {
                        client.getMusicHandler().unmute();
                        button_volume_mute.setIcon(new ImageIcon(sound_spritesheet.getSubimage(0,0,37, 35)));
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) 
                {
                    if(muted)
                    {
                        button_volume_mute.setIcon(new ImageIcon(sound_spritesheet.getSubimage(111,35,37, 35)));
                    }
                    else
                    {
                        button_volume_mute.setIcon(new ImageIcon(sound_spritesheet.getSubimage(0,35,37, 35)));
                    }
                    
                }

                @Override
                public void mouseExited(MouseEvent e) 
                {
                    if(muted)
                    {
                        button_volume_mute.setIcon(new ImageIcon(sound_spritesheet.getSubimage(111,0,37, 35)));
                    }
                    else
                    {
                        button_volume_mute.setIcon(new ImageIcon(sound_spritesheet.getSubimage(0,0,37, 35)));
                    }
                }
            });

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics p) 
    {
        
        p.setColor(Color.BLACK);
        p.fillRect(0, 0, 960, 960);
        
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
                    BufferedImage volume_bar = ImageIO.read(new File("res/volume_bar.png"));
                    p.drawImage(volume_bar.getSubimage(0, 0,48,35),800,820,null);

                    BufferedImage sound_spritesheet = ImageIO.read(new File("res/sound_spritesheet.png"));
                    BufferedImage volume_muted = sound_spritesheet.getSubimage(111,0, 37, 35);
                    BufferedImage volume_on = sound_spritesheet.getSubimage(0,0, 37, 35);

                    if(muted)
                    {
                        p.drawImage(volume_on,710,820,null);
                    }
                    else
                    {
                        p.drawImage(volume_muted,710,820,null);
                    }

                    for(int i = 0;i<(int)(this.client.getMusicHandler().getVolume()*10);i++)
                    {
                        p.drawImage(volume_bar.getSubimage(48, 0, 2, 35),805+(i*4),820,null);
                    }

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
                    System.out.println(e.getMessage());
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
