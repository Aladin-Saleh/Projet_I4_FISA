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

    public Screen(KeyHandler keyHandler)
    {   
        this.keyHandler = keyHandler;



        this.setTitle("Turtle");
        this.setSize(300,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);


        this.addKeyListener(keyHandler);






        this.setVisible(true);
    }





}
