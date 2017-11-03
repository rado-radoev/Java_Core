import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PlayCrapsApp {

	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private static void createAndShowGUI() {
		PlayCraps playCrapsFrame = new PlayCraps("Play Craps!");
		playCrapsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playCrapsFrame.setResizable(false);
		playCrapsFrame.setVisible(true);
		playCrapsFrame.setSize(300, 200);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		playCrapsFrame.setLocation(dim.width/2-playCrapsFrame.getSize().width/2, dim.height/2-playCrapsFrame.getSize().height/2);
	}

}
