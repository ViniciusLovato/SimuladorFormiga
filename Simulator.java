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

    private int counter;

    // Attributes for the Tournament Selection
    ArrayList<Ant> tournament;

    // Simple Constructor with default values
    public Simulator()
    {	
        counter = 0;
        setFieldSize(15, 15);
        colony = new Colony(1000);
        leaf = new Leaf(getRandomPosition(this.fieldWidth), getRandomPosition(this.fieldHeight));
        swarm = new ArrayList<Ant>();

        for(int i = 0; i < colony.getSize(); i++){
            // Add it to the list
            this.swarm.add(new Ant(colony.getPosX(), colony.getPosY(), 200, idGenerator()));
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
            this.swarm.add(new Ant(colony.getPosX(), colony.getPosY(), 200, idGenerator()));
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
        //summary();
    }

    public void increaseGeneration()
    {
    	this.generation++;
    }

    public void exportAnts()
    {
        ArrayList<Integer> movement;
        Writer writer = null;
        try
        {	
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("swarm.txt"), "utf-8"));

            for(Ant ant : swarm)
            {
                movement = ant.getMovementArray();
                for(Integer m : movement)
                {
                    writer.write(m + " ");
                }
                writer.write("\n");
            } 

            writer.close();
        }
        catch(Exception ex)
        {
            // Sorry
        }

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

    // Secondary fitness function to try a different aproach 
    // This function give smaller scores to the ants that find the fastest way to the leaf
    // The smallest score is the best
    private void fitness(Ant ant)
    {
        ant.setPosX(colony.getPosX());
        ant.setPosY(colony.getPosY());

        int tmpx, tmpy;
        boolean valid;

        // the distance of the journey
        ant.resetScore();

        for (Integer i : ant.getMovementArray()){
            // check whether or not the ant is at the leaf spot
            if(!foundLeaf(ant))
            {
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
	            ant.increaseScore(1);
            }
        }
    }

    // check whether or not the ant has eaten a leaf
    private void checkScore(Ant ant){
        if( (ant.getPosX() == this.leaf.getPosX()) && (ant.getPosY() == this.leaf.getPosY()) ){
            ant.increaseScore(1);
        }
    }

    // check whether or not the ant has eaten a leaf
    private boolean foundLeaf(Ant ant){
        return ( (ant.getPosX() == this.leaf.getPosX()) && (ant.getPosY() == this.leaf.getPosY()) );
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


    // Printing results to a file
    private void printCleanTournamentToFile(ArrayList<Ant> cleanTournament)
    {
        Writer writer = null;
        try
        {	

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("cleanTournament" + generation + ".m"), "utf-8"));

            writer.write("c" + generation + " = [");

            for(Ant ant : cleanTournament)
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
            this.tournament.add(selection.get(0));
            selection.clear();
        }

        Collections.sort(this.tournament);

        System.out.println("Tournament Summary:\nSamples: " + sample + "\nResult: ");
        for(Ant ant : tournament)
        {
            System.out.print("[" + ant.getScore() + "]");
        }
        System.out.println("\n------------------------------------------------");
        //printTournamentToFile(sample);


    }

    public void consolidateTournament(){
        swarm.clear();
        swarm = new ArrayList<Ant>(tournament);
    }

    public void printCleanTournament(){
        ArrayList<Ant> cleanTournament = new ArrayList<Ant>();

        for(Ant ant : tournament){
            if(compareAnt(ant, cleanTournament) == false){
                cleanTournament.add(ant);
            }
        }
        printCleanTournamentToFile(cleanTournament);
    }

    public boolean compareAnt(Ant newAnt, ArrayList<Ant> cleanTournament){
        for(Ant ant : cleanTournament){
            if(newAnt.getId() == ant.getId()){
                return true;
            }
        }
        return false;

    }

	// Function that creates a new generation of ants, mutating the existing ones
    public void mutateGeneration(double mutationRate){
        generation++;

        for(int i = 0; i < colony.getSize(); i++){
        		swarm.get(i).generateMutation(mutationRate);
        }

        Collections.sort(this.swarm);
    }

    // Function that creates a new generation of ants, receives the crossing over rate as a parameter
    public void newGeneration(double crossingOverRate, double mutationRate){

        generation++;

        int mom;
        int dad;

        double random;

        for(int i = 0; i < colony.getSize(); i++){
        	random = Math.random();
            if(Math.random() < crossingOverRate){
                mom = getRandomPosition(swarm.size());
                dad = getRandomPosition(swarm.size());

                swarm.add(new Ant(colony.getPosX(), colony.getPosY(), swarm.get(mom), swarm.get(dad), idGenerator(), mutationRate));
            }
        }

        run();

        Collections.sort(this.swarm);

        while(colony.getSize() < swarm.size())
        {
        	swarm.remove(colony.getSize());
        }
    }

    public int idGenerator(){
        return counter++;
    }

    public static void main(String Args[])
    {
        Simulator simulator = new Simulator();

        simulator.run();

        String command;
        Scanner terminalInput = new Scanner(System.in);

        double mutationRate = 0.3;
        double crossingOverRate = 0.7;

        do
        {
            // Prompt
            System.out.println("## Terminal ##");
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
                simulator.printCleanTournament();
            }
            else if(command.equals("rg")){
                System.out.println("Mutation rate: " + mutationRate);
                System.out.println("Crossing-over rate: " + crossingOverRate);
                // Number of generations
                System.out.print("How many generations?: ");
                command = terminalInput.nextLine();
                int generations = Integer.parseInt(command);

                // Sampling rate 
                System.out.print("What is the tournament sampling rate?: ");
                command = terminalInput.nextLine();
                int sampling = Integer.parseInt(command);

                simulator.printCurrentGeneration();
                for(int i = 0; i < generations; i ++){
                    simulator.tournament(sampling);
                    simulator.consolidateTournament();
                    simulator.newGeneration(crossingOverRate, mutationRate);
                    //simulator.printCleanTournament();
                    //simulator.printCurrentGeneration();
                }
            }
            // Change mutation rate
            else if(command.equals("cmr"))
            {
            	System.out.print("Change mutation rate (double): ");
                command = terminalInput.nextLine();
                mutationRate = Double.parseDouble(command);
            }
            // Change crossing-over rate
            else if(command.equals("ccr"))
            {
            	System.out.print("Change crossing-over rate (double): ");
                command = terminalInput.nextLine();
                crossingOverRate = Double.parseDouble(command);
            }
            // Runnnig regerations with mutation only
            else if(command.equals("rgmo"))
            {
           		System.out.println("Mutation rate: " + mutationRate);
           		// Number of generations
                System.out.print("How many generations?: ");
                command = terminalInput.nextLine();
                int generations = Integer.parseInt(command);

                // Sampling rate 
                System.out.print("What is the tournament sampling rate?: ");
                command = terminalInput.nextLine();
                int sampling = Integer.parseInt(command);

                simulator.printCurrentGeneration();
                for(int i = 0; i < generations; i ++){
                    simulator.tournament(sampling);
                    simulator.consolidateTournament();
                    simulator.mutateGeneration(mutationRate);
                    simulator.printCleanTournament();
                    simulator.printCurrentGeneration();
                }	
            }
            // Runnnig regerations no mutation and no crossing-over
            else if(command.equals("rgnone"))
            {
           		// Number of generations
                System.out.print("How many generations?: ");
                command = terminalInput.nextLine();
                int generations = Integer.parseInt(command);

                // Sampling rate 
                System.out.print("What is the tournament sampling rate?: ");
                command = terminalInput.nextLine();
                int sampling = Integer.parseInt(command);

                simulator.printCurrentGeneration();
                for(int i = 0; i < generations; i ++){
                    simulator.tournament(sampling);
                    simulator.consolidateTournament();
                    simulator.printCleanTournament();
                    simulator.increaseGeneration();
                    simulator.printCurrentGeneration();
                }	
            }
            // Runnnig regerations with crossingOver only
            else if(command.equals("rgco"))
            {
                System.out.println("Crossing-over rate: " + crossingOverRate);
                // Number of generations
                System.out.print("How many generations?: ");
                command = terminalInput.nextLine();
                int generations = Integer.parseInt(command);

                // Sampling rate 
                System.out.print("What is the tournament sampling rate?: ");
                command = terminalInput.nextLine();
                int sampling = Integer.parseInt(command);

                simulator.printCurrentGeneration();
                for(int i = 0; i < generations; i ++){
                    simulator.tournament(sampling);
                    simulator.consolidateTournament();
                    simulator.newGeneration(crossingOverRate, 0);
                    simulator.printCleanTournament();
                    simulator.printCurrentGeneration();
                }	
            }
            else if(command.equals("export"))
            {
                System.out.print("Exporting...");
                simulator.exportAnts();
                System.out.println("done");
            }
        }
        while(!command.equals("quit"));
        System.out.println("Thank you");
    }
}



