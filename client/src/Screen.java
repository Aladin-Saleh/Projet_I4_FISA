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
        this.setSize(900,900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);


        this.gui = new Display(map, client);
        this.add(gui);
        
        this.keyHandler = new KeyHandler(map, client, gui);




        this.addKeyListener(keyHandler);

        this.setVisible(true);
    }


    public Display getGUI()
    {
        return this.gui;
    }




}
