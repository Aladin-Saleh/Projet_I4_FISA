public class Cell {
    

    // Les murs de la cellule (true = mur présent, false = mur absent)
    private boolean eastWall;
    private boolean westWall;
    private boolean southWall;
    private boolean northWall; 


    private boolean isTransporter; // Si la cellule est un transporteur ou non

    private boolean isBonus; // Si la cellule est un bonus ou non

    private boolean isExit; // Si la cellule est la sortie ou non

    private boolean isKnown; // Si la cellule est connue ou non

    private int x;
    private int y;

    public Cell(int x, int y)
    {
        this.eastWall   = true;
        this.westWall   = true;
        this.southWall  = true;
        this.northWall  = true;
        this.isTransporter = false;
        this.isBonus    = false;
        this.isExit     = false;
        this.isKnown    = false;

        this.x = x;
        this.y = y;
    }








}
