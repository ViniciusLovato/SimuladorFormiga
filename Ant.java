import java.util.ArrayList;

public class Ant implements Comparable<Ant>
{
    // Ant position
    private int x;
    private int y;

    // Lifetime
    private int ttl;

    // Score
    private int score;

    private int id;
    // Array that is going to save the movement history
    private ArrayList<Integer> movement;

    // Constructor
    public Ant(int x, int y, int ttl, int id)
    {
		setTtl(ttl);
		setPosition(x, y);
		resetScore();
		this.id = id;

		this.movement = new ArrayList<Integer>(ttl);

		while(isAlive()){
		    // create the ant movement Array (DNA)
			generateDNA();	
		}	
    }

    // generate a new ant using two old ants
    public Ant(int x, int y, Ant mom, Ant dad, int id, double mutationRate)
    {
		setTtl(mom.getMovementArray().size());
		setPosition(x, y);
		resetScore();
		this.id = id;

		this.movement = new ArrayList<Integer>(this.getTtl());

    	int slice = (int) Math.round(Math.random()*(mom.getMovementArray().size() - 1));

    	for(int i = 0; i < slice; i++){
    		this.movement.add(mom.getMovementArray().get(i));
    	}

    	for(int i = slice; i < dad.getMovementArray().size() - 1; i++){
    		this.movement.add(dad.getMovementArray().get(i));
    	}

    	generateMutation(mutationRate);
    }

    // Function that mutates a gene using according to a rate
    public void generateMutation(double rate){

    	double mutationChance;

    	//for(Integer direction : this.getMovementArray()){
        for(int i = 0; i < movement.size(); i++){

        	// Creating mutation rate
        	mutationChance = Math.random();

			if(mutationChance < rate){
				double probability = Math.random();    

				// Moving
				if(probability <= 0.25){
					this.movement.set(i, 0);
				}
				else if(probability > 0.25 && probability <= 0.5){
					this.movement.set(i, 1);
				}
				else if(probability > 0.50 && probability <= 0.75){
					this.movement.set(i, 2);
				}
				else{ //if(probability > 0.75){
					this.movement.set(i, 3);
				} 
	    	}	
		}
    }

    @Override
	public int compareTo(Ant ant)
	{
	    return(this.getScore() - ant.getScore());
	}

    // Function that sets the ttl (lifetime)
    public void setTtl(int ttl)
    {
	this.ttl = ttl;
    }

    // Function that gets the ttl (lifetime)
    public int getTtl()
    {
	return this.ttl;
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
    public void generateDNA()
    {
		// Random number to choose which side the ant is gointo to move
		int direction;
		// Temporary position of the ant, used to validate the movement
		int tmpx, tmpy;

		// Probability 
		double probability;

		// While the movement is not valid
		
		// Random number
		probability = Math.random();

		    // Save position on temporary variables
		tmpx = this.x;    
		tmpy = this.y;    

		    // Moving
		if(probability <= 0.25){
			direction = 0;
			tmpx += 1;
		}
		else if(probability > 0.25 && probability <= 0.5){
			direction = 1;
			tmpx -= 1;
		}
		else if(probability > 0.50 && probability <= 0.75){
			direction = 2;
			tmpy += 1;
		}
		else{ //if(probability > 0.75){
			direction = 3;
			tmpy -= 1;
		}

		// Saving the new ant position
		setPosition(tmpx, tmpy);
		//System.out.println("Ant posx: " + tmpx + " " + tmpy);

		// Saving in the movement array
		this.movement.add(direction);

		// Reduces the lifetime of the ant
		this.ttl--;
    }      

    // Check if the ant could move one more time
    public boolean isAlive(){
		return(this.ttl > 0);
    }

    // return the ant movement array
    public ArrayList<Integer> getMovementArray()
    {
		return(this.movement);
    }

    // increase the ant score
    public void increaseScore(int value){
		this.score += value;	
    }

    public int getPosX(){
		return this.x;
    }

    public int getPosY(){
		return this.y;
    }

    public void setPosX(int x){
    	this.x = x;
    }

    public void setPosY(int y){
    	this.y = y;
    }

    public int getId(){
    	return id;
    }
}
