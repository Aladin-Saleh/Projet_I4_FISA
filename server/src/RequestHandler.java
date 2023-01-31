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


    public void handleRequest(String message) 
    {
        
        if (jsonHandler.readJSON(message).get("position") != null)
        {
            int x = Integer.parseInt(jsonHandler.readJSON(message).get("position").toString().split(",")[0], 10);
            int y = Integer.parseInt(jsonHandler.readJSON(message).get("position").toString().split(",")[1], 10);

            Map<String, String> response = new HashMap<String, String>();

            response.put("position", x + "," + y);
            response.put("positionInfoEast", this.maze.getMap()[x][y].getEastWall()+"");
            response.put("positionInfoWest", this.maze.getMap()[x][y].getWestWall()+"");
            response.put("positionInfoNorth", this.maze.getMap()[x][y].getNorthWall()+"");
            response.put("positionInfoSouth", this.maze.getMap()[x][y].getSouthWall()+"");

            this.clientHandler.sendMessage(this.jsonHandler.writeJSON(response));

        }

    }


}
