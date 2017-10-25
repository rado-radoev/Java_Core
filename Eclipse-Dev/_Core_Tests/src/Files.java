import java.io.*;

public class Files {

	// Method to read a file and print out contents
	public void readAndPirntFile(String fileName) {
		String line = null;
		
		// try to do the following
		try {
			// create a buffered reader
			BufferedReader reader = 
					new BufferedReader(new FileReader(fileName));
			// loop while there is more data
			while ((line = reader.readLine()) != null)  {
				// print the current line
				System.out.println(line);
			}
			
			// close the reader
			reader.close();
			
		} catch (FileNotFoundException ex) {
			SimpleOutput.showError("Couldn't find " + fileName + " please pick it.");
			fileName = FileChooser.pickAFile();
			readAndPirntFile(fileName);
		} catch (IOException ex) {
			SimpleOutput.showError("Error reading file " + fileName);
			ex.printStackTrace();
		}
	}
	
	/** 
	 * Method to write a dummy file
	 */
	public void writeSillyFile() {
		
		try {
			// try to open the buffered writer
			BufferedWriter writer = 
					new BufferedWriter(new FileWriter("silly.txt"));
			
			// write out the file
			writer.write("Here is some text.");
			writer.newLine();
			writer.write("Here is some more.");
			writer.newLine();
			writer.write("And now we are done.");
			writer.newLine();
			writer.newLine();
			writer.write("THE END");
			writer.close();
		} catch (Exception ex) {
			System.out.println("Error during write to silly.txt");
		}
		
	} 
	
	public static void main(String[] args) {
		Files writer = new Files();
		writer.writeSillyFile();
		writer.readAndPirntFile("silly.txt");
		
//		Files reader = new Files();
//		reader.readAndPirntFile("test.txt");
	}
}
