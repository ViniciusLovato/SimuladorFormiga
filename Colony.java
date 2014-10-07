public class Colony {

	private int posx;
	private int posy;

	private int colonySize;


	public Colony (int colonySize){
		setPosition(0, 0);
		setSize(colonySize);
	}

	public Colony (int posx, int posy, int colonySize){
		setPosition(posx, posy);
		setSize(colonySize);
	}

	public int getPosX(){
		return this.posx;
	}	

	public int getPosY(){
		return this.posy;
	}	

	public int getSize(){
		return this.colonySize;
	}

	public void setPosition(int posx, int posy){
		this.posx = posx;
		this.posy = posy;
	}
	
	public void setSize(int size){
		this.colonySize = size;
	}
}	
