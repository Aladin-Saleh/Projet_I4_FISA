import javax.imageio.ImageIO;
import javax.security.auth.PrivateCredentialPermission;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import com.fasterxml.jackson.databind.node.BooleanNode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.*;

public class Display extends JComponent {

    private Maze map;
    private Client client;
    private int idleFrameX;
    private int idleFrameY;
    private int direction;
    private JLabel button_volume_down;
    private JLabel button_volume_up;
    private JLabel button_volume_mute;
    private boolean muted;
    private boolean isBonusActive;

    //Chargement des images
    private BufferedImage tp;
    private BufferedImage sound_spritesheet;
    private BufferedImage volume;
    private BufferedImage volume_bar;
    private BufferedImage[] directions = {null,null,null,null,null};
    private BufferedImage[][] idle_spritesheet = new BufferedImage[2][4];

    private ImageIcon mute;
    private ImageIcon mute_hover;
    private ImageIcon on;
    private ImageIcon on_hover;
    private ImageIcon down;
    private ImageIcon down_hover;
    private ImageIcon up;
    private ImageIcon up_hover;

    public Display(Maze map, Client client) {
        this.map = map;
        this.client = client;
        this.map.setGUI(this);
        this.setDoubleBuffered(true);
        this.muted = this.client.getMusicHandler().getVolume() > 0f;
        this.isBonusActive = false;
        try
        {
            this.sound_spritesheet                  = ImageIO.read(new File("res/sound_spritesheet.png"));
            this.tp                                 = ImageIO.read(new File("res/pentacle.png"));
            this.mute                               = new ImageIcon(sound_spritesheet.getSubimage(0,0,37, 35));
            this.mute_hover                         = new ImageIcon(sound_spritesheet.getSubimage(0,35,37, 35));
            this.on                                 = new ImageIcon(sound_spritesheet.getSubimage(111,0,37, 35));
            this.on_hover                           = new ImageIcon(sound_spritesheet.getSubimage(111,35,37, 35));
            this.down                               = new ImageIcon(this.sound_spritesheet.getSubimage(37, 0, 37, 35));
            this.down_hover                         = new ImageIcon(sound_spritesheet.getSubimage(37,35,37, 35));
            this.up                                 = new ImageIcon(this.sound_spritesheet.getSubimage(74, 0, 37, 35));
            this.up_hover                           = new ImageIcon(sound_spritesheet.getSubimage(74,35,37, 35));

            BufferedImage volume_bar_spritesheet    = ImageIO.read(new File("res/volume_bar.png"));
            BufferedImage directions_spritesheet    = ImageIO.read(new File("res/directions.png"));
            BufferedImage idleBufferedImage         = ImageIO.read(new File("res/corbeau_spritesheet.png"));

            this.volume = volume_bar_spritesheet.getSubimage(0, 0,48,35);
            this.volume_bar = volume_bar_spritesheet.getSubimage(48, 0, 2, 35);

            for(int i = 0; i< 5; i++)
            {
                directions[i] = directions_spritesheet.getSubimage(75*i,0, 75, 75);
            }
            
            for(int i=0;i<2;i++)
            {
                for(int j=0;j<4;j++)
                {
                    this.idle_spritesheet[i][j] = idleBufferedImage.getSubimage(32*j, i*32, 32, 32);
                }
            }

            this.InitButtonMute();
            this.InitButtonVolumeDown();
            this.InitButtonVolumeUp();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics p) {

        p.setColor(Color.BLACK);
        p.fillRect(0, 0, 960, 960);

        if (!this.client.getGameIsOver()) {
            if (this.map.getMap() == null && this.map.getStartX() == -1 && this.map.getStartY() == -1) {
                p.setColor(Color.BLACK);
                p.fillRect(0, 0, 960, 960);
                p.setColor(Color.WHITE);
                p.drawString("Loading map...", 480, 480);
            }
            else
            {
                try
                {
                    p.drawImage(this.volume,800,820,null);

                    for(int i = 0;i<(int)(this.client.getMusicHandler().getVolume()*10);i++)
                    {
                        p.drawImage(this.volume_bar,804+(i*4),820,null);
                    }

                    if(this.direction == 0)
                    {
                        this.idleFrameY = 1;
                    } 
                    else if(this.direction == 2)
                    {
                        this.idleFrameY = 0;
                    }

                    BufferedImage player = this.idle_spritesheet[this.idleFrameY][this.idleFrameX];;

                    p.drawImage(this.directions[this.direction],40,820,null);
                    this.direction = 4;
                    

                    

                    // draw map
                    for (int i = 0; i < this.map.getMap().length; i++)
                    {
                        for (int j = 0; j < this.map.getMap()[0].length; j++)
                        {
        
                            if (this.map.getMap()[i][j].getIsOccupied())
                            {
                                p.drawImage(player,j*32, i*32, null);
                                this.idleFrameX = (this.idleFrameX+1)%4;
                            }

                            if (this.map.getMap()[i][j].getIsTransporter()) 
                            {
                                p.setColor(Color.WHITE);
                                p.drawOval(j * 32, i * 32, 32, 32);
                            }

                            if (this.map.getMap()[i][j].getEastWall()) {
                                p.setColor(Color.WHITE);
                                p.drawLine((j + 1) * 32, i * 32, (j + 1) * 32, (i + 1) * 32);
                            }

                            if (this.map.getMap()[i][j].getWestWall()) {
                                p.setColor(Color.WHITE);
                                p.drawLine(j * 32, i * 32, j * 32, (i + 1) * 32);
                            }

                            if (this.map.getMap()[i][j].getNorthWall()) {
                                p.setColor(Color.WHITE);
                                p.drawLine(j * 32, i * 32, (j + 1) * 32, i * 32);
                            }

                            if (this.map.getMap()[i][j].getSouthWall()) {
                                p.setColor(Color.WHITE);
                                p.drawLine(j * 32, (i + 1) * 32, (j + 1) * 32, (i + 1) * 32);
                            }

                            if(this.map.getMap()[i][j].getIsTransporter())
                            {
                                p.drawImage(this.tp,j*32,i*32,null);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            String message = this.client.getisWin() ? "You win!" : "You lose!";
            p.setColor(Color.BLACK);
            p.fillRect(0, 0, 960, 960);
            p.setColor(Color.WHITE);
            p.drawString(message, 450, 450);
        }
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void InitButtonMute()
    {
        this.button_volume_mute = new JLabel(mute);
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
                    button_volume_mute.setIcon(mute);
                }
                else
                {
                    client.getMusicHandler().unmute();
                    button_volume_mute.setIcon(on);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) 
            {
                if(muted)
                {
                    button_volume_mute.setIcon(mute_hover);
                }
                else
                {
                    button_volume_mute.setIcon(on_hover);
                }
                
            }

            @Override
            public void mouseExited(MouseEvent e) 
            {
                if(muted)
                {
                    button_volume_mute.setIcon(mute);
                }
                else
                {
                    button_volume_mute.setIcon(on);
                }
            }
        });
    }

    public void InitButtonVolumeUp()
    {
        this.button_volume_up = new JLabel(up);
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
                button_volume_up.setIcon(up_hover);
            }

            @Override
            public void mouseExited(MouseEvent e) 
            {
                button_volume_up.setIcon(up);
            }
        });
    }

    public void InitButtonVolumeDown()
    {
        this.button_volume_down = new JLabel(this.down);
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
                button_volume_down.setIcon(down_hover);
            }

            @Override
            public void mouseExited(MouseEvent e) 
            {
                button_volume_down.setIcon(down);
            }
        });
    }

    public void setIsBonusActive(boolean isBonusActive)
    {
        this.isBonusActive = isBonusActive;
    }
}
