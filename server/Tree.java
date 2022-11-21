package server;

public class Tree 
{
    private Tree parent;
    private int rank;
    private int size;

    public void MakeSet(Tree x)
    {
        if(this.Find(x) != this)
        {
            x.parent    = x;
            x.size      = 1;
            x.rank      = 0;
        }
    }

    public Tree Find(Tree x)
    {
        while(x.getParent() != x)
        {
            x.setParent(x.getParent().getParent());
            x = x.getParent();
        }
        return x;
    }

    public void Union(Tree x,Tree y)
    {
        x = this.Find(x);
        y = this.Find(y);

        if(x != y)
        {
           if(x.getRank()< y.getRank())
           {

           }
        }
    }

    // If necessary, rename variables to ensure that
    // x has rank at least as large as that of y
//     if x.rank < y.rank then
//         (x, y) := (y, x)
//     end if

//     // Make x the new root
//     y.parent := x
//     // If necessary, increment the rank of x
//     if x.rank = y.rank then
//         x.rank := x.rank + 1
//     end if
// end function

    public Tree getParent()
    {
        return parent;
    }

    public int getRank() 
    {
        return rank;
    }

    public int getSize() 
    {
        return size;
    }

    public void setParent(Tree parent) 
    {
        this.parent = parent;
    }
    
    public void setRank(int rank) 
    {
        this.rank = rank;
    }

    public void setSize(int size) 
    {
        this.size = size;
    }
}
