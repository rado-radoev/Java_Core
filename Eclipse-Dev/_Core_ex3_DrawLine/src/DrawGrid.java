import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawGrid extends JPanel {
	
	// initalize attributes and empty array
	private int y, x, startX, startY, endX, endY; 
	private int[] grid;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
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
			g.drawLine(startX, y, endX, y);
			g.drawLine(x, startY, x, endY);
			
			x += 20;
			y += 20;
		}
	}
}
