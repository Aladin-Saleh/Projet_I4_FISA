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
                if (!(this.map.getStartX() == -1) && !(this.map.getStartY() == -1))
                {
                    this.map.getMap()[this.map.getStartX()][this.map.getStartY()].setIsOccupied(false);
                }

                this.map.setStartX(x);
                this.map.setStartY(y);

                if (jsonHandler.readJSON(message).get("positionInfoWest") != null)
                {
                    boolean isWall = Boolean.parseBoolean(jsonHandler.readJSON(message).get("positionInfoWest").toString());
                    System.out.println("West is " + isWall);
                    this.map.setWest(isWall);
                }

                if (jsonHandler.readJSON(message).get("positionInfoEast") != null)
                {
                    boolean isWall = Boolean.parseBoolean(jsonHandler.readJSON(message).get("positionInfoEast").toString());
                    System.out.println("East is " + isWall);
                    this.map.setEast(isWall);
                }

                if (jsonHandler.readJSON(message).get("positionInfoNorth") != null)
                {
                    boolean isWall = Boolean.parseBoolean(jsonHandler.readJSON(message).get("positionInfoNorth").toString());
                    System.out.println("North is " + isWall);
                    this.map.setNorth(isWall);
                }

                if (jsonHandler.readJSON(message).get("positionInfoSouth") != null)
                {
                    boolean isWall = Boolean.parseBoolean(jsonHandler.readJSON(message).get("positionInfoSouth").toString());
                    System.out.println("South is " + isWall);
                    this.map.setSouth(isWall);
                }




            }
        }
    }



    
}
