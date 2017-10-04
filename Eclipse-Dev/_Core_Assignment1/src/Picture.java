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
















 