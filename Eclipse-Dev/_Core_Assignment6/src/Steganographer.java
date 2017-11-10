import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that encodes text from a file to a picture and decodes 
 * text from a picture to the screen
 * @author Radoslav Radoev
 *
 */
public class Steganographer {
	
	// constant of how many pixels apart to encode
	private final int SKIPPIXEL = 50;

	/**
	 * Method to encode text from file to a picture
	 * @param sourceFileName name of the source text file
	 * @param pictureToEncodeTo photo to use to encode text
	 * @param targetFileName file name of the output picture
	 */
	public void encode(String sourceFileName, Picture pictureToEncodeTo, String targetFileName) {
		// set up some values that will be used later on
		int character = 0;
		List<Integer> info = new ArrayList<Integer>();
		Pixel pixel = null;
		Pixel[] pixelArray = pictureToEncodeTo.getPixels();

		// try the following
		try (BufferedReader reader =
				new BufferedReader(new FileReader(sourceFileName))) {
			
			while ((character = reader.read()) != -1) {
				info.add(character);
			}
			
			
			// before doing anything else check if the picture that is being passed will
			// have enough pixels to hold the characters from the text file 
			// if not just throw an error and exit
			if (info.size() * SKIPPIXEL> pixelArray.length)
				throw new IndexOutOfBoundsException();
		
			// loop through text and encode it into the picture
			// while making sure we don't go over the text size or the picture size
			// if the file is not empty of course
			// we will rewrite every 50th pixel, so it isn't obvious what we are doing
			// we will replace the red color with the character(int value)
			if (info.size() > 0) {
				for (int fileIndex = 0, pictureIndex = 0; fileIndex < info.size() && 
						pictureIndex < pixelArray.length + SKIPPIXEL + 1; fileIndex++, pictureIndex += SKIPPIXEL) {
								
					// cast current char to int
					int currentChar = info.get(fileIndex);
					
					// get the current pixel
					pixel = pixelArray[pictureIndex];
					
				
					// set the red pixel to the new char value
					pixel.setRed(currentChar);
					
					// mark the end of the encoding with 2 black pixels next to each other
					if (fileIndex == info.size() - 1) {
						pixel.setColor(new Color(0,0,0));
						pixel = pixelArray[pictureIndex + 1];
						pixel.setColor(new Color(0,0,0));
					}
				}
			}
			
			// write the new picture
			pictureToEncodeTo.write(targetFileName + ".png");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Picture is not big enough to hold the text. Choose a larger or higher quality picutre. Min pixels: " + info.size() * SKIPPIXEL);
		}
		
	}
	
	/**
	 * Method to decode test from picture
	 * @param PictureToDecode picture that was encoded using Steganographer encode method
	 */
	public void decode(Picture pictureToDecode) {
		// get all the pixels
		Pixel[] pixelArray = pictureToDecode.getPixels();
		
		// loop through all the pixels and convert the red color pixel
		// to char, so it can be displayed
		for (int i = 0; i < pixelArray.length; i += SKIPPIXEL) {
			// if two adjecant pixels that are set to pitch black are found, stop decoding. Reached the end of the encoded string.
			if (pixelArray[i].getColor().equals(new Color(0,0,0)) && pixelArray[i + 1].getColor().equals(new Color(0,0,0)))
				return;
			
			// if no end of encoded string found output to console
			System.out.print((char) (pixelArray[i].getRed()));
		}
	}
	
	public static void main(String[] args) {
		
		Steganographer sten = 
				new Steganographer();
		
		Picture butterfly = 
				new Picture(FileChooser.getMediaPath("beach.jpg"));
		
		sten.encode(FileChooser.getMediaPath("parasites.txt"), butterfly, FileChooser.getMediaPath("encoded-butterfly1"));
		Picture pic = new Picture(FileChooser.getMediaPath("encoded-butterfly1.png"));
		
		pic.explore();
		sten.decode(pic);
		 
	}

}
