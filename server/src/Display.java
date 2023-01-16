import javax.swing.JComponent;
import javax.swing.*;
import java.awt.Graphics;
import java.util.*;
import java.util.List;
import java.awt.*;

public class Display extends JComponent
{
    

    // private int[][] gameZone;
    private GameZone gameZone;
    private Maze    maze;

    public Display(GameZone gameZone)
    {
        this.gameZone = gameZone;
        this.maze = null;
    }

    public Display(Maze maze)
    {
        this.maze = maze;
        this.gameZone = null;
    }

    

    

    @Override
    protected void paintComponent(Graphics p) 
    {
        Graphics gPaint = p.create();

        if (this.isOpaque()) 
        {
          gPaint.setColor(this.getBackground());
          gPaint.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

        if (this.gameZone != null)
        {
            this.gameZone.paintGameZone(gPaint);
        }
        else
        {
            this.maze.paintMaze(gPaint);
        }

    }
}
