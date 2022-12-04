import java.nio.file.SecureDirectoryStream;
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
                this.map[i][j] = new Cell(cpt);
                cpt++;
            }
        }
    }

    // Fonction récursive qui génère un labyrinthe avec l'algorithme DepthFirst
    private void generateViaDepthFirst()
    {
        Random random = new Random();
        int x = random.nextInt(this.map.length);
        int y = random.nextInt(this.map[0].length);
        recursiveDepthFirst(this.map[x][y], x, y);
    }

    // Fonction récursive qui détruit le mur entre la cellule current et une cellule voisine aléatoire non visitée
    private void recursiveDepthFirst(Cell current,int x,int y)
    {
        current.setVisited(true);
        Cell chosenCell;
        int[] xDirection = {x,x+1,x,x-1};
        int[] yDirection = {y+1,y,y-1,y};
        Random random    = new Random();
        int direction    = random.nextInt(4);

        // TODO : fonction qui retourne un tableau avec les directions encore possibles 
        while(numberOfPossibleDirections(x, y)>0)
        {
            if(doesExistCell(xDirection[direction], yDirection[direction]))
            {
                chosenCell = this.map[xDirection[direction]][yDirection[direction]];
                if(chosenCell.getIsVisited())
                {
                    destroyWallBetween(current,chosenCell,direction);
                    recursiveDepthFirst(chosenCell, xDirection[direction], yDirection[direction]);
                }
            }
        }
    }

    // Fonction qui detruit le mur entre la cellule current et chosenCell dans la direction passée en paramètre
    private void destroyWallBetween(Cell current, Cell chosenCell, int direction) 
    {
        switch(direction)
        {
            case 0: // South
                current.setSouthWall(false);
                chosenCell.setNorthWall(false);
                break;
            case 1: // East
                chosenCell.setWestWall(false);
                current.setEastWall(false);
                break;
            case 2: // North
                chosenCell.setSouthWall(false);
                current.setNorthWall(false);
                break;
            case 3: // West
                current.setWestWall(false);
                chosenCell.setEastWall(false);
                break;
            default:
                break;
        }
    }

    // Fonction qui retourne le nombre de direction possible depuis la cellule [x,y]dans le tableau this.map
    private int numberOfPossibleDirections(int x, int y) 
    {
        int nbDirections = 0;
        for(int i = 0;i<4;i++){
            if(doesExistCell(x, y))
            {
                Cell current = this.map[x][y];
                if(current.getIsVisited())
                {
                    nbDirections++;
                }
            }
        }

        return nbDirections;
    }

    // Fonction qui retourne un booléen pour indiquer si la cellule en position [x,y] existe dans le tableau this.map
    private boolean doesExistCell(int x, int y) 
    {
        boolean doesExist = false;
        if(x >= 0 && y >= 0 && x < this.map.length && y < this.map[0].length)
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
        for(int i = 0; i< this.map.length;i++)
        {
            for(int j= 0; j<this.map[0].length;j++)
            {
                Cell current = this.map[i][j];
                if(current.getSouthWall()){
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
            System.out.print("\n");
        }
    }
}
