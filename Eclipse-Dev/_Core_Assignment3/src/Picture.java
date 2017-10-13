
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 * 
 * Copyright Georgia Institute of Technology 2004-2005
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture() {
		/*
		 * not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor
		 */
		super();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName
	 *            the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param width
	 *            the width of the desired picture
	 * @param height
	 *            the height of the desired picture
	 */
	public Picture(int width, int height) {
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image
	 *            the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName,
	 *         height and width.
	 */
	public String toString() {
		String output = "Picture, filename " + getFileName() + " height "
				+ getHeight() + " width " + getWidth();
		return output;

	}

	public static void main(String[] args) {
		String fileName = FileChooser.pickAFile();
		Picture pictObj = new Picture(fileName);
		pictObj.explore();
	}

	public void increaseRed2() {
		increaseRed2(30);
	}

	public void increaseRed2(double amount) {
		increaseColor(amount, Color.RED);
	}
	




	private void increaseColor(double amount, Color color) {
		// get all the pixels
		Pixel[] pixels = getPixels();

		// the current pixel value
		int value = 0;

		for (Pixel pixel : pixels) {
			if (color.equals(Color.RED)) {
				value = pixel.getRed();
				value = (int) (value * amount);
				pixel.setRed(value);
			} else if (color.equals(Color.GREEN)) {
				value = pixel.getGreen();
				value = (int) (value * amount);
				pixel.setGreen(value);
			} else if (color.equals(Color.BLUE)) {
				value = pixel.getBlue();
				value = (int) (value * amount);
				pixel.setBlue(value);
			}
		}
	}

	/**
	 * Method to increase the amount of red by 30%
	 */
	public void increaseRed() {
		increaseRed2(30);
	}
	
	/**
	 * Method to increase the amount of red by specified amount
	 * @param amount 
	 * 					with which to increase the red
	 */
	public void increaseRed(double amount) {
		increaseColor(amount, Color.RED);
	}


	/**
	 * Method to increase the amount of green by 30%
	 */
	public void increaseGreen() {
		increaseGreen(30);
	}
	
	/**
	 * Method to increase the amount of green by specific amount
	 * @param amount 
	 * 					with which to increase the green
	 */
	public void increaseGreen(double amount) {
		increaseColor(amount, Color.GREEN);
	}

	/**
	 * Method to increase the amount of blue by 30%
	 */
	public void increaseBlue() {
		increaseBlue(30);
	}

	/**
	 * Method to increase the amount of blue by specific amount
	 * @param amount 
	 * 					with which to increase the color
	 */
	public void increaseBlue(double amount) {
		increaseColor(amount, Color.BLUE);
	}

	/**
	 * Method that blurs within specified rectangle
	 * 
	 * @param x
	 *            upper left point of origin
	 * @param y
	 *            upper left point of origin
	 * @param width
	 *            width of the blur rectangle
	 * @param height
	 *            height of the blur rectangle
	 * @param blurrPixels
	 *            number of pixels to average in all directions, blur strength
	 */
	public void blur(int startX, int startY, int width, int height,
			int blurrPixels) {
		Pixel pixel, samplePixel;

		int redValue = 0, greenValue = 0, blueValue = 0;
		int count = 0;

		// loop through the pixels (cols), starting from x, until the max width
		// is reached
		for (int x = startX; x < width + startX; x++) {
			// loop through the pixels (rows), starting from y, until the max
			// height is reached
			for (int y = startY; y < height + startY; y++) {

				// get the current pixel
				pixel = getPixel(x, y);

				// reset the count and red, green and blue values count = 0;
				count = 0;
				redValue = greenValue = blueValue = 0;

				// loop through number of pixels before and after x
				for (int xSample = x - blurrPixels; xSample <= x
						+ blurrPixels; xSample++) {
					for (int ySample = y - blurrPixels; ySample <= y
							+ blurrPixels; ySample++) {
						// check that we are in the range of acceptable pixels
						if (xSample >= 0 && xSample < getWidth() && ySample >= 0
								&& ySample < getHeight()) {
							samplePixel = getPixel(xSample, ySample);
							redValue += samplePixel.getRed();
							greenValue += samplePixel.getGreen();
							blueValue += samplePixel.getBlue();
							count++;
						}
					}
				}

				// user average color of surrounding pixels
				pixel.setColor(new Color(redValue / count, greenValue / count,
						blueValue / count));
			}
		}
	}
	
	
	
	/**
	 * Method to do edge detection by comparing the absolute value of the difference
	 * between the color intensities (average of the color values) between a pixel 
	 * and the pixel below it. If the absolute value of the difference between
	 * the color intensities is less than a passed amount the top pixel color will be set to one color.
	 * Otherwise it is set to another color.
	 * @param toColor top color that will replace any light color
	 * @param bottomColor bottom color that will replace any dark color
	 * @param amount if the absolute value of the differences in the color average is less than
	 * this, set the color to topColor, else to bottomColor
	 * @param startX starting x position
	 * @param endX ending x position
	 * @param startY starting y position
	 * @param endY ending y position
	 */
	public void edgeDetection(Color topColor, Color bottomColor, double amount, int startX, int endX, int startY, int endY) {
		Pixel topPixel, bottomPixel;
		double topAverage, bottomAverage;
		
		 // loop through the y values from 0 to height - 1
		for (int y = startY; y < endY; y++) {
			// loop through the x values from 0 to width
			for (int x = startX; x < endX; x++) {
				// get the top and bottom pixel
				topPixel = getPixel(x, y);
				bottomPixel = getPixel(x, y+1);
				
				// get the color averages for the two pixels
				topAverage = topPixel.getAverage();
				bottomAverage = bottomPixel.getAverage();
				
				// check if the absolute value of the difference is less than the amount
				if (Math.abs(topAverage - bottomAverage) < amount) {
					topPixel.setColor(topColor);
				}
				else {
					topPixel.setColor(bottomColor);
				}
			}
 		}
	}
	
	/**
	 * Method to pixelate picture by specific pixel size
	 * @param pixelSize the size of the pixel when pixelating the image
	 */
	public void pixelate(int pixelSize) {
		Pixel sourcePixel, destPixel;
		
		// loop through the rows until height - pixelSize is reached
		for(int y = 0; y < getHeight() - pixelSize; y += pixelSize) {
			// loop through the columns until width - pixelSize is reached
		    for(int x = 0; x < getWidth() - pixelSize; x += pixelSize) {

		        // get the current pixel
		        sourcePixel = getPixel(x, y);

		        // loop through the neighboring pixels and copy the current pixel there
		        for(int yy = y; (yy < y + pixelSize) && (yy < getHeight() - pixelSize); yy++) {
		            for(int xx = x; (xx < x + pixelSize) && (xx < getWidth() - pixelSize); xx++) {
		                destPixel = getPixel(xx, yy);
		                destPixel.setColor(sourcePixel.getColor());
		            }
		        }
		    }
		}
	}
	
	
	/**
	 * Method turns pixels with an average color < 85 to green.
	 * Pixels with an average color < 170 to red.
	 * All other pixels to blue.
	 */
	public void changePixels() {
		Pixel pixel;
		
		// loop through the columns
		for (int x = 0; x < getWidth(); x++) {
			// loop through the rows
			for (int y = 0; y < getHeight(); y++) {
				pixel = getPixel(x, y);
				
				if (pixel.getAverage() < 85) 
					pixel.setColor(Color.GREEN);
				else if (pixel.getAverage() < 170)
					pixel.setColor(Color.RED);
				else
					pixel.setColor(Color.BLUE);
			}
		}
	}
	
} // this } is the end of class Picture, put all new methods before this























