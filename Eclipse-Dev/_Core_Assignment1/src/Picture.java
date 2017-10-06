import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * Copyright Georgia Institute of Technology 2004-2005
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param width the width of the desired picture
   * @param height the height of the desired picture
   */
  public Picture(int width, int height)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  
  public static void main(String[] args) 
  {
     String fileName = FileChooser.pickAFile();
     Picture pictObj = new Picture(fileName);
     pictObj.explore();
  }
  
  /**
   * Method to mirror around a horizontal line in the middle
   * based on the height. It copies the bottom mirrored to the top
   */
  public void mirrorHorizontalBottomToTop() {
	  int height = getHeight();
	  int mirrorPoint = height / 2;
	  Pixel topPixel, bottomPixel;
	  
	  // loop through all the columns
	  for (int x = 0; x < getWidth(); x++) {
		  // loop through all the rows from 0 until the middle
		  for (int y = 0; y < mirrorPoint; y++) {
			  topPixel = getPixel(x, y);
			  bottomPixel = getPixel(x, height - 1 - y);
			  topPixel.setColor(bottomPixel.getColor());
		  }
	  }
	  
  }
  
  /**
   * Method to mirror around a horizontal line in the middle
   * based on the height. It copies the top mirrored to the bottom
   */
  public void mirrorHorizontal() {
	  int height = getHeight();
	  int mirrorPoint = height / 2;
	  Pixel topPixel, bottomPixel;
	  
	  // loop through all the columns
	  for (int x = 0; x < getWidth(); x++) {
		  // loop through all the rows from 0 until the middle
		  for (int y = 0; y < mirrorPoint; y++) {
			  topPixel = getPixel(x, y);
			  bottomPixel = getPixel(x, height - 1 - y);
			  bottomPixel.setColor(topPixel.getColor());
		  }
	  }
	  
  }
  
  /**
   * Method to mirror around a vertical line in the middle of the picture based on the width
   */
  public void mirrorVertical() {
	  int witdth = getWidth();
	  int mirrorPoint = witdth / 2;
	  Pixel leftPixel, rightPixel;
	  
	  // loop through all the rows
	  for (int y = 0; y < getHeight(); y++) {
		  // loop from 0 to the middle (mirrorPoint)
		  for (int x = 0; x < mirrorPoint; x++) {
			  leftPixel = getPixel(x, y);
			  rightPixel = getPixel(witdth - 1 - x, y);
			  rightPixel.setColor(leftPixel.getColor());
		  }
	  }
  }
  
  /**
   * Method to mirror part of the temple picture around a vertical line at a mirror point
   */
  public void mirrorTemple() {
	  int mirrorPoint = 276;
	  Pixel leftPixel, rightPixel;
	  
	  // loop through all the rows that contain the pediment(roof)
	  for (int y = 27; y < 97; y++) {
		  // loop from 13 to just before the mirror point
		  for (int x = 13; x < mirrorPoint; x++) {
			  leftPixel = getPixel(x, y);
			  rightPixel = getPixel(mirrorPoint + (mirrorPoint - x), y);
			  rightPixel.setColor(leftPixel.getColor());
		  }
	  }
  }
  
  /**
   * Method to lighten the colors in the picture
   */
  public void lighten2() {
	  Color color;
	  Pixel pixel;
	  
	  // loop through the columns (x direction)
	  for (int x = 0; x < getWidth(); x++) {
		  // loop through the rows (y direction)
		  for (int y = 0; y < getHeight(); y++) {
			  
			  // get the pixel at the x and y location
			  pixel = getPixel(x, y);
			  
			  // get the current color
			  color = pixel.getColor();
			  
			  // get a lighter color
			  color = color.brighter();
			  
			  // set the pixel color to the lighter color
			  pixel.setColor(color);
		  }
	  }
  }
  
  /**
   * Mehod to clear blue from the picture
   * (set the blue to 0 for all pixels)
   */
  public void clearBlue( ) {
	  Pixel[] pixelArray = getPixels();
	  Pixel pixel;
	  int index = 0;
	  
	  // loop through all the pixels
	  while (index < pixelArray.length) {
		  
		  // get the current pixel
		  pixel = pixelArray[index];
		  
		  // set the blue on the pixel to 0
		  pixel.setBlue(0);
		  
		  index++;
	  }
  }
  
  /**
   * Method to change the picture to grayscale with luminance
   */
  
  public void grayscaleWithLuminance() {
	  Pixel[] pixelArray = getPixels();
	  Pixel pixel;
	  int luminance = 0;
	  double redValue, greenValue, blueValue = .0;
	  
	  // loop through all the pixels
	  for (int i = 0; i < pixelArray.length; i++) {
		  // get the current pixel
		  pixel = pixelArray[i];
		  
		  // get the corrected red, green and blue values
		  redValue = pixel.getRed() * .299;
		  greenValue = pixel.getGreen() * .587;
		  blueValue = pixel.getBlue() * .114;
		  
		  // compute the intensity of the pixel (average value)
		  luminance = (int) (redValue + greenValue + blueValue);
		  
		  // set the pixel color to the new color
		  pixel.setColor(new Color(luminance, luminance, luminance));
	}
  }
  
  /**
   * Method to change the picture to grayscale 
   */
  public void grayscale() {
	  Pixel[] pixelArray = getPixels();
	  Pixel pixel;
	  int intensity = 0;
	  
	  // loop through all the pixels
	  for (int i = 0; i < pixelArray.length; i++) {
		
		  // get the current pixel
		  pixel = pixelArray[i];
		  
		  // compute the intensity of the pixel (average value)
		  intensity = (int) ((pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3);
		  
		  // set the pixel color to the new color
		  pixel.setColor(new Color(intensity, intensity, intensity));
	}
  }
  
  /**
   * Method to lighten the colors in the picture
   */
  public void lighten() {
	  Pixel[] pixelArray = getPixels();
	  Pixel pixel;
	  Color color;
	  
	  // loop through all the pixels
	  for (int i = 0; i < pixelArray.length; i++) {
		  
		  // get the current pixel
		  pixel = pixelArray[i];
		  
		  // get the current color
		  color = pixel.getColor();
		  
		  // get a lighter color
		  color = color.brighter();
		  
		  // set the pixel color to the lighter color
		  pixel.setColor(color);
	  }
  }
  
  /**
   * Method to darken the picture
   */
  public void darken() {
	  Pixel[] pixelArray = getPixels();
	  Pixel pixel;
	  Color color;
	  
	  // loop through all the pixels
	  for (int i = 0; i < pixelArray.length; i++) {
		  
		  // get the current pixel
		  pixel = pixelArray[i];
		  
		  // get the current color
		  color = pixel.getColor();
		  
		  // get a lighter color
		  color = color.darker();
		  
		  // set the pixel color to the lighter color
		  pixel.setColor(color);
	  } 
  }
  
  /**
   * Method to negate the picture
   */
  public void negate() {
	  Pixel[] pixelArray = getPixels();
	  Pixel pixel;
	  int redValue, greenValue, blueValue = 0;
	  
	  // loop through all the pixels
	  for (int i = 0; i < pixelArray.length; i++) {
		  
		  // get the current pixel
		  pixel = pixelArray[i];
		  
		  // get the current red, green, blue value
		  redValue = pixel.getRed();
		  greenValue = pixel.getGreen();
		  blueValue = pixel.getBlue();
		  
		  // set the pixel's color to the new color
		  pixel.setColor(new Color(255 - redValue, 
	  							   255 - greenValue,
	  							   255 - blueValue));
	  }
  }
  
  /**
   * Method to clear red from the picture
   * (set the red to 0 for all pixels)
   */
  public void clearRed() {
	  Pixel[] pixelArray = getPixels();
	  
	  for (int i = 0; i < pixelArray.length; i++) {
		pixelArray[i].setRed(0);
	}
  }
  
  
  /**
   * Method to clear green from the picture
   * (set the green to 0 for all pixels)
   */
  public void clearGreen() {
	  Pixel[] pixelArray = getPixels();
	  
	  for (int i = 0; i < pixelArray.length; i++) {
		pixelArray[i].setGreen(0);
	}
  }
  
  /**
   * Method to maximize red color
   */
  public void maximizeRed() {
	  Pixel[] pixelArray = getPixels();
	  
	  for (int i = 0; i < pixelArray.length; i++) {
		pixelArray[i].setRed(255);
	}
  }
  
  /**
   * Method to maximize green color
   */
  public void maximizeGreen() {
	  Pixel[] pixelArray = getPixels();
	  
	  for (int i = 0; i < pixelArray.length; i++) {
		pixelArray[i].setGreen(255);
	}
  }
  
  /**
   * Method to maximize blue color
   */
  public void maximizeBlue() {
	  Pixel[] pixelArray = getPixels();
	  
	  for (int i = 0; i < pixelArray.length; i++) {
		pixelArray[i].setBlue(255);
	}
  }
  
  public void modifyRGB(double redAmount, 
		  double greenAmount, 
		  double blueAmount) {
	  Pixel[] pixelArray = getPixels();
	  Pixel pixel;
	  
	  for (int i = 0; i < pixelArray.length; i++) {
		pixel = pixelArray[i];
		pixel.setRed((int) (pixel.getRed() * redAmount));
		pixel.setGreen((int) (pixel.getGreen() * greenAmount));
		pixel.setBlue((int) (pixel.getBlue() * blueAmount));
	}
  }

  /**
   * Method to leave only red color
   */
  public void onlyRed() {
	  clearGreen();
	  clearBlue();
  }
  
  
  /**
   * Method to leave only green color
   */
  public void onlyGreen() {
	  clearBlue();
	  clearRed();
  }
  
  /**
   * Method to leave only blue color
   */
  public void onlyBlue() {
	  clearGreen();
	  clearRed();
  }
  
  
  /**
   * Mehod to clear blue from the picture (shorter)
   * (set the blue to 0 for all pixels)
   */
  public void clearBlue2( ) {
	  Pixel[] pixelArray = getPixels();
	  Pixel pixel;
	  int i = 0;
	  
	  // loop through all the pixels
	  while (i < pixelArray.length) {
		  
		  // get and set the blue pixel to 0
		  pixelArray[i].setBlue(0);
		  
		  // increment index
		  i++;
	  }
  }
  
  /**
   * Method to increase the amount of red by 30%
   */
  public void increaseRed() {
	  Pixel[] pixelArray = getPixels();
	  Pixel pixel;
	  int value, index = 0;
	  
	  // loop through all the pixels
	  while (index < pixelArray.length) {
		  
		  // get the current pixel
		  pixel = pixelArray[index];
		  
		  // get the value or red
		  value = pixel.getRed();
		  
		  // change the value to 1.3 time it was
		  value = (int) (value * 1.3);
		  
		  // set the new red value to 1.3 times it was
		  pixel.setRed(value);
		  
		  // increment index
		  index++;
	  }
  }
  
  /**
   * Method to decrease the red by half in the current picture
   */
  public void decreaseRedHalf() {
	  Pixel[] pixelArray = this.getPixels();
	  Pixel pixel;
	  int value, index = 0;
	  
	  // loop through all the pixels
	  while (index < pixelArray.length) {
		  // get the current pixel
		  pixel = pixelArray[index];
		  
		  // get the value
		  value = pixel.getRed();
		  
		  // decrease the red value by 50% (1/2)
		  value = (int) (value * 0.5);
		  
		  // set the red value of the current pixel to the new value
		  pixel.setRed(value);
		  
		  // increment the index
		  index = index + 1;
	  }
  }
  
	public void decreaseRed() {
		Pixel[] pixelArray = this.getPixels();
		
		int value = 0;
		
		// loop through all the pixel in the array
		for (Pixel pixel : pixelArray) {
			
			// get the red value
			value = pixel.getRed();
			
			// decrease the red value by 50% (1/2)
			value = (int) (value * 0.5);
			
			// set the red value of the current pixel to the new value
			pixel.setRed(value);
		}
	}
	
	/**
	 * Method to simulate a sunset by decreasing the green and blue
	 */
	public void makeSunset() {
		Pixel[] pixelArray = this.getPixels();
		Pixel pixel;
		int value, i = 0;
		
		// loop through all the pixels
		while (i < pixelArray.length) {
			// get the current pixel
			pixel = pixelArray[i];
			
			// change the blue value
			value = pixel.getBlue();
			pixel.setBlue((int) (value * 0.7));
			
			// change the green value
			value = pixel.getGreen();
			pixel.setGreen((int) (value * 0.7));
			
			// increment the index
			i++;
		}
	}
	
	/**
	 * Method to change the red by an amount
	 * @param amount the amount to change the red by
	 */
	public void changeRed(double amount) {
		Pixel[] pixelArray =  getPixels();
		Pixel pixel;
		int value, i = 0;
		
		// loop through all the pixels
		while (i < pixelArray.length) {
			// get the current pixel
			pixel = pixelArray[i];
				
			// change the green value
			value = pixel.getRed();
			pixel.setRed((int) (value * amount));
			
			// increment the index
			i++;
		}
	}
	
	/**
	 * Method to reduce green in picture by 30%
	 */
	public void decreaseGreen() {
		Pixel[] pixelArray =  getPixels();
		Pixel pixel;
		int value, i = 0;
		
		// loop through all the pixels
		while (i < pixelArray.length) {
			// get the current pixel
			pixel = pixelArray[i];
				
			// change the green value
			value = pixel.getGreen();
			pixel.setGreen((int) (value * 0.7));
			
			// increment the index
			i++;
		}
	}
	
	/**
	 * Method to reduce blue in picture by 30%
	 */
	public void decreaseBlue() {
		Pixel[] pixelArray =  getPixels();
		Pixel pixel;
		int value, i = 0;
		
		// loop through all the pixels
		while (i < pixelArray.length) {
			// get the current pixel
			pixel = pixelArray[i];
				
			// change the blue value
			value = pixel.getBlue();
			pixel.setBlue((int) (value * 0.7));
			
			// increment the index
			i++;
		}
	}
	
	public void makeSunset2() {
		decreaseBlue();
		decreaseGreen();
	}
	
	/**
	 * Method to change the color of each pixel in the picture object
	 * by passed in amounts
	 * @param redAmount the amount to change the red value
	 * @param greenAmount the amount to change the green value
	 * @param blueAmount the amount to change the blue value
	 */
	public void changeColors(double redAmount,
							double greenAmount,
							double blueAmount) {
		
		Pixel[] pixelArray =  getPixels();
		Pixel pixel;
		int value, i = 0;
		
		// loop through all the pixels
		while (i < pixelArray.length) {
			// get the current pixel
			pixel = pixelArray[i];
				
			// change the red value
			value = pixel.getRed();
			pixel.setRed((int) (value * redAmount));
			
			// change the blue value
			value = pixel.getGreen();
			pixel.setGreen((int) (value * greenAmount));
			
			// change the blue value
			value = pixel.getBlue();
			pixel.setBlue((int) (value * blueAmount));
			
			// increment the index
			i++;
		}
	}
	
  
} // this } is the end of class Picture, put all new methods before this
















 