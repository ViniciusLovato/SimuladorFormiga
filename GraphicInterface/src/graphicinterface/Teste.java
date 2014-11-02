package graphicinterface;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Teste extends JPanel {
	
	// formiga
	public int x = 0;
	public int y = 0;
	public int largura = 40;
	public int altura = 40;
	
	// comida
	public int cx = 0;
	public int cy = 0;
	
	// formigueiro
	public int fx = 0;
	public int fy = 0;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.setBackground(Color.WHITE);
		
		// formiga
		g.setColor(Color.BLUE);
		g.fillRect(x, y, largura, altura);
		
		// comida
		g.setColor(Color.GREEN);
		g.fillOval(cx, cy, largura, altura);
		
		// formigueiro
		g.setColor(Color.RED);
		g.fillRect(fx, fy, largura, altura);		
	}
}
