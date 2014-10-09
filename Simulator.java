import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

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

    public static void main(String Args[])
    {
	Simulator simulator = new Simulator();

	simulator.run();

	simulator.tournament(16);
	simulator.tournament(64);
	simulator.tournament(128);
	simulator.tournament(512);

	System.out.println("Thank you");
    }
}



