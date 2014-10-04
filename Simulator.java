import java.util.ArrayList;
import java.util.Scanner;

public class Simulator
{
	// Field size
    private int fieldWidth;
    private int fieldHeight;
    
	// Ants
    ArrayList<Ant> swarm;
	// Colony
	Colony colony;
	// Leaf
	Leaf leaf;
	
    // Simple Constructor with default values
    public Simulator()
    {	
		setFieldSize(30, 30);
		colony = new Colony();
		leaf = new Leaf(getRandomPosition(this.fieldWidth), getRandomPosition(this.fieldHeight));
		swarm = new ArrayList<Ant>();

		for(int i = 0; i < colony.getSize(); i++){
			// Add it to the list
			this.swarm.add(new Ant(colony.getPosX(), colony.getPosY(), 200));
		}
    }

    // Constructor 
    public Simulator(int width, int height, int xcolony, int ycolony, int colonySize, int antTtl)
    {
		setFieldSize(width, height);
        colony = new Colony(xcolony, ycolony, colonySize);
        leaf = new Leaf(getRandomPosition(this.fieldWidth), getRandomPosition(this.fieldHeight));
		swarm = new ArrayList<Ant>();
		
		for(int i = 0; i < colony.getSize(); i++){
			// Add it to the list
			this.swarm.add(new Ant(colony.getPosX(), colony.getPosY(), 200));
		}
    }

    public void setFieldSize(int width, int height){
    	this.fieldWidth = width;
    	this.fieldHeight = height;
    }

    public void run()
    {
		for(Ant ant : this.swarm){
			while(ant.isAlive()){
				// move
				ant.move(fieldWidth, fieldHeight);
				checkScore(ant);				
			}				
		}
		summary();
    }

	// Used to generate a random position to set the leaf or the colony
	private int getRandomPosition(int max){
		return (int) Math.round(Math.random()*(max) - 1);
	}
	
	// print the summary that contains the ants' score
	private void summary(){
		System.out.println("Summary: Score");
		for(Ant ant : swarm){
			System.out.print(ant.getScore() + " ");	
		}
		System.out.println();	
	}

	// check whether or not the ant has eaten a leaf
	private void checkScore(Ant ant){
		if( (ant.getPosX() == this.leaf.getPosX()) && (ant.getPosY() == this.leaf.getPosY()) ){
			ant.increaseScore(1);
		}
	}

    public static void main(String Args[])
    {
		Simulator simulator = new Simulator();

		simulator.run();
		// Terminal
		// Available commands: 
		// quit 
		// replay n - shows the movement of ant which index is n
		String command;
		Scanner terminalInput = new Scanner(System.in);

		
		// do
		// {
		// 	// Prompt
		// 	System.out.println("## Terminal ##");
		// 	System.out.println("Available Commands: quit, replay");
		// 	System.out.print("You shall enter your command: ");

		// 	// Reading command
		// 	command = terminalInput.nextLine();

		// 	// Parsing the command
		// 	if(command.equals("replay"))
		// 	{
		// 		// Reading the parameters of the command
		// 		System.out.print("REPLAY Command: Please enter the index: ");
		// 		command = terminalInput.nextLine();
		// 		int index = Integer.parseInt(command);
		// 		simulator.replay(index);
		// 	}
		// }
		// while(!command.equals("quit"));
		System.out.println("Thank you");
	}


}



