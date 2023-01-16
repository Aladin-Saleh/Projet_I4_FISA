import java.util.ArrayList;

public class Cell {
    
    // Les murs de la cellule (true = mur présent, false = mur absent)
    private boolean eastWall;
    private boolean westWall;
    private boolean southWall;
    private boolean northWall; 
    
    private boolean isOccupied; // Si la cellule est occupée ou non

    private boolean isTransporter; // Si la cellule est un transporteur ou non

    private boolean isBonus; // Si la cellule est un bonus ou non

    private boolean isExit; // Si la cellule est la sortie ou non

    // Si la cellule a été visitée ou non
    private boolean isVisited;

    private int zoneId; // Identifiant de la zone à laquelle appartient la cellule

    // Coordonnées de la cellule dans la matrice
    private int x;
    private int y;

    private ArrayList<Cell> neighbors; // Listes des voisins de la cellule

    public Cell(int zoneId, int x, int y)
    {
        this.eastWall   = true;
        this.westWall   = true;
        this.southWall  = true;
        this.northWall  = true;
        this.isVisited  = false;
        this.isOccupied = false;


        // 3 % de chance d'avoir un bonus
        if(Math.random() < 0.02)
        {
            this.isBonus = true;
        }
        else
        {
            this.isBonus = false;
        }

        // 1 % de chance d'avoir un transporteur
        if(Math.random() < 0.01)
        {
            this.isTransporter = true;
        }
        else
        {
            this.isTransporter = false;
        }




        this.zoneId     = zoneId;
        this.x          = x;
        this.y          = y;
    }

    public boolean areSameZone(Cell c)
    {
        return this.zoneId == c.getZoneId();
    }

    public boolean isExit()
    {
        return this.isExit;
    }

    public void setExit(boolean isExit)
    {
        this.isExit = isExit;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }


    public boolean isBonus() {
        return isBonus;
    }

    public void setBonus(boolean isBonus) {
        this.isBonus = isBonus;
    }

    public boolean isTransporter() {
        return isTransporter;
    }

    public int getZoneId() {
        return zoneId;
    }

    public boolean getEastWall()
    {
        return this.eastWall;
    }

    public boolean getWestWall()
    {
        return this.westWall;
    }

    public boolean getSouthWall()
    {
        return this.southWall;
    }

    public boolean getNorthWall()
    {
        return this.northWall;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public void setEastWall(boolean east)
    {
        this.eastWall = east;
    }

    public void setWestWall(boolean west)
    {
        this.westWall = west;
    }

    public void setSouthWall(boolean south)
    {
        this.southWall = south;
    }

    public void setNorthWall(boolean north)
    {
        this.northWall = north;
    }

    public void setVisited(boolean isVisited) 
    {
        this.isVisited = isVisited;
    }

    public boolean getIsVisited()
    {
        return this.isVisited;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Cell> getNeighbors() {
        return neighbors;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setNeighbors(ArrayList<Cell> neighbors) {
        this.neighbors = neighbors;
    }
}
