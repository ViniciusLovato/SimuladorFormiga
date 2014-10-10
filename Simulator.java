import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;

public class Simulator
{
    // Field size
	private int fieldWidth;
	private int fieldHeight;

    private int generation;
    // Ants
	ArrayList<Ant> swarm;
    // Colony
	Colony colony;
    // Leaf
	Leaf leaf;

    // Attributes for the Tournament Selection
	ArrayList<Ant> tournament;

    // Simple Constructor with default values
	public Simulator()
	{	
		setFieldSize(15, 15);
		colony = new Colony(1000);
		leaf = new Leaf(getRandomPosition(this.fieldWidth), getRandomPosition(this.fieldHeight));
		swarm = new ArrayList<Ant>();

		for(int i = 0; i < colony.getSize(); i++){
	    // Add it to the list
			this.swarm.add(new Ant(colony.getPosX(), colony.getPosY(), 200));
		}

        generation = 0;
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

        generation = 0;
	}

	public void setFieldSize(int width, int height){
		this.fieldWidth = width;
		this.fieldHeight = height;
	}

	public void run()
	{
        for(Ant ant : swarm){
            fitness(ant);
        }
		summary();
	}

    // Used to generate a random position to set the leaf or the colony
	private int getRandomPosition(int max){
		return (int) Math.round(Math.random()*(max - 1));
	}

    // print the summary that contains the ants' score
	private void summary(){
		System.out.println("Summary: Score");
		for(Ant ant : swarm){
			System.out.print(ant.getScore() + " ");	
		}
		System.out.println();	
	}

    // moving ants
    private void fitness(Ant ant){
        
        ant.setPosX(colony.getPosX());
        ant.setPosY(colony.getPosY());
        

        int tmpx, tmpy;
        boolean valid;

        for (Integer i : ant.getMovementArray()){
            // check whether or not the ant is at the leaf spot
            checkScore(ant);

            valid = false;

            tmpx = ant.getPosX();
            tmpy = ant.getPosY();

            // try to move the ant according to its movement array
            if(i == 0){
                tmpx += 1;
            }
            else if(i == 1){
                tmpx -= 1;
            }
            else if(i == 2){
                tmpy += 1;
            }
            else if(i == 3){
                tmpy -= 1;
            }

            // Validating the movement
            if(tmpx >= fieldWidth || tmpy >= fieldHeight || tmpx < 0 || tmpy < 0)
                valid = false; // not possible
            else
                valid = true; // valid movement

            // performing the movement
            if(valid){
                ant.setPosY(tmpy);
                ant.setPosX(tmpx);
            }

        }
    }

    // check whether or not the ant has eaten a leaf
	private void checkScore(Ant ant){
		if( (ant.getPosX() == this.leaf.getPosX()) && (ant.getPosY() == this.leaf.getPosY()) ){
			ant.increaseScore(1);
		}
	}

    // Printing results to a file
	private void printTournamentToFile(int sample)
	{
		Writer writer = null;
		try
		{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tournament" + sample + ".m"), "utf-8"));

			writer.write("x" + sample + " = [");
			for(Ant ant : tournament)
			{
				writer.write(ant.getScore() + " ");
			} 
			writer.write("]");

			writer.close();
		}
		catch(Exception ex)
		{
	    // Sorry
		}
	}

    private void printCurrentGeneration()
    {
        Writer writer = null;
        try
        {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("generation" + generation + ".m"), "utf-8"));

            writer.write("g" + generation + " = [");
            for(Ant ant : swarm)
            {
                writer.write(ant.getScore() + " ");
            } 
            writer.write("]");

            writer.close();
        }
        catch(Exception ex)
        {
        // Sorry
        }
    }

    // Function that creates a tournament to select the best ants
	public void tournament(int sample)
	{
	// Creating a tournament
		this.tournament = new ArrayList<Ant>();

	// Creating the local sample array
		ArrayList<Ant> selection = new ArrayList<Ant>(sample);

	// Sorting the array
		Collections.sort(this.swarm);

	// Sampling the Ants
		for(int i = 0; i < this.swarm.size(); i++)
		{
	    // Sampling 
			for(int j = 0; j < sample; j++)
			{
		// Adds a random ant to the sampling array
				int random = getRandomPosition(this.swarm.size());
				selection.add(this.swarm.get(random));
			}

	    // Sorting the ants
			Collections.sort(selection);

	    // Saving the best ant
			this.tournament.add(selection.get(selection.size() - 1));
			selection.clear();
		}

		Collections.sort(this.tournament);

		System.out.println("Tournament Summary:\nSamples: " + sample + "\nResult: ");
		for(Ant ant : tournament)
		{
			System.out.print("[" + ant.getScore() + "]");
		}
		System.out.println("\n------------------------------------------------");
		printTournamentToFile(sample);


	}

    public void consolidateTournament(){
        swarm.clear();
        swarm = new ArrayList<Ant>(tournament);
    }

    public void newGeneration(int number){
        generation++;

        int mom;
        int dad;

        int deltaAnts = 0;
        for(int i = 0; i < number; i++){
            if(getRandomPosition(100) < 70){
                mom = getRandomPosition(swarm.size());
                dad = getRandomPosition(swarm.size());

                swarm.add(new Ant(colony.getPosX(), colony.getPosY(), swarm.get(mom), swarm.get(dad)));

                deltaAnts++;
            }
        }

        Collections.sort(this.swarm);
        // this.swarm.removeRange(0, deltaAnts - 1);

        swarm.subList(0, deltaAnts - 1).clear();
    }

	public static void main(String Args[])
	{
		Simulator simulator = new Simulator();

		simulator.run();

        String command;
        Scanner terminalInput = new Scanner(System.in);

        do
        {
            // Prompt
            System.out.println("## Terminal ##");
            System.out.println("Available Commands: quit, t");
            System.out.print("Please enter your command: ");

           // Reading command
            command = terminalInput.nextLine();

           // Parsing the command
            if(command.equals("t"))
            {
                  // Reading the parameters of the command
                System.out.print("Tournament Command: Please enter the size: ");
                command = terminalInput.nextLine();
                int size = Integer.parseInt(command);
                simulator.tournament(size);
            }
            else if(command.equals("run")){
                System.out.print("The following tournaments will be running: 16, 64, 128, 512");
                simulator.tournament(16);
                simulator.tournament(64);
                simulator.tournament(128);
                simulator.tournament(512);
            }
            else if(command.equals("pg")){
                simulator.printCurrentGeneration();
            }
            else if(command.equals("rg")){
                System.out.print("How many generations?: ");
                command = terminalInput.nextLine();
                int generations = Integer.parseInt(command);

                for(int i = 0; i < generations; i ++){
                    simulator.tournament(16);
                    simulator.consolidateTournament();
                    simulator.newGeneration(1000);
                    simulator.printCurrentGeneration();
                }
            }
        }
        while(!command.equals("quit"));
        System.out.println("Thank you");
    }
}



