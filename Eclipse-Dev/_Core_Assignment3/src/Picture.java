

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
   * Method to increase the amount of red by specified amount
   * @param amount with which to increase the red
   */
  public void increaseRed(double amount) {
	  Pixel[] pixelArray = getPixels();
	  Pixel pixel;
	  int value, index = 0;
	  
	  // loop through all the pixels
	  while (index < pixelArray.length) {
		  
		  // get the current pixel
		  pixel = pixelArray[index];
		  
		  // get the value or red
		  value = pixel.getRed();
		  
		  // change the value to amount
		  value = (int) (value * amount);
		  
		  // set the new red value to amount
		  pixel.setRed(value);
		  
		  // increment index
		  index++;
	  }
  }
  
  /**
   * Method to increase the amount of green by 30%
   */
  public void increaseGreen() {
	  Pixel[] pixelArray = getPixels();
	  Pixel pixel;
	  int value, index = 0;
	  
	  // loop through all the pixels
	  while (index < pixelArray.length) {
		  
		  // get the current pixel
		  pixel = pixelArray[index];
		  
		  // get the value or red
		  value = pixel.getGreen();
		  
		  // change the value to 1.3 time it was
		  value = (int) (value * 1.3);
		  
		  // set the new red value to 1.3 times it was
		  pixel.setGreen(value);
		  
		  // increment index
		  index++;
	  }
  }
  
  /**
   * Method to increase the amount of green by specific amount
   * @param amount with which to increase the green
   */
  public void increaseGreen(double amount) {
	  Pixel[] pixelArray = getPixels();
	  Pixel pixel;
	  int value, index = 0;
	  
	  // loop through all the pixels
	  while (index < pixelArray.length) {
		  
		  // get the current pixel
		  pixel = pixelArray[index];
		  
		  // get the value or red
		  value = pixel.getGreen();
		  
		  // change the value to the amount time it was
		  value = (int) (value * amount);
		  
		  // set the new red value to amount times 
		  pixel.setGreen(value);
		  
		  // increment index
		  index++;
	  }
  }
  
  /**
   * Method to increase the amount of blue by 30%
   */
  public void increaseBlue() {
	  Pixel[] pixelArray = getPixels();
	  Pixel pixel;
	  int value, index = 0;
	  
	  // loop through all the pixels
	  while (index < pixelArray.length) {
		  
		  // get the current pixel
		  pixel = pixelArray[index];
		  
		  // get the value or red
		  value = pixel.getBlue();
		  
		  // change the value to 1.3 time it was
		  value = (int) (value * 1.3);
		  
		  // set the new red value to 1.3 times it was
		  pixel.setBlue(value);
		  
		  // increment index
		  index++;
	  }
  }
  
  /**
   * Method to increase the amount of blue by specific amount
   * @param amount with which to increase the color
   */
  public void increaseBlue(double amount) {
	  Pixel[] pixelArray = getPixels();
	  Pixel pixel;
	  int value, index = 0;
	  
	  // loop through all the pixels
	  while (index < pixelArray.length) {
		  
		  // get the current pixel
		  pixel = pixelArray[index];
		  
		  // get the value or red
		  value = pixel.getBlue();
		  
		  // change the value to amount
		  value = (int) (value * amount);
		  
		  // set the new red value to amount
		  pixel.setBlue(value);
		  
		  // increment index
		  index++;
	  }
  }

  /**
   * Method that blurrs within specified rectangle
   * @param x upper left point of origin
   * @param y upper left point of origin
   * @param width widht of the blurr rectangle
   * @param height height of the blurr rectangle
   * @param blurrPixels number of pixels to average in all directions, blurr strength
   */
  public void blurr(int x, int y, int width, int height, int blurrPixels) {
	  Pixel pixel, samplePixel;
	  
	  int redValue = 0, greenValue = 0, blueValue = 0;
	  int count = 0;
  
	  // loop through the pixels (cols), starting from x, until the max width is reached
	  for (; x < width; x ++) {
		  // loop through the pixels (rows), starting from y, until the max height is reached
		  for (; y < height; y++) {
			  
			  // get the current pixel
			  pixel = getPixel(x, y);
			  
			  // reset the count and red, green and blue values
			  count = 0;
			  redValue = greenValue = blueValue = 0;
			  
			  // loop through number of pixels before and after x
			  for (int xSample = x - blurrPixels; xSample <= x + blurrPixels; xSample++) {
				  for (int ySample = y - blurrPixels; ySample <= y + blurrPixels; ySample++) {
					  // check that we are in the range of acceptable pixels
					  if (xSample >= 0 && xSample < getWidth() &&
							  ySample >= 0 && ySample < getHeight()) {
						  samplePixel = getPixel(xSample, ySample);
						  redValue +=  samplePixel.getRed();
						  greenValue += samplePixel.getGreen();
						  blueValue += samplePixel.getBlue();
						  count++;
					  }
				  }
			  }
			  
			  // user average color of surrounding pixels
			  Color newColor = new Color(redValue / count,
					  greenValue / count,
					  blueValue / count);
			  pixel.setColor(newColor);
		  }
	  }
  }
  
} // this } is the end of class Picture, put all new methods before this
 

















