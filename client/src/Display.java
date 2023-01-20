import javax.swing.*;
import java.awt.*;

public class Display extends JComponent implements Runnable {

    private Maze map;

    public Display(Maze map) 
    {
        this.map = map;
    }

    @Override
    protected void paintComponent(Graphics p) 
    {
        this.map.updateMaze();
        // if map is null, display loading screen
        if (this.map.getMap() == null && this.map.getStartX() == -1 && this.map.getStartY() == -1)
        {
            p.setColor(Color.BLACK);
            p.fillRect(0, 0, 900, 900);
            p.setColor(Color.WHITE);
            p.drawString("Loading map...", 100, 100);
        }
        else
        {
            // draw map
            for (int i = 0; i < this.map.getMap().length; i++)
            {
                for (int j = 0; j < this.map.getMap()[0].length; j++)
                {

                    if (this.map.getMap()[i][j].getIsKnown())
                    {
                        // p.setColor(Color.GREEN);
                        // p.fillRect(i*30, j*30, 30, 30);
                    }
                    else
                    {
                        // p.setColor(Color.BLACK);
                        // p.fillRect(i*30, j*30, 30, 30);
                    }

                    if (this.map.getMap()[i][j].getEastWall())
                    {
                        p.setColor(Color.RED);
                        p.drawLine((j+1)*30, i*30, (j+1)*30, (i+1)*30);
                    }

                    if (this.map.getMap()[i][j].getWestWall())
                    {
                        p.setColor(Color.RED);
                        p.drawLine(j*30, i*30, j*30, (i+1)*30);
                    }

                    if (this.map.getMap()[i][j].getNorthWall())
                    {
                        p.setColor(Color.RED);
                        p.drawLine(j*30, i*30, (j+1)*30, i*30);
                    }

                    if (this.map.getMap()[i][j].getSouthWall())
                    {
                        p.setColor(Color.RED);
                        p.drawLine(j*30, (i+1)*30, (j+1)*30, (i+1)*30);
                    }



                }
            }
        }

    }

    @Override
    public void run() {
        while (true)
        {
            this.repaint();
        }
        
    }

}
