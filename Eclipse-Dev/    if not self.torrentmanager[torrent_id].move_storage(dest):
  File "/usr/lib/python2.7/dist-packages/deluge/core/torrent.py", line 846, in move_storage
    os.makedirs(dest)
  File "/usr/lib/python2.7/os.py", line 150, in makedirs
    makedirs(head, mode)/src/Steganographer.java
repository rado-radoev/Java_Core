import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class that encodes text from a file to a picture and decodes 
 * text from a picture to the screen
 * @author Radoslav Radoev
 *
 */
public class Steganographer {

	/**
	 * Method to encode text from file to a picture
	 * @param sourceFileName name of the source text file
	 * @param pictureToEncodeTo photo to use to encode text
	 * @param targetFileName file name of the output picture
	 */
	@SuppressWarnings("unused")
	public void encode(String sourceFileName, Picture pictureToEncodeTo, String targetFileName) {
		// set up some values that will be used later on
		String line = null;
		String info = null;
		int value = 0;
		Pixel pixel = null;
		Pixel[] pixelArray = pictureToEncodeTo.getPixels();
		
		// try the following
		try ( BufferedReader reader = 
				new BufferedReader(new FileReader(sourceFileName))) {
			
			while ((line = reader.readLine()) != null) {	}
		
			// loop through text and encode it into the picture
			// while making sure we don't go over the text size or the picture size
			// if the file is not empty of course
			// we will rewrite every 5th pixel, so it isn't obvious what we are doing
			// we will add the current char value to the current pixel color
			if (line != null ) {
				for (int i = 0; i < line.length() && 
						i < pixelArray.length; i+= 5) {
					// cast current char to int
					int currentChar = (int) line.charAt(i);
					
					// get the current pixel
					pixel = pixelArray[i];
					
					// add the char value to red color of the current pixel
					// TO DO: can improve it, if the red + char value is > 255
					// then move to the next color etc.
					value = pixel.getRed() + currentChar;
					
					// set pixel to the new value
					pixelArray[i].setRed(value);
				}
			}
			
			pictureToEncodeTo.write(targetFileName + ".bmp");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		Steganographer sten = 
				new Steganographer();
		
		Picture butterfly = 
				new Picture(FileChooser.getMediaPath("butterfly1.jpg"));
		
		sten.encode(FileChooser.getMediaPath("parasites.txt"), 
				butterfly, 
				"encoded-butterfly1");

	}

}
