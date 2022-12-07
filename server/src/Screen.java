import javax.swing.JFrame;

public class Screen extends JFrame
{
    
    private int[][] gameZone;
    private int size;

    public Screen(int[][] gameZone,int cellSize)
    {
        this.gameZone   = gameZone;
        this.size       = cellSize;
        this.setTitle("Turtle");
        this.setSize(this.size * 40,this.size * 40);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        this.add(new Display(this.gameZone));






        this.setVisible(true);
    }


}
