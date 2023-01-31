import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.*;

public class Maze
{
    private Cell[][] map;
    private Random randomizer;
    private int rows;
    private int cols;

    // Constructeur de Maze de taille rows x cols
    public Maze(int rows,int cols)
    {
        this.map = new Cell[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.randomizer = new Random();

        this.initialiseMap();
        this.generateViaDepthFirst();
    }

    // Fonction qui initialise le tableau this.map avec des Cell ayant un identifiant unique
    private void initialiseMap() 
    {
        // Initialisation des cellules, on leur donne un identifiant unique (pas de voisins)
        int cpt = 0;
        for(int i = 0; i < this.map.length; i++)
        {
            for(int j = 0; j < this.map[i].length; j++)
            {
                this.map[i][j] = new Cell(cpt,i,j);
                // System.out.println("Initialisation cellule isVisited : "+this.map[i][j].getIsVisited());
                cpt++;
            }
        }

        for(int i = 0; i < this.map.length; i++)
        {
            for(int j = 0; j < this.map[i].length; j++)
            {
                /**
                 * |         | i, j-1  |        |    
                 * | i-1, j  | i, j    | i+1, j |
                 * |         | i, j+1  |        |
                 */
                int[] xDirection = {i, i+1, i, i-1};
                int[] yDirection = {j+1, j, j-1, j};

                // Les voisins de la cellule courante stocké dans une liste
                ArrayList<Cell> neighbors = new ArrayList<Cell>();

                for( int k = 0; k < xDirection.length; k++)
                {   
                    if(doesExistCell(xDirection[k], yDirection[k]))
                    {
                        neighbors.add(this.map[xDirection[k]][yDirection[k]]); // Ajout du voisin dans la liste
                    }
                }
                // On donne la liste des voisins à la cellule courante une fois qu'on a parcouru tous les voisins existants
                this.map[i][j].setNeighbors(neighbors); 
            }
        }
    }

    // Fonction récursive qui génère un Mazee avec l'algorithme DepthFirst
    private void generateViaDepthFirst()
    {
        Random random = new Random();
        int x         = random.nextInt(this.map.length);
        int y         = random.nextInt(this.map[0].length);
        recursiveDepthFirst(this.map[x][y]);
        this.createExit(this.map.length, this.map[0].length);
    }

    // Fonction récursive qui détruit le mur entre la cellule current et une cellule voisine aléatoire non visitée
    private void recursiveDepthFirst(Cell current)
    {
        current.setVisited(true);
        // TODO : fonction qui retourne un tableau avec les directions encore possibles 
        int nbPossibleDirections = numberOfPossibleDirections(current);
        Random random            = new Random();
        int direction            = random.nextInt(current.getNeighbors().size());
        
        while (nbPossibleDirections > 0)
        {
            Cell chosenCell      = current.getNeighbors().get(direction);
            if (!chosenCell.getIsVisited())
            {
                destroyWallBetween(current,chosenCell);
                recursiveDepthFirst(chosenCell);
            }
            nbPossibleDirections = numberOfPossibleDirections(current);
            direction            = random.nextInt(current.getNeighbors().size());
        }
    }

    public void createExit(int xLength, int yLength)
    {
        Random random = new Random();
        int x         = random.nextInt(xLength);
        int y         = random.nextInt(yLength);
        
        if (this.doesExistCell(x, y))
        {
            this.map[x][y].setExit(true);
        }
        else
        {
            this.createExit(xLength, yLength);
        }
    }

    // Fonction qui detruit le mur entre la cellule current et chosenCell dans la direction passée en paramètre
    private void destroyWallBetween(Cell current, Cell chosenCell) 
    {
        int yDest = chosenCell.getY() - current.getY();
        int xDest = chosenCell.getX() - current.getX();

        if(yDest == 1 && xDest == 0)// Ouest
        {
            chosenCell.setWestWall(false);
            current.setEastWall(false);
        }
        else if(yDest == -1 && xDest == 0)// Est
        {
            current.setWestWall(false);
            chosenCell.setEastWall(false);
        }
        else if(yDest == 0 && xDest == 1)// Sud
        {
            current.setSouthWall(false);
            chosenCell.setNorthWall(false);
        }
        else if(yDest == 0 && xDest == -1)// Nord
        {
            chosenCell.setSouthWall(false);
            current.setNorthWall(false);
        }
    }

    // Fonction qui retourne le nombre de direction possible depuis la cellule [x,y] dans le tableau this.map
    private int numberOfPossibleDirections(Cell current) 
    {
        int nbDirections = 0;
        for (Cell cell : current.getNeighbors()) {
            if(!cell.getIsVisited())
            {   
                nbDirections++;
            }
        }
        return nbDirections;
    }

    // Fonction qui retourne un booléen pour indiquer si la cellule en position [x,y] existe dans le tableau this.map
    private boolean doesExistCell(int x, int y) 
    {
        boolean doesExist = false;
        if ( x >= 0 && y >= 0 && x < this.map.length && y < this.map[0].length)
        {
            doesExist = true;
        }
        return doesExist;
    }

    public Cell[][] getMap()
    {
        return this.map;
    }

    public void setExit(int x, int y)
    {
        this.map[x][y].setExit(true);
    }


    public void paintMaze(Graphics gPaint)
    {
        gPaint.setColor(Color.BLACK);

        for(int i = 0; i < this.map.length; i++)
        {
            for(int j = 0; j < this.map[i].length; j++)
            {
                Cell current = this.map[i][j];
                // Display the x and y coordinates of the cell
                gPaint.setColor(Color.BLACK);
                // gPaint.drawString(current.getX() + "," + current.getY(), j*30, i*30);
                if (current.isOccupied())
                {
                    gPaint.setColor(Color.YELLOW);
                    // Fill circle
                    gPaint.fillOval(j*30, i*30, 30, 30);
                    gPaint.setColor(Color.BLACK);
                }
                if (current.isBonus())
                {
                    gPaint.setColor(Color.GREEN);
                    gPaint.fillRect(j*30, i*30, 30, 30);
                    gPaint.setColor(Color.BLACK);
                }
                if (current.isTransporter())
                {
                    gPaint.setColor(Color.BLUE);
                    gPaint.fillRect(j*30, i*30, 30, 30);
                    gPaint.setColor(Color.BLACK);
                }
                if (current.isExit())
                {
                    gPaint.setColor(Color.RED);
                    gPaint.fillRect(j*30, i*30, 30, 30);
                    gPaint.setColor(Color.BLACK);
                }
                if(current.getNorthWall())
                {
                    gPaint.drawLine(j*30, i*30, (j+1)*30, i*30);
                }
                if(current.getSouthWall())
                {
                    gPaint.drawLine(j*30, (i+1)*30, (j+1)*30, (i+1)*30);
                }
                if(current.getWestWall())
                {
                    gPaint.drawLine(j*30, i*30, j*30, (i+1)*30);
                }
                if(current.getEastWall())
                {
                    gPaint.drawLine((j+1)*30, i*30, (j+1)*30, (i+1)*30);
                }
            }
        }

    }



    public int[] getFreeCell()
    {
        int[] freeCell = new int[2];
        boolean isFreeCell = false;



        while (!isFreeCell)
        {

            int x = this.randomizer.nextInt(this.map.length);
            int y = this.randomizer.nextInt(this.map[0].length);
            if (this.doesExistCell(x, y) && !this.map[x][y].isExit() && !this.map[x][y].isTransporter())
            {
                System.out.println("getFreeCell x : " + x + " y : " + y);
                freeCell[0] = x;
                freeCell[1] = y;
                this.map[x][y].setOccupied(true);
                isFreeCell = true;
            }
        }
        
        return freeCell;
    }




    // Fonction qui dessine le Mazee
    public void diplayMaze()
    {
        for(int i = 0;i < (this.map.length*2)+1; i++){
            System.out.print("_");
        }
        System.out.println();
        for(int i = 0; i< this.map.length;i++)
        {
            System.out.print("|");
            for(int j= 0; j<this.map[0].length;j++)
            {
                Cell current = this.map[i][j];
            
                if(current.getSouthWall())
                {
                    System.out.print("_");
                }
                else
                {
                    System.out.print(" ");
                }

                if(current.getEastWall())
                {
                    System.out.print("|");
                }
                else
                {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    public int getRows()
    {
        return this.rows;
    }

    public int getCols()
    {
        return this.cols;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public void setCols(int cols)
    {
        this.cols = cols;
    }
}
