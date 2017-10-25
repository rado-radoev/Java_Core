import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

/**
 * Class to create a cartoon out of a picture
 * @author superlamer
 */
public class Cartoon {
	///////////// fields /////////////
	private Picture picture;
	
	///////////// constructor /////////////
	/**
	 * Constructor that takes the picture
	 * @param p the picture to use
	 */
	public Cartoon(Picture p) {
		picture = p;
	}
	
	///////////// methods /////////////
	/**
	 * Mehtod to add a word ballon that contains the message
	 * @param message the text to show
	 * @param xPos the top left for the word balloon
	 * @param yPos the top left for the word baloon
	 */
	public void addWordBalloon(String message, int xPos, int yPos) {
		// get the Graphics2D
		Graphics g = picture.getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		// get the font information for the message
		Font font = new Font("Arial", Font.BOLD, 24);
		FontRenderContext frc = g2d.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(message, frc);
		LineMetrics metrics = font.getLineMetrics(message, frc);
		float lineHeight = metrics.getHeight();
		float ascent = metrics.getAscent();
		
		// draw the ellipse for the word balloon
		double ellipseWidth = bounds.getWidth() * 1.5;
		double ellipseHeight = bounds.getHeight() * 2.0;
		g2d.setColor(Color.WHITE);
		g2d.fill(new Ellipse2D.Double(xPos, yPos, ellipseWidth, ellipseHeight));
		
		// draw the message centered in the ellipse
		float x0 = (float) ((ellipseWidth - bounds.getWidth()) / 2 + xPos);
		float y0 = (float) ((ellipseHeight - bounds.getHeight()) / 2 + yPos + ascent);
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(font);
		g2d.drawString(message, x0, y0);
	}
	
	public static void main(String[] args) {
		Picture picture = 
				new Picture(FileChooser.getMediaPath("horse.jpg"));
		Cartoon cartoon = new Cartoon(picture);
		cartoon.addWordBalloon("What's up, Wilbur?", 42, 20);
		picture.explore();
	}
}











