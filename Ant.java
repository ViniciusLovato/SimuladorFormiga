import java.util.ArrayList;

public class Ant
{
    // Ant position
    private int x;
    private int y;
    
    // Lifetime
    private int ttl;
    
    // Score
    private int score;
    
    // Array that is going to save the movement history
    private ArrayList<Integer> movement;
    
    // Constructor
    public Ant(int x, int y, int ttl)
    {
        setTTL(ttl);
        setPosition(x, y);
	resetScore();
        
        this.movement = new ArrayList<Integer>(ttl);
    }
    
    // Function that sets the ttl (lifetime)
    private void setTTL(int ttl)
    {
        this.ttl = ttl;
    }
    
    // Function to set the position
    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    // Function to get the ant score
    public int getScore()
    {
	return this.score;
    }

    // Function to reset the score  
    public void resetScore()
    {
	this.score = 0;
    }
    
    // Function that moves the ant
    public void move(Field f)
    {
	// Random number to choose which side the ant is gointo to move
        int direction;
	// Temporary position of the ant, used to validate the movement
	int tmpx, tmpy;

	boolean valid;
        
	// While the movement is not valid
        do
        {
	    // Random number
	    direction = (int) Math.round(Math.random()*3);
            
	    // Save position on temporary variables
	    tmpx = this.x;    
	    tmpy = this.y;    

	    // Moving
            if(direction == 0)
            {
                tmpx += 1;
            }
	    else if(direction == 1)
	    {
		tmpx -= 1;

	    }
	    else if(direction == 2)
	    {
		tmpy += 1;

	    }
	    else if(direction == 3)
	    {
		tmpy -= 1;
	    }

	    // Validating the movement
	    if(tmpx >= f.getWidth()|| tmpy >= f.getHeight() || tmpx < 0 || tmpy < 0)
		valid = false; // not possible
	    else
		valid = true; // valid movement
	}
	while(!valid);

	// Saving the new ant position
	setPosition(tmpx, tmpy);

	// Saving in the movement array
	this.movement.add(direction);

	// Save the ant position on the field
	f.setAnt(tmpx, tmpy);

	// Reduces the lifetime of the ant
	this.ttl--;
    }      

    // Check if the ant could move one more time
    public boolean isAlive()
    {
	return(this.ttl > 0);
    }


    // Function that calculates the score
    public int updateScore(Field f)
    {
	// Check if the ant found the leaf
	if(f.checkLeafPosition(this.x, this.y))
	{
	    score++;
	}
	return score;
    }

    public ArrayList<Integer> getMovementArray()
    {
	return(this.movement);
    }

    // Function that prints the ants
    public void print()
    {
	String dir = "";
	System.out.println("Ant Score: " + getScore());
	System.out.println("Ant Movements:");
	for(Integer i : this.movement)
	{
	    if(i == 0)
		dir = "UP";
	    else if(i == 1)
		dir = "DOWN";
	    else if(i == 2)
		dir = "RIGHT";
	    else if(i == 3)
		dir = "LEFT";
	    System.out.print(dir + " ");
	}
	System.out.println();

    }
}
