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


    public void handleRequest(String message) 
    {
        
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
    

                if (this.maze.getMap()[x][y].isTransporter())
                {
                    int[] position = this.maze.getFreeCell();
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
                response.put("position", currentX + "," + currentY);
                response.put("positionInfoEast", this.maze.getMap()[currentX][currentY].getEastWall()+"");
                response.put("positionInfoWest", this.maze.getMap()[currentX][currentY].getWestWall()+"");
                response.put("positionInfoNorth", this.maze.getMap()[currentX][currentY].getNorthWall()+"");
                response.put("positionInfoSouth", this.maze.getMap()[currentX][currentY].getSouthWall()+"");
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
