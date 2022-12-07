import javax.swing.JFrame;

public class Screen extends JFrame
{
    
    private int[][] gameZone;

    public Screen(int[][] gameZone)
    {
        this.gameZone = gameZone;
        this.setTitle("Turtle");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.add(new Display(this.gameZone));






        this.setVisible(true);
    }


}
