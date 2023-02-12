import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuDisplay extends JComponent implements MouseListener {
    


    private Rectangle playButton = new Rectangle(100, 100, 100, 50);
    private Rectangle helpButton = new Rectangle(100, 200, 100, 50);
    private Rectangle quitButton = new Rectangle(100, 300, 100, 50);


    @Override
    protected void paintComponent(Graphics p) 
    {
        super.paintComponent(p);
        Graphics2D g = (Graphics2D) p;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 500);
        g.setColor(Color.WHITE);
        g.draw(playButton);
        g.draw(helpButton);
        g.draw(quitButton);
        g.drawString("Play", playButton.x + 19, playButton.y + 30);
        g.drawString("Help", helpButton.x + 19, helpButton.y + 30);
        g.drawString("Quit", quitButton.x + 19, quitButton.y + 30);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
        if (playButton.contains(e.getPoint()))
        {
            System.out.println("Play");
            new Turtle(5000);

        }
        else if (helpButton.contains(e.getPoint()))
        {
            System.out.println("Help");
        }
        else if (quitButton.contains(e.getPoint()))
        {
            System.out.println("Quit");
        }


    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }








}
