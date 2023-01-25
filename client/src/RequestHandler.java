public class RequestHandler 
{

    private JSONHandler jsonHandler;
    private Maze map;

    public RequestHandler(Maze map)
    {
        this.map = map;
        this.jsonHandler = new JSONHandler();
    }
    

    public void handleRequest(String message)
    {
        if (message != null)
        {
            System.out.println("Message received: " + message);
            if (jsonHandler.readJSON(message).get("maze") != null)
            {
                int xLength = Integer.parseInt(jsonHandler.readJSON(message).get("maze").toString().split(",")[0], 10);
                int yLength = Integer.parseInt(jsonHandler.readJSON(message).get("maze").toString().split(",")[1], 10);
                this.map.setMap(new Cell[xLength][yLength]);
                
            }

            if (jsonHandler.readJSON(message).get("position") != null)
            {
                int x = Integer.parseInt(jsonHandler.readJSON(message).get("position").toString().split(",")[0], 10);
                int y = Integer.parseInt(jsonHandler.readJSON(message).get("position").toString().split(",")[1], 10);
                this.map.setStartX(x);
                this.map.setStartY(y);

                if (jsonHandler.readJSON(message).get("positionInfoWest") != null)
                {
                    boolean isWall = Boolean.parseBoolean(jsonHandler.readJSON(message).get("positionInfoWest").toString());
                    if (isWall) this.map.setWest(new int[]{x,y});
                    System.out.println("West wall setted to " + isWall);
                }

                if (jsonHandler.readJSON(message).get("positionInfoEast") != null)
                {
                    boolean isWall = Boolean.parseBoolean(jsonHandler.readJSON(message).get("positionInfoEast").toString());
                    if (isWall) this.map.setEast(new int[]{x,y});
                    System.out.println("East wall setted to " + isWall);
                }

                if (jsonHandler.readJSON(message).get("positionInfoNorth") != null)
                {
                    boolean isWall = Boolean.parseBoolean(jsonHandler.readJSON(message).get("positionInfoNorth").toString());
                    if (isWall) this.map.setNorth(new int[]{x,y});
                    System.out.println("North wall setted to " + isWall);
                }

                if (jsonHandler.readJSON(message).get("positionInfoSouth") != null)
                {
                    boolean isWall = Boolean.parseBoolean(jsonHandler.readJSON(message).get("positionInfoSouth").toString());
                    if (isWall) this.map.setSouth(new int[]{x,y});
                    System.out.println("South wall setted to " + isWall);
                }




            }
        }
    }



    
}