/*
package graphicinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Ant extends JPanel {

	private int squareX = 50;
	private int squareY = 50;
	private int squareW = 20;
	private int squareH = 20;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(squareX, squareY, squareW, squareH);
		g.setColor(Color.BLACK);
		g.drawRect(squareX, squareY, squareW, squareH);
	}

	public void moveSquare(int direcao) {
		
		int x = squareX;
		int y = squareY;
		
		switch(direcao) {
			case 0:	// direita
				x++;
				break;
			case 1:	// esquerda
				x--;
				break;
			case 2:	// sobe
				y--;
				break;
			case 3:	// desce
				y++;
				break;
			default:
				break;
		}	
		
		int OFFSET = 1;
		if ((squareX != x) || (squareY != y)) {
			repaint(squareX, squareY, squareW + OFFSET, squareH + OFFSET);
			squareX = x;
			squareY = y;
			repaint(squareX, squareY, squareW + OFFSET, squareH + OFFSET);
		}
	}
}
*/