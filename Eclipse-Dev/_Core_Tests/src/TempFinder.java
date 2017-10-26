import java.io.*;

/**
 * Class to find the temperature in a web page
 * @author superlamer
 */
public class TempFinder {

	/**
	 * Method to find the temperature in the passed file
	 * @param fileName the name of the file to look in
	 */
	public String getTemp(String fileName) {
		String seq = "<b>$deg";
		String temp = null;
		String line = null;
		
		// try the following
		try {
			
			// red from the file
			BufferedReader reader = 
					new BufferedReader(new FileReader(fileName));
			
			// loop until the end of file or found sequence
			while ((line = reader.readLine()) != null &&
					line.indexOf(seq) < 0)   {
				line += reader.readLine();
			}
			// close reader, not needed anymore
			//reader.close();
			
			// if there is a current lie
			if (line != null) {
				// find the temperature
				int degreeIndex = line.indexOf(seq);
				int startIndex = line.lastIndexOf('>', degreeIndex);
				temp = line.substring(startIndex + 1, degreeIndex);
			}
		} catch (FileNotFoundException ex) {
			SimpleOutput.showError("Couldn't find file " + fileName);
			fileName = FileChooser.pickAFile();
			getTemp(fileName);
		} catch (Exception ex) {
			SimpleOutput.showError("Error during read or write");
			ex.printStackTrace();
		}
		
		return temp;
	}
	
	
	public static void main(String[] args) {
		TempFinder tempFinder = new TempFinder();
		String fileName = FileChooser.getMediaPath("ajc-weather.html");
		String temp = tempFinder.getTemp(fileName);
		if (temp == null)
			System.out.println("Sorry, no temp was found in " + fileName);
		else
			System.out.println("The current temperature is: " + temp);
	}

}









