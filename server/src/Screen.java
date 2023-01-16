import javax.swing.JFrame;

public class Screen extends JFrame
{
    
    // private int[][] gameZone;
    private GameZone gameZone;
    private Maze    maze;
    private int size;
    private Display display;

    public Screen(GameZone gameZone,int cellSize)
    {
        this.gameZone   = gameZone;
        this.size       = cellSize;
        this.setTitle("Turtle");
        this.setSize(this.size * 30,this.size * 30);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        this.add(new Display(this.gameZone));
        this.setVisible(true);
    }

    public Screen(int cellSize, Display display)
    {
        // this.maze       = maze;
        this.size       = cellSize;
        this.display    = display;
        this.setTitle("Turtle");
        this.setSize(this.size * 30,this.size * 30);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        this.add(this.display);
        // this.add();
        this.setVisible(true);
    }


}
