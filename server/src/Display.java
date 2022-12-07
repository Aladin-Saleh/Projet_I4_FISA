import javax.swing.JComponent;
import javax.swing.*;
import java.awt.Graphics;
import java.util.*;
import java.util.List;
import java.awt.*;

public class Display extends JComponent
{
    

    private int[][] gameZone;


    public Display(int[][] gameZone)
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

        gPaint.setColor(Color.ORANGE);

        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) {
                
                if (this.gameZone[i][j] == GameZone.WALL)
                {
                    gPaint.fillRect(i*50, j*50, 50, 50);
                }

                if (this.gameZone[i][j] == GameZone.EMPTY)
                {
                    gPaint.setColor(Color.GREEN);
                    gPaint.fillRect(i*50, j*50, 50, 50);
                }

                if (this.gameZone[i][j] == GameZone.EXIT)
                {
                    gPaint.setColor(Color.RED);
                    gPaint.fillRect(i*50, j*50, 50, 50);
                }

                if (this.gameZone[i][j] == GameZone.BONUS_SALAD)
                {
                    gPaint.setColor(Color.YELLOW);
                    gPaint.fillRect(i*50, j*50, 50, 50);
                }

                if (this.gameZone[i][j] == GameZone.TRANSPORTER)
                {
                    gPaint.setColor(Color.BLUE);
                    gPaint.fillRect(i*50, j*50, 50, 50);
                }
            }
        }


    }
}
