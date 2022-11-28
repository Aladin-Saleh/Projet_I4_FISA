import java.io.ObjectInputStream.GetField;
import java.util.Set;

public class Cell {
    private boolean eastWall;
    private boolean westWall;
    private boolean southWall;
    private boolean northWall;
    private int zoneId;
    private boolean isVisited;

    public Cell(int zoneId)
    {
        this.eastWall   = true;
        this.westWall   = true;
        this.southWall  = true;
        this.northWall  = true;
        this.zoneId     = zoneId;
        this.isVisited  = false;
    }

    public boolean areSameZone(Cell c)
    {
        return this.zoneId == c.getZoneId();
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
}
