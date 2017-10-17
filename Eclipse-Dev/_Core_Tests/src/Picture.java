import java.awt.*;
import java.awt.Shape;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D.Double;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

import com.sun.javafx.geom.Ellipse2D;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

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
   * Method to overlap one picture with another
   * horizontally on top of the current picture. First
   * the part of the first picture before the overlap will be displayed, 
   * next to that will be the overlapping region
   * up to the end of the first picture, after that
   * is the remainder of the second picture
   * @param p1 the first picture to display
   * @param p2 the second picture to display
   * @param startOverlap the x position where the overlap begins
   */
  public void overlapPictures(Picture p1, Picture p2, int startOverlap) {
	  int amountOverlap = p1.getWidth() - startOverlap;
	  
	  // get the Graphics2D object
	  Graphics g = getGraphics();
	  Graphics2D g2d = (Graphics2D) g;
	  
	  // draw p1 up to overlap point
	  g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0f));
	  
	  g2d.drawImage(p1.getImage(), 0, 0, startOverlap, p1.getHeight(),
			  0, 0, startOverlap, p1.getHeight(),
			  null);
	  
	  // draw p1 in the overlap area (replace background)
	  g2d.drawImage(p1.getImage(), startOverlap,  0,  p1.getWidth(), p1.getHeight(),
			  startOverlap, 0, p1.getWidth(), p1.getHeight(),
			  null);
	  
	  // set the composite to blend old and new pixels - 50%
	  g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5f));
	  
	  g2d.drawImage(p2.getImage(), startOverlap, 0, p2.getWidth() + startOverlap,
			  p2.getHeight(), amountOverlap, 0, p2.getWidth(),
			  p2.getHeight(), null);
	  
	  // draw p2 after the overlap
	  g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0f));
	  
	  g2d.drawImage(p2.getImage(), p1.getWidth(), 0, p2.getWidth() + startOverlap,
			  p2.getHeight(), amountOverlap, 0, p2.getWidth(),
			  p2.getHeight(), null);
  }
  
  /**
   * Method to add a gradient painted sun to the current picture
   * @param x the x location for the upper left corner of the rectangle enclosing the sun
   * @param y the y location for the upper left corner of the rectangle enclosing the sun
   * @param witdh the width of the enclosing rectangle
   * @param height the height of the enclosing rectangle 
   */
  public void drawSun(int x, int y, int width, int height) {
	  // get the graphics2D object for the picture
	  Graphics g = getGraphics();
	  Graphics2D g2d = (Graphics2D) g;
	  
	  // create the gradient for painting from yellow to red with
	  // yellow at the top of the sun and red at the bottom
	  float xMid = (float) (width / 0.5 + x);
	  GradientPaint gPaint = new GradientPaint(xMid, y, Color.YELLOW, xMid, y + height, Color.RED);
	  
	  // set the gradient and draw the ellipse
	  g2d.setPaint(gPaint);
	  //g2d.fill(new Ellipse2D.Double(x, y, width, height));
  
  }
  
  
  /**
   * Method to create a new picture by shearing the current picture
   * by the x and y factors
   * @param xFactor multiplier to use to shift in x directions based on y index
   * @param yFactor multiplier to use to shift in y directions based on x index
   * @return the resulting picture
   */
  public Picture shear(double xFactor, double yFactor) {
	  // set up the shear transform
	  AffineTransform shearTransform = new AffineTransform();
	  shearTransform.shear(xFactor, yFactor);
	  Rectangle2D rect = getTransformEnclosingRect(shearTransform);
  
	  // create a new picture object, big enough to hold the result
	  Picture result = new Picture((int) (Math.ceil(rect.getWidth())),
			  (int) (Math.ceil(getHeight())));
  
	  // get the graphics 2d object from the result
	  Graphics g = result.getGraphics();
	  Graphics2D g2d = (Graphics2D) g;
  
	  // save the current transformation and set-up to center the new image
	  AffineTransform savedTrans = g2d.getTransform();
	  AffineTransform centerTrans = new AffineTransform();
	  centerTrans.translate(0 - rect.getX(), 0 - rect.getY());
	  g2d.setTransform(centerTrans);
	  
	  // draw the current image onto the result image shared
	  g2d.drawImage(getImage(), shearTransform, null);
	  
	  // reset g2d transformation to the saved one
	  g2d.setTransform(savedTrans);
	  
	  return result;
  }
  
  
  
  /**
   * Method to create a new picture by scaling the current
   * picture by the given x and y factors
   * @param xFactor the amount to scale in x
   * @param yFactor the amount to scale in y
   * @return the resulting picture
   */
  public Picture scale(double xFactor, double yFactor) {
	  // set up the scale transform
	  AffineTransform scaleTransform = new AffineTransform();
	  scaleTransform.scale(xFactor, yFactor);
	  
	  //create a new picture that is the right size
	  Picture result = new Picture((int) (getWidth() * xFactor),
			  (int) (getHeight() * yFactor));
			  
	  // get the graphcis 2D object to draw on the result
	  Graphics g = result.getGraphics();
	  Graphics2D g2d = (Graphics2D) g;
	  
	  // draw the current image onto tthe result image scaled
	  g2d.drawImage(getImage(), scaleTransform, null);
	  
	  return result;
  }
  
  /**
   * Method to copy the passed picture into the current picture
   * at the given x and y position in the current picture
   * @param source the picture to copy
   * @param x the x of the upper left corner to copy to
   * @param y the y of the upper left corner to copy to
   */
  public void copy2D(Picture source, int x, int y) {
	  // get the graphics object
	  Graphics g = getGraphics();
	  Graphics2D g2d = (Graphics2D) g;
	  
	  // copy the image
	  g2d.drawImage(source.getImage(), x, y, null);
  }
  
  /**
   * Method to copy the passed picture into the current picture
   * at the given x and y position in the current picture
   * @param source the picture to copy
   * @param x the x of the upper left corner to copy to
   * @param y the y of the upper left corner to copy to
   */
  public void copy(Picture source, int x, int y) {
	  // get the graphics object
	  Graphics g = getGraphics();
	  
	  // copy the image
	  g.drawImage(source.getImage(), x, y, null);
  }
  
  /**
   * Method to add two crossed lines to a picture.
   * One line will go from the top left corner to the
   * bottom right corner. The other will go from the bottom left
   * corner to the top right corner.
   */
  public void drawWideX(Color color, float width) {
	  // get the Graphics2D object
	  Graphics graphics = getGraphics();
	  Graphics2D g2 = (Graphics2D) graphics;
	  
	  // set the color and brush width
	  g2.setPaint(color);
	  g2.setStroke(new BasicStroke(width));
	  
	  // get the max x and y values
	  int maxX = getWidth() - 1;
	  int maxY = getHeight() - 1;
	  
	  // draw the lines
	  g2.draw(new Line2D.Double(0,0,maxX, maxY));
	  g2.draw(new Line2D.Double(0, maxY, maxX, 0));
  }
  
  /**
   * Method to draw a picture with a succession of rectangles
   * on the current picture
   */
  public void drawRectangles() {
	  Graphics g = getGraphics();
	  Color color = null;
	  
	  // loop 25 times 
	  for (int i = 25; i > 0; i--) {
		  g.setColor(Color.BLACK);
		  g.drawRect(i, i, i * 3, i * 4);
		  g.drawRect(100 + i * 4, 100 + i * 3, i * 8, i * 10);
	  }
	  
  }
  
  /**
   * Method to make fun of someone
   * turns skin green
   * turns eyes red
   * turns hair orange
   */
  public void makeFun() {
	  Pixel currentPixel, newPixel;
	  // Make eyes red
	  for (int x = 1888; x < 2800; x++) {
		  for (int y = 2132; y < 2516; y++) {
			  currentPixel = getPixel(x, y);
			  if (currentPixel.getRed() < 40 &&
					  currentPixel.getGreen() < 40 &&
					  currentPixel.getBlue() < 40) {
				  newPixel = getPixel(x, y);
				  newPixel.setColor(Color.RED);
			  }
		  }
	  }
	  
	  // make hair green
	  for (int x = 2180; x < 3108; x++) {
		  for (int y = 1352; y < 2540; y++) {
			  currentPixel = getPixel(x, y);
			  if (currentPixel.getRed() < 60 &&
					  currentPixel.getGreen() < 60 &&
					  currentPixel.getBlue() < 60) {
				  newPixel = getPixel(x, y);
				  newPixel.setColor(Color.GREEN);
			  }
		  }
	  }
	  
	  // make skin orange
	  for (int x = 1968; x < 2800; x++) {
		  for (int y = 1672; y < 2872; y++) {
			  currentPixel = getPixel(x, y);
			  if (currentPixel.getRed() < 200 &&
					  currentPixel.getGreen() < 200 &&
					  currentPixel.getBlue() < 200) {
				  newPixel = getPixel(x, y);
				  newPixel.setColor(Color.ORANGE);
			  }
		  }
	  }
  }
  
  /**
   * Method to copy all pixels but white to another picture
   * @param newPic new picture to copy to
   */
  public void copyAllButWhite(Picture newPicture) {
	  Pixel currentPixel, newPixel;
	  
	  for (int sourceX = 0, targetX = 0; sourceX < getWidth(); sourceX++, targetX++) {
		  for (int sourceY = 0, targetY = 0; sourceY < getHeight(); sourceY++, targetY++) {
			  currentPixel = getPixel(sourceX, sourceY);
			  if (currentPixel.getRed() < 200 && 
					  currentPixel.getGreen() < 200 &&
					  currentPixel.getBlue() < 200) {
				  newPixel = newPicture.getPixel(targetX, targetY);
			  	  newPixel.setColor(currentPixel.getColor());
			  }
		  }
	  }  
  }
  
  /**
   * Method to do chromakey using a blue background
   * @param newBg the new background image to use to replace
   * the blue from the current picture
   */
  public void chromakeyShorter(Picture newFg, int x, int y, int width, int heigh) {
	  Pixel[] pixelArray = getPixels();
	  Pixel currentPixel, newPixel;
	  
	  // loop through the pixels
	  for (int i = 0; i < pixelArray.length; i++) {
		  // get current pixel
		  currentPixel = pixelArray[i];
		  
		  // if the desired pixel is mostly blue and is at the specified x and y position
		  if ((currentPixel.getX() == x && currentPixel.getY() == y) 
				  && currentPixel.getRed() + currentPixel.getGreen() < currentPixel.getBlue()) {
			  
			  // start copying the pixel from the foreground picture
			  for (int xx = x, fgPixX = 0; xx < x + width; xx++, fgPixX++) {
				  for (int yy = y, fgPixY = 0; yy < y + heigh; yy++, fgPixY++) {
					  newPixel = newFg.getPixel(fgPixX, fgPixY);
					  currentPixel.setColor(newPixel.getColor());
				  }
			  }
		  }
	  }
  }
  
  
  
  /**
   * Method to do chromakey using a blue background
   * @param newBg the new background image to use to replace
   * the blue from the current picture
   */
  public void chromakeyShorter(Picture newBg) {
	  Pixel[] pixelArray = getPixels();
	  Pixel currentPixel, newPixel;
	  
	  // loop thorugh the pixels
	  for (int i = 0; i < pixelArray.length; i++) {
		  // get current pixel
		  currentPixel = pixelArray[i];
		  
		  /* if the color at the current pixel is mostly blue
		   * (blue value is greater than green and red combined)
		   * then use the new background
		   */
		  if (currentPixel.getRed() + currentPixel.getGreen() < currentPixel.getBlue()) {
			  newPixel = newBg.getPixel(currentPixel.getX(), currentPixel.getY());
			  currentPixel.setColor(newPixel.getColor());
		  }
	  }
  }
  
  
  /**
   * Method to do chromakey using a blue background
   * @param newBg the new background image to use to replace
   * the blue from the current picture
   */
  public void chromakey(Picture newBg) {
	  Pixel currentPixel, newPixel;
	  
	  // loop through the columns
	  for (int x = 0; x < getWidth(); x++) {
		  // loop through the rows
		  for (int y = 0; y < getHeight(); y++) {
			  // get the current pixel
			  currentPixel = getPixel(x, y);
			  
			  /* if the color at the current pixel is mostly blue
			   * (blue value is greater than red and green combined)
			   * then use new background color
			   */
			  if (currentPixel.getRed() + currentPixel.getGreen() < currentPixel.getBlue()) {
				  newPixel = newBg.getPixel(x, y);
				  currentPixel.setColor(newPixel.getColor());
			  }
			   
		  }
	  }
  }
  
  /**
   * Method to replace the background in the current picture
   * with the background from another picture
   * @param oldBackground a picture with the old background to replace
   * @param newBackground a picture with the new background to use
   * @param threshold if the distance between the current pixel color
   * and the background pixel color is less than this amount
   * use the new background pixel color
   */
  public void swapBackground(Picture oldBackground, Picture newBackground, double treshold) {
	  Pixel currentPixel, oldPixel, newPixel;
	  
	  // loop through the columns
	  for (int x = 0; x < getWidth(); x++) {
		  // loop through the rows
		  for (int y = 0; y < getHeight(); y++) {
			  // get the current pixel and the old background
			  currentPixel = getPixel(x, y);
			  oldPixel = oldBackground.getPixel(x, y);
			  
			  /* if the distance between the current pixel color
			   * and the old background pixel color is less than 
			   * the threshold then swap in the new background pixel
			   */
			  if (currentPixel.colorDistance(oldPixel.getColor()) < treshold) {
				  newPixel = newBackground.getPixel(x, y);
				  currentPixel.setColor(newPixel.getColor());
			  }
		  }
	  }
  }
  
  /**
   * Method to blur the pixels
   * @param numPixels the number of pixels t average in all diections so if the numPixels is 2
   * then we will average all pixels in the rectangle defined by 2 before the
   * current pixel to 2 after the current pixel
   */
  public void blur(int numPixels) {
	  Pixel pixel, samplePixel;
	  int redValue = 0, greenValue = 0, blueValue = 0;
	  int count = 0;
	  
	  // loop through the pixels
	  for (int x = 0; x < getWidth(); x++) {
		for (int y = 0; y < getHeight(); y++) {
			// get the current pixel
			pixel = this.getPixel(x, y);
			
			// reset the count and red, green, blue values
			count = 0;
			redValue = greenValue = blueValue = 0;
			
			// loop through pixel numPixel before x to numPixels after x
			for (int xSample = x - numPixels; xSample <= x + numPixels; xSample++) {
				for (int ySample = y - numPixels; ySample <= y + numPixels; ySample++) {
					// check that we are in the range of acceptable pixels
					if (xSample >= 0 && xSample < getWidth() &&
							ySample >= 0 && ySample < getHeight()) {
						samplePixel = getPixel(xSample, ySample);
						redValue = redValue + samplePixel.getRed();
						greenValue = greenValue + samplePixel.getGreen();
						blueValue = blueValue + samplePixel.getBlue();
						count++;
					}
				}
			}
			
			// Use average color of surrounding pixels
			Color newColor = new Color(redValue / count,
					greenValue / count, 
					blueValue / count);
			pixel.setColor(newColor);
		}
	}
  }
  
  /**
   * Method to replace the pixel colors in the current picture object that 
   * have a color distance less than the passed
   * @param amount to white or black with the passed replacement color
   * @param replacementColor the new color to use
   */
  public void highlightLightAndDark(double amount, Color replacementColor) {
	  Pixel pixel;
	  
	  // loop through all the pixels in the x direction
	  for (int x = 0; x < getWidth(); x++) {
		  // loop through all the pixels in hte y direction
		  for (int y = 0; y < getHeight(); y++) {
			  // get the current pixel
			  pixel = getPixel(x, y);
			  
			  // if the distance from white and black is less than the
			  // passed amount use the replace color instead
			  if (pixel.colorDistance(Color.WHITE) < amount ||
					  pixel.colorDistance(Color.BLACK) < amount) {
				  pixel.setColor(replacementColor);
			  }
		  }
	  }
  }
  
  /**
   * Method to posterize (reduce the number of colors) in the picture
   * @param numLevels the number of color levels to use
   */
  public void posterize(int numLevels) {
	  Pixel pixel;
	  int redValue = 0, greenValue = 0, blueValue = 0;
	  int increment = (int) (256.0 / numLevels);
	  int bottomValue, topValue, middleValue = 0;
	  
	  // loop through the pixels
	  for (int x = 0; x < getWidth(); x++) {
		  for (int y = 0; y < getHeight(); y++) {
			  // get the current pixel and colors
			  pixel = getPixel(x, y);
			  redValue = pixel.getRed();
			  greenValue = pixel.getGreen();
			  blueValue = pixel.getBlue();
			  
			  // loop through the number of levels
			  for (int i = 0; i < numLevels; i++) {
				  // compute the bottom, top, middle values 
				  bottomValue = i * increment;
				  topValue = (i + 1) * increment;
				  middleValue = (int) ((bottomValue + topValue - 1) / 2.0);
			  
				  // check if current values are in current range and if so set them to the middle value
				  if (bottomValue <= redValue &&
						  redValue < topValue)
					  pixel.setRed(middleValue);
				  if (bottomValue <= greenValue &&
						  greenValue < topValue)
					  pixel.setGreen(middleValue);
				  if (bottomValue <= blueValue &&
						  blueValue < topValue)
					  pixel.setBlue(middleValue);
			  
			  }
		  }
	  }
  }
  
  /**
   * Method to posterize (reduce the number of colors) in the picture
   * THe number of reds, greens and blues will be 4
   */
  public void posterize() {
	  Pixel pixel;
	  double redValue = 0, greenValue = 0, blueValue = 0;
	  
	  // loop through the pixels
	  for (int x = 0; x < getWidth(); x++) {
		  for (int y = 0; y < getHeight(); y++) {
			  // get the current pixel and colors
			  pixel = getPixel(x, y);
			  redValue = pixel.getRed();
			  greenValue = pixel.getGreen();
			  blueValue = pixel.getBlue();
			  
			  // check for red range and change color
			  if (redValue < 64)
				  redValue = 31;
			  else if (redValue < 128)
				  redValue = 95;
			  else if (redValue < 192)
				  redValue = 159;
			  else 
				  redValue = 223;
			  
			  // check for green range
			  if (greenValue < 64)
				  greenValue = 31;
			  else if (greenValue < 128)
				  greenValue = 95;
			  else if (greenValue < 192)
				  greenValue = 159;
			  else 
				  greenValue = 223;
			  
			  // check for blue range
			  if (blueValue < 64)
				  blueValue = 31;
			  else if (blueValue < 128)
				  blueValue = 95;
			  else if (blueValue < 192)
				  blueValue = 159;
			  else 
				  blueValue = 223;
			  
			  // set the colors
			  pixel.setRed((int)redValue);
			  pixel.setGreen((int)greenValue);
			  pixel.setBlue((int)blueValue);
		  }
	  }
  }
  
  /**
   * Method to change the current picture to a sepia tint (modify the middle colors to a light brown 
   * and the lights colors to a light yellow and make the shadows darker)
   */
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
   * Method to remove red-eye from the current picture object
   * in the rectangle defined by startX, startY, endX, endY. 
   * The red will be replaced with the passed newColor
   * @param startX the top left corner x value of a rectangle
   * @param startY the top left corner y value of a rectangle
   * @param endX the bottom right corner x value of a rectangle
   * @param endY the bottom right corner y value of a rectangle
   * @param newColor the new color to use
   */
  public void removeRedEye(int startX, int startY, int endX, int endY, Color newColor) {
	  Pixel pixel;
	  // loop through the pixels in the rectangle defined by the startX, startY and endX, endY
	  for(int x = startX; x < endX; x++) {
		  for (int y = startY; y < endY; y++) {
			  // get the current pixel
			  pixel = getPixel(x, y);
			  
			  // if the color is near red then change it
			  if (pixel.colorDistance(Color.RED) < 167)
				  pixel.setColor(newColor);
		  }
	  }
  }
  
  /**
   * Method to do simple edge detection by comparing the absolute
   * value of the difference between the color intensities 
   * (average of the color values) between a pixel and the pixel below it. If the 
   * absolute value of the difference between the color intensities is 
   * less than a passed amount the top 
   * pixel color will be set to white.
   * Otherwise it is set to black.
   * @param amount if the absolute value of the differences in the 
   * color average is less that this
   * set the color to white, else black
   */
  
  public void edgeDetectionArea(double amount, int startX, int endX, int startY, int endY) {
	  Pixel topPixel, bottomPixel;
	  double topAverage = 0D, bottomAverage = 0D;
	  
	  
	  // loop through y (row) values from 0 to height - 1
	  for (int y = startY; y < endY; y++) {
		  // loop through x(col) values from 0 to width
		  for (int x = startX; x < endX; x++) {
			  // get the top and bottom pixels
			  topPixel = getPixel(x, y);
			  bottomPixel = getPixel(x, y+1);
			  
			  // get the color averages for the two pixels
			  topAverage = topPixel.getAverage();
			  bottomAverage = bottomPixel.getAverage();
			  
			  // check if the absolute value of the difference is less than the amount
			  if (Math.abs(topAverage - bottomAverage) < amount)
				  // value is lower then the amount
				  topPixel.setColor(Color.WHITE);
			  else
				  // else set the color to black
				  topPixel.setColor(Color.BLACK);
		  }
	  }
	  
  }
  
  
  /**
   * Method to do simple edge detection by comparing the absolute
   * value of the difference between the color intensities 
   * (average of the color values) between a pixel and the pixel below it. If the 
   * absolute value of the difference between the color intensities is 
   * less than a passed amount the top 
   * pixel color will be set to white.
   * Otherwise it is set to black.
   * @param amount if the absolute value of the differences in the 
   * color average is less that this
   * set the color to white, else black
   */
  
  public void edgeDetection(double amount) {
	  Pixel topPixel, bottomPixel;
	  double topAverage = 0D, bottomAverage = 0D;
	  int endY = getHeight() - 1;
	  
	  // loop through y (row) values from 0 to height - 1
	  for (int y = 0; y < endY; y++) {
		  // loop through x(col) values from 0 to width
		  for (int x = 0; x < getWidth(); x++) {
			  // get the top and bottom pixels
			  topPixel = getPixel(x, y);
			  bottomPixel = getPixel(x, y+1);
			  
			  // get the color averages for the two pixels
			  topAverage = topPixel.getAverage();
			  bottomAverage = bottomPixel.getAverage();
			  
			  // check if the absolute value of the difference is less than the amount
			  if (Math.abs(topAverage - bottomAverage) < amount)
				  // value is lower then the amount
				  topPixel.setColor(Color.WHITE);
			  else
				  // else set the color to black
				  topPixel.setColor(Color.BLACK);
		  }
	  }
	  
  }
  
  /**
   * Method to flip a picture to the right
   */
  public Picture flipToRight() {
	  int height = getHeight();
	  int width = getWidth();
	  
	  Pixel sourcePixel, targetPixel;
	  
	  Picture target = new Picture(width, height);
	  
	  // loop through all the columns
	  for (int sourceX = 0; sourceX < width; sourceX++) {
		  // loop through all the rows
		  for (int sourceY = 0; sourceY < height; sourceY++) {
			  sourcePixel = getPixel(sourceX, sourceY);
			  targetPixel = target.getPixel(width - 1-  sourceX, sourceY) ;
			  targetPixel.setColor(sourcePixel.getColor());
		   }
	  }
	  
	  return target;
  }
  
  /**
   * Method to flip a picture upside down
   */
  public Picture flipUpSideDown() {
	  int height = getHeight();
	  int width = getWidth();
	  
	  Pixel sourcePixel, targetPixel;
	  
	  Picture target = new Picture(width, height);
	  
	  // loop through all the columns
	  for (int sourceX = 0; sourceX < width; sourceX++) {
		  // loop through all the rows
		  for (int sourceY = 0; sourceY < height; sourceY++) {
			  sourcePixel = getPixel(sourceX, sourceY);
			  targetPixel = target.getPixel(width - 1 - sourceX, height - 1 - sourceY) ;
			  targetPixel.setColor(sourcePixel.getColor());
		   }
	  }
	  
	  return target;
  }
  
 
  /**
   * Method that will mirror Victor's head vertically (ex 5.3)
   */
  public void mirrorVictorsHeadVertically() {
	  int height = getHeight();
	  int mirrorPoint;
  }
  
  /**
   * Method that will mirror Victor's head horizontally (ex 5.4)
   */
  public void mirrorVictorsHeadHorizontally() {
	  Pixel leftPixel, rightPixel;
	  int mirrorPoint = 3088;
	  
	  // loop through rolls
	  for (int y = 1408; y < 2984; y++) {
		  
		  // loop through columns
		  for (int x = 1740; x < mirrorPoint; x++) {
			  leftPixel = getPixel(x, y);
			  rightPixel = getPixel(mirrorPoint + (mirrorPoint - x),  y);
			  rightPixel.setColor(leftPixel.getColor());
			  
		  }
	  }
  }
  
  /**
   * Method to create a new picture that is scaled up by the passed number of times
   * @return the new scaled up picture
   */
  public Picture scaleUp(int numTimes) {
	  Picture targetPicture = 
			  new Picture(getWidth() * numTimes,
		  	 			  getHeight() * numTimes);
	  
	  Pixel sourcePixel, targetPixel;
	  int targetX = 0, targetY = 0;
	  
	  // loop through the source picture columns
	  for (int sourceX = 0; sourceX < getWidth(); sourceX++) {
		  
		  // loop through the source picture rows
		  for (int sourceY = 0; sourceY < getHeight(); sourceY++) {
			  // get the source pixel
			  sourcePixel = getPixel(sourceX, sourceY);
			  
			  // loop copying to the target y
			  for (int indexY = 0; indexY < numTimes; indexY++) {
				  // loop copying to the target x
				  for (int indexX = 0; indexX < numTimes; indexX++) {
					  targetX = sourceX * numTimes + indexX;
					  targetY = sourceY * numTimes + indexY;
					  System.out.println("sourceX: " + sourceX);
					  System.out.println("sourceY: " + sourceY);
					  System.out.println("targetX: " + targetX);
					  System.out.println("targetY: " + targetY);
					  targetPixel = targetPicture.getPixel(targetX, targetY);
					  targetPixel.setColor(sourcePixel.getColor());
				  }
			  }
		  }
	  }
	  return targetPicture;
  }
  
  /**
   * Method to copy a flower but scaled to 2x normal size onto the current picture
   */
  public void copyFlowerLarger() {
	  Picture flowerPicture =
			  new Picture(FileChooser.getMediaPath("rose.jpg"));
	  Pixel sourcePixel, targetPixel;
	  
	  // loop through columns
	  for (double sourceX = 0, targetX = 0; sourceX < flowerPicture.getWidth(); sourceX += 0.5, targetX++) {
		  
		  // loop through rows
		  for (double sourceY = 0, targetY = 0; sourceY < flowerPicture.getHeight(); sourceY += 0.5, targetY++) {
			  sourcePixel = flowerPicture.getPixel((int) sourceX, (int)sourceY);
			  targetPixel = getPixel((int) targetX, (int) targetY);
			  targetPixel.setColor(sourcePixel.getColor());
		  }
	  }
  }
  
  /**
   * Method to copy the picture of Jakita but smaller (half as big)
   * to the current picture
   */
  public void copyJakitaSmaller() {
	  Picture jakitaPicture =
			  new Picture(FileChooser.getMediaPath("jakita.jpg"));
	  Pixel sourcePixel, targetPixel;
	  
	  // loop through columns
	  for (int sourceX = 0, targetX = 0; sourceX < jakitaPicture.getWidth(); sourceX+=2, targetX++) {
		  // loop through rows
		  for (int sourceY = 0, targetY = 0; sourceY < jakitaPicture.getHeight(); sourceY+=2, targetY++) {
			  sourcePixel = jakitaPicture.getPixel(sourceX, sourceY);
			  targetPixel = getPixel(targetX, targetY);
			  targetPixel.setColor(sourcePixel.getColor());
		  }
	  }
  }
  
  /**
   * Method to copy the picture but rotate
   * left 90 degrees and return rotated picture
   */
  public Picture copyPictureRotateLeft(Picture picture) {
	  Picture newPicture = new Picture(picture.getHeight(), picture.getWidth());
	  Pixel sourcePixel, targetPixel;
	  
	  // loop through columns
	  for (int sourceX = 0; sourceX < picture.getWidth(); sourceX++) {
		  // loop through rows
		  for (int sourceY = 0; sourceY < picture.getHeight(); sourceY++) {
			  // set the target pixel color to the source pixel color
			  sourcePixel = picture.getPixel(sourceX, sourceY);
			  targetPixel = newPicture.getPixel(sourceY, picture.getWidth() - 1 - sourceX);
			  targetPixel.setColor(sourcePixel.getColor());
		  }
	  }
	  return newPicture;
  }
  
  /**
   * Method to copy the picture of Katie but rotate
   * her left 90 degrees on the current picutre
   */
  public void copyKatieLeftRotation() {
	  String fileName = FileChooser.getMediaPath("KatieFancy.jpg");
	  Picture sourcePicture = new Picture(fileName);
	  Pixel sourcePixel, targetPixel;
	  
	  // loop through columns
	  for (int sourceX = 0; sourceX < sourcePicture.getWidth(); sourceX++) {
		  // loop through rows
		  for (int sourceY = 0; sourceY < sourcePicture.getHeight(); sourceY++) {
			  // set the target pixel color to the source pixel color
			  sourcePixel = sourcePicture.getPixel(sourceX, sourceY);
			  targetPixel = getPixel(sourceY, sourcePicture.getWidth() - 1 - sourceX);
			  targetPixel.setColor(sourcePixel.getColor());
		  }
	  }
  }
  
  /**
   * Method to blend two picture together 
   */
  public void blendPictures() {
	  // create the sister pictures
	  Picture katiePicture = 
			  new Picture(FileChooser.getMediaPath("KatieFancy.jpg"));
	  Picture jennyPicture = 
			  new Picture(FileChooser.getMediaPath("JenParty.jpg"));
	  
	  // declare the source and target pixel variables
	  Pixel katiePixel, jennyPixel, targetPixel;
	  
	  /* declare the target x and source x since we will need
	   * the values after the for loop
	   */
	  int sourceX = 0, targetX = 0;
  
	  // copy the first 150 pixels of katie to the canvas
	  for (; sourceX < 150; sourceX++, targetX++) {
		  for (int sourceY = 0, targetY = 0; sourceY < katiePicture.getHeight(); sourceY++, targetY++) {
			  katiePixel = katiePicture.getPixel(sourceX, sourceY);
			  jennyPixel = getPixel(targetX, targetY);
			  jennyPixel.setColor(katiePixel.getColor());
		  }
	  }
	  
	  /* copy 50% of katie and 50% of jenny till the end of katie's width */
	  for (; sourceX < katiePicture.getWidth(); sourceX++, targetX++) {
		  for (int sourceY = 0, targetY = 0; sourceY < katiePicture.getHeight(); sourceY++, targetY++) {
			  katiePixel = katiePicture.getPixel(sourceX, sourceY);
			  jennyPixel = jennyPicture.getPixel(sourceX - 150, sourceY);
			  targetPixel = getPixel(targetX, targetY);
			  targetPixel.setColor(new Color( (int) (katiePixel.getRed() * .5 +
					  								jennyPixel.getRed() * .5) ,
					  						  (int) (katiePixel.getGreen() * .5 +
					  						  		jennyPixel.getGreen() * .5),
					  						  (int) (katiePixel.getBlue() * .5 +
					  								 jennyPixel.getBlue() * .5)));
		  }
	  }
	  
	  // copy the rest of jenny
	  sourceX = sourceX - 150;
	  for (; sourceX < jennyPicture.getWidth(); sourceX++, targetX++) {
		  for (int sourceY = 0, targetY = 0; sourceY < jennyPicture.getHeight(); sourceY++, targetY++) {
			  jennyPixel = jennyPicture.getPixel(sourceX, sourceY);
			  targetPixel = getPixel(targetX, targetY);
			  targetPixel.setColor(jennyPixel.getColor());
		  }
	  }
	  
  }
   
  
  /**
   * Method that will copy all of the passed source picture into
   * the current picture object starting with the left corner
   * given by xStart, yStart
   * @param sourcePath the picture object to copy
   * @param xStart the x position to start the copy into on the target
   * @param yStart the y position to start the copy into on the target
   */
  public void copyPictureTo(Picture sourcePicture, int xStart, int yStart) {
	  Pixel sourcePixel, targetPixel;
	  
	  // loop through the columns
	  for (int sourceX = 0, targetX = xStart; sourceX < sourcePicture.getWidth() - 1; sourceX++, targetX++) {
		  // loop through the rows
		  for (int sourceY = 0, targetY = yStart; sourceY < sourcePicture.getHeight() - 1 ; sourceY++, targetY++) {
			  sourcePixel = sourcePicture.getPixel(sourceX, sourceY);
			  targetPixel = getPixel(targetX, targetY);
			  targetPixel.setColor(sourcePixel.getColor());
		  }
	  }
  }
  
  /**
   * Method that will copy all of the passed source picture into
   * the current picture object starting with the left corner
   * given by xStart. It will put the sourcePicture at 5 pixels from the bottom of this picgure
   * @param sourcePicture the picture object to copy
   * @param xStart the x position to start the copy in the target
   */
  public void copyPictureTo(Picture sourcePicture, int xStart) {
	  Pixel sourcePixel, targetPixel;
	  
	  // loop through the columns
	  for (int sourceX = 0, targetX = xStart; sourceX < sourcePicture.getWidth(); sourceX++, targetX++) {
		  
		  // loop through rows
		  for (int sourceY = 0, targetY = getHeight() - sourcePicture.getHeight() - 5;
				  sourceY < sourcePicture.getHeight(); sourceY++, targetY++) {
			  sourcePixel = sourcePicture.getPixel(sourceX, sourceY);
			  targetPixel = getPixel(targetX, targetY);
			  targetPixel.setColor(sourcePixel.getColor());
		  }
	  }
  }
  
  /**
   * Method to copy two flowers in a pattern to the bottom
   * (5 pixels from bottom) of the current picture
   */
  public void copyFlowersBetter() {
	  // crate the flower picture
	  Picture flower1Picture =
			  new Picture(FileChooser.getMediaPath("flower1.jpg"));
	  Picture flower2Picture =
			  new Picture(FileChooser.getMediaPath("flower2.jpg"));
	  
	  // copy the first flower picture to near the
	  // bottom left corner of the canvas
	  copyPictureTo(flower1Picture, 0);
	  
	  // copy the flower2 picture starting with
	  // x = 100 in the canvas
	  copyPictureTo(flower2Picture, 100);
	  
	  // copy the flower1 negated to x = 200
	  flower1Picture.negate();
	  copyPictureTo(flower1Picture, 200);
	  
	  // clear the blue in flower 2 picture and
	  // add at x = 300 in the canvas
	  flower2Picture.clearBlue();
	  copyPictureTo(flower2Picture, 300);
	  
	  // copy the negated flower 1 to x = 400
	  copyPictureTo(flower1Picture, 400);
  }
  
  /**
   * Method to copy flower pictures to create a collage.
   * All the flower pictures will be lined up near the bottom
   * of the current picture ( 5 pixels from the bottom)
   */
  public void copyFlower() {
	  // create the flower pictures
	  Picture flower1Picture = 
			  new Picture(FileChooser.getMediaPath("flower1.jpg"));
	  Picture flower2Picture =
			  new Picture(FileChooser.getMediaPath("flower2.jpg"));
	  
	  // declare the source and target pixel variables
	  Pixel sourcePixel, targetPixel;
	  
	  // save the heights of the two pictures
	  int flower1Height = flower1Picture.getHeight();
	  int flower2Height = flower2Picture.getHeight();
	  
	  /* copy the first flower picture to 5 pixels from the bottom
	   * left corner of the current picture
	   */
	  for (int sourceX = 0, targetX = 0; sourceX < flower1Picture.getWidth(); sourceX++, targetX++) {
		  for (int sourceY = 0, targetY = getHeight() - flower1Height - 5;
				  sourceY < flower1Picture.getHeight(); sourceY++, targetY++) {
			  sourcePixel = flower1Picture.getPixel(sourceX, sourceY);
			  targetPixel = getPixel(targetX, targetY);
			  targetPixel.setColor(sourcePixel.getColor());
		  }
	  }
	  
	  // copy the flower2 picture starting with x = 100
	  for (int sourceX = 0, targetX = 100; sourceX < flower2Picture.getWidth(); sourceX++, targetX++) {
		  for (int sourceY = 0, targetY = getHeight() - flower2Height - 5;
				  sourceY < flower2Picture.getHeight(); sourceY++, targetY++) {
			  sourcePixel = flower2Picture.getPixel(sourceX, sourceY);
			  targetPixel = getPixel(targetX, targetY);
			  targetPixel.setColor(sourcePixel.getColor());
		  }
	  }
	  
	  // copy the flower1 negated to x = 200
	  flower1Picture.negate();
	  for (int sourceX = 0, targetX = 200; sourceX < flower1Picture.getWidth(); sourceX++, targetX++) {
		  for (int sourceY = 0, targetY = getHeight() - flower1Height - 5; sourceY < flower1Height; sourceY++, targetY++) {
			  sourcePixel = flower1Picture.getPixel(sourceX, sourceY);
			  targetPixel = getPixel(targetX, targetY);
			  targetPixel.setColor(sourcePixel.getColor());
		  }
	  }
	  
	  // clear the blue in flower 2 picture and add at x = 300
	  flower2Picture.clearBlue();
	  for (int sourceX = 0, targetX = 300; sourceX < flower2Picture.getWidth(); sourceX++, targetX++) {
		  for (int sourceY = 0, targetY = getHeight() - flower2Height - 5; sourceY < flower2Height; sourceY++, targetY++) {
			  sourcePixel = flower2Picture.getPixel(sourceX, sourceY);
			  targetPixel = getPixel(targetX, targetY);
			  targetPixel.setColor(sourcePixel.getColor());
		  }
	  }
	  
	  // copy the negated flower 1 to x = 400
	  for (int sourceX = 0, targetX = 400; sourceX < flower1Picture.getWidth(); sourceX++, targetX++) {
		  for (int sourceY = 0, targetY = getHeight() - flower1Height - 5; sourceY < flower1Height; sourceY++, targetY++) {
			  sourcePixel = flower1Picture.getPixel(sourceX, sourceY);
			  targetPixel = getPixel(targetX, targetY);
			  targetPixel.setColor(sourcePixel.getColor());
		  }
	  }
  }
  
  /**
   * Method to copy Katie's face to the current picture
   */
  public void copyKatiesFace() {
	  String sourceFile = FileChooser.getMediaPath("KatieFancy.jpg");
	  Picture sourcePicture = new Picture(sourceFile);
	  Pixel sourcePixel, targetPixel;
	  
	  // loop thought the columns
	  for (int sourceX = 70, targetX = 100; sourceX < 135; sourceX++, targetX++) {
		  // loop through rows
		  for (int sourceY = 3, targetY = 100; sourceY < 80; sourceY++, targetY++) {
			  // set the target pixel color the source pixel color
			  sourcePixel = sourcePicture.getPixel(sourceX, sourceY);
			  targetPixel = getPixel(targetX, targetY);
			  targetPixel.setColor(sourcePixel.getColor());
		  }
	  }
  }
  
  /**
   * Method to copy the picutre of Katie to (100, 100) in the current picutre
   */
  public void copyKatieMidway() {
	  String sourceFile = FileChooser.getMediaPath("KatieFancy.jpg");
	  Picture sourcePicture = new Picture(sourceFile);
	  Pixel sourcePixel, targetPixel;
	  
	  // loop thought the columns
	  for (int sourceX = 0, targetX = 100; sourceX < sourcePicture.getWidth(); sourceX++, targetX++) {
		  // loop through rows
		  for (int sourceY = 0, targetY = 100; sourceY < sourcePicture.getHeight(); sourceY++, targetY++) {
			  // set the target pixel color the source pixel color
			  sourcePixel = sourcePicture.getPixel(sourceX, sourceY);
			  targetPixel = getPixel(targetX, targetY);
			  targetPixel.setColor(sourcePixel.getColor());
		  }
	  }
  }
  
  /**
   * Method to copy the picture of Katie to the upper
   * left corner of the current picutre
   */
  public void copyKatie() {
	  String sourceFile = FileChooser.getMediaPath("KatieFancy.jpg");
	  Picture sourcePicture = new Picture(sourceFile);
	  Pixel sourcePixel, targetPixel;
	  
	  // loop thought the columns
	  for (int sourceX = 0, targetX = 0; sourceX < sourcePicture.getWidth(); sourceX++, targetX++) {
		  // loop through rows
		  for (int sourceY = 0, targetY = 0; sourceY < sourcePicture.getHeight(); sourceY++, targetY++) {
			  // set the target pixel color the source pixel color
			  sourcePixel = sourcePicture.getPixel(sourceX, sourceY);
			  targetPixel = getPixel(targetX, targetY);
			  targetPixel.setColor(sourcePixel.getColor());
		  }
	  }
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
















 