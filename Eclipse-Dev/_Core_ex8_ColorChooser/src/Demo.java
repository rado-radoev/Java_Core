import javax.swing.JFrame;

public class Demo {

	public static void main(String[] args) {
		ColorChooser myColorChooser = new ColorChooser();
		myColorChooser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myColorChooser.setSize(650, 400);
		myColorChooser.setVisible(true);
	}
}
