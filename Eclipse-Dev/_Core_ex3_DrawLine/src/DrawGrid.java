import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawGrid extends JPanel {
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int startX = 10, endX = 10, startY = 10, endY = 10;
		
		for (int col = 0; col <= 8; col++) {
		
			g.drawLine(startX, 10, endX, 170);
			
			for (int row = col - 1; row < col; row++) {
				g.drawLine(10, startY, 170, endY);
			}
			
			startX+= 20;
			endX+= 20;
			startY+= 20;
			endY+= 20;
		}
	}
}
