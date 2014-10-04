public class Leaf {

	private int posx;
	private int posy;

	public Leaf (int posx, int posy){
		setPosition(posx, posy);
	}

	public int getPosX(){
		return this.posx;
	}	

	public int getPosY(){
		return this.posy;
	}	

	public void setPosition(int posx, int posy){
		this.posx = posx;
		this.posy = posy;
	}
}
