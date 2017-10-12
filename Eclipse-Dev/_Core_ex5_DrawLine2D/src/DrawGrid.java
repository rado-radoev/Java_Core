import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class DrawGrid extends JPanel {
	
	// initalize attributes and empty array
	private int y, x, startX, startY, endX, endY; 
	private int[] grid;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		// set the 8x8 grid
		grid = new int[9];

		// set start and end rows
		startX = 10;
		x = startX;
		endX = startX * grid.length * 2 - 10;
		
		// set start and end rows
		startY = 10;
		y = startY;
		endY = startY * grid.length * 2 - 10;
		
		
		// Draw 8 x 8 grid. Grid space 20px.
		for (int i = 0; i < grid.length; i++) {
		/*
			System.out.println("startX: " + startX);
			System.out.println("endX: " + endX);
			System.out.println("startY: " + startY);
			System.out.println("endY: " + endX);
			System.out.println("x: " + x);
			System.out.println("y: " + y);
		*/	
			g2d.setColor(Color.GREEN);
			g2d.draw(new Line2D.Double(startX, y, endX, y));
			g2d.draw(new Line2D.Double(x, startY, x, endY));
			
			x += 20;
			y += 20;
		}
	}
}
