import java.awt.Graphics;
import java.awt.*;


public class GameZone {
    
    // Constantes
    public static final int EMPTY       = 0;
    public static final int WALL        = 1;
    public static final int TRANSPORTER = 2;
    public static final int BONUS_SALAD = 3;
    public static final int EXIT        = 4;
    // Tout les nombres au dessus de 4 sont des joueurs


    // Matrice de jeu 10x10
    private int[][] gameZone;


    public GameZone()
    {
        this.gameZone = new int[10][10];
        this.generateGameZone();
        this.displayGameZone();
    }


    public GameZone(int length)
    {
        this.gameZone = new int[length][length];
        this.generateGameZone();
        this.displayGameZone();
    }


    // Generer une zone de jeu aléatoire labirynthique
    // Il existe forcement un chemin entre la case de départ et la case d'arrivée (EXIT)
    public void generateGameZone()
    {
        // Initialisation de la matrice de jeu
        for (int i = 0; i < this.gameZone.length; i++)
        {
            for (int j = 0; j < this.gameZone[i].length; j++)
            {
                this.gameZone[i][j] = GameZone.EMPTY;
            }
        }

        // Génération des murs
        for (int i = 0; i < this.gameZone.length; i++)
        {
            for (int j = 0; j < this.gameZone[i].length; j++)
            {
                if (i == 0 || i == this.gameZone.length - 1 || j == 0 || j == this.gameZone[i].length - 1)
                {
                    this.gameZone[i][j] = GameZone.WALL;
                }
                else
                {
                    if (Math.random() < 0.2)
                    {
                        this.gameZone[i][j] = GameZone.WALL;
                    }
                }
            }
        }

        // Génération des bonus
        for (int i = 0; i < this.gameZone.length; i++)
        {
            for (int j = 0; j < this.gameZone[i].length; j++)
            {
                if (this.gameZone[i][j] == GameZone.EMPTY)
                {
                    if (Math.random() < 0.1)
                    {
                        this.gameZone[i][j] = GameZone.BONUS_SALAD;
                    }
                }
            }
        }

        // Génération des portails
        for (int i = 0; i < this.gameZone.length; i++)
        {
            for (int j = 0; j < this.gameZone[i].length; j++)
            {
                if (this.gameZone[i][j] == GameZone.EMPTY)
                {
                    if (Math.random() < 0.1)
                    {
                        this.gameZone[i][j] = GameZone.TRANSPORTER;
                    }
                }
            }
        }

        // Génération de la sortie
        for (int i = 0; i < this.gameZone.length; i++)
        {
            for (int j = 0; j < this.gameZone[i].length; j++)
            {
                if (this.gameZone[i][j] == GameZone.EMPTY)
                {
                    this.gameZone[i][j] = GameZone.EXIT;
                    return;
                }
            }
        }
    }

    // Retourne les coordonnées d'une case vide aléatoire
    public int[] getRandomEmptyCase()
    {
        int[] coords = new int[2];
        int x = 0;
        int y = 0;

        do
        {
            x = (int) (Math.random() * this.gameZone.length);
            y = (int) (Math.random() * this.gameZone.length);
        }
        while (this.gameZone[x][y] != GameZone.EMPTY);

        coords[0] = x;
        coords[1] = y;

        return coords;
    }




    // Retourne la matrice de jeu (debug)
    public void displayGameZone()
    {
        for (int i = 0; i < this.gameZone.length; i++)
        {
            for (int j = 0; j < this.gameZone[i].length; j++)
            {
                System.out.print(this.gameZone[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void paintGameZone(Graphics gPaint)
    {
        gPaint.setColor(Color.ORANGE);

        for (int i = 0; i < this.gameZone.length; i++) 
        {
            for (int j = 0; j < this.gameZone.length; j++) 
            {
                
                System.out.print(this.gameZone[i][j]);

                if (this.gameZone[i][j] == GameZone.WALL)
                {
                    gPaint.setColor(Color.ORANGE);
                    gPaint.fillRect(i*35, j*35, 35, 35);
                }

                if (this.gameZone[i][j] == GameZone.EMPTY)
                {
                    gPaint.setColor(Color.GREEN);
                    gPaint.fillRect(i*35, j*35, 35, 35);
                }

                if (this.gameZone[i][j] == GameZone.EXIT)
                {
                    gPaint.setColor(Color.RED);
                    gPaint.fillRect(i*35, j*35, 35, 35);
                }

                if (this.gameZone[i][j] == GameZone.BONUS_SALAD)
                {
                    gPaint.setColor(Color.YELLOW);
                    gPaint.fillRect(i*35, j*35, 35, 35);
                }

                if (this.gameZone[i][j] == GameZone.TRANSPORTER)
                {
                    gPaint.setColor(Color.BLUE);
                    gPaint.fillRect(i*35, j*35, 35, 35);
                }
            }
            System.out.println();
        }
    }



    // Retourne la matrice de jeu
    public int[][] getGameZone()
    {
        return this.gameZone;
    }

    public void setGameZone(int[][] gameZone)
    {
        this.gameZone = gameZone;
    }


}
