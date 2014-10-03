import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Thread;

public class Simulator
{
    // Map where the ants will stay
    Field f;

    // Ants
    ArrayList<Ant> ants;
    int colonySize;
    // Lifetime of the ants
    int ttl;

    int xcolony, ycolony;
    
    
    // Simple Constructor with default values
    public Simulator()
    {
	this.ttl = 200;
	this.xcolony = 0;
	this.ycolony = 0;
	f = new Field();
        f.setColony(xcolony, ycolony);
        f.setLeaf((int) Math.round(Math.random()*(f.getWidth() - 1)), (int) Math.round(Math.random()*(f.getHeight() - 1)));

	// Colony size (number of ants)
	this.colonySize = 10;

	ants = new ArrayList<Ant>();
    }

    // Constructor 
    public Simulator(int width, int height, int xcolony, int ycolony, int colonySize, int ttl)
    {
	this.ttl = ttl;
	this.xcolony = 0;
	this.ycolony = 0;
	f = new Field(width, height);
        f.setColony(xcolony, ycolony);
        f.setLeaf( (int) Math.random()*f.getWidth(), (int) Math.random()*f.getHeight());

	// Colony size (number of ants)
	this.colonySize = colonySize;

	ants = new ArrayList<Ant>();
    }

    public void print()
    {
	System.out.println("Field: ");
	f.print();
    }

    public void run()
    {
	int size = this.colonySize;
	// Temporary ant
	Ant a;
	// While there is more ants to be added
	do
	{
	    // Create a new ant
	    a = new Ant(this.xcolony, this.ycolony, this.ttl);
	    // Add it to the list
	    this.ants.add(a);
	    // While it still alive
	    while(a.isAlive())
	    {
		// move
		a.move(f);
		a.updateScore(f);
	    }
	    // print score
	    a.print();

	    // Decreasing the number of ants
	    size--;
	    System.out.println("size: " + size);
	}while(size > 0);
    }


    // Function that replays the movement of a specific ant
    public void replay(int index)
    {
	if(index < this.ttl-1)
	{
	    ArrayList<Integer> movement = this.ants.get(index).getMovementArray();

	    // Getting the colony position
	    int xreplay = this.xcolony;
	    int yreplay = this.ycolony;

	    for(Integer i : movement)
	    {
		// Parsing the movement
		if(i == 0)
		    xreplay += 1;
		else if(i == 1)
		    xreplay -= 1;
		else if(i == 2)
		    yreplay += 1;
		else if(i == 3)
		    yreplay -= 1;

		// Setting the ant position
		f.setAnt(xreplay, yreplay);
		// Printing the field
		f.print();

		// Waiting  400ms
		try{
		    Thread.sleep(400);
		}catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	    }
	}
	// Invalid index 
	else
	{
	    System.out.println("Invalid index!");
	}
    }

    // Function test the environment with one ant
    // This function only should be used for debug
    public void step()
    {
	// Create a new ant
	Ant a = new Ant(this.xcolony, this.ycolony, this.ttl);

	// While it still alive
	while(a.isAlive())
	{
	    // move
	    a.move(f);
	    a.updateScore(f);
	    f.print();

	    try{
		Thread.sleep(250);
	    }catch(InterruptedException ex)
	    {
		Thread.currentThread().interrupt();
	    }
	}
	// print score
	a.print();
    }

    public static void main(String Args[])
    {
	Simulator s1 = new Simulator();

	s1.run();

	// Terminal
	// Available commands: 
	// quit 
	// replay n - shows the movement of ant which index is n

	String command;
	Scanner terminalInput = new Scanner(System.in);

	do
	{
	    // Prompt
	    System.out.println("## Terminal ##");
	    System.out.println("Available Commands: quit, replay");
	    System.out.print("Please enter your command: ");

	    // Reading command
	    command = terminalInput.nextLine();

	    // Parsing the command
	    if(command.equals("replay"))
	    {
		// Reading the parameters of the command
		System.out.print("REPLAY Command: Please enter the index: ");
		command = terminalInput.nextLine();
		int index = Integer.parseInt(command);
		s1.replay(index);
	    }
	}
	while(!command.equals("quit"));
	System.out.println("Thank you");
    }
}
