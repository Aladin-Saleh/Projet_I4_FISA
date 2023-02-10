import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Screen extends JFrame 
{
    private KeyHandler keyHandler;
    private Display gui;

    public Screen()
    {
        this.setTitle("Turtle");
        this.setSize(300,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        this.setVisible(true);
    }

    public Screen(Maze map, Client client)
    {   
        this.setTitle("Turtle");
        this.setSize(960,960);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        this.gui = new Display(map, client);
        this.add(gui);
        this.keyHandler = new KeyHandler(map, client, gui);
        this.addKeyListener(keyHandler);
        this.setVisible(true);
        this.gui.repaint();
        this.repaint();
    }
 
    public void repaint()
    {
        ScheduledExecutorService executorService;
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(
            new Runnable(){

                @Override
                public void run()
                {
                    gui.repaint();
                }
            }, 0, 500, TimeUnit.MILLISECONDS);
    }

    public Display getGUI()
    {
        return this.gui;
    }
}
