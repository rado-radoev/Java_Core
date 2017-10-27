import java.io.FileWriter;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Class that writes sound to a file
 * @author superlamer
 */
public class SoundToFile {

	/**
	 * Method to write out the values in the sound file as a text
	 * @param fileName the name of the file to write to
	 */
	public static void writeSamplesAsText(Sound sound, String fileName) {
		int value = 0;
		
		// try the following
		try {
			
			BufferedWriter writer = 
					new BufferedWriter(new FileWriter(fileName));
			
			// loop through the samples
			for (int i = 0; i < sound.getLength();i++) {
				// get the int value 
				value = sound.getSampleValueAt(i);
				
				// write it as a string (text)
				writer.write(String.valueOf(value));
				
				// add new line
				writer.newLine();
			}
			
			// close the writer
			writer.close();
		} catch (Exception e) {
			SimpleOutput.showError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to create a sound from a text file
	 * @param fileName the name of the file to read from
	 * @return the created sound
	 */
	public static Sound createSoundFromText(String fileName) {
		String line = null;
		int value = 0;
		
		// create the sound to read into
		Sound s = 
				new Sound(FileChooser.getMediaPath("sec3silence.wav"));
		
		//try the following
		try {
			// create the buffered read
			BufferedReader reader = 
					new BufferedReader(new FileReader(fileName));
			
			// loop reading the values
			int index = 0;
			while ((line = reader.readLine()) != null && 
					index < s.getLength()) {
				value = Integer.parseInt(line);
				s.setSampleValueAt(index++, value);
			}
			// close the reader
			reader.close();
		} catch (FileNotFoundException ex) {
			SimpleOutput.showError("Couldn't find file " + fileName);
			fileName = FileChooser.pickAFile();
			s = createSoundFromText(fileName);
		} catch (Exception ex) {
			SimpleOutput.showError("Error during read or write");
			ex.printStackTrace();
		}
		
		return s;
	}
	
	
	/**
	 * Method to turn a sound into a picture
	 * @param sound the sound to turn into picture
	 * @return a created picture
	 */
	public static Picture soundToPicture(Sound sound)	 {
		int value = 0;
		Pixel pixel = null;
		
		// Create a picture to write to
		Picture p = 
				new Picture(FileChooser.getMediaPath("640x480.jpg"));
		
		// get the pixel array
		Pixel[] pixelArray = p.getPixels();
		
		// loop through the pixels array
		for (int i = 0; i < pixelArray.length && i < sound.getLength(); i++) {
			// get the pixel 
			pixel = pixelArray[i];
			
			// set the color based on the sample value
			value = sound.getSampleValueAt(i);
			if (value > 1000)
				pixel.setColor(Color.RED);
			else if (value < -1000) 
				pixel.setColor(Color.BLUE);
			else
				pixel.setColor(Color.GREEN);
		}
		
		return p;
	}
	
	
	public static void main(String[] args) {
		//Sound s = new Sound(FileChooser.getMediaPath("her.wav"));
		//SoundToFile.writeSamplesAsText(s, "her.txt");
		
		Sound s = 
				new Sound(SoundToFile.createSoundFromText("her copy.txt"));
//		s.explore();
		
		
		Picture p =
				new Picture(SoundToFile.soundToPicture(s));
		p.explore();
	}

}


















