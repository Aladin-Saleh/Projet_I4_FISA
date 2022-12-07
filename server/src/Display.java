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


    public Display(GameZone gameZone)
    {
        this.gameZone = gameZone;
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

        this.gameZone.paintGameZone(gPaint);

    }
}
