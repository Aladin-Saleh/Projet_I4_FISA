public class Maze {
    

    private Cell[][] map;
    private int rows;
    private int cols;

    private int startX;
    private int startY;

    private boolean east;
    private boolean west;
    private boolean north;
    private boolean south;
    private Display gui;

    public Maze()
    {
        this.rows = 0;
        this.cols = 0;
        this.startX = -1;
        this.startY = -1;
        this.map = null;
        this.gui = null;
    }

    public Maze(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        this.startX = -1;
        this.startY = -1;
        this.map = new Cell[rows][cols];
        this.gui = null;
    }

    public void setGUI(Display gui)
    {
        this.gui = gui;
    }

    public Display getGUI()
    {
        return this.gui;
    }

    public void updateMaze()
    {
        if (this.map != null && startY != -1 && startX != -1)
        {
            this.map[startX][startY].setIsOccupied(true);

            if (this.east) { this.map[startX][startY].setEastWall(east); }
            if (this.west) { this.map[startX][startY].setWestWall(west); }
            if (this.north) { this.map[startX][startY].setNorthWall(north); }
            if (this.south) { this.map[startX][startY].setSouthWall(south); }
        }
    }

    public boolean isWall(String direction)
    {
        if (direction.equals("east"))
        {
            return this.map[startX][startY + 1].getEastWall();
        }

        if (direction.equals("west"))
        {
            return this.map[startX][startY -1].getWestWall();
        }

        if (direction.equals("north"))
        {
            return this.map[startX - 1][startY].getNorthWall();
        }

        if (direction.equals("south"))
        {
            return this.map[startX + 1][startY].getSouthWall();
        }

        return false;
    }


    // Getters 
    public boolean getEast() { return this.east; }
    public boolean getWest() { return this.west; }
    public boolean getNorth() { return this.north; }
    public boolean getSouth() { return this.south; }

    // Setters
    public void setEast(boolean east) { this.east = east; }
    public void setWest(boolean west) { this.west = west; }
    public void setNorth(boolean north) { this.north = north; }
    public void setSouth(boolean south) { this.south = south; }
 
    public void setStartX(int startX)
    {
        this.startX = startX;
        System.out.println("New start X setted to " + this.startX );
    }

    public int getStartX()
    {
        return this.startX;
    }

    public void setStartY(int startY)
    {
        this.startY = startY;
        System.out.println("New start Y setted to " + this.startY );
    }

    public int getStartY()
    {
        return this.startY;
    }

    public void setMap(Cell[][] map)
    {
        this.map = map;
        System.out.println("New map setted" + this.map.length + "x" + this.map[0].length);
        for (int i = 0; i < this.map.length; i++)
        {
            for (int j = 0; j < this.map[0].length; j++)
            {
                this.map[i][j] = new Cell(i,j);
                if (i == this.startX && j == this.startY)
                {
                    this.map[i][j].setIsKnown(true);
                    this.map[i][j].setIsOccupied(true);
                }
            }
        }
    }

    public Cell[][] getMap()
    {
        return this.map;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public int getRows()
    {
        return this.rows;
    }

    public void setCols(int cols)
    {
        this.cols = cols;
    }

    public int getCols()
    {
        return this.cols;
    }
}
