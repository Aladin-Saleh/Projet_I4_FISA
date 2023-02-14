import java.util.HashMap;
import java.util.Map;

public class RequestHandler 
{
    


    private ClientHandler   clientHandler;
    private JSONHandler     jsonHandler;
    private Maze            maze;

    public RequestHandler(ClientHandler clientHandler, Maze maze) 
    {
        this.clientHandler = clientHandler;
        this.maze = maze;
        this.jsonHandler = new JSONHandler();
    }

    public boolean checkDeplacement(int x, int y, String direction)
    {
        switch (direction) {
            case "N":
                return this.maze.getMap()[x][y].getNorthWall();
            case "S":
                return this.maze.getMap()[x][y].getSouthWall();
            case "E":
                return this.maze.getMap()[x][y].getEastWall();
            case "W":
                return this.maze.getMap()[x][y].getWestWall();
            default:
                return false;
        }
    }

    public void updateWall(int currentX, int currentY, int nextX, int nextY)
    {
        if (currentX == nextX)
        {
            if (currentY > nextY)
            {
                System.out.println("Wall West open");
                this.maze.getMap()[currentX][currentY].setWestWall(false);
                this.maze.getMap()[nextX][nextY].setEastWall(false);

            }
            else
            {
                System.out.println("Wall East open");
                this.maze.getMap()[currentX][currentY].setEastWall(false);
                this.maze.getMap()[nextX][nextY].setWestWall(false);
            }
        }
        else
        {
            if (currentX > nextX)
            {
                System.out.println("Wall North open");
                this.maze.getMap()[currentX][currentY].setNorthWall(false);
                this.maze.getMap()[nextX][nextY].setSouthWall(false);
            }
            else
            {
                System.out.println("Wall South open");
                this.maze.getMap()[currentX][currentY].setSouthWall(false);
                this.maze.getMap()[nextX][nextY].setNorthWall(false);
            }
        }
    }


    public void handleRequest(String message) 
    {
        if (jsonHandler.readJSON(message).get("close") != null)
        {
            int x = Integer.parseInt(jsonHandler.readJSON(message).get("close").toString().split(",")[0], 10);
            int y = Integer.parseInt(jsonHandler.readJSON(message).get("close").toString().split(",")[1], 10);

            System.out.println(jsonHandler.readJSON(message).get("close"));
            this.maze.getMap()[x][y].setOccupied(false);
        }

        if (jsonHandler.readJSON(message).get("position") != null)
        {
            Map<String, String> response = new HashMap<String, String>();


            int currentX = Integer.parseInt(jsonHandler.readJSON(message).get("currentPosition").toString().split(",")[0], 10);
            int currentY = Integer.parseInt(jsonHandler.readJSON(message).get("currentPosition").toString().split(",")[1], 10);

            String direction = jsonHandler.readJSON(message).get("direction").toString();

            if (!this.checkDeplacement(currentX, currentY, direction))
            {
                int x = Integer.parseInt(jsonHandler.readJSON(message).get("position").toString().split(",")[0], 10);
                int y = Integer.parseInt(jsonHandler.readJSON(message).get("position").toString().split(",")[1], 10);

                this.maze.getMap()[currentX][currentY].setOccupied(false);
                this.maze.getMap()[x][y].setOccupied(true);

                response.put("exit", this.maze.getMap()[x][y].isExit()+"");
                response.put("teleport", this.maze.getMap()[x][y].isTransporter()+"");
                response.put("bonus", this.maze.getMap()[x][y].isBonus()+"");

                if (this.maze.getMap()[x][y].isBonus())
                {
                    this.maze.getMap()[x][y].setBonus(false);
                }
    

                if (this.maze.getMap()[x][y].isTransporter())
                {
                    int[] position = this.maze.getFreeCell();
                    // Send the transporter position to the client
                    response.put("teleportPosition", x + "," + y);
                    response.put("position", position[0] + "," + position[1]);
                    response.put("positionInfoEast", this.maze.getMap()[position[0]][position[1]].getEastWall()+"");
                    response.put("positionInfoWest", this.maze.getMap()[position[0]][position[1]].getWestWall()+"");
                    response.put("positionInfoNorth", this.maze.getMap()[position[0]][position[1]].getNorthWall()+"");
                    response.put("positionInfoSouth", this.maze.getMap()[position[0]][position[1]].getSouthWall()+"");
                }
                else
                {
                    response.put("position", x + "," + y);
                    response.put("positionInfoEast", this.maze.getMap()[x][y].getEastWall()+"");
                    response.put("positionInfoWest", this.maze.getMap()[x][y].getWestWall()+"");
                    response.put("positionInfoNorth", this.maze.getMap()[x][y].getNorthWall()+"");
                    response.put("positionInfoSouth", this.maze.getMap()[x][y].getSouthWall()+"");
                }

    
            }
            else
            {
                if (jsonHandler.readJSON(message).get("bonus") != null)
                {
                    int countBonus = Integer.parseInt(jsonHandler.readJSON(message).get("bonus").toString());
                    System.out.println("countBonus : " + countBonus);
                    if (countBonus > 0)
                    {
                        int x = Integer.parseInt(jsonHandler.readJSON(message).get("position").toString().split(",")[0], 10);
                        int y = Integer.parseInt(jsonHandler.readJSON(message).get("position").toString().split(",")[1], 10);
                        
                        this.maze.getMap()[currentX][currentY].setOccupied(false);
                        this.maze.getMap()[x][y].setOccupied(true);

                        this.updateWall(currentX, currentY, x, y);

                        response.put("bonusUsed", "true");
                        response.put("position", x + "," + y);
                        response.put("positionInfoEast", this.maze.getMap()[x][y].getEastWall()+"");
                        response.put("positionInfoWest", this.maze.getMap()[x][y].getWestWall()+"");
                        response.put("positionInfoNorth", this.maze.getMap()[x][y].getNorthWall()+"");
                        response.put("positionInfoSouth", this.maze.getMap()[x][y].getSouthWall()+"");
                        
                    }
        
                }
                else
                {
                    response.put("position", currentX + "," + currentY);
                    response.put("positionInfoEast", this.maze.getMap()[currentX][currentY].getEastWall()+"");
                    response.put("positionInfoWest", this.maze.getMap()[currentX][currentY].getWestWall()+"");
                    response.put("positionInfoNorth", this.maze.getMap()[currentX][currentY].getNorthWall()+"");
                    response.put("positionInfoSouth", this.maze.getMap()[currentX][currentY].getSouthWall()+"");
                }

            }


            this.clientHandler.sendMessage(this.jsonHandler.writeJSON(response));

        }
        
        if (jsonHandler.readJSON(message).get("exit") != null)
        {

            Map<String, String> response = new HashMap<String, String>();

            response.put("end", "true");
            this.clientHandler.broadcast(this.jsonHandler.writeJSON(response));
        }

        

    }


}
