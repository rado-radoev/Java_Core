import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

public class DrawGrid extends JPanel {

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int col = 0; col < 8; col++) {
			for (int row = col; row < col + 1; row++) {
				//g.drawLine(0, 10, 0, 10);
			}
			g.drawLine(50, 150, 5, 150);
		}
	}
}
