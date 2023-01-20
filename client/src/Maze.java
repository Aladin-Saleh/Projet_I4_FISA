public class Maze {
    

    private Cell[][] map;
    private int rows;
    private int cols;

    private int startX;
    private int startY;

    private int[] east = new int[2];
    private int[] west = new int[2];
    private int[] north = new int[2];
    private int[] south = new int[2];

    public Maze()
    {
        this.rows = 0;
        this.cols = 0;
        this.startX = -1;
        this.startY = -1;
        this.map = null;
    }

    public Maze(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        this.startX = -1;
        this.startY = -1;
        this.map = new Cell[rows][cols];
    }

    public void updateMaze()
    {
        if (this.map != null)
        {
            for (int i = 0; i < this.map.length; i++)
            {
                for (int j = 0; j < this.map[0].length; j++)
                {
                    this.map[i][j] = new Cell(i,j);
                    if (i == this.startX && j == this.startY)
                    {
                        this.map[i][j].setIsKnown(true);
                    }

                    if (this.east[0] == i && this.east[1] == j)
                    {
                        this.map[i][j].setEastWall(true);
                    }

                    if (this.west[0] == i && this.west[1] == j)
                    {
                        this.map[i][j].setWestWall(true);
                    }

                    if (this.north[0] == i && this.north[1] == j)
                    {
                        this.map[i][j].setNorthWall(true);
                    }

                    if (this.south[0] == i && this.south[1] == j)
                    {
                        this.map[i][j].setSouthWall(true);
                    }


                    
                }
            }
        }
    }


    public void setEast(int[] east)
    {
        this.east = east;
    }

    public int[] getEast()
    {
        return this.east;
    }

    public void setWest(int[] west)
    {
        this.west = west;
    }

    public int[] getWest()
    {
        return this.west;
    }

    public void setNorth(int[] north)
    {
        this.north = north;
    }

    public int[] getNorth()
    {
        return this.north;
    }

    public void setSouth(int[] south)
    {
        this.south = south;
    }

    public int[] getSouth()
    {
        return this.south;
    }





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
