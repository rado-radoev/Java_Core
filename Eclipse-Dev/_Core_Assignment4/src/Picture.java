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
//     String fileName = FileChooser.pickAFile();
//     Picture pictObj = new Picture(fileName);
//     pictObj.explore();
	  
		Picture pic = 
				new Picture("blue-mark.jpg");
		Picture halo = 
				new Picture("halo_clean_background_cropped.jpg");
		Picture newBg1 = new Picture(pic.getWidth(), pic.getHeight());
		
		newBg1.chromakey(halo, pic, 262, 89, halo.getWidth(), halo.getHeight()).explore();
  }
  
  /**
   * Method to display a foreground image on top of a background image at specific location 
   * and with specific width and height
   * @param foreground the foreground image
   * @param background the background image
   * @param startX the top most x position of the foreground image
   * @param startY the top most y position of the foreground image
   * @param width the width of the foreground image
   * @param height the height of the foreground image
   * @return new image containing background and foregroud images
   */
  public Picture chromakey(Picture foreground, Picture background,
		  int startX, int startY, int width, int height) {
	  
	  // temporary picture to be returned
	  Picture target = new Picture(background.getWidth(), background.getHeight());
	  
	  // foreground, background and target pixels
	  Pixel fgPixel, bgPixel, tgPixle;
	  
	  // loop through background image columns
	  for (int backgroundX = 0; backgroundX < background.getWidth(); backgroundX++) {
		  
		  // loop through background image rows
		  for (int backgroundY = 0; backgroundY < background.getHeight(); backgroundY++) {
			  
			  // get the pixels at current background and target picture
			  bgPixel = background.getPixel(backgroundX, backgroundY);
			  tgPixle = target.getPixel(backgroundX, backgroundY);
			  
			  // if the current pixel position is in the range of x + width and y + height
			  // copy the foreground pixel, only if it is yellow
			  // otherwise copy the background pixel to target
			  if (((backgroundX > startX && backgroundX < startX + width) && 
					  (backgroundY > startY && backgroundY < startY + height)) &&
					  bgPixel.colorDistance(Color.BLUE) < 200) {
				  
				  // get the current foreground pixel
				  fgPixel = foreground.getPixel(backgroundX - startX, backgroundY - startY);
				  
				  // if current foreground pixel is mostly green
				  if (fgPixel.colorDistance(Color.GREEN) < 200) {
					  // set the target pixel to the background pixel
					  tgPixle.setColor(bgPixel.getColor());
				  }
				  else {
					  // if the pixel is not greenish, then it will be displayed
					  tgPixle.setColor(fgPixel.getColor());					  
				  }
			  }
			  else {
				  // if not in the range of foreground pixels, display the background pixel
				  tgPixle.setColor(bgPixel.getColor());
			  }
		  }
	  }
	  // return the temporary image
	  return target;
  }
  
  
} // this } is the end of class Picture, put all new methods before this
 