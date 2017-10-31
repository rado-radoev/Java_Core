import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ReadPoll {

	// input file name, by default it is numbers.txt
	private String inputFile = "numbers.txt";
	
	// output file name, by default it is output.txt
	private String outputFile = "output.txt";
	
	// get input file name
	public String getInputFile() {
		return inputFile;
	}

	// set input file name
	public void setInputFile(String file) {
		this.inputFile = file;
	}

	// get output file name
	public String getOutputFile() {
		return outputFile;
	}
	
	// set output file name
	public void setOutputFile(String file) {
		this.outputFile = file;
	}
	
	// main applicaiton execution
	public static void main(String[] args) {
		
		// create read poll object
		ReadPoll rp = new ReadPoll();
		
		// check if input file exists
		rp.checkFile(rp.getInputFile());
		
		// if output file does not exist, create it
		if (rp.checkFile(rp.getOutputFile())) {
			try {
				File file = new File(rp.getOutputFile());
				file.createNewFile();
			} catch (Exception e) {
				System.out.println("File " + rp.getOutputFile() + " cannot be created.");
			}
		}
		
		// output results to a file
		rp.outputResults();
		
		// display results to screen
		rp.displayResults();
	}


	/**
	 * Method to check if file exists
	 * @param file the file name or full path to check for existance
	 * @return boolean true or false if the file exists or not
	 */
	private boolean checkFile(String file) {
		// convert the string to path variable
		Path path = Paths.get(file);
		
		// if the file does not exist throw an display a console mesage and return false
		if (!Files.exists(path)) {
			System.out.println("File does not exist. Try creating it first. Terminating ...");
			return false;
		}
		
		// file exists
		return true;
	}
	
	/**
	 * Method to write ouptut to a file
	 */
	private void outputResults() {
		
		// create a writer method to write to file
		// object will be automatically closed at the end, no need to close it
		try (BufferedWriter writer = 
				new BufferedWriter(new FileWriter(getOutputFile()))) {

		// get all the results
		int[] answers = readFile();
		
		// loop through the results and add each one to a new line
		for (int i = 1; i < answers.length; i++) {
			writer.write("Answer " + i + " voted: " + answers[i] + " times");
			writer.newLine();
		}
		
		// throw an exception if cannot write to file
		} catch (IOException io) {
			System.out.printf("%s %s%n","Error while writing to output file", getOutputFile());
		}
	}
	
	/**
	 * Method to write output to console
	 */
	public void displayResults() {
		
		// get all the results
		int[] answers = readFile();
		
		// loop through the results and dispaly each one to the console
		for (int i = 1; i < answers.length; i++) {
			System.out.println("Answer " + i + " voted: " + answers[i] + " times");
		}
	}
	
	
	/**
	 * Method to read a text file
	 * @return
	 */
	private int[] readFile() {
		// create a place holder array that will hold the possible 5 answers
		// this array will be used for frequency counter
		int[] frequencey = new int[6];
		
		// read an input file using Scanner
		try (Scanner scanner = new Scanner(Paths.get(getInputFile()))) {
			// continue reading file, while there is still input to read
			while (scanner.hasNext()) {
				int num = scanner.nextInt();
				// increment the frequency counter based on the number read
				frequencey[num]++;
			}
		// throw exceptions if the wrong input is detected or cannot access the file	
		} catch (IllegalStateException ise) {
			System.out.println("Error reading file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// return the frequency counter
		return frequencey;
	}

}
