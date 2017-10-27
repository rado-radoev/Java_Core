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

	/**
	 * Method to encode text from file to a picture
	 * @param sourceFileName name of the source text file
	 * @param pictureToEncodeTo photo to use to encode text
	 * @param targetFileName file name of the output picture
	 */
	@SuppressWarnings("unused")
	public void encode(String sourceFileName, Picture pictureToEncodeTo, String targetFileName) {
		// set up some values that will be used later on
		int character = 0;
		List info = new ArrayList<Integer>();
		int value = 0;
		Pixel pixel = null;
		Pixel[] pixelArray = pictureToEncodeTo.getPixels();

		// try the following
		try {
			BufferedReader reader =
					new BufferedReader(new FileReader(sourceFileName));
			
			while ((character = reader.read()) != -1) {
				info.add(character);
			}
		
			// loop through text and encode it into the picture
			// while making sure we don't go over the text size or the picture size
			// if the file is not empty of course
			// we will rewrite every 5th pixel, so it isn't obvious what we are doing
			// we will add the current char value to the current pixel color
			if (info.size() > 0) {
				for (int fileIndex = 0, pictureIndex = 0; fileIndex < info.size() && 
						pictureIndex < pixelArray.length; fileIndex++, pictureIndex += 50) {
					
					if (pictureIndex >= pixelArray.length) {
						System.out.println("limit reached");
					}
					System.out.println("FileIndex: " + fileIndex);
					System.out.println("Picture Index: " + pictureIndex);
					
					// cast current char to int
					int currentChar = (int) info.get(fileIndex);
					
					// get the current pixel
					pixel = pixelArray[pictureIndex];
					
					// add the char value to red color of the current pixel
					// TO DO: can improve it, if the red + char value is > 255
					// then move to the next color etc.
					//value = pixel.getRed() + currentChar;
					value = currentChar;
					
					// set pixel to the new value
					pixel.setRed(value);
				}
			}
			System.out.println(pixelArray.length);
			pictureToEncodeTo.write(targetFileName + ".png");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to decode test from picture
	 * @param PictureToDecode picture that was encoded using Steganographer encode method
	 */
	public void decode(Picture PictureToDecode) {
		
	}
	
	public static void main(String[] args) {
		//FileChooser.setMediaPath("C:\\GitHub\\Java_Core\\Media Computation book source\\mediasources-no-movies-7-30-06\\intro-prog-java\\mediasources\\");
		
		Steganographer sten = 
				new Steganographer();
		
		Picture butterfly = 
				new Picture(FileChooser.getMediaPath("butterfly1.jpg"));
		
		// sten.encode(FileChooser.getMediaPath("parasites.txt"), butterfly, FileChooser.getMediaPath("encoded-butterfly1"));

		// Picture pic = new Picture(FileChooser.getMediaPath("encoded-butterfly1.png"));
		// pic.explore();
	}

}
