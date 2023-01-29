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

    private boolean isOccupied;

    private int x;
    private int y;

    public Cell(int x, int y)
    {
        this.eastWall   = false;
        this.westWall   = false;
        this.southWall  = false;
        this.northWall  = false;
        this.isTransporter = false;
        this.isBonus    = false;
        this.isExit     = false;
        this.isKnown    = false;
        this.isOccupied = false;

        this.x = x;
        this.y = y;
    }


    // Getters
    public boolean getEastWall()   { return this.eastWall; }
    public boolean getWestWall()   { return this.westWall; }
    public boolean getSouthWall()  { return this.southWall; }
    public boolean getNorthWall()  { return this.northWall; }

    public boolean getIsTransporter() { return this.isTransporter; }
    public boolean getIsBonus()    { return this.isBonus; }
    public boolean getIsExit()     { return this.isExit; }
    public boolean getIsKnown()    { return this.isKnown; }
    public boolean getIsOccupied() { return this.isOccupied; }

    public int getX() { return this.x; }
    public int getY() { return this.y; }

    // Setters
    public void setEastWall(boolean eastWall)   { this.eastWall = eastWall; }
    public void setWestWall(boolean westWall)   { this.westWall = westWall; }
    public void setSouthWall(boolean southWall) { this.southWall = southWall; }
    public void setNorthWall(boolean northWall) { this.northWall = northWall; }

    public void setIsTransporter(boolean isTransporter) { this.isTransporter = isTransporter; }
    public void setIsBonus(boolean isBonus)    { this.isBonus = isBonus; }
    public void setIsExit(boolean isExit)     { this.isExit = isExit; }
    public void setIsKnown(boolean isKnown)    { this.isKnown = isKnown; }
    public void setIsOccupied(boolean isOccupied) { this.isOccupied = isOccupied; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }







}
