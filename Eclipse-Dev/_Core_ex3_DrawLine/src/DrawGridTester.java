import javax.swing.JFrame;

public class DrawGridTester {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Draw 8 by 8 grid");
		DrawGrid grid = new DrawGrid();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(grid);
		frame.setSize(250,250);
		frame.setVisible(true);
	}
}
