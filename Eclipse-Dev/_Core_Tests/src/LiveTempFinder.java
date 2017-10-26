import java.io.*;
import java.net.URL;
import java.nio.Buffer;

/**
 * Class to find temperature in a web page
 * @author superlamer
 */
public class LiveTempFinder {

	/**
	 * Method to find the temperature in the passed file
	 * @param fileName the name of the file to look in
	 * @return string representing the temperature
	 */
	public String getTemp(String fileName) {
		String seq = "&ordm";
		String temp = null;
		String line = null;
		
		// try the following
		try {
			// read from file
			BufferedReader reader =
					new BufferedReader(new FileReader(fileName));
			
			// loop till the end of file or find sequence
			while ((line = reader.readLine()) != null && 
					line.indexOf(seq) < 0) {
			
			}
			
			// if there is a current line
			if (line != null) {
				// find the temperature
				int degreeIndex = line.indexOf(seq);
				int startIndex = line.lastIndexOf('>', degreeIndex);
				temp = line.substring(startIndex + 1, degreeIndex	);
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
	
	/**
	 * Method to get the temperature from a network
	 * @param urlStr the url as a string
	 * @return the temperature as a string
	 */
	public String getTempFromNetwork(String urlStr) {
		String temp = null;
		String line = null;
		String seq = "<span class=\"temperature\"";
		
		try {
			// create a url
			URL url = new URL(urlStr);
			
			// open a buffered reader on the url
			InputStream inStr = url.openStream();
			BufferedReader reader =
					new BufferedReader(new InputStreamReader(inStr));
			
			// loop until the end of file or find sequence
			while ((line = reader.readLine()) != null && 
					line.indexOf(seq) < 0) {
				
			}
			
			// if there is a current line
			if (line != null) {
				// find the temperature
				int seqIndex = line.indexOf(seq);
				int startIndex = line.indexOf('>', seqIndex);
				int degreeIndex = line.indexOf('<', seq.length() + seqIndex);
				temp = line.substring(startIndex + 1, degreeIndex	);
			}

		} catch (FileNotFoundException ex) {
			SimpleOutput.showError("Couldn't connect to " + urlStr);
		} catch (Exception ex) {
			SimpleOutput.showError("Error during read or write");
			ex.printStackTrace();
		}
		
		return temp;
	}
	
	public static void main(String[] args) {
		LiveTempFinder liveTempFinder = new LiveTempFinder();
		String url = "http://www.ajc.com/weather/92124/";
		String temp = liveTempFinder.getTempFromNetwork(url);
		if (temp == null)
			System.out.println("Sorry, no temp was found at " + url);
		else
			System.out.println("The current temp from the network is " + temp);
		
	}

}












