import java.nio.file.SecureDirectoryStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Labyrinth
{
    private Cell[][] map;

    // Constructeur de labyrinthe de taille rows x cols
    public Labyrinth(int rows,int cols)
    {
        map = new Cell[rows][cols];

        this.initialiseMap();
        Random random = new Random();
        this.generateViaDepthFirst();
        // if(random.nextInt(2)==0)
        // {
        //     this.generateViaDepthFirst();
        // }
        // else
        // {
        //     this.generateViaKruskal();
        // }
    }

    // Fonction qui initialise le tableau this.map avec des Cell ayant un identifiant unique
    private void initialiseMap() 
    {
        int cpt = 0;
        for(int i=0; i< this.map.length;i++)
        {
            for(int j = 0;j<this.map[i].length;j++)
            {
                this.map[i][j] = new Cell(cpt,i,j);
                // System.out.println("Initialisation cellule isVisited : "+this.map[i][j].getIsVisited());
                cpt++;
            }
        }

        for(int i=0; i< this.map.length;i++)
        {
            for(int j = 0;j<this.map[i].length;j++)
            {
                int[] xDirection = {i,i+1,i,i-1};
                int[] yDirection = {j+1,j,j-1,j};
                ArrayList<Cell> neighbors = new ArrayList<Cell>();
                for( int k = 0; k < 4; k++)
                {   
                    if(doesExistCell(xDirection[k], yDirection[k]))
                    {
                        neighbors.add(this.map[xDirection[k]][yDirection[k]]);
                    }
                }
                this.map[i][j].setNeighbors(neighbors);
            }
        }
    }

    // Fonction récursive qui génère un labyrinthe avec l'algorithme DepthFirst
    private void generateViaDepthFirst()
    {
        Random random = new Random();
        int x         = random.nextInt(this.map.length);
        int y         = random.nextInt(this.map[0].length);
        recursiveDepthFirst(this.map[x][y]);
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

    // Fonction qui retourne le nombre de direction possible depuis la cellule [x,y]dans le tableau this.map
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

    // TODO : algorithme de génération de labryrinth de Kruskal
    // private void generateViaKruskal()
    // {

    // }

    // Fonction qui retourne le labryrinthe
    public Cell[][] getMap()
    {
        return this.map;
    }


    // Fonction qui dessine le labyrinthe
    public void Display()
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
}
