// Jason Baumer - Assignment 6 - Java II - Professor:  Duane Wesley

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.text.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Random;


public class Stenographer extends SimplePicture 
{	
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Stenographer ()
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
  public Stenographer(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param width the width of the desired picture
   * @param height the height of the desired picture
   */
  public Stenographer(int width, int height)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   */
  public Stenographer(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  // **ASSIGNMENT 6 HOMEWORK METHODS 
  
  	public static void main(String[] args) 
  	
  	{
 		
  		// set this for your file directory path
  		String baseFilePath = "//Users//superlamer//GitHub//Java_Core//Media Computation book source//mediasources-no-movies-7-30-06//intro-prog-java//mediasources//";
  		
  		// name of the text file you want to encode / decode
  		String textFileName = "parasites.txt";
  		
  		// name of picture file to encode into
  		String pictureFileName = "testPicture.png";
  		
  		// name of the file where the encoded picture is stored.  **NOTE** this is also the file that will be decoded
  		String outFileName = "encodedPicture.png";
  		
  		// run the methods, you don't need to edit these
  		encodeTextIntoPicture(baseFilePath, textFileName, pictureFileName, outFileName);
  		decodeHiddenMessage(baseFilePath, outFileName);

  		
	}
  	
  	
  	// this method will encode text from the file into a picture.  It will do so by randomly depositing encoded pixels into the image
  	public static void encodeTextIntoPicture(String baseFilePath, String textFileName, String pictureFileName, String outFileName)
  	{
  		// store array of characters from text file here
  		char[] arrayOfChars = readTextFromFile(baseFilePath + textFileName);
  		Picture inputPicture = new Picture(baseFilePath + pictureFileName);
  		Pixel[] pixelArray = inputPicture.getPixels();
  		Pixel pixelMod = null;
  		int charValue = 0;
  		
  		// loop through the array of characters and deposit them randomly into image
  		for (int letter = 0;  letter < arrayOfChars.length; letter++)
  		{
  			Random ran = new Random();  			
  			int pixel = ran.nextInt(pixelArray.length);
  			pixelMod = pixelArray[pixel];
  			charValue = (int) arrayOfChars[letter];
  			
  			// encode the pixel using the char value in red, the order value in green, and the signature in blue
  			Color newColor = new Color(charValue, letter, 1);
  			pixelMod.setColor(newColor); 		
  		}

  		// output file
  		inputPicture.write(baseFilePath + outFileName); 		
  	}
  	
  	// this method reads the input text file and return an array of characters
  	public static char[] readTextFromFile(String fileName)
  	{
  		char[] charArray = null;
  		
  		try {
  				// just scan the file using scanner and get the characters	
  				String content = new Scanner(new File(fileName)).useDelimiter("\\Z").next();
  				// put chars in array
  				charArray = content.toCharArray();
  				// throw exception if file not found
  			} catch (FileNotFoundException ex) 
  				{
  				System.out.println("File Not Found: " + fileName);
  				}      
  		
  			return charArray;
  	}
     	

  	// the meat and potatoes - decode a hidden message randomly stored in image
   public static void decodeHiddenMessage(String baseFilePath, String outFileName)
   {
	   // get the image to decode
	   Picture decodeImage = new Picture (baseFilePath + outFileName);
	   
	   // initialize all the arrays and the stringbuilder
	   char testChar; 
	   Pixel[] pixelArray = decodeImage.getPixels();
	   int[] decodeArray = new int[pixelArray.length];
	   StringBuilder decodedMessage = new StringBuilder(64);
	   
	   
	   // loop through all the pixels, check for signature pixel, if signature pixel found, input value at green pixel index
	   for (Pixel testPixel : pixelArray)
	   {
		   if (testPixel.getBlue() == 1)
		   {			   
					   decodeArray[testPixel.getGreen()] = testPixel.getRed();	
		   }

	   }
		   
	   // loop through the decoded array and change the red pixel values into char values then build string
	   for (int num : decodeArray)
	   {
		   testChar = (char) num;
		   decodedMessage.append(testChar);
	   }
	   

	   System.out.println(decodedMessage);
		   
	   }

}
  
