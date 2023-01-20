import javax.swing.JFrame;

public class Screen extends JFrame 
{

    private KeyHandler keyHandler;

    public Screen()
    {
        this.setTitle("Turtle");
        this.setSize(300,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        this.setVisible(true);
    }

    public Screen(KeyHandler keyHandler, Maze map)
    {   
        this.keyHandler = keyHandler;



        this.setTitle("Turtle");
        this.setSize(900,900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);


        this.addKeyListener(keyHandler);
        Display gui = new Display(map);
        this.add(gui);



        new Thread(gui).start();


        this.setVisible(true);
    }





}
