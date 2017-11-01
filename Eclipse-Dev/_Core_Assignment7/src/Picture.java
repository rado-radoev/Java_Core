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
  
  public void sepiaTint() {
	  Pixel pixel;
	  double redValue = 0, greenValue = 0, blueValue = 0;
	  
	  // first change the current picture to grayscale
	  grayscale();
	  
	  // loop through the pixels (cols)
	  for (int x = 0; x < getWidth(); x++) {
		  // loop through the rows
		  for (int y = 0; y < getHeight(); y++) {
			  // get the current pixel and color values
			  pixel = getPixel(x, y);
			  
			  redValue = pixel.getRed();
			  greenValue = pixel.getGreen();
			  blueValue = pixel.getBlue();
			  
			  // tint the shadows darker
			  if (redValue < 60) {
				  redValue *= .9;
				  greenValue *= .9;
				  blueValue *= .9;
			  }
			  
			  // tint the midtones a light brown by reducing the blue
			  if (redValue < 190) {
				  blueValue *= .8;
			  }
			  // tint the highlights a light yellow by reducing the blue
			  else {
				  blueValue *= .9;
			  }
			  
			  // set the colors
			  pixel.setRed((int) redValue);
			  pixel.setGreen((int)greenValue);
			  pixel.setBlue((int) blueValue);
		  }
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
  
  public static void main(String[] args) 
  {
     String fileName = FileChooser.pickAFile();
     Picture pictObj = new Picture(fileName);
     pictObj.explore();
  }
  
  
} // this } is the end of class Picture, put all new methods before this
 