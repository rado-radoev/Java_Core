import java.awt.*;

/**
 * Class to create frames for a movie
 * @author superlamer
 *
 */
public class MovieMaker {

	/**
	 * Method  to make a ovie that has a rectangle moving around
	 * @param direcotry the directory to put the movie frames
	 */
	public void makeRectangleMovie(String directory) {
		int framesPerSec = 30;
		Picture p = null;
		Graphics g = null;
		
		FrameSequencer frameSequencer =
				new FrameSequencer(directory);
		
		// loop through the first second
		for (int i = 0; i < framesPerSec;i++) {
			// draw a filled rectangle
			p = new Picture(640, 480);
			g = p.getGraphics();
			g.setColor(Color.RED);
			g.fillRect(i * 10, i * 5, 50, 50);
			
			// add frame to sequencer
			frameSequencer.addFrame(p);
		}
		
		// replay the movie
		frameSequencer.show();
		frameSequencer.play(framesPerSec);
	}
	
	
	/**
	 * Method to create a tickertape movie
	 * @param directory the directory to write to
	 * @param message the string to display
	 */
	public void makeTicketrTapeMovie(String directory, String message) {
		int framesPerSec = 30;
		Picture p = null;
		Graphics g = null;
		FrameSequencer frameSequencer = 
				new FrameSequencer(directory);
		Font font = new Font("Arial", Font.BOLD, 24);
		
		// loop for 2 seconds
		for (int i = 0; i < framesPerSec * 2; i++) {
			// draw the string
			p = new Picture(300, 100);
			g = p.getGraphics();
			g.setColor(Color.BLACK);
			g.setFont(font);
			g.drawString(message, 300 - (i * 10), 50);
		
			// add frame to sequencer
			frameSequencer.addFrame(p);
		}
		
		// replay the movie
		frameSequencer.show();
		frameSequencer.play(framesPerSec);
	}

	
	
	
	public static void main(String[] args) {
		MovieMaker movieMkaker = new MovieMaker();
		String dir = FileChooser.pickADirectory();
		//movieMkaker.makeRectangleMovie(dir);
		movieMkaker.makeTicketrTapeMovie(dir, "Watch more porn");
	}
}
