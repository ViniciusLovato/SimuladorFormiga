public class Field
{
    // Map where the ants are going to walk
    private char[][] map;

     // Map size
    private int width;
    private int height;

    // Colony position
    public int xcolony;
    public int ycolony;
    
    // Leaf position
    public int xleaf;
    public int yleaf;

    public int xant;
    public int yant;
        
    // Simple Constructor with default values
    public Field()
    {
        setSize(30, 30);
        this.map = new char[ getWidth() ][ getHeight() ];
	reset();
    }

    // Simple Constructor with default values
    public Field(int width, int height)
    {
        setSize(width, height);
        this.map = new char[ getWidth() ][ getHeight() ];
	reset();
    }

    // Getters
    public int getWidth()
    {
        return this.width;
    }
    
    public int getHeight()
    {
        return this.height;
    }

    // Function to set the size of the map
    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    // Function to set the colony position
    public void setColony(int x, int y)
    {
        this.xcolony = x;
        this.ycolony = y;
    }

    // Function to set the ant position
    public void setAnt(int x, int y)
    {
        this.xant = x;
        this.yant = y;
    }
    
    // Function to set the leaf position
    public void setLeaf(int x, int y)
    {
        this.xleaf = x;
        this.yleaf = y;
    }

    // Checks if the leaf is in this position
    public boolean checkLeafPosition(int x, int y)
    {
	return (this.xleaf == x && this.yleaf == y);
    }
 
    // Function to reset the map
    public void reset()
    {
        for(int i = 0; i < this.height; i++)
        {
            for(int j = 0; j < this.height; j++)
            {
                this.map[i][j] = '.';
            }    
        }
    }
    
    public void print()
    {
	reset();

	this.map[xcolony][ycolony] = 'C';
	this.map[xleaf][yleaf] = 'L';
	this.map[xant][yant] = 'A';

        for(int i = 0; i < this.height; i++)
        {
            for(int j = 0; j < this.height; j++)
            {
                System.out.print(this.map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();        
	System.out.println("Colony position:\t(" + xcolony + ", " + ycolony + ")");
	System.out.println("Ant position:\t\t(" + xant + ", " + yant + ")");
	System.out.println("Leaf position:\t\t(" + xleaf + ", " + yleaf + ")");
    }
}
